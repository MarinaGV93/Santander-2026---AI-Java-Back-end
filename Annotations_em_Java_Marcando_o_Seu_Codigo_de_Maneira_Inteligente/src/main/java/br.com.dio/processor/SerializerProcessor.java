package br.com.dio.processor;

import br.com.dio.annotation.SerializerMethod;
import br.com.dio.annotation.SerializerType;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SerializerProcessor {

    public String serializer(final Object object) throws IllegalAccessException, InvocationTargetException {
        //Garantir que o objeto não seja nulo
        Objects.requireNonNull(object, "O objeto não pode ser nulo");

        //Classe do objeto que será serializado
        var clazz = object.getClass();

        //Buscar as anotações que tem no objeto para fazer as configurações
        var typeAnnotations = Stream.of(clazz.getAnnotations())
                //Filtrar
                // .filter(SerializerType.class::isInstance)

                //Cast. Mapear
                // .map(SerializerType.class::cast)

                //Filtrar e mapear
                .flatMap(a   ->  (a instanceof SerializerType s)? Stream.of(s) : Stream.empty())

                //Pegar a anotação
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Somente com @SerializerType anotado"));

        //Acessar a variável fieldFormat
        var fieldNameFormatter = typeAnnotations.fieldFormat().getFormat();

        //Acessar a variável prettify
        var prettify = typeAnnotations.prettify();

        //Criar um mapa para pegar os campos
        Map<String, Object> elements = new HashMap<>();

        //Tem acesso a propriedades privadas do codigo e até modificá-las
        for (var field :  clazz.getDeclaredFields()){

            //Tornar acessível
            field.setAccessible(true);
            elements.put(field.getName(), field.get(object));
        }

        //Pegar os metodos anotados
        var annotatedMethods = Stream.of(object.getClass().getMethods())
                //Ignorar metodos que nao estao anotados
                .filter(m -> Stream.of(m.getAnnotations())
                    .anyMatch(a -> a.annotationType().equals(SerializerMethod.class)))
                .toList();

        for (var method: annotatedMethods){
            method.setAccessible(true);
            var customName = method.getAnnotation(SerializerMethod.class).value();

            //Se o nome for vazio, usa o nome do metodo
            elements.put(customName.isBlank() ? method.getName() : customName, method.invoke(object));
        }

        //Montar o JSON

        //Pega os campos e os valores do mapa e transforma em uma string no formato JSON
        var jsonFields =  elements.entrySet().stream()
                .map(e -> String.format(
                        "    \"%s\":%s",

                        //Aplica o formatador
                        fieldNameFormatter.apply(e.getKey()),

                        //Pega o valor
                        formatValue(e.getValue())
                ))
                //Junta os campos
                .collect(Collectors.joining(String.format(",%s", System.lineSeparator())));

        //Montar o JSON
        var json = String.format("{%s%s%s}", System.lineSeparator(), jsonFields, System.lineSeparator());

        //Verifica se o prettify é true, se for, retorna o json, caso contrário, substitui as quebras de linha por vazio e retorna o json em uma linha
        return prettify ? json : json.replaceAll(System.lineSeparator(), "")

                //Substitui os espaços por vazio
                .replaceAll(" ","");
    }

    private String formatValue(final Object value){

        //Se o objeto for uma instancia de string, passa o valor entre aspas, caso contrário, retorna o valor como string
        return value instanceof String s ?
                String.format("\"%s\"", s) :
                value.toString();
    }
}
