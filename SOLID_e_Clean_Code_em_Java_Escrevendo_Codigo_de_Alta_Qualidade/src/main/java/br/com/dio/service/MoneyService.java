package br.com.dio.service;

import br.com.dio.dao.MoneyDAO;

import java.math.BigDecimal;

public class MoneyService {

    private final MoneyDAO dao;

    public MoneyService(MoneyDAO dao) {
        this.dao = dao;
    }

    //Adicionar dinheiro no caixa
    public BigDecimal add(final BigDecimal money){
        return dao.add(money);
    }

    //Subtrair dinheiro do caixa
    public BigDecimal dec(final BigDecimal money){
        return dao.add(money.multiply(new BigDecimal("-1")));
    }

    //Pegar o valor do caixa
    public BigDecimal getMoney() {
        return dao.getMoney();
    }
}
