# 🎙️ Budgeting API com Spring AI

> Projeto desenvolvido como Desafio de Projeto da trilha de Spring Boot da [DIO](https://dio.me), com foco na integração de inteligência artificial mantendo uma arquitetura em camadas.

---

## 📌 Sobre o Projeto

O **Budgeting API** é um sistema de gestão orçamentária pessoal que utiliza **Spring AI** para processar requisições via comandos de voz e texto. O sistema aceita arquivos de áudio, realiza a transcrição para texto (Speech-to-Text), identifica o caso de uso adequado por meio de **Tool Calling** (Function Calling) e pode responder em áudio (Text-to-Speech).

### 📐 Arquitetura
A aplicação segue os princípios de separação de responsabilidades e Domain-Driven Design (DDD):
- `domain`: Modelos de negócio, regras principais e contratos de repositório.
- `application`: Casos de uso da aplicação chamados pela API ou pelo Spring AI.
- `infrastructure`: Adaptadores HTTP, persistência (JPA) e integração com o Spring AI.

---

## 🛠️ Alterações e Arquivos Criados

Para realizar essa evolução mantendo a integridade da arquitetura, os seguintes componentes foram ajustados/criados:

1. **Camada de Aplicação (`application`)**:
    - `[NovoUseCase.java]`: Implementação do novo caso de uso responsável por [ex: calcular o somatório das transações].
2. **Camada de Infraestrutura (`infrastructure`)**:
    - `[Controller/Adapter]`: Registro do novo método exposto com a anotação `@Tool` do Spring AI, permitindo que o modelo invoque o caso de uso automaticamente a partir da pergunta do usuário.
3. **Camada de Domínio (`domain`)** *(se aplicável)*:
    - `[TransactionRepository.java]`: [Ex: Adição de método de consulta por categoria].

---

## 🧪 Como Testar a Evolução

### Pré-requisitos
- Java 21+
- Chave de API da OpenAI (configurada na variável de ambiente `SPRING_AI_OPENAI_API_KEY`) ou serviço equivalente configurado.

### Executando a Aplicação
```bash
./gradlew bootRun
