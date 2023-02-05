resource "google_compute_network" "msciq-engg-nw-dev-vpc" {
  name                    = "msciq-engg-dev-vpc-terraform"
  auto_create_subnetworks = "false"
  routing_mode            = "GLOBAL"
}

resource "google_compute_subnetwork" "msciq-engg-nw-app-subnet" {
  ip_cidr_range = "10.10.10.0/24"
  name          = "msciq-engg-na-ce2-dev-app-subnet"
  network       = google_compute_network.msciq-engg-nw-dev-vpc.id
  region        = var.region

  secondary_ip_range = [
    {
      ip_cidr_range = "10.10.11.0/24"
      range_name    = "services"
    },
    {
      ip_cidr_range = "10.1.0.0/20"
      range_name    = "pods"
    }
  ]
  private_ip_google_access = true
}

resource "google_compute_subnetwork" "msciq-engg-nw-database-subnet" {
  ip_cidr_range = "10.20.10.0/24"
  name          = "msciq-engg-na-ce2-dev-db-subnet"
  network       = google_compute_network.msciq-engg-nw-dev-vpc.name
  region        = var.region

  private_ip_google_access = true
}

resource "google_compute_global_address" "msciq-engg-global-peering-ip" {
  name          = "google-managed-services-custom"
  description   = "IP address reserved"
  purpose       = "VPC_PEERING"
  address_type = "INTERNAL"
  prefix_length = 24
  network       = google_compute_network.msciq-engg-nw-dev-vpc.id
}

resource "google_service_networking_connection" "msciq-engg-nw-svc-conn" {
  network                 = google_compute_network.msciq-engg-nw-dev-vpc.id
  reserved_peering_ranges = [google_compute_global_address.msciq-engg-global-peering-ip.name]
  service                 = "servicenetworking.googleapis.com"
}
