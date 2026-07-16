-- Mudar o delilmitador de linha dos comandos
-- DELIMITER $
CREATE TRIGGER tgr_employee_audit_insert AFTER INSERT ON employees
FOR EACH ROW
BEGIN
    -- Coloca o comando
    INSERT INTO employees_audit(
    employee_id,
    name,
    salary,
    birthday,
    operation
    )
    VALUES(
    NEW.id,
    NEW.name,
    NEW.salary,
    NEW.birthday,
    'I'
    );
-- Indica onde termina a duração do delimitador
END;