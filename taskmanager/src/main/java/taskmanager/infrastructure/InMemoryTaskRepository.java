package taskmanager.infrastructure;

import taskmanager.domain.Task;
import taskmanager.domain.TaskId;
import taskmanager.domain.TaskRepository;

import java.util.*;

public class InMemoryTaskRepository implements TaskRepository {

    private final Map<TaskId, Task> storage = new HashMap<>();

    @Override
    public Task save(Task task) {
        storage.put(task.getId(), task);
        return task;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Task> findById(TaskId id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void delete(Task task) {
        storage.remove(task.getId());
    }
}
