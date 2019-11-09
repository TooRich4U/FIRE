package com.pixelnos.fire.backend.manager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {
    @Test
    public void createCurrency(){
        String name = "US Dollars";
        String shortName = "USD";
        String symbol = "$";
        int conversionRate = 1;
        Currency currency = new Currency(name, shortName, symbol, conversionRate);
        assertEquals(name, currency.getName());
        assertEquals(shortName, currency.getShortName());
        assertEquals(symbol, currency.getSymbol());
        assertEquals(conversionRate, currency.getConversionRate(), 0.00001);
    }

    @Test
    public void formatAmount(){
        Currency currency = new Currency("US Dollars", "USD", "$", 1.0);
        assertEquals("$100.25", currency.format(100.25));
        assertEquals("$1'255.25", currency.format(1255.25001));
    }

}
