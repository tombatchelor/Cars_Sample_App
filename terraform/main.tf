module "aws" {
  source = "./aws"
}
/*
module "observe_kinesis_firehose" {
  source = "github.com/observeinc/terraform-aws-kinesis-firehose//eks?ref=main"

  observe_customer = 
  observe_token    = 
  observe_domain   = 

  eks_cluster_name = "observe-demo-cluster"
  pod_execution_role_arns = [
    "arn:aws:iam::739672403694:role/observe-demo-cluster-fargate20220204224815189000000010"
  ]
}
*/
