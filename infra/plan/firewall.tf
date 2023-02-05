resource "google_compute_firewall" "msciq-app-dev-postgresql" {
  name    = "allow-only-gke-cluster"
  network = google_compute_network.msciq-engg-nw-dev-vpc.name

  allow {
    protocol = "tcp"
    ports    = ["5432"]
  }

  priority = 1000

  source_ranges = ["10.10.10.0/24"]

}


resource "google_compute_firewall" "msciq-app-dev-firewall" {
  name    = "allow-only-authorized-networks"
  network = google_compute_network.msciq-engg-nw-dev-vpc.name

  allow {
    protocol = "tcp"
  }

  priority = 1000

  source_ranges = var.authorized_source_ranges
}