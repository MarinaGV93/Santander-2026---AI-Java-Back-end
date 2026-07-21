/**
 * Projeto Spring Boot gerado via Spring Initializr.
 * Os seguintes módulos foram selecionados:
 * - Spring Data JPA
 * - Spring Web
 * - H2 Database
 * - OpenFeign
 */

package gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//Habilita o feign dentro do projeto

@EnableFeignClients
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        // Inicia a aplicação Spring Boot
        SpringApplication.run(Application.class, args);
    }
}
