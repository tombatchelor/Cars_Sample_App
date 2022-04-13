variable "region" {
  default     = "us-east-1"
  description = "AWS region"
}

variable "observe_customer" {
  default     = ""
  description = "Observe Customer ID"
}

variable "observe_token" {
  default     = ""
  description = "Observe Ingest Token"
}

variable "observe_domain" {
  default     = "observeinc.com"
  description = "Observe Domain"
}