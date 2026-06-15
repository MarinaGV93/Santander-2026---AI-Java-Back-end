package br.com.dio;

import br.com.dio.model.Person;
import br.com.dio.model.User;
import br.com.dio.processor.SerializerProcessor;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        var processor = new SerializerProcessor();
        System.out.println(processor.serializer(new Person(1, "Marina", 32)));
        System.out.println(processor.serializer(new User(2, "Marina Golao Vale", 32, 1000)));
    }
}