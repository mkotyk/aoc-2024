plugins {
    kotlin("jvm") version "2.0.21"
    id("org.jetbrains.kotlinx.dataframe") version "0.14.1"
    `java-library`
}

group = "com.psyndicate.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
}

dependencies {
    implementation("org.jetbrains.kotlinx:multik-core:0.2.3")
    implementation("org.jetbrains.kotlinx:multik-default:0.2.3")
    implementation("org.jetbrains.kotlinx:dataframe:0.14.1")
    implementation("org.jetbrains.kotlinx:kandy-lets-plot:0.7.0")
    implementation("org.jetbrains.kotlinx:kotlin-statistics-jvm:0.3.1")
    testImplementation(kotlin("test"))
    implementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}