CREATE TABLE IF NOT EXISTS contacts(
    id BIGINT not null auto_increment,
    description VARCHAR(50) not null,
    type VARCHAR(30),
-- ID de outra tabela
    employee_id BIGINT not null,

-- Chave estrangeira para a tabela employees (nome da tabela)
-- Coloca o id dessa tabela, referenciando o id da tabela employees

    CONSTRAINT fk_contacts_employees FOREIGN KEY (employee_id) REFERENCES employees(id),

    PRIMARY KEY (id)
);