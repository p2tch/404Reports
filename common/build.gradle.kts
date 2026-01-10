plugins {
    id("java")
}

group = "dev.p2tch"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.okaeri.cloud/releases")
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2-1")

    implementation("com.google.inject:guice:7.0.0")
    implementation("com.j256.ormlite:ormlite-jdbc:6.1")
    implementation("com.github.ben-manes.caffeine:caffeine:3.2.3")
    implementation("eu.okaeri:okaeri-configs-yaml-snakeyaml:6.0.0-beta.3")
}

tasks.test {
    useJUnitPlatform()
}