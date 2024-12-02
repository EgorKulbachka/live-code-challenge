import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.finmid.payables-financing"
version = "0.0.1-SNAPSHOT"

plugins {
    kotlin("jvm") version "2.1.0"
}


java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.0")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("com.zaxxer:HikariCP:6.2.1")

    implementation("org.flywaydb:flyway-database-postgresql:11.0.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
    testImplementation("org.testcontainers:junit-jupiter:1.20.4")
    testImplementation("org.testcontainers:postgresql:1.20.4")
}

tasks.test {
    useJUnitPlatform()
}

