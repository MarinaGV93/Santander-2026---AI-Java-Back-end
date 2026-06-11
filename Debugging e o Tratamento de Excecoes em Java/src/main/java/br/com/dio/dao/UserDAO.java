package br.com.dio.dao;

import br.com.dio.exception.EmptyStorageException;
import br.com.dio.exception.UserNotFoundException;
import br.com.dio.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    //Contador
    private long nextId = 1;

    // Lista de dados
    private final List<UserModel> models = new ArrayList<>();

    //Metodos
    public UserModel save(final UserModel model){
        model.setId(nextId++);
        models.add(model);
        return model;
    }

    public UserModel update(final UserModel model){
        var toUpdate = findById(model.getId());
        models.remove(toUpdate);
        models.add(model);
        return model;
    }

    public void delete(final long id){
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel findById(final long id){
        verifyStorage();
        var message = String.format("Usuário não encontrado com o id %s", id);
        return models.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(message));
    }

    public List<UserModel> findAll(){
        List<UserModel> result = null;

        //Tratar exceção
        try{
            verifyStorage();
            result = models;
        }catch (EmptyStorageException ex){
            ex.printStackTrace();
            result = new ArrayList<>();
        }
            return result;
    }

    private void verifyStorage(){
        if(models.isEmpty()){
            throw new EmptyStorageException("O armazenamento está vazio");
        }
    }
}
