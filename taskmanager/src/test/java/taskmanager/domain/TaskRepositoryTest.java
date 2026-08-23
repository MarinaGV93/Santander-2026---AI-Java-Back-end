package taskmanager.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class TaskRepositoryTest {
    TaskRepository repository;

    // Cria o repositorio
    protected abstract TaskRepository createRepository();

    // Instancia o repositorio antes de cada teste
    @BeforeEach
    public void setUp() {
        this.repository = createRepository();
    }

    // Teste de salvar e buscar tarefa pelo id
    @Test
    void should_save_and_retrieve_task_by_id(){

        // given
        // Verifica se o id é null
        var task = new Task("Task 1", Optional.empty());

        // when
        // Salva a tarefa no repositorio
        var saved = repository.save(task);

        // Busca a tarefa pelo id
        Optional<Task> result = repository.findById(saved.getId());

        // then
        // Verifica se a tarefa foi salva e buscada corretamente
        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(task.getId());
        assertThat(result.get().getDescription()).isEqualTo(task.getDescription());
        assertThat(result.get().getStatus()).isEqualTo(task.getStatus());
    }

    // Teste de buscar todas as tarefas persistidas
    @Test
    void should_find_all_persisted_tasks(){

        // given
        // Cria tarefas
        var task1 = new Task("Arrumar chuveiro", Optional.of("Comprar chuveiro novo"));
        var task2 = new Task("Trocar interruptor", Optional.of("Encontrar chave de fenda"));

        // Salva as tarefas no repositorio
        repository.save(task1);
        repository.save(task2);

        // when
        // Busca todas as tarefas
        List<Task> tasks = repository.findAll();

        // then
        // Verifica se as tarefas foram salvas e buscadas corretamente
        assertThat(tasks).hasSize(2);
        assertThat(tasks).contains(task1, task2);
    }

    // Teste de exclusao de tarefa pelo id
    @Test
    void should_delete_task_by_id(){

        // given
        // Cria e salva uma tarefa
        var task = repository.save(new Task("Treinar na academima", Optional.empty()));
        var taskId = task.getId();

        // when
        // Exclui a tarefa pelo id
        repository.delete(task);
        Optional<Task> result = repository.findById(taskId);

        // then
        // Verifica se a tarefa foi excluida
        assertThat(result).isEmpty();
    }

    // Teste de buscar tarefa inexistente
    @Test
    void should_return_empty_when_searching_non_existent_task(){

        // given
        // Cria um id inexistente
        var nonExistentId = new TaskId();

        // when
        // Busca a tarefa pelo id inexistente
        Optional<Task> result = repository.findById(nonExistentId);

        // then
        // Verifica se a tarefa nao existe
        assertThat(result).isEmpty();
    }

    // Teste de atualizacao de status da tarefa
    @Test
    void should_update_task_status_successfully(){

        // given
        // Cria e salva uma tarefa
        var task = repository.save(new Task("Atualizar Carteira de Habilitação", Optional.empty()));
        var taskId = task.getId();

        // Atualiza a tarefa
        task.setDescription(Optional.of("Não expirou ainda"));
        task.setStatus(TaskStatus.IN_PROGRESS);

        // when
        // Salva a tarefa atualizada
        repository.save(task);
        Optional<Task> result = repository.findById(task.getId());

        // then
        // Verifica se o status foi atualizado
        assertThat(result).isPresent();
        assertThat(result.get().getDescription()).isEqualTo(Optional.of("Não expirou ainda"));
        assertThat(result.get().getStatus()).isEqualTo(TaskStatus.IN_PROGRESS);
    }
}