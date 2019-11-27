package com.pixelnos.fire.backend.manager;

import com.pixelnos.fire.ymlreader.YMLValue;

public class AccountFactory {
    public Account createAccountFromYML(YMLValue value) {
        CurrencyFactory factory = new CurrencyFactory();
        return new Account(value, factory);
    }
}