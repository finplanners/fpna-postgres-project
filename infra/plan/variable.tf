variable "region" {
  type    = string
  default = "us-central1"
}

variable "authorized_source_ranges" {
  type        = list(string)
  description = "Addresses or CIDR blocks which are allowed to connect to GKE API Server."
}

variable "project" {
  default = "msciq-dev"
}


variable "zone" {
  default = "us-central1-c"
}

variable "gke_master_ipv4_cidr_block" {
  type    = string
  default = "172.23.0.0/28"
}

variable "pgsql_db_version" {
  default = "POSTGRES_14"
}

variable "pgsql_db_location_pref" {
  type    = string
  default = "us-central1-b"
}

variable "pgsql_db_availability_type" {
  type    = string
  default = "REGIONAL"
}

variable "pgsql_db_machine_type" {
  type    = string
  default = "db-f1-micro"
}

variable "pgsql_db_disk_size" {
  type    = string
  default = "100"
}