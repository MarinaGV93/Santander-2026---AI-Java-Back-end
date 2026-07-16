package br.com.dio.persistence;

import br.com.dio.persistence.entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {
    public void insert(final EmployeeEntity entity){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Cria o statement (comando SQL)
                var statement = connection.createStatement();
        ){
            //Comando SQL de inserção
            var sql = "INSERT INTO employees (name, salary, birthday) VALUES ('" +
                    entity.getName() + "', " +
                    entity.getSalary().toString() + ", '" +
                    formarOffSetDateTime(entity.getBirthday()) + "')";

            //Insere o registro no banco de dados
            statement.executeUpdate(sql);

            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
            System.out.println("------------------------------");

            //Obter o ID inserido
            if (statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void update(final EmployeeEntity entity){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Cria o statement (comando SQL)
                var statement = connection.createStatement();
        ){
            //Comando SQL de inserção
            var sql = "UPDATE employees set " +
                    "name = '" + entity.getName() + "', " +
                    "salary = " + entity.getSalary().toString() + ", " +
                    "birthday = '" + formarOffSetDateTime(entity.getBirthday()) + "' " +
                    "WHERE id = " + entity.getId();

            //Insere o registro no banco de dados
            statement.executeUpdate(sql);

            System.out.printf("Foram afetados %s registros na base de dados", statement.getUpdateCount());
            System.out.println("------------------------------");

            //Obter o ID inserido
            if (statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void delete(final long id){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Cria o statement (comando SQL)
                var statement = connection.createStatement();
        ){
            //Comando SQL de exclusão
            var sql = "DELETE FROM employees WHERE id = " + id;

            //Insere o registro no banco de dados
            statement.executeUpdate(sql);
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public List<EmployeeEntity> findAll(){
        List<EmployeeEntity> entities = new ArrayList<>();

        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Cria o statement (comando SQL)
                var statement = connection.createStatement();
        ){
            //Seleção de todos (*) os registros em ordem alfabética decrescente
            statement.executeQuery("SELECT * FROM employees ORDER BY name DESC");

            //Recupera o resultado
            var resultSet = statement.getResultSet();

            //Seta os valores do resultado
            while (resultSet.next()) {
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("Id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                entities.add(entity);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();

        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Cria o statement (comando SQL)
                var statement = connection.createStatement();
        ){
            //Seleção de todos (*) os registros com uma opção de filtragem
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);

            //Recupera o resultado
            var resultSet = statement.getResultSet();

            //Seta os valores do resultado
            if (resultSet.next()) {
                entity.setId(resultSet.getLong("Id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entity;
    }

    //Formatar a data de nascimento
    private String formarOffSetDateTime(final OffsetDateTime dateTime){
        //Converter para o fuso horário UTC
        var utcDateTime = dateTime.withOffsetSameInstant(UTC);

        return utcDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
