package com.example.demo;

public class Currency {

    private String symbol;
    private String signification;

    public Currency(String symbol, String signification) {
        this.symbol = symbol;
        this.signification = signification;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSignification() {
        return signification;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "symbol='" + symbol + ", signification='" + signification + '}';
    }
}
