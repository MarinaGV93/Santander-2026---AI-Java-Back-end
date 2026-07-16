package br.com.dio.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Data
public class EmployeeEntity {

    private long id;
    private String name;
    private BigDecimal salary;
    private OffsetDateTime birthday;

    // Relacionamento com a entidade de contato (relacionamento 1:1)
    // private ContactEntity contact;

    // Relacionamento com a entidade de contato (relacionamento 1:N)
    private List<ContactEntity> contacts;

    // Relacionamento com a entidade de módulo (relacionamento N:N)
    private List<ModuleEntity> modules;
}
