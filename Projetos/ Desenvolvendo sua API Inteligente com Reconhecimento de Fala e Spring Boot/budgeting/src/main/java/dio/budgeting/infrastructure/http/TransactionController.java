package dio.budgeting.infrastructure.http;

import dio.budgeting.application.ListTransactionByCategoryUseCase;
import dio.budgeting.application.PersistTransactionUseCase;
import dio.budgeting.domain.Category;
import dio.budgeting.infrastructure.http.request.TransactionRequest;
import dio.budgeting.infrastructure.http.response.TransactionResponse;
import org.springframework.ai.audio.transcription.TranscriptionModel;
import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final PersistTransactionUseCase persistTransactionUseCase;

    private final ListTransactionByCategoryUseCase listTransactionByCategoryUseCase;

    private final TranscriptionModel transcriptionModel;

    private final ChatClient chatClient;

    private final TextToSpeechModel textToSpeechModel;

    public TransactionController(
            PersistTransactionUseCase persistTransactionUseCase,
            ListTransactionByCategoryUseCase listTransactionByCategoryUseCase,
            TranscriptionModel transcriptionModel,
            // Indicar que o arquivo system-message.st será injetado como recurso
            @Value("classpath:prompts/system-message.st") Resource systemPrompt,
            ChatClient.Builder chatClientBuilder,
            TextToSpeechModel textToSpeechModel) {
        this.persistTransactionUseCase = persistTransactionUseCase;
        this.listTransactionByCategoryUseCase = listTransactionByCategoryUseCase;
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClientBuilder
                // Indicar que o assistente financeiro será utilizado como sistema para o chat
                .defaultSystem(systemPrompt.toString())

                // Indicar que o assistente financeiro terá acesso aos casos de uso para persistir e listar transações (pela instancia)
                .defaultTools(persistTransactionUseCase, listTransactionByCategoryUseCase)
                .build();
        this.textToSpeechModel = textToSpeechModel;
    }

    @PostMapping

    // Indicar que o status de retorno da requisição será 201 Created
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponse createTransaction(
            // Pegar a informação do corpo da requisição para montar um objeto Transaction
            @RequestBody
            // Criar um objeto DTO
            TransactionRequest request
            ) {

        // Chamar o caso de uso para persistir a transação
        var transaction = persistTransactionUseCase.execute(request.toInput());

        return TransactionResponse.from(transaction);
    }

    @GetMapping("/{category}")
    public List<TransactionResponse> readTransactions(@PathVariable Category category) {
        return listTransactionByCategoryUseCase.execute(category).stream().map(TransactionResponse::from).toList();
    }

    @PostMapping(value = "/ai", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "audio/mp3")
    ResponseEntity<Resource> transcribe(@RequestParam("file")MultipartFile file){

        // Chamar o modelo de transcrição para transcrever o áudio enviado pelo usuário
        var userMessage = transcriptionModel.transcribe(file.getResource());

        // Chamar o chatClient para processar a mensagem do usuário e gerar uma resposta
        var result = chatClient.prompt().user(userMessage).call().content();

        // Gerar o áudio da resposta do chat usando o modelo de Text-to-Speech
        byte[] audio = textToSpeechModel.call(result);

        // Criar um recurso de áudio a partir do array de bytes
        var resource = new ByteArrayResource(audio);

        // Retornar o recurso de áudio como resposta da requisição, com o cabeçalho Content-Disposition para indicar que é um arquivo de download
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())
                .body((Resource) resource);
    }
}
