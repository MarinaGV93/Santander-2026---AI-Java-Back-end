package taskmanager.infrastructure;

import taskmanager.domain.*;

class InMemoryTaskRepositoryTest extends TaskRepositoryTest {

    // Instanciando o repositorio antes de cada teste e armazenando no repositorio
    @Override
    protected TaskRepository createRepository() {
        return new InMemoryTaskRepository();
    }
}

// PAREI NO ORQUESTRANDO O DOMINIO