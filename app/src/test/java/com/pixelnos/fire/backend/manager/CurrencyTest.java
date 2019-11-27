package com.pixelnos.fire.backend.manager;
import com.pixelnos.fire.ymlreader.YMLReader;
import com.pixelnos.fire.ymlreader.YMLValue;
import com.pixelnos.fire.ymlreader.YMLWriter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {
    @Test
    public void createCurrency(){
        String name = "US Dollars";
        String shortName = "USD";
        String symbol = "$";
        double conversionRate = 1;
        Currency currency = new Currency(name, shortName, symbol, conversionRate);
        assertEquals(name, currency.getName());
        assertEquals(shortName, currency.getShortName());
        assertEquals(symbol, currency.getSymbol());
        assertEquals(conversionRate, currency.getConversionRate(), 0.00001);
    }

    @Test
    public void createCurrencyFromYML(){
        String yml = "short_name: USD\n"+
                "name: US Dollars\n"+
                "symbol: $\n"+
                "conversion_rate: 1.0";
        YMLValue value = YMLReader.read(yml);
        Currency currency = new Currency(value);
        assertEquals("US Dollars", currency.getName());
        assertEquals("USD", currency.getShortName());
        assertEquals("$", currency.getSymbol());
        assertEquals(1.0, currency.getConversionRate(), 0.00001);
    }

    @Test
    public void toYML(){
        String name = "US Dollars";
        String shortName = "USD";
        String symbol = "$";
        double conversionRate = 1.0;
        Currency currency = new Currency(name, shortName, symbol, conversionRate);
        String yml = "short_name: USD\n"+
                "name: US Dollars\n"+
                "symbol: $\n"+
                "conversion_rate: 1.00000";
        assertEquals(yml,YMLWriter.write(currency.toYML()));
    }

    @Test
    public void formatAmount(){
        Currency currency = new Currency("US Dollars", "USD", "$", 1.0);
        assertEquals("$100.25", currency.format(100.25));
        assertEquals("$1'255.25", currency.format(1255.25001));
    }

}
