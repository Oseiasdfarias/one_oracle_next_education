package br.com.projetoConversor.cunsultaApi;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {
    public DadosApi buscarConversoes(String conv1, String conv2) {
        String end = "https://v6.exchangerate-api.com/v6/83b0daae62de89ad3fa7f134/pair/";
        URI endereco = URI.create(end + conv1 + "/" + conv2 );
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endereco)
                .build();
        try {
            HttpResponse<String> response = HttpClient
                    .newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), DadosApi.class);
        } catch (Exception e) {
            throw new RuntimeException("Não consegui obter o endereço a partir desse CEP.");
        }
    }
}
