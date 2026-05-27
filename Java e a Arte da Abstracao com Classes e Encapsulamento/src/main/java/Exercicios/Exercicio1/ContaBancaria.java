package Exercicios.Exercicio1;

public class ContaBancaria {

    private double saldo;
    private double limiteChequeEspecial;
    private double chequeEspecialUsado;

    // Getters para consulta
    public double getSaldo() {
        return saldo;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public double getChequeEspecialUsado() {
        return chequeEspecialUsado;
    }

    //Verificar se está usando cheque especial
    public boolean estaUsandoChequeEspecial() {
        return chequeEspecialUsado > 0;
    }

    //Construtor
    public ContaBancaria(double depositoInicial){

        //Saldo = deposito inicial
        this.saldo = depositoInicial;

        System.out.println("\nSaldo inicial: " + saldo + "\n");

        //Se deposito incial for menor ou igual à 500,00
        if (depositoInicial <= 500.00){

            //O limite fica 50,00
            limiteChequeEspecial = 50.00;

            System.out.println("Limite do cheque especial: " + limiteChequeEspecial + "\n");

            //Se nao
        } else {

            //O chegue especial tem 50% do valor depositado
            limiteChequeEspecial = depositoInicial * 0.5;

            System.out.println("Limite do cheque especial: " + limiteChequeEspecial + "\n");
        }

        //Limite é de 0
        this.limiteChequeEspecial = 0;

        System.out.println("Limite do cheque especial: " + limiteChequeEspecial + "\n");
    }

    //Depositar
    public void depositar(double valor){
        //Se o cheque especial for maior que 0
        if (this.chequeEspecialUsado > 0){

            // A taxa é de 20% do valor do cheque especial
            double taxa = this.chequeEspecialUsado * 0.20;

            System.out.println("Cobrança de taxa " + taxa + "\n");

            // Subtrai a taxa do valor
            valor -= taxa;

            System.out.println("Saldo atual: " + valor + "\n");
        }

        // Repor o cheque especial
        // Se o cheque especial for maior que 0
        if (chequeEspecialUsado > 0){

            System.out.println("Repor o cheque especial\n");

            // Se o valor for maior ou igual ao cheque especial
            if (valor >= this.chequeEspecialUsado){

                // Subtrai o cheque especial do valor
                valor -= this.chequeEspecialUsado;

                // Zera o cheque especial
                this.chequeEspecialUsado = 0;

                System.out.println("Cheque especial reposto: " + this.chequeEspecialUsado + "\n");

            // Se o valor for menor que o cheque especial
            } else {

                // Subtrai o valor do cheque especial
                this.chequeEspecialUsado -= valor;

                // Zera o valor
                valor = 0;

                System.out.println("Cheque especial reposto: " + this.chequeEspecialUsado + "\n");
            }
        }

        // Adiciona o valor ao saldo
        this.saldo += valor;
        System.out.println("Saldo atual: " + this.saldo + "\n");
    }


    public boolean sacar(double valor){
        var disponivelTotal = this.saldo + (this.limiteChequeEspecial - this.chequeEspecialUsado);

        if (valor <= disponivelTotal){
            if (valor <= this.saldo){
                this.saldo -= valor;
                System.out.println("Saldo atual: " + this.saldo + "\n");
            } else {
                double resto = valor - this.saldo;
                this.saldo = 0;
                this.chequeEspecialUsado += resto;
                System.out.println("Saldo atual: " + this.saldo + "\n");
            }
            System.out.println("Saque realizado com sucesso\n");
            return true;
        }
        System.out.println("Saldo insuficiente\n");
        return false;
    }


}
