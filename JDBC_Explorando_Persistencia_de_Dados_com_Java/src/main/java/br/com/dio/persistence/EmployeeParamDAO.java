package br.com.dio.persistence;

import br.com.dio.persistence.entity.ContactEntity;
import br.com.dio.persistence.entity.EmployeeEntity;
import br.com.dio.persistence.entity.ModuleEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;
import static java.util.TimeZone.LONG;

public class EmployeeParamDAO {

    private final ContactDAO contactDAO = new ContactDAO();

    private final AccessDAO accessesDAO = new AccessDAO();

    public void insert(final EmployeeEntity entity){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Preparando o comando
                var statement = connection.prepareStatement("INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)");
        ){

            // Setando os valores
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));

            statement.executeUpdate();

            //Obter o ID inserido
            if (statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());

                // Inserir os acessos do colaborador
                entity.getModules().stream().map(ModuleEntity::getId).forEach(m -> accessesDAO.insert(entity.getId(), m));
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insertWithProcedure(final EmployeeEntity entity){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Preparando o comando
                var statement = connection.prepareCall("CALL prc_insert_employee(?, ?, ?, ?);"
                )
        ){
            // Registrar o parâmetro de saída (id)
            statement.registerOutParameter(1,  LONG);

            //Parametros de entrada
            statement.setString(2, entity.getName());
            statement.setBigDecimal(3, entity.getSalary());
            statement.setTimestamp(4, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));

            statement.execute();

            entity.setId(statement.getLong(1));

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void insert(final List<EmployeeEntity> entities){
        // Teste de conexão
        try(
                //Cria a conexão
                var connection = ConnectionUtil.getConnection()
        ){
            var sql = "INSERT INTO employees (name, salary, birthday) VALUES (?, ?, ?)";
            try(
                    //Preparando o comando
                    var statement = connection.prepareStatement(sql)
            ){
                // Desabilitar o auto commit. Permite que o commit seja feito manualmente. O processo de commit é feito no final do bloco. O padrão é true.
                connection.setAutoCommit(false);

                // Executar os comandos em lote
                for (int i = 0; i < entities.size(); i++) {

                    // Setando os valores
                    statement.setString(1, entities.get(i).getName());
                    statement.setBigDecimal(2, entities.get(i).getSalary());
                    var timestamp = Timestamp.valueOf(entities.get(i).getBirthday().atZoneSameInstant(UTC).toLocalDateTime());
                    statement.setTimestamp(3, timestamp);

                    //Adiciona os parametros para uma execução em lote
                    statement.addBatch();

                    //Se o número de registros for múltiplo de 1000 ou for o último registro, executa os lotes adicionados
                    if (i % 1000 == 0 || i == entities.size() - 1){
                        statement.executeBatch();
                    }

                    //Se o número de registros for 8000, lança uma exceção
                    if (i == 8000){
                        throw new SQLException();
                    }
                }

                //Confirmar a operação
                connection.commit();
            }catch (SQLException ex){
                // Reverter a transação caso falhe
                connection.rollback();
                ex.printStackTrace();
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void update(final EmployeeEntity entity){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                var statement = connection.prepareStatement(
                        "UPDATE employees set name = ?, salary = ?, birthday = ? WHERE id = ?"
                );
        ){
            statement.setString(1, entity.getName());
            statement.setBigDecimal(2, entity.getSalary());
            statement.setTimestamp(3, Timestamp.valueOf(entity.getBirthday().atZoneSameInstant(UTC).toLocalDateTime()));

            statement.setLong(4, entity.getId());

            //Insere o registro no banco de dados
            statement.executeUpdate();

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
                var statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?");
        ){
            statement.setLong(1, id);

            //Insere o registro no banco de dados
            statement.executeUpdate();
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

                // Trazer os contatos do colaborador
                entity.setContacts(contactDAO.findByEmployeeId(resultSet.getLong("id")));

                entities.add(entity);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();

        var sql = "SELECT e.id as employee_id, " +
                "e.name, " +
                "e.salary, " +
                "e.birthday, " +
                "c.id as contact_id, " +
                "c.description, " +
                "c.type " +
                "FROM employees e " +
                "LEFT JOIN contacts c " +
                "ON c.employee_id = e.id " +
                "WHERE e.id = ?";

        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                var statement = connection.prepareStatement(sql);
        ){
            statement.setLong(1, id);

            //Executa o comando
            statement.executeQuery();

            //Recupera o resultado
            var resultSet = statement.getResultSet();

            //Seta os valores do resultado
            if (resultSet.next()) {
                entity.setId(resultSet.getLong("employee_id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));

                //Seta o contato
                // entity.setContact(new ContactEntity());
                // entity.getContact().setId(resultSet.getLong("contact_id"));
                // entity.getContact().setDescription(resultSet.getString("description"));
                // entity.getContact().setType(resultSet.getString("type"));

                //Instanciar a lista de contatos
                entity.setContacts(new ArrayList<>());

                //Trazer os contatos do colaborador
                do{
                    var contact = new ContactEntity();

                    //Seta o contato
                    contact.setId(resultSet.getLong("contact_id"));
                    contact.setDescription(resultSet.getString("description"));
                    contact.setType(resultSet.getString("type"));

                    //Adiciona o contato na lista
                    entity.getContacts().add(contact);
                }while(resultSet.next());
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
