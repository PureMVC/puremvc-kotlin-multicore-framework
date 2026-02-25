plugins {
    kotlin("jvm") version "2.2.21"
    id("org.jetbrains.dokka") version "1.8.20"
    `maven-publish`
    signing
}

group = "org.puremvc.kotlin.multicore"
version = "1.4.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

kotlin {
    jvmToolchain(21)
}

tasks.dokkaHtml {
    outputDirectory.set(rootDir.resolve("docs"))
    dokkaSourceSets {
        named("main") {}
    }
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])

            artifactId = "puremvc-kotlin-multicore"

            pom {
                name.set("PureMVC Kotlin MultiCore")
                description.set("Kotlin MultiCore version of the PureMVC framework")
                url.set("https://github.com/yourusername/puremvc-kotlin-multicore")
                licenses {
                    license {
                        name.set("BSD 3-Clause")
                        url.set("https://opensource.org/licenses/BSD-3-Clause")
                    }
                }
                developers {
                    developer {
                        id.set("saadshams")
                        name.set("Saad Shams")
                        email.set("saad.shams@puremvc.org")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/yourusername/puremvc-kotlin-multicore.git")
                    developerConnection.set("scm:git:ssh://github.com/yourusername/puremvc-kotlin-multicore.git")
                    url.set("https://github.com/yourusername/puremvc-kotlin-multicore")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["maven"])
}
