package br.com.dio.dao;

import java.math.BigDecimal;

public class MoneyDAO {

    private static BigDecimal money = BigDecimal.ZERO;

    //Adicionar dinheiro no caixa
    public BigDecimal add(final BigDecimal money){
        this.money = this.money.add(money);
        return this.money;
    }

    //Pegar o valor do caixa
    public static BigDecimal getMoney() {
        return money;
    }
}
