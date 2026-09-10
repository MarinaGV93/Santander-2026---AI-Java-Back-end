package dio.budgeting;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Carrega a configuração da aplicação
@SpringBootTest(classes = BudgetingApplication.class)
// Só executa o teste se a variável de ambiente estiver setada
// @EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiChatModelIT {

    // Injeção da chave da API a partir do application.properties
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    // Injeção da URL base do OpenAI a partir do application.properties
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

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

        // // Configura a URL base e o RestClient diretamente para o domínio da Groq
        // var restClientBuilder = RestClient.builder()
        //         .baseUrl("https://api.groq.com/openai")
        //         .defaultHeader("Authorization", "Bearer " + apiKey);

        // Instancia o cliente da API passando a chave
        var openAiApi = OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey(apiKey)
                // .restClientBuilder(restClientBuilder)
                .build();

        // Usar quando tiver mais de 1 chat model, ou tem configurações específicas para alguma coisa.

        // Configura o chat model
        var options = OpenAiChatOptions.builder()
                // .model("gpt-4o-mini")
                .model("llama-3.1-8b-instant")
                // .temperature(0.8)
                .temperature(0.7)
                // .responseFormat(ResponseFormat.builder().type(ResponseFormat.Type.TEXT).build())
                .build();

        // // Instancia o chat model
        var chatModel = OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(options)
                .build();

        // Executa o chat model com um prompt
        var response = chatModel.call("Gere um registro de budgeting, com descrição de gasto, valor em reais e local");

        // Verifica se a resposta não está vazia
        Assertions.assertThat(response).isNotEmpty();

        // Imprime a resposta no console
        System.out.println("Resposta da Groq: " + response);

        // Verifica se o chat model não é nulo
        assertThat(chatModel).isNotNull();
    }
}
