package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import br.com.alura.screenmatch.model.DadosTemporada;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();


    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=8ff55a50";

    public void exibeMenu() {
        System.out.println("Digite o nome da série para busca: ");
        var nomeSerie = leitura.nextLine();
        var json = consumoApi.obterDados(
        ENDERECO
                + nomeSerie.replace(" ", "+")
                + API_KEY
        );
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i<= dados.totalTemporada(); i++) {
            json = consumoApi.obterDados(
                    ENDERECO
                            + nomeSerie.replace(" ", "+")
                            + "&season=" + i + API_KEY);

            DadosTemporada dadosTemporada =
                    conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
