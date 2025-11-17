package br.com.modelos;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConversorDeMoedas {

    private static final String API_KEY = "f4395aef2b43b3f68fd0c9de";
    private static final String URL_BASE = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/BRL";

    // Método que busca a taxa e faz a conversão
    public double converter(double valorBRL, String moedaDestino) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        if (json.get("result").getAsString().equals("success")) {
            JsonObject rates = json.getAsJsonObject("conversion_rates");
            double taxa = rates.get(moedaDestino).getAsDouble();
            return valorBRL * taxa;
        } else {
            throw new RuntimeException("Erro ao buscar cotação da API");
        }
    }

    // Método que retorna a taxa atual da moeda
    public double buscarTaxa(String moedaDestino) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_BASE))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();

        if (json.get("result").getAsString().equals("success")) {
            JsonObject rates = json.getAsJsonObject("conversion_rates");
            return rates.get(moedaDestino).getAsDouble();
        } else {
            throw new RuntimeException("Erro ao buscar taxa de câmbio");
        }
    }
}
