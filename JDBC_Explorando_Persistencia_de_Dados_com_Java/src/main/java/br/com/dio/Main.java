package br.com.dio;

import br.com.dio.persistence.*;
import br.com.dio.persistence.entity.ContactEntity;
import br.com.dio.persistence.entity.EmployeeEntity;
import br.com.dio.persistence.entity.ModuleEntity;
import net.datafaker.Faker;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;

public class Main {

    // private final static EmployeeDAO employeeDAO = new EmployeeDAO();
    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private final static Faker faker = new Faker(Locale.of("pt", "BR"));
    private final static ContactDAO contactDAO = new ContactDAO();
    private final static ModuleDAO moduleDAO = new ModuleDAO();

    public static void main(String[] args) {

        // Criar configuração do Flyway
        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost:3306/jdbc-sample?allowPublicKeyRetrieval=true&useSSL=false",
                        "root","root")
                //Configuração do delimitador de linha
                // .configuration(java.util.Map.of("flyway.plugins.mysql.delimiter", "$$"))
                .baselineOnMigrate(true) // ESSA LINHA RESOLVE O ERRO ATUAL!
                .load();

        // Esta linha vai atualizar os checksums antigos no banco com os teus novos arquivos locais!
        flyway.repair();

        // Chamar
        flyway.migrate();

        System.out.println("Migrations executadas com sucesso!");
        System.out.println("------------------------------------------");

        // System.out.println("Inserindo um registro:");
        // var insert = new EmployeeEntity();
        // insert.setName("Marina");
        // insert.setSalary(new BigDecimal("3500"));
        // insert.setBirthday(OffsetDateTime.now().minusYears(32));
        // System.out.println(insert);
        // // employeeDAO.insertWithProcedure(insert);
        // employeeDAO.insert(insert);
        //
        // System.out.println(insert);
        // System.out.println("------------------------------");

        /*
        System.out.println("Imprimindo todos os registros:");
        employeeDAO.findAll().forEach(System.out::println);
        System.out.println("------------------------------");
        */

        /*
        System.out.println("Imprimindo o registro com id = 1:");
        System.out.println(employeeDAO.findById(1));
        System.out.println("------------------------------");
        */

        // System.out.println("Atualizando o registro com id = 3:");
        // var update = new EmployeeEntity();
        // update.setId(insert.getId());
        // update.setName("Fiona");
        // update.setSalary(new BigDecimal("2000"));
        // update.setBirthday(OffsetDateTime.now().minusYears(28).minusDays(3));
        // employeeDAO.update(update);
        // System.out.println(update);
        // System.out.println("------------------------------");

        // System.out.println("Excluindo o registro com id = 1:");
        // employeeDAO.delete(insert.getId());
        // System.out.println("------------------------------");

        // employeeAuditDAO.findAll().forEach(System.out::println);

        //Montar lista de colaboradores
        // var entities = Stream.generate(() -> {
        //     var employee = new EmployeeEntity();
        //     employee.setName(faker.name().fullName());
        //     employee.setSalary(new BigDecimal(faker.number().digits(4)));
        //     employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.now(), UTC));
        //     return employee;
        // })
        //         //Com limite de 10000
        //         .limit(10000).toList();
        //
        // employeeDAO.insert(entities);

        // System.out.println("Inserindo um registro:");
        // var employee = new EmployeeEntity();
        // employee.setName("Fiona");
        // employee.setSalary(new BigDecimal("3000"));
        // employee.setBirthday(OffsetDateTime.now().minusYears(30));
        // System.out.println(employee);
        // // employeeDAO.insertWithProcedure(insert);
        // employeeDAO.insert(employee);
        //
        // System.out.println(employee);
        // System.out.println("------------------------------");


        // Conectando o contato ao colaborador
        // var contact = new ContactEntity();
        // contact.setDescription("marinas@marina.com");
        // contact.setType("Email");
        // contact.setEmployee(employee);
        // contactDAO.insert(contact);

        // Imprimindo o registro com id = 1:
        // System.out.println(employeeDAO.findById(1));

        // System.out.println("Inserindo um registro:");
        // var employee = new EmployeeEntity();
        // employee.setName("Fiona");
        // employee.setSalary(new BigDecimal("3000"));
        // employee.setBirthday(OffsetDateTime.now().minusYears(30));
        // System.out.println(employee);
        // // employeeDAO.insertWithProcedure(insert);
        // employeeDAO.insert(employee);
        //
        // System.out.println(employee);
        // System.out.println("------------------------------");
        //
        //
        // //Conectando o contato ao colaborador
        // var contact1 = new ContactEntity();
        // contact1.setDescription("marinas@marina.com");
        // contact1.setType("Email");
        // contact1.setEmployee(employee);
        // contactDAO.insert(contact1);
        //
        // var contact2 = new ContactEntity();
        // contact2.setDescription("65987854");
        // contact2.setType("Telefone");
        // contact2.setEmployee(employee);
        // contactDAO.insert(contact2);

        // System.out.println(employeeDAO.findById(1));

        // Imprimir todos os registros:
        // employeeDAO.findAll().forEach(System.out::println);

        //Montar lista de colaboradores
        // var entities = Stream.generate(() -> {
        //     var employee = new EmployeeEntity();
        //     employee.setName(faker.name().fullName());
        //     employee.setSalary(new BigDecimal(faker.number().digits(4)));
        //     employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.now(), UTC));
        //
        //     //Setar os módulos
        //     employee.setModules(new ArrayList<>());
        //
        //     // Setar a quantidade de módulos
        //     var moduleAmount = faker.number().numberBetween(1, 4);
        //
        //     for (int i = 0; i < moduleAmount; i++) {
        //         var module = new ModuleEntity();
        //         module.setId(i + 1);
        //         employee.getModules().add(module);
        //     }
        //     return employee;
        // })
        //         //Com limite de 3
        //         .limit(3).toList();
        //
        // entities.forEach(employeeDAO::insert);

        // moduleDAO.findAll().forEach(System.out::println);
    }
}
