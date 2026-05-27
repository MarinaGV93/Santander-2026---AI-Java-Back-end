package Exercicios.Exercicio3;

import java.util.Scanner;

public class Main {

    private final static Scanner scanner = new Scanner(System.in);
    private final static PetMachine petMachine = new PetMachine();

    public static void main(String[] args) {
        //Colocar um delimitador para aceitar espaço no nome do pet
        scanner.useDelimiter("\\n");
        var option = -1;

        //Criar menu
        do {
            System.out.println("------------------------------------");
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - Dar banho no pet");
            System.out.println("2 - Abastecer com água;");
            System.out.println("3 - Abastecer com shampoo;");
            System.out.println("4 - Verificar nível de água;");
            System.out.println("5 - Verificar nível de shampoo;");
            System.out.println("6 - Verificar se tem pet no banho;");
            System.out.println("7 - Colocar pet na maquina;");
            System.out.println("8 - Retirar pet da máquina;");
            System.out.println("9 - Limpar maquina.");
            System.out.println("0 - Sair");
            System.out.println("------------------------------------");
            option = scanner.nextInt();

            switch (option){
                case 1 -> petMachine.takeAShower();
                case 2 -> setWater();
                case 3 -> setShampoo();
                case 4 -> verifyWater();
                case 5 ->
                        //Criar um metodo com essa parte
                        //{
                            //var amount = petMachine.getShampoo();
                            //System.out.println("A máquina está no momento com " + //amount +  " litro(s) de shampoo");
                        //}
                        verifyShampoo();

                //Extrair um metodo do 'hasPet' (CTRL + ALT + M)
                case 6 -> checkIfHasPetInMachine();
                case 7 -> setPetInPetMachine();
                case 8 -> petMachine.removePet();
                case 9 -> petMachine.wash();
                case 0 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }
        }while (true);
    }

    private static void setWater(){
        System.out.println("Tentando colocar água na máquina\n");
        petMachine.addWater();
    }

    private static void setShampoo(){
        System.out.println("Tentando colocar shampoo na máquina\n");
        petMachine.addShampoo();
    }

    private static void verifyWater() {
        var amount = petMachine.getWater();
        System.out.println("A máquina está no momento com " + amount +  " litro(s) de água\n");
    }

    private static void verifyShampoo() {
        var amount = petMachine.getShampoo();
        System.out.println("A máquina está no momento com " + amount +  " litro(s) de shampoo\n");
    }

    //Criou um mét odo de 'hasPet'
    private static void checkIfHasPetInMachine() {
        var hasPet = petMachine.hasPet();
        System.out.println(hasPet ? "Tem pet na máquina\n" : "Não tem pet na máquina\n");
    }

    //Fazer as chamadas

    //Colocar pet na máquina
    //Para acessar, precisa colocar como 'static'
    public static void setPetInPetMachine(){
        var name = "";

        //Trava o usuário ate nao passar o nome do pet
        while (name == null || name.isEmpty()){
            System.out.println("\nInforme o nome do pet");
            name = scanner.next();
        }
        var pet = new Pet(name);
        petMachine.setPet(pet);
    }
}
