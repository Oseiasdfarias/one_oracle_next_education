package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.*;
import br.com.alura.screenmatch.repository.SerieRepository;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private List<DadosSerie> dadosSeries = new ArrayList<>();

    private SerieRepository repositorio;

    private List<Serie> series = new ArrayList<>();

    private Optional<Serie> serieBusca;

    public Principal(SerieRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    \n 1 - Buscar séries
                     2 - Buscar episódios
                     3 - Listar séries buscadas
                     4 - Buscar Série por Título
                     5 - Buscar Série por Ator
                     6 - Buscar Top 5 Séries
                     7 - Busca Série por Categoria
                     8 - Filtrar séries
                     9 - Buscar Episódio por Trecho
                    10 - Buscar Top Episódioas por Série
                    11 - Buscar Episódios a Partir de uma Data
                                    
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    listarSeriesBuscadas();
                    break;
                case 4:
                    buscaSeriePorTitulo();
                    break;
                case 5:
                    buscaSeriePorAtor();
                    break;
                case 6:
                    buscarTop5Series();
                    break;
                case 7:
                    BuscarSeriePorCategoria();
                    break;
                case 8:
                    filtrarSeriesPorTemporadaEAvaliacao();
                    break;
                case 9:
                    buscarEpisodioPorTrecho();
                    break;
                case 10:
                    buscarTopEpisodiosPorSerie();
                    break;
                case 11:
                    buscarEpisodioApartirDeData();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }


    private void buscarSerieWeb() {
        DadosSerie dados = getDadosSerie();
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println(dados);
    }

    private DadosSerie getDadosSerie() {
        System.out.println("Digite o nome da série para busca");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        return dados;
    }

    private void buscarEpisodioPorSerie(){
        listarSeriesBuscadas();
        System.out.println("Digite um dos nomes de série da lista:");
        var nomeSerie = leitura.nextLine();

        Optional<Serie> serie = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if(serie.isPresent()) {
            var serieEncontrada = serie.get();
            List<DadosTemporada> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalTemporadas(); i++) {
                var json = consumo.obterDados(ENDERECO + serieEncontrada.getTitulo().replace(" ", "+") + "&season=" + i + API_KEY);
                DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
                temporadas.add(dadosTemporada);
            }
            temporadas.forEach(System.out::println);
            List<Episodio> episodios = temporadas.stream()
                    .flatMap(d -> d.episodios().stream()
                            .map(e -> new Episodio(d.numero(), e)))
                    .collect(Collectors.toList());

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada);
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void listarSeriesBuscadas() {
        series = repositorio.findAll();
        series.stream().sorted(Comparator.comparing(Serie::getGenero))
                .forEach(System.out::println);
    }

    private void buscaSeriePorTitulo() {
        System.out.println("Digite um dos nomes de série da lista:");
        var nomeSerie = leitura.nextLine();
        serieBusca = repositorio.findByTituloContainingIgnoreCase(nomeSerie);

        if (serieBusca.isPresent()) {
            System.out.println("Dados da Série: " + serieBusca.get());
        } else {
            System.out.println("Série não encontrada!");
        }
    }

    private void buscaSeriePorAtor() {
        System.out.println("Digite um dos nome do Ator para buscar no banco de dados");
        var nomeAtor = leitura.nextLine();
        System.out.println("Avaliações a partir de qual valor?");
        var avaliacao = leitura.nextDouble();

        List<Serie> seriesEncontradas = repositorio.
                findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(nomeAtor, avaliacao);

        if (!seriesEncontradas.isEmpty()) {
            System.out.println("\nSérie em que " + nomeAtor + " trabalhou.");
            seriesEncontradas.forEach(
                    s -> System.out.println(s.getTitulo() +
                            " | Avaliação: " + s.getAvaliacao()));
        } else {
            System.out.println("\nNão foi encontrada nunhuma série" +
                    " que tenha esse ator listado como protagonista!\n");
        }
    }

    private void buscarTop5Series() {
        List<Serie> serieTop = repositorio.findTop5ByOrderByAvaliacaoDesc();
        if (!serieTop.isEmpty()) {
            System.out.println("\nTop 5 Séries");
            serieTop.forEach(s -> System.out.println(s.getTitulo() +
                    " | Avaliação: " + s.getAvaliacao()));
        } else {
            System.out.println("\nNão foi encontrada nunhuma série");
        }
    }

    private void BuscarSeriePorCategoria() {
        System.out.println("Qual categoria deseja busca de Série?");
        var nomeDoGenero = leitura.nextLine();
        Categoria categoria = Categoria.fromPortugues(nomeDoGenero);
        List<Serie> seriePorCategoria = repositorio.findByGenero(categoria);
        if (!seriePorCategoria.isEmpty()) {
            System.out.println("\nCategoria: " + StringUtils.capitalize(nomeDoGenero));
            seriePorCategoria.forEach(s -> System.out.println(
                    "Título: " + s.getTitulo() +
                    " | Avaliação: " + s.getAvaliacao()));
        } else {
            System.out.println("\nNão foi encontrada nunhuma série");
        }
    }

    private void filtrarSeriesPorTemporadaEAvaliacao(){
        System.out.println("\nFiltrar séries até quantas temporadas? ");
        var totalTemporadas = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Com avaliação a partir de que valor? ");
        var avaliacao = leitura.nextDouble();
        leitura.nextLine();
        List<Serie> filtroSeries = repositorio.seriePorTemporada(totalTemporadas, avaliacao);

        System.out.println("\nSéries Filtradas");
        filtroSeries.forEach(s ->
                System.out.println(s.getTitulo() + "  - Avaliação: " + s.getAvaliacao()));
    }

    private void buscarEpisodioPorTrecho() {
        System.out.println("Digite um trecho de um episódio: ");
        var trechoEpisodio = leitura.nextLine();
        List<Episodio> episodiosEncontrados = repositorio.episodioPorTrecho(trechoEpisodio);
        episodiosEncontrados.forEach(e -> System.out.println(
                "Série: " + e.getSerie().getTitulo() +
                " | Título: " + e.getTitulo() +
                " | Temporada: " + e.getTemporada() +
                " | Avaliação: " + e.getAvaliacao()));
    }

    private void buscarTopEpisodiosPorSerie() {
        buscaSeriePorTitulo();
        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            List<Episodio> topEpisodio = repositorio.topEpisodioPorSerie(serie);
            topEpisodio.forEach(e -> System.out.println(
                    "Série: " + e.getSerie().getTitulo() +
                            " | Título: " + e.getTitulo() +
                            " | Temporada: " + e.getTemporada() +
                            " | Avaliação: " + e.getAvaliacao()));
        }
    }

    private void buscarEpisodioApartirDeData() {
        buscaSeriePorTitulo();
        if (serieBusca.isPresent()) {
            Serie serie = serieBusca.get();
            System.out.println("Digite o ano limite para iniciar a busca: ");
            var anoLancamento = leitura.nextInt();
            leitura.nextLine();

            List<Episodio> episodioAno = repositorio.
                    buscarEpisodioAnoInicial(serie, anoLancamento);
            episodioAno.forEach(e -> System.out.println(
                    "Data de Lançamento: " + e.getDataLancamento() +
                            " | Série: " + e.getSerie().getTitulo() +
                            " | Título: " + e.getTitulo() +
                            " | Temporada: " + e.getTemporada() +
                            " | Avaliação: " + e.getAvaliacao()));
        }
    }
}