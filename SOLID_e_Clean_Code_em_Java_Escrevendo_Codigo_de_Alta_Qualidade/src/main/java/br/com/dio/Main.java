//Antes de refatorar

package br.com.dio;

import br.com.dio.dao.BasicBasketDAO;
import br.com.dio.dao.MoneyDAO;
import br.com.dio.service.BasicBasketService;
import br.com.dio.service.MoneyService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    //Adicionar instancia
    private final static MoneyService moneyService = new MoneyService(new MoneyDAO());
    private final static BasicBasketService basicBasketService = new BasicBasketService(new BasicBasketDAO(), moneyService);

    private final static Scanner scanner = new Scanner(System.in);
    private static List<BasicBasket> stock = new ArrayList<>();

    //Colocar no MoneyDAO
    // private static BigDecimal money = BigDecimal.ZERO;

    public static void main(String[] args) {
        System.out.println("Bem vindo ao sistema de armazém");
        System.out.println("Selecione a opção desejada");
        var option = -1;
        while (true){
            System.out.println("1 - Verificar estoque de cesta básica");
            System.out.println("2 - Verificar caixa");
            System.out.println("3 - Receber Cestas");
            System.out.println("4 - Vender Cestas");
            System.out.println("5 - Remover itens vencidos");
            System.out.println("6 - Sair");
            option = scanner.nextInt();
            switch (option){
                case 1 -> checkStock();
                case 2 -> checkMoney();
                case 3 -> receiveItems();
                case 4 -> soldItems();
                case 5 -> removeItemsOutOfDate();
                case 6 -> System.exit(0);

                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void soldItems() {
        System.out.println("Quantas cestas serão vendidas");
        var amount = scanner.nextInt();

        //Colocar no BasicBasketDAO
        // stock.sort(Comparator.comparing(BasicBasket::price));
        // var toSold = stock.subList(0, amount);
        //
        // var value = toSold.stream().map(BasicBasket::price).reduce(BigDecimal.ZERO, BigDecimal::add);
        // money = money.add(value);

        // System.out.printf("O valor da venda é de %s \n", value);

        //Colocar no BasicBasketService
        var total = basicBasketService.sold(amount);
        System.out.printf("O valor da venda é de %s \n", total);

    }

    private static void checkStock(){
        //Colocar no BasicBasketDAO
        // int amount = stock.size();
        // long outOfDate = stock.stream().filter(b -> b.validate().isBefore(LocalDate.now())).count();

        // System.out.printf("Existem %s cestas em estoque, das quais %s estão fora do prazo de validade \n", amount, outOfDate);

        var stockInfo = basicBasketService.getInfo();
        System.out.printf("Existem %s cestas em estoque, das quais %s estão fora do prazo de validade \n", stockInfo.total(), stockInfo.outOfDate());
    }

    private static void checkMoney(){
        // System.out.printf("O caixa no momento é de %s\n", money);

        System.out.printf("O caixa no momento é de %s\n", moneyService.getMoney());
    }

    private static void removeItemsOutOfDate(){
        //Colocar no BasicBasketService
        // var outOfDate = stock.stream().filter(b -> b.validate().isBefore(LocalDate.now())).toList();

        var outOfDate = basicBasketService.removeOutOfDate();

        var lost = outOfDate.stream().map(BasicBasket::price).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.printf("Foram descartadas do estoque %s cestas vencidas, o prejuizo foi de %s \n", outOfDate.size(), lost);
    }

    private static void receiveItems(){
        System.out.println("Informe o valor da entrega");
        var price = scanner.nextBigDecimal();
        System.out.println("Informe a quantidade de cestas da entrega");
        var amount = scanner.nextLong();
        System.out.println("Informe a data de vencimento");
        // var validate = scanner.next();
        var stringValidate = scanner.next();
        // var day = Integer.parseInt(validate.split("/")[0]);
        // var month = Integer.parseInt(validate.split("/")[1]);
        // var year = Integer.parseInt(validate.split("/")[2]);
        // var box = new Box(amount, LocalDate.of(year, month, day), price);

        var validate = toLocalDate(stringValidate);

        var box = new Box(amount, validate, price);

        //Colocar no service
        // var unitPrice = box.price().divide(new BigDecimal(box.amount()), RoundingMode.CEILING);
        // var finalPrice = unitPrice.add(unitPrice.multiply(new BigDecimal("0.20")));
        // var baskets = Stream.generate(() -> new BasicBasket(box.validate(), finalPrice))
        //         .limit(box.amount())
        //         .toList();
        // stock.addAll(baskets);

        //Colocar no service
        var baskets = basicBasketService.receive(box);

        System.out.printf("Foram adicionadas %s cestas ao estoque\n", baskets.size());
    }

    //Metodo para converter String para LocalDate
    private static LocalDate toLocalDate(final String date){
        var splitedDate = Stream.of(date.split("/")).mapToInt(Integer::parseInt).toArray();

        //Retorna o LocalDate
        return LocalDate.of(splitedDate[2], splitedDate[1], splitedDate[0]);
    }
}