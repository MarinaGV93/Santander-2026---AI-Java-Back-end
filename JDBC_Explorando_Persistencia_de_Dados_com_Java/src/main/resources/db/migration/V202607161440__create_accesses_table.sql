-- Criação da tabela employee_modules se ela não existir
-- Vai fazer a relação entre os colaboradores e de contatos
-- Nao precisa montar no Java

CREATE TABLE IF NOT EXISTS accesses(
    employee_id BIGINT not null,
    module_id BIGINT not null,
    PRIMARY KEY (employee_id, module_id),

    -- Chave estrangeira para a tabela employees (nome da tabela)
-- Coloca o id dessa tabela, referenciando o id da tabela employees

    CONSTRAINT fk_accesses_employees FOREIGN KEY (employee_id) REFERENCES employees(id),

-- Chave estrangeira para a tabela modules (nome da tabela)
-- Coloca o id dessa tabela, referenciando o id da tabela modules

    CONSTRAINT fk_accesses_modules FOREIGN KEY (module_id) REFERENCES modules(id)
)engine = InnoDB default charset = utf8;