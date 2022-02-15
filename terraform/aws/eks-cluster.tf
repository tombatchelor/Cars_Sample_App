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

  fargate_profiles = {
    default = {
      name = "default"
      selectors = [
        {
          namespace = "kube-system"
          labels = {
            k8s-app = "kube-dns"
          }
        },
        {
          namespace = "default"
        },
        {
          namespace = "observe-demo"
        },
        {
          namespace = "cars-app"
        }
      ]

      tags = {
        Owner = "test"
      }

      timeouts = {
        create = "20m"
        delete = "20m"
      }
    }
  }
}

data "aws_eks_cluster" "cluster" {
  name = module.eks.cluster_id
}

data "aws_eks_cluster_auth" "cluster" {
  name = module.eks.cluster_id
}
