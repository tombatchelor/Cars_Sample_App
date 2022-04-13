data "aws_availability_zones" "available" {}

locals {
  cluster_name = "observe-demo-cluster"
}
/*
resource "random_string" "suffix" {
  length  = 8
  special = false
}
*/
module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "3.2.0"

  name                 = "observe-demo-vpc"
  cidr                 = "10.0.0.0/16"
  azs                  = ["us-east-1a", "us-east-1b", "us-east-1d"]
  private_subnets      = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
  public_subnets       = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]
  enable_nat_gateway   = true
  single_nat_gateway   = true
  enable_dns_hostnames = true

  tags = {
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
  }

  public_subnet_tags = {
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
    "kubernetes.io/role/elb"                      = "1"
  }

  private_subnet_tags = {
    "kubernetes.io/cluster/${local.cluster_name}" = "shared"
    "kubernetes.io/role/internal-elb"             = "1"
  }
}

data "aws_subnet" "private1" {
  cidr_block = "10.0.1.0/24"
  depends_on = [
    module.vpc
  ]
}

data "aws_subnet" "private2" {
  vpc_id     = module.vpc.vpc_id
  cidr_block = "10.0.2.0/24"
  depends_on = [
    module.vpc
  ]
}

data "aws_subnet" "private3" {
  vpc_id     = module.vpc.vpc_id
  cidr_block = "10.0.3.0/24"
  depends_on = [
    module.vpc
  ]
}

data "aws_subnet" "public1" {
  vpc_id     = module.vpc.vpc_id
  cidr_block = "10.0.4.0/24"
  depends_on = [
    module.vpc
  ]
}

data "aws_subnet" "public2" {
  vpc_id     = module.vpc.vpc_id
  cidr_block = "10.0.5.0/24"
  depends_on = [
    module.vpc
  ]
}

data "aws_subnet" "public3" {
  vpc_id     = module.vpc.vpc_id
  cidr_block = "10.0.6.0/24"
  depends_on = [
    module.vpc
  ]
}

