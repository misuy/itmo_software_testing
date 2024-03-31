plugins {
    id("java")
}

group = "misuy."
version = "1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "misuy.Main"
    }
}

tasks.test {
    useJUnitPlatform()
}