plugins {
    id("java")
}

group = "dev.p2tch"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:26.0.2-1")

    implementation("com.google.inject:guice:7.0.0")
}

tasks.test {
    useJUnitPlatform()
}