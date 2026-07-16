package br.com.dio.persistence.entity;

import lombok.Data;

@Data
public class ContactEntity {

    private long id;

    private String description;

    private String type;

    // Relacionamento com a entidade de colaborador
    private EmployeeEntity employee;
}
