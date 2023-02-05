output "cloud-sql-connection-name" {
  value = google_sql_database_instance.pgsql_db.connection_name
}

output "cloud-sql-instance-name" {
  value = "mysql-private-${random_string.db_name_suffix.result}"
}
s