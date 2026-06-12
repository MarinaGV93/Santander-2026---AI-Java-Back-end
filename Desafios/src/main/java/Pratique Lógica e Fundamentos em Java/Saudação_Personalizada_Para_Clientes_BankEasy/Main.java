/*
Você foi contratado como desenvolvedor júnior para a equipe de um novo app bancário digital chamado BankEasy. Seu primeiro desafio é implementar uma funcionalidade essencial: o sistema de saudação personalizada para clientes. Toda vez que um usuário faz login, o app deve exibir uma mensagem de boas-vindas com base no nome do cliente e no tipo de conta que ele possui. O objetivo é garantir que cada cliente se sinta valorizado ao acessar o aplicativo.

Sua tarefa é criar um programa que receba o nome do cliente e o tipo de conta (corrente, poupanca ou investimento) e retorne uma mensagem de saudação personalizada. Se o tipo de conta informado não for reconhecido, o sistema deve retornar uma mensagem de erro amigável. Não utilize bibliotecas externas, apenas recursos básicos da linguagem.

Entrada
Uma única linha contendo dois valores separados por espaço: o nome do cliente (sem espaços) e o tipo de conta (corrente, poupanca ou investimento). O nome pode conter letras e números.

Saída
Uma linha com a mensagem de saudação no seguinte formato: "Bem-vindo(a), [nome]! Sua conta [tipo] está pronta para uso." Caso o tipo de conta não seja válido, exiba: "Tipo de conta invalido."

Exemplos
A tabela abaixo apresenta exemplos de entrada e saída:

Entrada	Saída
Ana corrente	Bem-vindo(a), Ana! Sua conta corrente esta pronta para uso.
Lucas poupanca	Bem-vindo(a), Lucas! Sua conta poupanca esta pronta para uso.
Joao investimento	Bem-vindo(a), Joao! Sua conta investimento esta pronta para uso.
Maria premium	Tipo de conta invalido.
 */
package Saudação_Personalizada_Para_Clientes_BankEasy;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lê a linha de entrada e separa em nome e tipo de conta
        String input = scanner.nextLine();
        String[] partes = input.split(" ");

        String nome = partes[0];
        String tipoConta = partes[1];

        // TODO: Verifique se o tipo de conta é válido ("corrente", "poupanca" ou "investimento")
        // e imprima a mensagem de saudação personalizada ou a mensagem de erro conforme o caso.
        if (tipoConta.equals("corrente") || tipoConta.equals("poupanca") || tipoConta.equals("investimento")){
            System.out.println("Bem-vindo(a), " + nome + "! Sua conta " + tipoConta + " esta pronta para uso.");
        } else {
            System.out.println("Tipo de conta invalido.");
        }

        scanner.close();
    }
}