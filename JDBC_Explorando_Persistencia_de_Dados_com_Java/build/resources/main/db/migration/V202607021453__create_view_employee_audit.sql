-- Garante a remoção da view antiga antes de recriá-la
DROP VIEW IF EXISTS view_employee_audit;

CREATE VIEW view_employee_audit AS
SELECT * FROM employees_audit
ORDER BY created_at;