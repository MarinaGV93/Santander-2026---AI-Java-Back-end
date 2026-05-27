/*
Criando a primeira Classe

    Classe é como se fosse uma forma
    Tudo que passar por essa forma, terá o mesmo formato


public class Person{
    //Deixar as propriedades com acesso privado
    //final -> Define que ela é uma constante, que é um valor que nao muda, somente na criação do objeto (public Person()...)
    private final String name;
    private int age;

    //Cria para pegar o ano atual
    private int lastYearAgeInc  = OffsetDateTime.now().getYear();

    //Proteger os aceessos das propriedades com getters e setters

    public String getName() {
        return name;
    }

    //Se quiser o nome como somente leitura, tira o set
    public void setName(String name) {
        //Usa o 'this' para que quer acessar a variável, e não o argumento que esta entre parenteses e atribui a ela o 'name' argumento. Não consegue usar no modelo 'static'. Variáveis não são acessiveis por ele, acessa pela classe (Person.)
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    //public void setAge(int age) {
        //this.age = age;
    //}

    //Quando não tem nenhum construtor, o objeto assume que ele vai ter um construtor sem parâmetros
        //public Person(){
        //}

        //OU

        //private Person(){}

    //Criar um metodo para poder dar acesso e liberar o incremento da idade, mas de acorod com as regra: só pode incrementar a idade 1x por ano
    public void incAge(){
        if (this.lastYearAgeInc >= OffsetDateTime.now().getYear()) return;

        this.age +=1;
        this.lastYearAgeInc = OffsetDateTime.now().getYear();
    }

    public Person(String name){
        this.name = name;

        //Ser iniciada com algum número, nao pode ser alterada de fora e apaga o setAge
        this.age = 1;
    }

    //OU

    //public Person(String name, int age){
        //this.name = name;
        //this.age = age;
    //}
}

public class Main(){

    //Static -> O método nao pertence exatamente a sua instância, vai usar a classe para acessar. Só consegue usar dentro de métodos 'static', coisas estáticas ou coisas criadas no próprio contesto
    public static void main(String[] args) {

        //Declarar o objeto Person
        var male = new Person();

        male.setName("João");
        male.setAge(12);

        var female = new Person();
        female.setName("Maria");
        female.setAge(10);

        System.out.println("Male name: " + male.getName() + " age: " + male.getAge());
        System.out.println("Female name: " + female.getName() + " age: " + female.getAge());
    }
}
 */

/*
Trabalhando com Records

    É uma classe mutável, que cria uma vez e os valores não podem ser alterados
    Somente para leitura
    Pode ter mais de 1 construtor mas tem que ter obrigatoriamente a chamar o construtor default do record
 */

public record Person(
        //Colocar aqui os atributos
        //Todos os atributos criados aqui são privados e é usado no construtor dele
        String name, int age
){
    //Não pode definir propriedades sem a palavra 'static' (atributos estaticos)
    //private static String name;

    //Contrutor compacto
    //É o mesmo que o construtor sem argumento
    //É rodado depois do 'var person...' do main
    public Person{
        System.out.println(name);
        System.out.println(age);
    }


    //Pode ter um construtor secundário
    public Person(String name){
        this(name,  1);
    }
}

public class Main(){
    public static void main(String[] args) {
        var person = new Person("Marina", 12);

        //Le o valor de um atributo por meio de método
        System.out.println(person.name());

        //Reaproveitar os valores que tem no record para criar um objeto
        var newPerson = new Person(person.name(), 13)
    }
}