project.repositories {
    mavenCentral()
    mavenLocal()
}

project.configure<org.gradle.api.publish.PublishingExtension> {
    publications {
        register("mavenJava", org.gradle.api.publish.maven.MavenPublication::class) {
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}