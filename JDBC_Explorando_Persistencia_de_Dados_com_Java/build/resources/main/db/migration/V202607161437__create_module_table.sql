-- Criação da tabela modules se ela não existir

CREATE TABLE IF NOT EXISTS modules(
    id BIGINT not null auto_increment,
    name VARCHAR(150) not null,
    PRIMARY KEY (id)
)engine = InnoDB default charset = utf8;