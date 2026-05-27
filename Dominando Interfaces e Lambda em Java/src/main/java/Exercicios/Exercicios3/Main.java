package Exercicios.Exercicios3;

import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int option;
        GeometricForm geometricForm = null;

        while (true){
            System.out.println("Escolha a forma geométrica para calcular a área:");
            System.out.println("1 - Quadrado");
            System.out.println("2 - Retângulo");
            System.out.println("3 - Círculo");
            System.out.println("4 - Sair");
            option = scanner.nextInt();

            if (option == 1) {
                geometricForm = createSquare();
            } else if (option == 2) {
                geometricForm = createRrectangle();
            } else if (option == 3) {
                geometricForm = createCircle();
            } else if (option == 4) {
                break;
            } else {
                System.out.println("Opção inválida");
                continue;
            }
            System.out.println("\nO resultado do cálculo da área foi de " + geometricForm.getArea());

            // switch (option){
            //     case 1 -> createSquare();
            //     case 2 -> createRrectangle();
            //     case 3 -> createCircle();
            // }
        }
    }

    private static GeometricForm createSquare(){
        System.out.println("Informe os tamanhos dos lados: ");
        var side = scanner.nextDouble();
        return new Square(side);
    }

    private static GeometricForm createRrectangle(){
        System.out.println("Informe a base: ");
        var base = scanner.nextDouble();
        System.out.println("Informe a altura: ");
        var height = scanner.nextDouble();
        return new Rectangle(height, base);
    }

    private static GeometricForm createCircle(){
        System.out.println("Informe o raio: ");
        var radius = scanner.nextDouble();
        return new Circle(radius);
    }
}
