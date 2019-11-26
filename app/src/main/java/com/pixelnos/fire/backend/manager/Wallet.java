package com.pixelnos.fire.backend.manager;
import java.util.HashMap;
import java.util.Map;

class YMLInvalidException extends Exception {
    public YMLInvalidException(){
        super("Invalid Wallet YML, Wallet or accounts do not exist.");
    }
}

public class Wallet {
    private Map<String,Account> accounts = new HashMap<>();
    public Wallet(){}
    public Wallet(AccountFactory accountFactory, String yml) throws YMLInvalidException {
        String lines[] = yml.split("\\r?\\n");
        checkYMLValidity(lines);
        addAccountsFromYMLLines(accountFactory, lines);
    }

    private void addAccountsFromYMLLines(AccountFactory accountFactory, String[] lines) {
        int startAccountLine = 2;
        int endAccountLine = 2;
        boolean accountFound = false;
        for(int lineIndex = 2; lineIndex < lines.length && getShift(lines[lineIndex]) > 2; lineIndex++){
            if(lines[lineIndex].compareTo("    -") == 0 || lineIndex == lines.length - 1 || getShift(lines[lineIndex+1]) <= 2){

                if(accountFound){
                    endAccountLine = lineIndex;
                    if(getShift(lines[lineIndex + 1]) <= 2) {
                        endAccountLine = lineIndex + 1;
                    }
                    addAccountFromString(accountFactory, lines, startAccountLine, endAccountLine);
                }
                accountFound = true;
                startAccountLine = lineIndex+1;
            }
        }
    }

    int getShift(String string){
        int shift = 0;
        while(string.charAt(shift) == ' '){
            shift += 1;
        }
        return shift;
    }

    private void checkYMLValidity(String[] lines) throws YMLInvalidException {
        if(lines[0].compareTo("Wallet:") != 0 || lines[1].trim().compareTo("accounts:") != 0){
            throw new YMLInvalidException();
        }
    }

    private void addAccountFromString(AccountFactory accountFactory, String[] lines, int startAccountLine, int endAccountLine) {
        String newYML = "";
        for (int index = startAccountLine; index < endAccountLine; index++) {
            newYML += lines[index] + "\n";

        }
        add(accountFactory.createAccountFromYML(newYML));
    }

    public Account getAccountByName(String accountName){
        return accounts.get(accountName);
    }
    public Map<String,Account> getAccounts (){
        return accounts;
    }
    public void add(Account account) {
        accounts.put(account.getName(),account);
    }

    public void delete(Account account){
        accounts.remove(account.getName());
    }
    public String toYML(String shift){
        String yml = shift + "Wallet:\n";
        yml += shift + "  accounts:\n";
        for(Map.Entry<String, Account> account : accounts.entrySet()){
            yml += shift + "    -\n"+ account.getValue().toYML(shift + "      ") + "\n";
        }
        return yml;
    }

}
