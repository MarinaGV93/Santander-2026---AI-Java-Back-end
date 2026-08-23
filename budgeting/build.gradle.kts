plugins {
    id("java")
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "tech.ada.java.projetos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        implementation(platform("org.springframework.ai:spring-ai-bom:2.0.0-M4"))
        implementation("org.springframework.ai:spring-ai-starter-model-openai")
    }
}

tasks.test {
    useJUnitPlatform()
}