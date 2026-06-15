package br.com.dio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//Indica em que momento vai funcionar
@Retention(RetentionPolicy.RUNTIME)

//Definir o alvo (lugares que pode ser aplicada)
@Target(ElementType.TYPE)

public @interface SerializerType {

    //Propriedades
    //Opcionais

    //ENUM
    FieldFormatEnum fieldFormat() default FieldFormatEnum.CAMEL_CASE;

    boolean prettify() default true;
}
