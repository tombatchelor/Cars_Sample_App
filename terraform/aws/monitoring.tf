module "observe_kinesis_firehose" {
  source = "github.com/observeinc/terraform-aws-kinesis-firehose//eks?ref=main"

  observe_customer = var.observe_customer
  observe_token    = var.observe_token
  observe_domain   = var.observe_domain

  depends_on = [
    module.eks
  ]

  eks_cluster_arn         = module.eks.cluster_arn
  pod_execution_role_arns = [for group in module.eks.fargate_profiles : group.fargate_profile_pod_execution_role_arn]
}
