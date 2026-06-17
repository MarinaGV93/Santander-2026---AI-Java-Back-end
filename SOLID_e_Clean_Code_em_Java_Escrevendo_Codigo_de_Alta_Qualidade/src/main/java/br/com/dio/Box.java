//Antes de refatorar

package br.com.dio;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.math.RoundingMode.CEILING;

public record Box(long amount, LocalDate validate, BigDecimal price) {

    //Pega o valor unitário de cada cesta
    public BigDecimal unitPrice(){
        return price().divide(BigDecimal.valueOf(amount()),CEILING);
    }
}