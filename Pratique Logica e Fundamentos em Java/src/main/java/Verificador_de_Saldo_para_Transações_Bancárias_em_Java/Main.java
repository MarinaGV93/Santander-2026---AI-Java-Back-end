/*
Você foi contratado como desenvolvedor júnior em um banco digital inovador. Sua primeira tarefa é criar uma funcionalidade simples, porém essencial: um verificador de saldo. Clientes do banco frequentemente consultam seus saldos para saber se podem realizar compras ou transferências. Para garantir uma experiência ágil, o sistema precisa informar rapidamente se o saldo é suficiente para uma determinada operação. Sua missão é implementar um programa que receba o saldo atual da conta e o valor de uma transação, e informe se a transação pode ser realizada ou não.

Implemente um programa que leia dois valores inteiros: o saldo disponível na conta e o valor da transação desejada. O programa deve verificar se o saldo é maior ou igual ao valor da transação. Se for suficiente, imprima "Transacao aprovada". Caso contrário, imprima "Saldo insuficiente". Considere que os valores sempre serão inteiros e não negativos. Não utilize bibliotecas externas, apenas recursos padrão da linguagem.

Entrada
Dois números inteiros separados por espaço, representando respectivamente o saldo disponível e o valor da transação. Ambos os valores são maiores ou iguais a zero.

Saída
Uma única linha contendo "Transacao aprovada" se o saldo for suficiente para a transação, ou "Saldo insuficiente" caso contrário.

Exemplos
A tabela abaixo apresenta exemplos de entrada e saída:

Entrada	Saída
100 50	Transacao aprovada
30 40	Saldo insuficiente
0 0	Transacao aprovada
75 75	Transacao aprovada
 */

package Verificador_de_Saldo_para_Transações_Bancárias_em_Java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lê os dois valores inteiros da entrada (saldo e valor da transação)
        int saldo = scanner.nextInt();
        int valorTransacao = scanner.nextInt();

        // TODO: Verifique se o saldo é suficiente e imprima a mensagem apropriada
        if (saldo >= valorTransacao) {
            System.out.println("Transacao aprovada");
        } else {
            System.out.println("Saldo insuficiente");
        }

        scanner.close();
    }
}
