module "eks" {
  source          = "terraform-aws-modules/eks/aws"
  version         = "18.3.1"
  cluster_name    = local.cluster_name
  cluster_version = "1.21"
  subnet_ids      = module.vpc.private_subnets

  vpc_id = module.vpc.vpc_id

  self_managed_node_group_defaults = {
    root_volume_type = "gp2"
  }

  eks_managed_node_groups = {
    one = {
      desired_size        = 3
      min_size            = 2
      max_size            = 4
      name                = "worker-group-1"
      instance_type       = ["t2.medium"]
      additional_userdata = "echo foo bar"
    }
  }
}


data "aws_eks_cluster" "cluster" {
  name = module.eks.cluster_id
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}
