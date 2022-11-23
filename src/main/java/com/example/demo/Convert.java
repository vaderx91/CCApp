package com.example.demo;

import org.json.JSONException;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.List;

public interface Convert {

    Double convert(String from, String to, Double value) throws IOException, JSONException;
    List<Currency> getAllCurrencies() throws IOException, JSONException;
    List<String> getAllSymbols(List<Currency> list);
    List<ArchiveCurrency> getArchiveValues(String base, Integer durationDays, String symbol) throws IOException, JSONException;

}
