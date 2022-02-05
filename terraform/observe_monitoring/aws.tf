module "observe_kinesis_firehose" {
  source = "github.com/observeinc/terraform-aws-kinesis-firehose//eks?ref=main"

  observe_customer = var.observe_customer
  observe_token    = var.observe_token
  observe_domain   = var.observe_domain

  eks_cluster_name = "my-example-cluster"
  pod_execution_role_arns = [
    "arn:aws:iam::123456789012:role/my-example-cluster-FargatePodExecutionRole-K3ZLJXIIXQGE",
    "arn:aws:iam::123456789012:role/my-example-cluster-FargatePodExecutionRole-AE134MASIOL4",
  ]
}