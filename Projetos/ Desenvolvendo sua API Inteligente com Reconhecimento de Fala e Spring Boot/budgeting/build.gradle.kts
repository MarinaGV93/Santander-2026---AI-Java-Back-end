plugins {
    id("java")
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("io.freefair.lombok") version "9.2.0"
}

group = "tech.ada.java.projetos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.google.code.gson:gson:2.11.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation(platform("org.springframework.ai:spring-ai-bom:2.0.0-M4"))
    implementation("org.springframework.ai:spring-ai-starter-model-openai")

// Permite suporte ao gerenciamento do ciclo de vida do container
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")

// JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

// MySQL
    runtimeOnly("com.mysql:mysql-connector-j")
}

tasks.test {
    useJUnitPlatform()
}