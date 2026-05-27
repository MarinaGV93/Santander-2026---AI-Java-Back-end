package Exercicios.Exercicio1;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ContaBancaria conta;

    public static void main(String[] args) {
        var option = -1;

        System.out.println("\nBem vindo(a) ao sistema de conta bancaria\n");
        System.out.println("Informe o deposito inicial: ");
        conta = new ContaBancaria(scanner.nextDouble());
        do {
            System.out.println("-----------------------------------");
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Consultar cheque especial");
            System.out.println("3 - Depositar dinheiro");
            System.out.println("4 - Sacar dinheiro");
            System.out.println("5 - Pagar um boleto");
            System.out.println("6 - Verificar se a conta está usando cheque especial");
            System.out.println("0 - Sair");
            System.out.println("-----------------------------------");
            option = scanner.nextInt();

            switch (option){
                case 1 -> consultarSaldo();
                case 2 -> consultarCheque();
                case 3 -> depositar();
                case 4 -> sacar();
                case 5 -> pagarBoleto();
                case 6 -> chequeUsado();
                case 0 -> System.exit(0);
                break;
                default -> System.out.println("Opção inválida");
            }
        }while (true);
    }

    private static void depositar() {
        System.out.println("Valor a depositar: R$");
        conta.depositar(scanner.nextDouble());
    }

    private static void pagarBoleto() {
        if (conta.getChequeEspecialUsado() > 0) {
            System.out.println("ALERTA: A sua conta está a utilizar o Cheque Especial!");
        } else {
            System.out.println("A sua conta está saudável (Saldo Real).");
        }
    }

    private static void sacar() {
        System.out.print("Valor do Saque/Boleto: R$ ");
        if (conta.sacar(scanner.nextDouble())) {
            System.out.println("Transação aprovada!");
        } else {
            System.out.println("ERRO: Saldo e Limite insuficientes.");
        }
    }

    private static void consultarSaldo(){
        System.out.printf("Saldo atual: R$ %.2f\n", conta.getSaldo());
    }

    private static void consultarCheque(){
        System.out.printf("Limite de Cheque Especial: R$ %.2f\n", conta.getLimiteChequeEspecial());
        System.out.printf("Valor em uso no momento: R$ %.2f\n", conta.getChequeEspecialUsado()
        );
    }

    private static void chequeUsado() {
        if (conta.estaUsandoChequeEspecial()) {
            System.out.println("SIM! Você está utilizando o crédito do cheque especial.");
        } else {
            System.out.println("NÃO. Você está operando apenas com seu saldo real.");
        }
    }
}
