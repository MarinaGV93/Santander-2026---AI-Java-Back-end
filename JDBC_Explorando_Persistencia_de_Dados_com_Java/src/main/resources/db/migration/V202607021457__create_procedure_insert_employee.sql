-- Garante a remoção caso a procedure tenha ficado órfã de execuções anteriores
DROP PROCEDURE IF EXISTS prc_insert_employee;

CREATE PROCEDURE prc_insert_employee(
    -- OUT: Devolve o ID do registro gerado para o Java
    OUT p_id BIGINT,
    -- IN: Dados de entrada que o Java vai enviar
    IN p_name VARCHAR(255),
    IN p_salary DECIMAL(10, 2),
    IN p_birthday DATE
)
BEGIN
    -- Realiza a inserção dos dados recebidos
    INSERT INTO employees (name, salary, birthday)
    VALUES (p_name, p_salary, p_birthday);

    -- Aloca o ID gerado na variável de saída
    SET p_id = LAST_INSERT_ID();
END;