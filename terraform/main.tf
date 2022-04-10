module "aws" {
  source = "./aws"

  region           = "us-east-1"
  observe_customer = "126338107931"
  observe_token    = "ds1KmgHrvuefEhQmE4DY:LhedMK0EGV9XmAoPzig8ukjxmmKTL3XO"
  observe_domain   = "observe-staging.com"
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


module "observe_collection" {
  source = "github.com/observeinc/terraform-aws-collection"

  name             = "obsrve-demo-collection"
  observe_customer = "126338107931"
  observe_token    = "ds1KmgHrvuefEhQmE4DY:LhedMK0EGV9XmAoPzig8ukjxmmKTL3XO"
  observe_domain   = "observe-staging.com"
}
