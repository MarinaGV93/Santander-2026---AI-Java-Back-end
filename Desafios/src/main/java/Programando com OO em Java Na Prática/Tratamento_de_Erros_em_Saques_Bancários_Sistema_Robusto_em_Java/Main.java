/*
Você foi contratado como desenvolvedor júnior para o Banco Futuro, uma instituição que está modernizando seu sistema bancário. Sua primeira tarefa é criar um módulo simples para processar saques em contas correntes. O sistema deve ser robusto: ao tentar sacar um valor inválido (como um número negativo, zero ou maior que o saldo disponível), o sistema deve identificar o erro e retornar uma mensagem apropriada, sem encerrar o programa abruptamente. Seu desafio é implementar o tratamento de exceções para garantir que o sistema responda corretamente a cada situação, ajudando o banco a evitar transtornos para os clientes e a manter a integridade das operações.

Implemente um programa que leia dois valores: o saldo atual da conta e o valor do saque solicitado. Se o saque for possível, exiba o novo saldo. Caso contrário, exiba uma mensagem de erro específica para cada situação: "Valor invalido" para saques negativos ou zero, e "Saldo insuficiente" para saques acima do saldo. Utilize o tratamento de exceções para lidar com possíveis erros de entrada, como valores não numéricos, exibindo "Entrada invalida" nesses casos.

Entrada
Duas linhas, cada uma contendo um valor. A primeira linha representa o saldo atual (um número inteiro). A segunda linha representa o valor do saque solicitado (um número inteiro). Os valores podem ser positivos, zero, negativos ou não numéricos.

Saída
Se o saque for realizado com sucesso, exiba o novo saldo (um número inteiro). Caso contrário, exiba uma das mensagens: "Valor invalido", "Saldo insuficiente" ou "Entrada invalida", conforme o caso.

Exemplos
A tabela abaixo apresenta exemplos de entrada e saída:

Entrada	Saída
1000
200	800
500
700	Saldo insuficiente
300
-50	Valor invalido
abc
100	Entrada invalida
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            // Leitura dos valores de entrada
            String saldoInput = scanner.nextLine();
            String saqueInput = scanner.nextLine();

            int saldo = Integer.parseInt(saldoInput.trim());
            int valorSaque = Integer.parseInt(saqueInput.trim());

            // TODO: Verifique se o valor do saque é menor ou igual a zero e imprima "Valor invalido" se for o caso

            // Dica: Use uma estrutura condicional para validar o valor do saque antes de prosseguir

            if(valorSaque <= 0){
                System.out.println("Valor invalido");
                return;
            }

            // Verifica se há saldo suficiente para o saque
            if (valorSaque > saldo) {
                System.out.println("Saldo insuficiente");
                return;
            }

            // Saque realizado com sucesso
            System.out.println(saldo - valorSaque);

        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida");
        }
    }
}