package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;
import br.com.alura.screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalComLista {
    public static void main(String[] args) {
        Filme meuFilme = new Filme("Avatar", 2023);
        meuFilme.avalia(9);
        Filme outroFilme = new Filme("Ghost in the Shell", 1996);
        outroFilme.avalia(10);
        Serie lost = new Serie("Lost", 2000);
        lost.avalia(5);
        var filmeDoPaulo = new Filme("DogVille", 2003);
        filmeDoPaulo.avalia(9.7);

        List<Titulo> lista = new ArrayList<>();
        lista.add(filmeDoPaulo);
        lista.add(meuFilme);
        lista.add(outroFilme);
        lista.add(lost);

        for (Titulo item: lista) {
            System.out.println(item.getNome());
            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        System.out.println("Lista de Títulos ordenada: ");
        Collections.sort(lista);
        System.out.println(lista);
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println(lista);
    }
}
