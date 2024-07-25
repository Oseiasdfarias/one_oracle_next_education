package br.com.alura.screenmatch.repository;

import br.com.alura.screenmatch.model.Categoria;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long> {
    Optional<Serie>findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(
            String nomeAtor, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(
            int totalTemporadas, double avaliacao);

    @Query("select s\n" +
            "from Serie s \n" +
            "where (s.totalTemporadas  <= :totalTemporadas) and (s.avaliacao >= :avaliacao)")
    List<Serie> seriePorTemporada(int totalTemporadas, double avaliacao);

    @Query("select e from Serie s join s.episodios e where e.titulo ilike %:trechoEpisodio%")
    List<Episodio> episodioPorTrecho(String trechoEpisodio);

    @Query("select e from Serie s join s.episodios" +
            " e where s = :serie order by e.avaliacao desc limit 5")
    List<Episodio> topEpisodioPorSerie(Serie serie);

    @Query("select e from Serie s join s.episodios" +
            " e where s = :serie and year(e.dataLancamento) >= " +
            ":anoLancamento order by e.dataLancamento desc")
    List<Episodio> buscarEpisodioAnoInicial(Serie serie, int anoLancamento);
}
