resource "google_container_cluster" "msciq-dev-private" {
  name     = "msciq-ui-gke-cluster-auto"
  location = var.region

  network    = google_compute_network.msciq-engg-nw-dev-vpc.name
  subnetwork = google_compute_subnetwork.msciq-engg-nw-app-subnet.id

  private_cluster_config {
    enable_private_endpoint = false
    enable_private_nodes    = true
    master_ipv4_cidr_block  = var.gke_master_ipv4_cidr_block
  }

  master_authorized_networks_config {
    dynamic "cidr_blocks" {
      for_each = var.authorized_source_ranges
      content {
        cidr_block = cidr_blocks.value
      }
    }
  }

  maintenance_policy {
    recurring_window {
      end_time   = "2050-01-01T04:00:00Z"
      recurrence = "FREQ=WEEKLY"
      start_time = "2023-01-23T00:00:00Z"
    }
  }

  enable_autopilot = true

  ip_allocation_policy {
    cluster_secondary_range_name  = "pods"
    services_secondary_range_name = "services"
  }

  release_channel {
    channel = "REGULAR"
  }

}