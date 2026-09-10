// Informações do JPA

package dio.budgeting.infrastructure.persistence.entity;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

// Tem referencia com uma tabela do banco de dados
@Entity
// Gera os métodos getters, setters, equals, hashCode e toString
@Data
// Gera um construtor sem argumentos
@NoArgsConstructor
// Gera um construtor com todos os argumentos
@AllArgsConstructor
public class TransactionEntity {
    // Campos da tabela
    @Id
    private UUID id;

    private String description;
    private long amount;

    // Indica que o campo category é um enum e deve ser armazenado como uma string no banco de dados
    @Enumerated(EnumType.STRING)
    private Category category;

    // Metodo para converter de TransactionEntity para Transaction
    public static TransactionEntity from(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId().id(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getCategory());
    }

    // Metodo para converter de Transaction para TransactionEntity
    public Transaction toDomain() {
        return new  Transaction(
                new TransactionId(this.id),
                this.description,
                this.amount,
                this.category
        );
    }
}
