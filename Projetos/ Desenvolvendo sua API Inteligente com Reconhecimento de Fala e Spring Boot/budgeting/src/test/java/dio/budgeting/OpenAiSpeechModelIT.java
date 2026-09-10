package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// Carrega a configuração da aplicação
@SpringBootTest(classes = BudgetingApplication.class)
// Só executa o teste se a variável de ambiente estiver setada
// @EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".*")
public class OpenAiSpeechModelIT {

    // Injeta o modelo de transcrição de áudio do OpenAI
    @Autowired
    OpenAiAudioSpeechModel openAiAudioSpeechModel;

    @Test
    // Roda o teste
    public void should_produceAudio_when_textIsProvided() throws IOException {

        // Chama o modelo de transcrição de áudio do OpenAI para processar o arquivo de áudio
        var response = openAiAudioSpeechModel.call("O valor total do serviço ficou em 60 reais. Posso confirmar o pagamento?");

        // Verifica o tamanho do arquivo de áudio
        assertThat(response).hasSizeGreaterThan(1024);

        // Cria um arquivo temporário para salvar o áudio
        var tempFile = Files.createTempFile("AUDIO", ".mp2");

        // Salva o arquivo de áudio no arquivo temporário
        Files.write(tempFile, response);

        // Imprime o caminho do arquivo temporário
        System.out.println(tempFile.toAbsolutePath());
    }
}
