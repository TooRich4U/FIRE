package com.pixelnos.fire.backend.manager;

class Currency {
    private String _name;
    private String _shortName;
    private String _symbol;
    private double _conversionRate;

    public Currency(String name, String shortName, String symbol, double conversionRate) {
        _name = name;
        _shortName = shortName;
        _symbol = symbol;
        _conversionRate = conversionRate;
    }

    public String getName() {
        return _name;
    }

    public String getShortName() {
        return _shortName;
    }

    public String getSymbol() {
        return _symbol;
    }

    public double getConversionRate() {
        return _conversionRate;
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
        return _symbol + amountStr;
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
