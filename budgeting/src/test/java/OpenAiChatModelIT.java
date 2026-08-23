import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.ResponseFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Carrega a configuração da aplicação
@SpringBootTest(classes = BudgetingApplication.class)
// Só executa o teste se a variável de ambiente estiver setada
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiChatModelIT {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    // Injeção do OpenAI (não é necessário, pq o Spring Boot já inicia o contexto)
    // @Autowired
    // private OpenAiApi openAiApi;
    // Igual = OpenAiApi openAiApi = new OpenAiApi();

    // Se for colocar as variáveis no application.properties
    // @Autowired
    // OpenAiChatModel chatModel;

    // Verifica se o chat model retorna uma resposta
    @Test
    void should_receiveResponse_when_chatModelIsCalled() {

        // Instancia o cliente da API passando a chave
        var openAiApi = OpenAiApi.builder()
                .apiKey(apiKey)
                .build();

        // Usar quando tiver mais de 1 chat model, ou tem configurações específicas para alguma coisa.

        // Configura o chat model
        var options = OpenAiChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.8)
                .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
                .build();

        // // Instancia o chat model
        var chatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(options)
                .build();

        // Executa o chat model com um prompt
        var response = chatModel.call("Gere um registro de budgeting, com descrição de gasto, valor em reais e local");

        assertThat(response).isNotEmpty();

        System.out.println(response);

        assertNotNull(chatModel);
    }
}
