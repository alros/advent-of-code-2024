plugins {
    kotlin("jvm") version "2.1.0"
}

group = "advent2024"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //implementation("com.madgag:gifwriter:1.0.0")
    implementation("com.madgag:animated-gif-lib:1.4")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}