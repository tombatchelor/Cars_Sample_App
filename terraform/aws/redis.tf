resource "aws_elasticache_cluster" "session-store" {
  cluster_id           = "session-store"
  engine               = "redis"
  node_type            = "cache.t3.micro"
  num_cache_nodes      = 1
  parameter_group_name = "default.redis6.x"
  engine_version       = "6.x"
  port                 = 6379
  availability_zone    = "us-west-1c"
}

resource "aws_security_group" "default" {
  name_prefix = "session"
  vpc_id      = module.vpc.vpc_id

  ingress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_elasticache_subnet_group" "default" {
  name       = "session-cache-subnet"
  subnet_ids = [aws_subnet.main.id]
}

resource "aws_elasticache_replication_group" "default" {
  replication_group_id          = "session-cache"
  replication_group_description = "Redis cluster for Hashicorp ElastiCache example"

  node_type            = "cache.t3.micro"
  port                 = 6379
  parameter_group_name = "default.redis6.x.cluster.on"

  snapshot_retention_limit = 5
  snapshot_window          = "00:00-05:00"

  subnet_group_name          = aws_elasticache_subnet_group.default.name
  automatic_failover_enabled = true

  cluster_mode {
    replicas_per_node_group = 3
    num_node_groups         = "1"
  }
}