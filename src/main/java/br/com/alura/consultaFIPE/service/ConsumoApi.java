package br.com.alura.consultaFIPE.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpClient.newHttpClient;

public class ConsumoApi {

    public String obterDados(String endereco) {
        HttpClient client = newHttpClient();
        HttpRequest.Builder builder = HttpRequest.newBuilder();
        builder.uri(URI.create(endereco));
        HttpRequest request = builder
                .build();
        HttpResponse<String> response;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return response.body();
    }
}
