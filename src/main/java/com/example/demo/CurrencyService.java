package com.example.demo;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class CurrencyService {
    private Convert convert;

    public CurrencyService(Convert convert) {
        this.convert = convert;
    }

    public Double conv(String from, String to, Double amount) throws JSONException, IOException {
        return convert.convert(from, to, amount);
    }

    public List<Currency> getAllCurrencies() throws JSONException, IOException {
        return convert.getAllCurrencies();
    }

    public List<Currency> findCurrency(List<Currency> list, String symbol) {
        String symbol2 = toUpperCase(symbol);
        return list.stream().filter(currency -> currency.getSymbol().contains(symbol2)).collect(Collectors.toList());
    }

    public List<String> getAllSymbols(List<Currency> list) {
        return convert.getAllSymbols(list);
    }

    private String toUpperCase(String str) {
        String st = "";
        for (int i = 0; i < str.length(); i++) {
            st += Character.toUpperCase(str.charAt(i)) + "";
        }
        return st;
    }
}
