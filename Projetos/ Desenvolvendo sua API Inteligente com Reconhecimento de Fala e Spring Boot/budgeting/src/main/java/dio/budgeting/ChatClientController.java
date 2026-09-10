package dio.budgeting;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatClientController {

    // Injeção do OpenAiChatModel para chamar o modelo de chat da OpenAI
    // Mais abstrato
    private final ChatClient chatClient;

    // Construtor para injetar o OpenAiChatModel
    public ChatClientController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    // Endpoint GET para chamar o modelo de chat da OpenAI com um prompt
    @GetMapping("/chat")
    public String chat(@RequestParam(defaultValue = "Gere um registro de gasto de teste") String prompt) {
        return this.chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
