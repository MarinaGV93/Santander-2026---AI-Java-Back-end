-- Criação da tabela employees se ela não existir

CREATE TABLE  IF NOT EXISTS employees_audit(
   id BIGINT not null auto_increment,
    name VARCHAR(150),
    old_name VARCHAR(150),
    salary DECIMAL(10,2),
    old_salary DECIMAL(10,2),
    birthday TIMESTAMP,
    old_birthday TIMESTAMP,
    operation CHAR(1),
-- Data de criação como padrão o horário atual
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
-- Chave primária
    PRIMARY KEY (id)
)engine=InnoDB default charset=utf8;