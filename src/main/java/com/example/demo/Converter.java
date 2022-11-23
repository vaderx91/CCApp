package com.example.demo;

import org.json.JSONException;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Converter implements Convert {
    private ApiConnection apiConnection;
    private JsonConversion jsonConversion;

    public Converter(com.example.demo.ApiConnection apiConnection, com.example.demo.JsonConversion jsonConversion) {
        this.apiConnection = apiConnection;
        this.jsonConversion = jsonConversion;
    }

    @Override
    public Double convert(String from, String to, Double value) throws IOException, JSONException {
        StringBuilder sb = apiConnection.getRate(from, to, value);
        Double result = jsonConversion.getDataConversion(sb);
        return result;
    }

    @Override
    public List<Currency> getAllCurrencies() throws IOException, JSONException {
        List<Currency> listCurrencies = new ArrayList();
        StringBuilder sb = apiConnection.getSymbolsWithSign();
        String SymbolsWithSign[] = jsonConversion.getDataSymbols(sb);
        for (String line : SymbolsWithSign) {
            String tmp[] = line.split(":");
            String symbol = tmp[0].replace("(", "").replace("\"", "");
            String signification = tmp[1].replace(")", "").replace("\"", "");
            Currency currency = new Currency(symbol, signification);
            listCurrencies.add(currency);
        }
        return listCurrencies;
    }

    @Override
    public List<String> getAllSymbols(List<Currency> list) {
        List<String> listSymbols = list.stream().map(currency -> currency.getSymbol()).collect(Collectors.toList());
        Collections.sort(listSymbols);
        return listSymbols;
    }

    @Override
    public List<ArchiveCurrency> getArchiveValues(String base, Integer durationDays, String symbol) throws IOException, JSONException {
        LocalDate today = LocalDate.now();
        LocalDate dayAgo = today.minusDays(durationDays);

        StringBuilder sb = apiConnection.getArchiveCurrency(base, dayAgo, today, symbol);
        String archiveValue[] = jsonConversion.getDataArchiveCurrency(sb);
        List<ArchiveCurrency> listArchCur = new ArrayList();
        for (String line : archiveValue) {
            String dayArch = line.subSequence(1, 12).toString().replace("\"", "");
            Double value = Double.parseDouble(line.subSequence(20, 27).toString().replace(":", ""));
            ArchiveCurrency archiveCurrency = new ArchiveCurrency(base, symbol, base, value);
            listArchCur.add(archiveCurrency);
        }

        Collections.sort(listArchCur, Comparator.comparing(ArchiveCurrency::getDay));

        return listArchCur;
    }
}
