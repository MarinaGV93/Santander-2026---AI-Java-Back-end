package dio.budgeting.application;

import dio.budgeting.application.input.PersistTransactionInput;
import dio.budgeting.application.output.TransactionOutput;
import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

// Classe de serviço que persiste uma transação no banco de dados
@Service
public class PersistTransactionUseCase {
    private final TransactionRepository transactionRepository;

    public PersistTransactionUseCase(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Tool(name = "persist-transaction", description = "Persiste uma nova transação financeira")
    // Unico metodo publico
    // Cria uma transação com uma classe como parametro
    public TransactionOutput execute(PersistTransactionInput
                                // Objeto só para transacionar dados
                                input){
        var transaction = transactionRepository.save(
                new Transaction(input.description(), input.amount(), input.category()));
        return TransactionOutput.from(transaction);
    }
}
