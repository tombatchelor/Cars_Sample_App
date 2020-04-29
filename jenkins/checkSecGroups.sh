#!/bin/bash

SEC_GROUP_ID=sg-0c793b435ce34cdd0
NAMESPACE=cars-app

# Get IPs for all nodes that have a cars app pod
host_ips=()
while IFS= read -r line; do
  host_ips+=( "$line" )
done < <( kubectl get pods -o json -n $NAMESPACE -l app=cars_app | jq -r '.items[] | .status | .hostIP' )

echo Host IPs
echo "${host_ips[@]}"

# Get Pod IPs
pod_ips=()
while IFS= read -r line; do
  pod_ips+=( "$line" )
done < <( kubectl get pods -o json -n $NAMESPACE -l app=cars_app | jq -r '.items[] | .status | .podIP' )

echo Pod IPs
echo "${pod_ips[@]}"

# Add all host IPs to the security group
for host_ip in ${host_ips[@]}; do
  echo Adding Host IP $host_ip to security group
  aws ec2 authorize-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr $host_ip/32
done

# Get IPs for Securty Group
sec_ips=()
while IFS= read -r line; do
  sec_ips+=( "$(grep -oE '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' <<< "$line")" )
done < <( aws ec2 describe-security-groups --group-ids $SEC_GROUP_ID | jq -r '.SecurityGroups[] | .IpPermissions[] | .IpRanges[] | .CidrIp' )

echo Security Group IPs
echo "${sec_ips[@]}"

# Revoke all lines in the security group
for sec_ip in ${sec_ips[@]}; do
  echo Removing $sec_ip from security group
  aws ec2 revoke-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr $sec_ip/32
done

# Loop over Pods, make sure 2 are in the security group
for i in ${!pod_ips[@]}; do
  pod_ip=${pod_ips[$i]}
  if [ $i != 2 ]; then
    echo Adding Pod IP $pod_ip to security group
    aws ec2 authorize-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr $pod_ip/32
    echo Adding Host IP ${host_ips[$i]} security group
    aws ec2 authorize-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr ${host_ips[$i]}/32
  fi
done
