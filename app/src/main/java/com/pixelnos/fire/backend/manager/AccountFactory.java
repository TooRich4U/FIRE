package com.pixelnos.fire.backend.manager;

public class AccountFactory {
    Account createAccountFromYML(String newYML) {
        return new Account(newYML);
    }
}