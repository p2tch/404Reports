plugins {
    id("java")
    id("net.kyori.blossom") version "2.2.0"
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            name = "papermc-repo"
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
        maven("https://repo.okaeri.cloud/releases")
        maven("https://repo.panda-lang.org/releases")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "net.kyori.blossom")

    sourceSets {
        main {
            blossom {
                resources {
                    trimNewlines = false

                    property("version", project.version.toString())
                }
                javaSources {
                    property("version", project.version.toString())
                }
            }
        }
    }
}

