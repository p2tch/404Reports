plugins {
    id("java")
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            name = "papermc-repo"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven("https://repo.velocitypowered.com/snapshots/")
    }
}
