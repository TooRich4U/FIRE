package com.pixelnos.fire.backend.manager;

import com.pixelnos.fire.ymlreader.YMLReader;
import com.pixelnos.fire.ymlreader.YMLValue;

import java.util.HashMap;
import java.util.Map;

class YMLInvalidException extends Exception {
    public YMLInvalidException() {
        super("Invalid Wallet YML, Wallet or accounts do not exist.");
    }
}

public class Wallet {
    private Map<String, Account> accounts = new HashMap<>();

    public Wallet() {
    }

    public Wallet(AccountFactory accountFactory, String yml) {
        YMLValue value = YMLReader.read(yml);
        addAccountsFromYMLLines(accountFactory, value);
    }

    private void addAccountsFromYMLLines(AccountFactory accountFactory, YMLValue value) {
        for (YMLValue accountValue : value.get("accounts").asArrayList()) {
            add(accountFactory.createAccountFromYML(accountValue));
        }
    }

    public Account getAccountByName(String accountName) {
        return accounts.get(accountName);
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void add(Account account) {
        accounts.put(account.getName(), account);
    }

    public void delete(Account account) {
        accounts.remove(account.getName());
    }

    public YMLValue toYML() throws Exception {
        YMLValue ymlWallet = new YMLValue();
        YMLValue ymlAccounts = new YMLValue();
        for (Map.Entry<String, Account> account : accounts.entrySet()) {
            ymlAccounts.add(account.getValue().toYML());
        }
        ymlWallet.addEntry("accounts", ymlAccounts);
        return ymlWallet;
    }


}
