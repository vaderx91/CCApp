package com.example.demo;

import org.json.JSONException;
import org.json.JSONObject;


public class JsonConversion {
    public Double getDataConversion(StringBuilder sb) throws JSONException {
        JSONObject meResponse = new JSONObject(sb.toString());
        return meResponse.getDouble("result");
    }

    public String[] getDataSymbols(StringBuilder stringBuilder) throws JSONException {
        JSONObject meResponse = new JSONObject(stringBuilder.toString());
        String symbolsCurrencies[] = meResponse.get("symbols").toString().split(",");
        return symbolsCurrencies;
    }

    public String[] getDataArchiveCurrency(StringBuilder stringBuilder) throws JSONException {
        JSONObject meResponse = new JSONObject(stringBuilder.toString());
        String archiveValues[] = meResponse.get("rates").toString().split(",");
        return archiveValues;
    }
}
