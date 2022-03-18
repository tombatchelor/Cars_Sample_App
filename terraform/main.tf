module "aws" {
  source = "./aws"

  region           = "us-east-1"
  observe_customer = "126338107931"
  observe_token    = "OedWF9hA7Qk4EEoZH1CrQta9dfi_DgbX"
  observe_domain   = "observe-staging.com"
}

module "kubernetes" {
  depends_on = [
    module.aws
  ]

  source = "./kubernetes"
}

provider "kubernetes" {
  host                   = module.aws.cluster_endpoint
  cluster_ca_certificate = module.aws.cluster_ca_certificates
  exec {
    api_version = "client.authentication.k8s.io/v1alpha1"
    command     = "aws"
    args = [
      "eks",
      "get-token",
      "--cluster-name",
      module.aws.cluster_name
    ]
  }
}

/*
module "observe_collection" {
  source = "github.com/observeinc/terraform-aws-collection"

  name             = "obsrve-demo-collection"
  observe_customer = "126338107931"
  observe_token    = "OedWF9hA7Qk4EEoZH1CrQta9dfi_DgbX"
  observe_domain   = "observe-staging.com"
}
*/