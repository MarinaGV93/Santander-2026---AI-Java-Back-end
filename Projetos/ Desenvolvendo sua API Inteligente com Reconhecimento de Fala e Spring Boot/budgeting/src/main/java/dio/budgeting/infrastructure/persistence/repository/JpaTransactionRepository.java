package dio.budgeting.infrastructure.persistence.repository;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import dio.budgeting.infrastructure.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

// Implementação do repositório de transações usando JPA
@Repository
public class JpaTransactionRepository implements TransactionRepository {
    private final TransactionEntityRepository transactionEntityRepository;

    public JpaTransactionRepository(TransactionEntityRepository transactionEntityRepository) {
        this.transactionEntityRepository = transactionEntityRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        // Converter a entidade de domínio para a entidade do JPA
        var entity = TransactionEntity.from(transaction);

        // Salvar a entidade no banco de dados e converter de volta para a entidade de domínio
        return transactionEntityRepository.save(entity).toDomain();
    }

    @Override
    public List<Transaction> findAllByCategory(Category category) {

        // Buscar todas as entidades do JPA pelo categoria e converter para a entidade de domínio
        return transactionEntityRepository.findAllByCategory(category)

                // Converter a lista de entidades do JPA para um stream
                .stream()

                // Converter cada entidade do JPA para a entidade de domínio
                .map(TransactionEntity::toDomain)

                // Coletar os resultados em uma lista
                .toList();
    }
}
