package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class ApiConnection {
    private final String url;
    private final String apiKey;

    public ApiConnection(String url, String apiKey) {
        this.url = url;
        this.apiKey = apiKey;
    }

    public StringBuilder getRate(String from, String to, Double amount) throws IOException {
        URL objURL = new URL(url + "/convert?to=" + to + "&from=" + from + "&amount=" + amount);
        HttpURLConnection http = (HttpURLConnection) objURL.openConnection();
        http.setRequestProperty("apikey", apiKey);
        StringBuilder sb = getStringBuilder(http);

        return sb;
    }

    public StringBuilder getSymbolsWithSign() throws IOException {
        URL objURL = new URL(url + "/symbols");
        HttpURLConnection http = (HttpURLConnection) objURL.openConnection();
        http.setRequestProperty("apikey", apiKey);
        return getStringBuilder(http);
    }

    public StringBuilder getArchiveCurrency(String base, LocalDate startDate,
                                            LocalDate endDate, String symbol) throws IOException {
        URL objURL = new URL(url + "/timeseries?start_date=" + startDate + "&end_date=" + endDate + "&base=" + base + "&symbols=" + symbol);
        HttpURLConnection http = (HttpURLConnection) objURL.openConnection();
        http.setRequestProperty("apikey", apiKey);
        return getStringBuilder(http);
    }

    private StringBuilder getStringBuilder(HttpURLConnection http) throws IOException {
        if (http.getResponseCode() != 200) {
            throw new IllegalStateException("Connection failed");
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        return stringBuilder;
    }
}
