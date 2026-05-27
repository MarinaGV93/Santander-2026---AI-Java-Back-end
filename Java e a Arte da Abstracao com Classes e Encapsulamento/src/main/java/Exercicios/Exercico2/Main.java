//Painel do motorista

package Exercicios.Exercico2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        Carro meuCarro = new Carro();

        int opcao = -1;

        do {
            System.out.println("-----------------------------------");
            System.out.println("Escolha uma das opções:");
            System.out.println("1 - Ligar o carro");
            System.out.println("2 - Desligar o carro");
            System.out.println("3 - Acelerar");
            System.out.println("4 - Diminuir velocidade");
            System.out.println("5 - Virar para esquerda");
            System.out.println("6 - Virar para direita");
            System.out.println("7 - Verificar velocidade");
            System.out.println("8 - Trocar a marcha");
            System.out.println("9 - Sair");
            System.out.println("-----------------------------------");
            opcao = scanner.nextInt();

            switch (opcao){
                case 1 -> meuCarro.ligar();
                case 2 -> meuCarro.desligar();
                case 3 -> meuCarro.acelerar();
                case 4 -> meuCarro.desacelerar();
                case 5 -> meuCarro.virar("Esquerda");
                case 6 -> meuCarro.virar("Direita");
                case 7 -> System.out.println("Velocidade atual: " + meuCarro.getVelocidade() + " km/h");
                case 8 -> trocarMarcha(meuCarro, scanner);
                case 9 -> System.exit(0);
                default -> System.out.println("Opção inválida");
            }

        }while (true);
    }

    private static void trocarMarcha(Carro meuCarro, Scanner scanner) {
        System.out.println("Para qual marcha (0-6)?");
        meuCarro.trocarMarcha(scanner.nextInt());
    };
}
