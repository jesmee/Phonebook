plugins {
    id("java")
    id("org.flywaydb.flyway") version "10.0.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}


flyway {
    url = "jdbc:postgresql://localhost:5432/phonebook"
    user = "admin"
    password = "admin"
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}



dependencies {
    implementation(project(":models"))
    implementation(project(":repository"))
    implementation(project(":sql_connectors"))
    implementation(project(mapOf("path" to ":sql_connectors")))
}