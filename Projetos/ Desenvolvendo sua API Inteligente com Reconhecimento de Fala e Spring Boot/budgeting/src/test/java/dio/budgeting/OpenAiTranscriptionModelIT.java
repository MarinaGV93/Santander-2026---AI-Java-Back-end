package dio.budgeting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// Carrega a configuração da aplicação
@SpringBootTest(classes = BudgetingApplication.class)
// Só executa o teste se a variável de ambiente estiver setada
// @EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiTranscriptionModelIT {

    // Injeta o modelo de transcrição de áudio do OpenAI
    @Autowired
    OpenAiAudioTranscriptionModel openAiAudioTranscriptionModel;

    // Pode passar parametros para o teste, como arquivos de áudio diferentes
    @ParameterizedTest

    // Precisa falar qual vai ser uma source
    // Arquivo de testo com separação de vírgula
    @CsvSource({
            // Nome do arquivo, texto esperado
            "Audio1.mp3, 60 reais",
            "Audio2.mp3, 200 reais"
    })
    // Roda o teste e ja popula o fileName
    public void should_containExpectedKeywords_when_audioFilesAreProcessed(String fileName, String expectedKeywords) {

        // Procurar o audio na pasta
        var recording = new ClassPathResource("audio/" + fileName);

        // // Pode criar o options em vez de passar o response
        // var options = OpenAiAudioTranscriptionOptions.builder()
        //         .model("whisper-large-v3")
        //         .language("pt")
        //         .temperature(0f)
        //         .responseFormat(OpenAiAudioApi.TranscriptResponseFormat.TEXT)
        //         .prompt(prompt)
        //         .build();


        // Chama o modelo de transcrição de áudio do OpenAI para processar o arquivo de áudio
        var response = openAiAudioTranscriptionModel.call(recording);

        // Verifica se a resposta contém as palavras-chave esperadas
        // assertThat(response).isNotEmpty();

        // Verifica se a resposta contém as palavras-chave esperadas
        assertThat(response).contains(expectedKeywords);

        System.out.println("Transcrição do arquivo " + fileName + ": " + response);
    }
}
