package dio.budgeting;

import org.springframework.ai.audio.tts.TextToSpeechModel;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class TextSpeechController {
    private final TextToSpeechModel textToSpeechModel;

    public TextSpeechController(TextToSpeechModel textToSpeechModel) {
        this.textToSpeechModel = textToSpeechModel;
    }

    // Requisição
    @PostMapping(value = "/sinthesize", produces = "audio/mp3")
    // @RequestBody é usado para mapear o corpo da requisição para um objeto Java
    public ResponseEntity<Resource> sinthesize(@RequestBody SynthesizeRequest request) {

        // Resposta de áudio
        byte[] audio = textToSpeechModel.call(request.text);

        // Cria um recurso de bytes a partir do áudio gerado
        var resource = new ByteArrayResource(audio);

        // Retorna o áudio na URL (requisição)
        return ResponseEntity

                // ok() é usado para indicar que a requisição foi bem-sucedida
                .ok()

                // header() é usado para adicionar headers na resposta
                .header(HttpHeaders

                                // Content-Disposition é usado para indicar que o conteúdo da resposta deve ser tratado como um anexo
                                .CONTENT_DISPOSITION,

                        // attachment() é usado para indicar que o conteúdo da resposta deve ser tratado como um anexo
                        ContentDisposition.attachment()
                                .filename("audio.mp3")
                                .build()
                                .toString())

                // body() é usado para definir o corpo da resposta
                .body(resource);

    }

    public record SynthesizeRequest(String text){

    }
}
