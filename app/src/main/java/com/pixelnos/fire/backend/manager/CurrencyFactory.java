package com.pixelnos.fire.backend.manager;

import com.pixelnos.fire.ymlreader.YMLValue;

public class CurrencyFactory{
    public Currency createFromYML(YMLValue value) {
        return new Currency(value);
    }
}
