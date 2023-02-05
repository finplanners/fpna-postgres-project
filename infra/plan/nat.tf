resource "google_compute_address" "msciq-app-dev-addr" {
  name   = "msciq-app-dev-addr"
  region = var.region
}

resource "google_compute_router" "msciq-app-dev-router" {
  name    = "msciq-app-dev-router"
  network = google_compute_network.msciq-engg-nw-dev-vpc.id
}

resource "google_compute_router_nat" "msciq-app-dev-nat" {
  name                               = "msciq-app-dev-nat"
  router                             = google_compute_router.msciq-app-dev-router.name
  nat_ip_allocate_option             = "MANUAL_ONLY"
  nat_ips                            = [google_compute_address.msciq-app-dev-addr.self_link]
  source_subnetwork_ip_ranges_to_nat = "LIST_OF_SUBNETWORKS"
  subnetwork {
    name                    = google_compute_subnetwork.msciq-engg-nw-app-subnet.id
    source_ip_ranges_to_nat = ["ALL_IP_RANGES"]
  }
  depends_on = [google_compute_address.msciq-app-dev-addr]
}