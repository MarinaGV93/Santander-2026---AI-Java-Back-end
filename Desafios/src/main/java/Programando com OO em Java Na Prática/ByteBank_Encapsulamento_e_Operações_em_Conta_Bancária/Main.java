/*
Em um futuro próximo, o Banco Digital ByteBank revolucionou o gerenciamento de contas bancárias ao permitir que clientes controlem suas finanças de forma totalmente digital. Para garantir a segurança e organização dos dados, o ByteBank exige que todas as operações sejam realizadas por meio de métodos encapsulados, protegendo as informações sensíveis dos usuários. Você foi contratado para implementar uma parte fundamental desse sistema: a classe Conta, responsável por armazenar o saldo de um cliente e permitir operações básicas de depósito e saque, sempre respeitando as regras de encapsulamento.

Implemente uma classe chamada Conta que possua um saldo inicial definido no momento da criação. A classe deve disponibilizar métodos para depositar e sacar valores, garantindo que o saldo nunca fique negativo. Se uma tentativa de saque exceder o saldo disponível, a operação deve ser ignorada. Após uma sequência de operações, o programa deve exibir o saldo final da conta. Utilize encapsulamento para proteger o atributo saldo, permitindo acesso apenas por meio dos métodos definidos.

Entrada
A primeira linha contém um inteiro representando o saldo inicial da conta. As linhas seguintes contêm comandos no formato "depositar X" ou "sacar X", onde X é um inteiro positivo. O processamento termina ao receber o comando "fim".

Saída
Imprima uma única linha contendo o saldo final da conta após todas as operações.

Exemplos
A tabela abaixo apresenta exemplos de entrada e saída:

Entrada	Saída
100
depositar 50
sacar 30
fim	120
200
sacar 250
depositar 100
fim	300
50
sacar 20
sacar 40
fim	30
0
depositar 10
sacar 5
fim	5
 */

import java.util.Scanner;

// Classe Conta com saldo encapsulado
class Conta {
    private int saldo;

    public Conta(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public void depositar(int valor) {
        if (valor > 0) {
            saldo += valor;
        }
    }

    public void sacar(int valor) {
        // TODO: Permitir saque apenas se houver saldo suficiente
        // Dica: cheque se 'valor' é menor ou igual ao saldo antes de subtrair
        if(valor <= saldo){
            saldo -= valor;
        }
    }

    public int getSaldo() {
        return saldo;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int saldoInicial = Integer.parseInt(scanner.nextLine());
        Conta conta = new Conta(saldoInicial);

        while (true) {
            String comando = scanner.nextLine().trim();
            if (comando.equals("fim")) break;

            String[] partes = comando.split(" ");
            String operacao = partes[0];
            int valor = Integer.parseInt(partes[1]);

            if (operacao.equals("depositar")) {
                conta.depositar(valor);
            } else if (operacao.equals("sacar")) {
                conta.sacar(valor);
            }
        }

        System.out.println(conta.getSaldo());
    }
}