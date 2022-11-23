package com.example.demo;

public class ArchiveCurrency {
    private String base;
    private String symbol;
    private String day;
    private Double value;

    public ArchiveCurrency(String base, String symbol, String day, Double value) {
        this.base = base;
        this.symbol = symbol;
        this.day = day;
        this.value = value;
    }

    public String getDay() {
        return day;
    }

    public Double getValue() {
        return value;
    }
}
