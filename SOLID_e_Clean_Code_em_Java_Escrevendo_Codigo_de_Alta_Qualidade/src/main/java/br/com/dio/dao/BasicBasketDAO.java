//Refatorado

package br.com.dio.dao;

import br.com.dio.BasicBasket;
import br.com.dio.model.StockInfo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BasicBasketDAO {

    private final List<BasicBasket> stock = new ArrayList<>();

    //Adicionar cestas
    public List<BasicBasket> addBatch(final List<BasicBasket> baskets){
        stock.addAll(baskets);
        return stock;
    }

    //Reduzir da cesta
    public List<BasicBasket> remove(final int amount){

        //Ordena o estoque por preço (menor para maior)
        stock.sort(Comparator.comparing(BasicBasket::price));

        //Remove o estoque. Gera uma sublista a partir do indice 0 até o amount (quantidade de cestas)
        return stock.subList(0, amount);
    }

    //Ver o estoque
    public StockInfo getInfo(){
        return new StockInfo(stock.size(), stock.stream().filter(b -> b.validate().isBefore(LocalDate.now())).count());
    }

    //Remover itens vencidos do estoque
    public List<BasicBasket> removeOutOfDate(){
        var outOfDate =  stock.stream().filter(b -> b.validate().isBefore(LocalDate.now())).toList();
        stock.removeAll(outOfDate);
        return outOfDate;
    }
}
