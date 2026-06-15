package br.com.dio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Indica em que momento vai funcionar
//RUNTIME - Armazena informações sobre o codigo durante a execução
@Retention(RetentionPolicy.RUNTIME)

//Definir o alvo (lugares que pode ser aplicada)
@Target(ElementType.METHOD)
public @interface SerializerMethod {

    //Se nao informar o nome do JSON, o nome do atributo sera o nome do metodo
    String value() default "";
}
