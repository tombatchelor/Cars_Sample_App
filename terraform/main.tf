module "aws" {
  source = "./aws"
  region = "us-west-1"
}

module "observe_kinesis_firehose" {
  source = "github.com/observeinc/terraform-aws-kinesis-firehose//eks?ref=main"

  observe_customer = ""
  observe_token    = ""
  observe_domain   = "observe-staging.com"

  depends_on = [
    module.aws
  ]

  eks_cluster_arn         = module.aws.cluster_arn
  pod_execution_role_arns = [for group in module.aws.fargate_profiles : group.fargate_profile_pod_execution_role_arn]
}
