package br.com.dio.persistence;

import java.sql.SQLException;

public class AccessDAO {

    public void insert(final long employeeId, final long moduleId){
        // Teste de conexão
        try(

                //Cria a conexão
                var connection = ConnectionUtil.getConnection();

                //Preparando o comando
                var statement = connection.prepareStatement("INSERT INTO accesses (employee_id, module_id) VALUES (?, ?)");
        ){

            // Setando os valores
            statement.setLong(1, employeeId);
            statement.setLong(2, moduleId);

            statement.executeUpdate();

        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
