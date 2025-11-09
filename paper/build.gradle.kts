plugins {
    id("java")
    id("com.gradleup.shadow") version("9.2.2")
}

group = "dev.p2tch"
version = "1.0.0"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.panda-lang.org/releases") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")

    implementation("com.google.inject:guice:7.0.0")
    implementation("dev.rollczi:litecommands-bukkit:3.10.6")
    implementation("dev.dejvokep:boosted-yaml:1.3.6")
}

sourceSets {
    main {
        blossom {
            javaSources {
                property("version", project.version.toString())
            }
        }
    }
}


tasks.test {
    useJUnitPlatform()
}