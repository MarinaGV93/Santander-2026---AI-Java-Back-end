package dio.budgeting;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatModelController {

    // Injeção do OpenAiChatModel para chamar o modelo de chat da OpenAI
    // Sempre de um serviço, como OpenAi, Microsoft, Gemini...
    private final OpenAiChatModel openAiChatModel;

    // Construtor para injetar o OpenAiChatModel
    public ChatModelController(OpenAiChatModel openAiChatModel) {
        this.openAiChatModel = openAiChatModel;
    }

    // Endpoint GET para chamar o modelo de chat da OpenAI com um prompt
    @GetMapping("/chat-model")
    public String chat(@RequestParam(defaultValue = "Gere um registro de gasto de teste") String prompt) {
        return this.openAiChatModel.call(prompt);
    }
}
