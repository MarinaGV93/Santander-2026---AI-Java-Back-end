package Keyword;

public class Client {
    private static String name;
    private int age;

    private Client(){

    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {

        //Não precisa usar 'this.' porque esta estatica
        name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
