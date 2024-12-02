package com.finmid.challenge;

public class BankingServiceJava {
    private final BankingService bankingService;

    public BankingServiceJava(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    public void transferMoney() {
        throw new IllegalStateException("Not implemented");
    }
}
