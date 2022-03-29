resource "aws_elasticache_cluster" "session-store" {
  cluster_id           = "session-store"
  engine               = "redis"
  node_type            = "cache.t3.micro"
  num_cache_nodes      = 1
  parameter_group_name = "default.redis6.x"
  engine_version       = "6.x"
  port                 = 6379
  availability_zone    = "us-east-1d"
}
