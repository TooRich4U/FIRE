package com.pixelnos.fire.backend.manager;

import com.pixelnos.fire.ymlreader.YMLValue;

public class Currency {
    private String name;
    private String shortName;
    private String symbol;
    private double conversionRate;

    public Currency(String name, String shortName, String symbol, double conversionRate) {
        this.name = name;
        this.shortName = shortName;
        this.symbol = symbol;
        this.conversionRate = conversionRate;
    }

    public Currency(YMLValue value) {
        name = value.get("name").asString();
        shortName = value.get("short_name").asString();
        symbol = value.get("symbol").asString();
        conversionRate = value.get("conversion_rate").asDouble();
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public String toYML(){
        return name;
    }

    public String format(double amount) {
        String amountStr = String.format("%.2f", amount);
        int countDigits = 0;
        for(int i = amountStr.length()-4; i > 0; i--){
            countDigits += 1;
            if(countDigits%3 == 0){
                amountStr = insertString(amountStr, "'", i-1);
            }
        }
        return symbol + amountStr;
    }

    // Function to insert string
    public static String insertString(
            String originalString,
            String stringToBeInserted,
            int index)
    {

        // Create a new string
        String newString = originalString.substring(0, index + 1)
                + stringToBeInserted
                + originalString.substring(index + 1);

        // return the modified String
        return newString;
    }
}
