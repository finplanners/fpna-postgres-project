resource "random_string" "db_name_suffix" {
  length  = 4
  special = false
  upper   = false
}


resource "google_sql_database_instance" "pgsql_db" {
  name              = "pgsql-msciq-${random_string.db_name_suffix.result}"
  region            = var.region
  database_version  = var.pgsql_db_version

  settings {

    availability_type = var.pgsql_db_availability_type
    location_preference {
      zone = var.pgsql_db_location_pref
    }
    tier = var.pgsql_db_machine_type

    disk_size = var.pgsql_db_disk_size

    ip_configuration {
      ipv4_enabled    = false
      private_network = google_compute_network.msciq-engg-nw-dev-vpc.id
    }

    backup_configuration {
      binary_log_enabled = false
      enabled            = false
      start_time         = "06:00"
    }
  }

  depends_on = [google_service_networking_connection.msciq-engg-nw-svc-conn]

}

data "google_secret_manager_secret_version" "msciq-admin-user-credentials" {
  secret = "msciq-admin-user-credentials"
}

resource "google_sql_database" "msciq-engg-db-app-dev-db" {
  name = "postgresdb"
  instance = google_sql_database_instance.pgsql_db.name
}

resource "google_sql_user" "msciq-engg-db-app-dev-db-user" {
  instance = google_sql_database_instance.pgsql_db.name
  name     = "postgresdb"
  password = data.google_secret_manager_secret_version.msciq-admin-user-credentials.secret_data
}