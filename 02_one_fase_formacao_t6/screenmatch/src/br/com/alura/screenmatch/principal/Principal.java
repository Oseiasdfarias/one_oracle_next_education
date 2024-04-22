package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.calculos.CalculadoraDeTempo;
import br.com.alura.screenmatch.calculos.FiltroRecimendacao;
import br.com.alura.screenmatch.modelos.Episodio;
import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("Avatar", 2023);
        meuFilme.setDuracaoEmMinutos(200);
        System.out.println("Duração do filme: " + meuFilme.getDuracaoEmMinutos());

        Filme outroFilme = new Filme("Ghost in the Shell", 1996);
        outroFilme.setDuracaoEmMinutos(82);
        System.out.println("Duração do filme: " + outroFilme.getDuracaoEmMinutos());


        meuFilme.exibeFichaTecnica();
        meuFilme.avalia(9.7);
        meuFilme.avalia(10);
        meuFilme.avalia(9.5);
        System.out.println(meuFilme.pegaMedia());
        System.out.println("Total de Avaliações: " + meuFilme.getTotalAvalicacoes());

        Serie lost = new Serie("Lost", 2000);
        lost.exibeFichaTecnica();
        lost.setTemporadas(15);
        lost.setEpsodiosPorTemporada(10);
        lost.setMinutosPorTemporada(50);
        System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
        calculadora.inclui(meuFilme);
        calculadora.inclui(outroFilme);
        calculadora.inclui(lost);
        System.out.println(calculadora.getTempoTotal());

        FiltroRecimendacao filtro = new FiltroRecimendacao();
        filtro.filtra(meuFilme);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(lost);
        episodio.setTotalVisualizacoes(300);
        filtro.filtra(episodio);

        var filmeDoPaulo = new Filme("DogVille", 2003);
        filmeDoPaulo.setDuracaoEmMinutos(200);
        filmeDoPaulo.avalia(10);

        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(filmeDoPaulo);
        listaDeFilmes.add(meuFilme);
        listaDeFilmes.add(outroFilme);
        System.out.println("Tamanho da lista é " + listaDeFilmes.size());
        System.out.println("toString: " + listaDeFilmes);
        System.out.println("toString: " + listaDeFilmes.get(0).toString());
    }
}
