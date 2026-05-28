import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

    //Lista
        //Lista sem tipagem
        //Lista test = new ArrayList();

        //Lista com tipagem
        List<String> test = new ArrayList<>();

        //Adicionar elementos
        test.add("a");
        // test.add(1);
        // test.add(false);

        //Recuperar algum indice
        var a = test.get(0);

    //Array
        //Criar um array definindo um tamanho
        // int[] codes = new int[2];
        // codes[0] = 789;
        // codes[1] = 852;

        //Criar com uma inicialização
        int[] codes1 = {987, 258};

        //Imprimir o tamanho do array
        System.out.println(codes1.length);

        //Imprimir o valor do indice
        System.out.println("\nImprimindo os valores: ");
        System.out.println("Indice 1: " + codes1[0]);
        System.out.println("Indice 2: " + codes1[1]);

        //Aumentar o array criado
        int[] codes2 = {codes1[0], codes1[1], 0};

        //Converter o tipo
        List<Integer> codes3 = new ArrayList<>();
        codes3.add(codes1[0]);
        codes3.add(codes1[1]);

        //Imprimir tudo
        System.out.println("\nImprimindo a lista:");
        codes3.forEach(System.out::println);

        List<User> users = new ArrayList<>();
        var user = new User(1, "João");
        users.add(user);
        users.add(new User(2, "Maria"));
        users.add(new User(3, "Leo"));

        //Imprimir se tem o usuario
        System.out.println("\nContêm na lista? " + users.contains(user));
        System.out.println("\nContêm na lista? " + users.contains(new User(6, "Carlos")));

        //Ele nao acha (se nao tiver o metodo toString) porque ele nao compara objetos, ele compara o endereço de memoria que esta
        System.out.println("\nContêm na lista? " + users.contains(new User(1, "João")));

        System.out.println("\nSão iguais? " + new User(1, "João").equals(new User(1, "João")));

        //Imprimir tamanho
        System.out.println("\nTamanho da lista: " + users.size());

        //Ver se esta vazio
        System.out.println("\nVazio? " + users.isEmpty());

        //Retorna o primeiro valor
        System.out.println("\nPrimeiro valor: " + users.getFirst());

        //Retorna o ultimo valor
        System.out.println("\nÚltimo valor: " + users.getLast());

        //Remove o objeto
        System.out.println("\nRemoveu o objeto? " + users.remove(new User(8, "Léo")));

        //Remover por indica
        System.out.println("\nRemoveu o índice? " + users.remove(1));

        //Limpar a lista
        users.clear();


        //Imprimir lista
        System.out.println("\nLista limpa: " + users);
    }
}
