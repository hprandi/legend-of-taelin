plugins {
    kotlin("jvm") version "2.0.20"
    id("org.openjfx.javafxplugin") version "0.0.14"
}

group = "com.hprandi.legend-of-taelin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openjfx:javafx-base:20.0.2")
    implementation("org.openjfx:javafx-controls:20.0.2")
    implementation("org.openjfx:javafx-graphics:20.0.2")

    testImplementation(kotlin("test"))
}

javafx {
    version = "20.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}