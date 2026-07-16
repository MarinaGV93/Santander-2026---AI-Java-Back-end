-- Garante a remoção caso a trigger tenha ficado órfã de execuções anteriores
DROP TRIGGER IF EXISTS tgr_employee_audit_delete;

CREATE TRIGGER tgr_employee_audit_delete BEFORE DELETE ON employees
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
    OLD.id,
    OLD.name,
    OLD.salary,
    OLD.birthday,
    'D'
    );
-- Indica onde termina a duração do delimitador
END;