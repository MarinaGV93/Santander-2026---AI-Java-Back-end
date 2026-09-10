package dio.budgeting;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BudgetingApplication {

    // Procura por um bean que retorna o ChatClient
    @Bean
    ChatClient chatChatClient(ChatClient.Builder builder) {

        // Instancia o ChatClient
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(BudgetingApplication.class, args);
    }
}
