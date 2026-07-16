plugins {
    id("java")
}

group = "tech.ada.java.projetos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Driver de conexão do MySQL
    implementation("com.mysql:mysql-connector-j:8.4.0")

    // Flyway 10 Moderno
    implementation("org.flywaydb:flyway-core:10.15.2")
    implementation("org.flywaydb:flyway-mysql:10.15.2")

    // Datafaker
    implementation("net.datafaker:datafaker:2.7.0")

    // Lombok atualizado para a versão mais recente estável
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
    testCompileOnly("org.projectlombok:lombok:1.18.38")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.38")
}

//Permitir que o Lombok aceda às partes do sistema que ele precisa para gerar os Getters/Setters sem lançar o ExceptionInInitializerError
tasks.withType<JavaCompile> {
    options.compilerArgs.addAll(listOf(
        "-Xlint:unchecked",
        "-Xlint:deprecation",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.model=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
        "--add-opens", "jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED"
    ))
}