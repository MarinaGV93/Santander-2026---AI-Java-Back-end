package dio.budgeting;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// Carrega o contexto da aplicação
@SpringBootTest
// Só executa o teste se a variável de ambiente estiver setada
// @EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiChatClientIT {

    @Autowired
    ChatModel openAiChatClientIT;

    @Test
    void should_executeSum_when_prompted(){

        // Cria um cliente de chat com o modelo OpenAI
        var chatClient = ChatClient.builder(openAiChatClientIT)
                // Define o sistema padrão para o chat
                .defaultSystem("Você é um matemático")
                // Constroi o cliente
                .build();

        // Faz uma chamada ao modelo de chat com um prompt específico
        var response = chatClient.prompt("Soma 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado final, sem explicações")
                // Executa a chamada ao modelo
                .call()
                // Retorna o conteúdo da resposta
                .content();

        // Verifica se a resposta contém o resultado esperado da operação matemática
        assertThat(response).contains("0");

        System.out.println("Resultado da operação: " + response) ;
    }
}
