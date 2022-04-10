
resource "aws_elasticache_cluster" "session-store" {
  cluster_id           = "session-store"
  engine               = "redis"
  node_type            = "cache.t3.micro"
  num_cache_nodes      = 1
  parameter_group_name = "default.redis6.x"
  engine_version       = "6.x"
  port                 = 6379
  availability_zone    = "us-east-1d"
  subnet_group_name    = aws_elasticache_subnet_group.session-store-subnet.name
}

resource "aws_elasticache_subnet_group" "session-store-subnet" {
  name = "session-store-subnet"
  subnet_ids = [data.aws_subnet.private1.id, ata.aws_subnet.private2.id, data.aws_subnet.private3.id]
}
