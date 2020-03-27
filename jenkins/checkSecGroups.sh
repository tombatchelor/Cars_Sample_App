#!/bin/bash

SEC_GROUP_ID=sg-0c793b435ce34cdd0
NAMESPACE=cars-app

# Check pods are balanced across nodes and re-balance if not
host_list=()
while IFS= read -r line; do
  host_list+=( "$line" )
done < <( kubectl get pods -o json -n $NAMESPACE -l app=cars_app | jq -r '.items[] | .spec | .nodeName' | sort )

echo Host List is
echo "${host_list[@]}"

last_host=NONE
for host in ${host_list[@]}; do
  if [ "$host" == "$last_host" ]; then
    podname=`kubectl get pods -o json -n $NAMESPACE -l app=cars_app --field-selector spec.nodeName=$host | jq -r '.items[0] | .metadata | .name' | sort`
    echo Deleting pod $podname from node $host
    kubectl delete pod $podname -n $NAMESPACE
  fi
  last_host=$host
done

# Get IPs for Securty Group, Pods and Hosts
sec_ips=()
while IFS= read -r line; do
  sec_ips+=( "$(grep -oE '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' <<< "$line")" )
done < <( aws ec2 describe-security-groups --group-ids $SEC_GROUP_ID | jq -r '.SecurityGroups[] | .IpPermissions[] | .IpRanges[] | .CidrIp' )

echo Security Group IPs
echo "${sec_ips[@]}"

pod_ips=()
while IFS= read -r line; do
  pod_ips+=( "$line" )
done < <( kubectl get pods -o json -n $NAMESPACE -l app=cars_app | jq -r '.items[] | .status | .podIP' )

echo Pod IPs
echo "${pod_ips[@]}"

host_ips=()
while IFS= read -r line; do
  host_ips+=( "$line" )
done < <( kubectl get pods -o json -n $NAMESPACE -l app=cars_app | jq -r '.items[] | .status | .hostIP' )

echo Host IPs
echo "${host_ips[@]}"

# Revoke all hosts that are in the security group

for host_ip in ${host_ips[@]}; do
  for sec_ip in ${sec_ips[@]}; do
    if [ "$host_ip" == "$sec_ip" ]; then
      echo Removing host: $host_ip from security Group
      aws ec2 revoke-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr $sec_ip/32
    fi
  done
done

# Get IPs for Securty Group, Pods and Hosts
sec_ips=()
while IFS= read -r line; do
  sec_ips+=( "$(grep -oE '[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}' <<< "$line")" )
done < <( aws ec2 describe-security-groups --group-ids $SEC_GROUP_ID | jq -r '.SecurityGroups[] | .IpPermissions[] | .IpRanges[] | .CidrIp' )

echo Security Group IPs, Hosts removed
echo "${sec_ips[@]}"

# Loop over Security Group IPs and if they don't match a pod, remove
for sec_ip in ${sec_ips[@]}; do
  match=FALSE
  for pod_ip in ${pod_ips[@]}; do
    if [ "$pod_ip" = "$sec_ip" ]; then
      match=TRUE
    fi
  done
  if [ "$match" == "FALSE" ]; then
    echo Removing Pod $sec_ip from security group
    aws ec2 revoke-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr $sec_ip/32
  fi
done

# Loop over Pods, make sure 2 are in the security group
for i in ${!pod_ips[@]}; do
  pod_ip=${pod_ips[$i]}
  match=FALSE
  for sec_ip in ${sec_ips[@]}; do
    if [ "$pod_ip" = "$sec_ip" ]; then
      match=TRUE
    fi
  done
  if [ $i != 2 ]; then
    if [ "$match" == "FALSE" ]; then
      echo Adding Pod IP $pod_ip to security group
      aws ec2 authorize-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr $pod_ip/32
    fi
    echo Adding Host IP ${host_ips[$i]} security group
    aws ec2 authorize-security-group-ingress --group-id $SEC_GROUP_ID --protocol tcp --port 3306 --cidr ${host_ips[$i]}/32
  fi
done

