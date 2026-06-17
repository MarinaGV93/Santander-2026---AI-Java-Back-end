//Refatorado

package br.com.dio.service;

import br.com.dio.BasicBasket;
import br.com.dio.Box;
import br.com.dio.dao.BasicBasketDAO;
import br.com.dio.model.StockInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class BasicBasketService {

    private final BasicBasketDAO dao;
    private final MoneyService moneyService;

    public BasicBasketService(BasicBasketDAO dao, MoneyService moneyService) {
        this.dao = dao;
        this.moneyService = moneyService;
    }

    public List<BasicBasket> receive(final Box box){

        //Pega o valor unitário de cada cesta do box
        var unitAmount = box.unitPrice();

        //Adiciona 20% de lucro
        var finalPrice = unitAmount.add(unitAmount.multiply(new BigDecimal("0.20")));

        //Montar as cestas
        var baskets =

                //Gera as cestas
                Stream.generate(() -> new BasicBasket(
                        //Usa a data de validade do box
                        box.validate(),
                                //Preço final
                                finalPrice))

                        //Limite de geração será a quantidade do box
                .limit(box.amount())
                .toList();
        return dao.addBatch(baskets);
    }

    //Vender cestas
    public BigDecimal sold(final int amount){
        var toSold = dao.remove(amount);

        //Gerar o valor da venda
        var total = toSold.stream()

                //Mapear o preço de cada cesta
                .map(BasicBasket::price)

                //Somar todos os preços
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        moneyService.add(total);
        return total;
    }

    //Ver o estoque
    public StockInfo getInfo(){
        return dao.getInfo();
    }

    //Remover itens vencidos do estoque
    public List<BasicBasket> removeOutOfDate(){
        var outOfDate = dao.removeOutOfDate();
        var total = outOfDate.stream()

                //Mapear o preço de cada cesta
                .map(BasicBasket::price)

                //Somar todos os preços
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        moneyService.dec(total);
        return outOfDate;
    }
}
