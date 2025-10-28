plugins {
    id("java")
}

group = "dev.p2tch"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")

    implementation("com.google.inject:guice:7.0.0")
}

tasks.test {
    useJUnitPlatform()
}