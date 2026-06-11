package br.com.dio.exception;

public class UserNotFoundException extends RuntimeException{

    //Construtor
    public UserNotFoundException(String message) {
        super(message);
    }
}
