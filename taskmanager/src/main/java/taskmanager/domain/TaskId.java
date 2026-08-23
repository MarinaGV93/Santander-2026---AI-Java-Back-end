package taskmanager.domain;

import org.springframework.util.Assert;

import java.util.UUID;

public record TaskId(UUID id) {
    public TaskId {
        Assert.notNull(id, "Id cannot be null");
    }

    // Cria um id aleatório
    public TaskId(){
        this(UUID.randomUUID());
    }
}
