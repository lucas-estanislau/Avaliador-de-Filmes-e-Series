package br.ufrn.repo.sistema;

import br.ufrn.repo.audiovisual.Filme;
import br.ufrn.repo.audiovisual.Midia;
import br.ufrn.repo.audiovisual.Serie;
import br.ufrn.repo.avaliacao.Avaliacao;
import br.ufrn.repo.avltree.ArvoreAVL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Sistema {
    private List<Midia> listaMidias;
    private ArvoreAVL<Avaliacao> avaliacoesUsuario;

    public Sistema() {
        this.listaMidias = new ArrayList<>();
        this.avaliacoesUsuario = new ArvoreAVL<>();
        carregarDados();
    }

    private void carregarDados() {
        // --- 10 FILMES ---
        listaMidias.add(new Filme("Matrix", "Sci-Fi", "O escolhido descobre a verdade", 14, LocalDate.of(1999, 5, 21), "Wachowski", "2h16m"));
        listaMidias.add(new Filme("O Poderoso Chefão", "Crime", "A saga da família Corleone", 16, LocalDate.of(1972, 3, 24), "Francis Ford Coppola", "2h55m"));
        listaMidias.add(new Filme("Interestelar", "Sci-Fi", "Viagem através de buraco de minhoca", 10, LocalDate.of(2014, 11, 6), "Christopher Nolan", "2h49m"));
        listaMidias.add(new Filme("Toy Story", "Animação", "Brinquedos ganham vida", 0, LocalDate.of(1995, 12, 22), "John Lasseter", "1h21m"));
        listaMidias.add(new Filme("Pulp Fiction", "Crime", "Histórias de criminosos se cruzam", 18, LocalDate.of(1994, 10, 14), "Quentin Tarantino", "2h34m"));
        listaMidias.add(new Filme("A Viagem de Chihiro", "Anime", "Garota entra no mundo dos espíritos", 0, LocalDate.of(2001, 7, 20), "Hayao Miyazaki", "2h05m"));
        listaMidias.add(new Filme("Parasita", "Suspense", "Família pobre se infiltra em casa rica", 16, LocalDate.of(2019, 11, 7), "Bong Joon-ho", "2h12m"));
        listaMidias.add(new Filme("Vingadores: Ultimato", "Ação", "Heróis tentam reverter o estalo", 12, LocalDate.of(2019, 4, 25), "Irmãos Russo", "3h01m"));
        listaMidias.add(new Filme("O Senhor dos Anéis", "Fantasia", "A jornada para destruir o anel", 12, LocalDate.of(2001, 12, 19), "Peter Jackson", "2h58m"));
        listaMidias.add(new Filme("Titanic", "Romance", "O navio afunda e o Jack não sobe na porta", 12, LocalDate.of(1997, 12, 19), "James Cameron", "3h14m"));

        // --- 10 SÉRIES ---
        listaMidias.add(new Serie("Friends", "Comédia", "Seis amigos vivendo em NY", 12, LocalDate.of(1994, 9, 22), 236));
        listaMidias.add(new Serie("Breaking Bad", "Drama", "Professor de química vira traficante", 16, LocalDate.of(2008, 1, 20), 62));
        listaMidias.add(new Serie("Game of Thrones", "Fantasia", "Luta pelo trono de ferro", 18, LocalDate.of(2011, 4, 17), 73));
        listaMidias.add(new Serie("Stranger Things", "Sci-Fi", "Crianças enfrentam monstros nos anos 80", 14, LocalDate.of(2016, 7, 15), 34));
        listaMidias.add(new Serie("The Office", "Comédia", "O dia a dia de um escritório de papel", 12, LocalDate.of(2005, 3, 24), 201));
        listaMidias.add(new Serie("Black Mirror", "Sci-Fi", "O lado sombrio da tecnologia", 16, LocalDate.of(2011, 12, 4), 27));
        listaMidias.add(new Serie("The Crown", "Drama", "A vida da Rainha Elizabeth II", 14, LocalDate.of(2016, 11, 4), 60));
        listaMidias.add(new Serie("Arcane", "Animação", "A origem de campeões de Piltover", 14, LocalDate.of(2021, 11, 6), 9));
        listaMidias.add(new Serie("The Mandalorian", "Sci-Fi", "Caçador de recompensas protege bebê Yoda", 12, LocalDate.of(2019, 11, 12), 24));
        listaMidias.add(new Serie("Sherlock", "Mistério", "Detetive moderno em Londres", 14, LocalDate.of(2010, 7, 25), 13));

        System.out.println("Dados gerados pelo gemini enquanto flavia nao faz a lista!");
    }

    public Midia buscarMidia(String nome) {
        for (Midia md : listaMidias) {
            if (md.get_titulo().equalsIgnoreCase(nome)) {
                return md;
            }
        }return null;
    }

    public void adicionarAvaliacao(Avaliacao novaAvaliacao){
        avaliacoesUsuario.inserir(novaAvaliacao);
        System.out.println("Avaliação salva com sucesso!");
    }

    public Avaliacao buscarAvalicao(String tituloMidia){
        List<Avaliacao> lista = avaliacoesUsuario.emOrdem();
        for(Avaliacao av: lista){
            if (av.get_midia().get_titulo().equalsIgnoreCase(tituloMidia)) {
                return av;
            }
        }return null;
        }

    public boolean removerAvaliacao(Avaliacao avaliacaoRemover){
        Avaliacao encontrada = avaliacoesUsuario.buscar(avaliacaoRemover);
        if (encontrada != null) {
            avaliacoesUsuario.remover(avaliacaoRemover);
            return true;
        }
        return false;
    }

   public List<Avaliacao> getTodasAvaliacoes(){
        return this.avaliacoesUsuario.emOrdem();
   }

  public List<Avaliacao> listarFilmesRecentes(){
      List<Avaliacao> todas = getTodasAvaliacoes();
      List<Avaliacao> soFilmes = new ArrayList<>();

      for (Avaliacao av : todas) {
          if (av.get_midia().get_tipo().equalsIgnoreCase("Filme")) {
              soFilmes.add(av);
          }
      }
      if (!soFilmes.isEmpty()) {
          mergeSort(soFilmes, 0, soFilmes.size() - 1);
      }
      int quantidade = Math.min(soFilmes.size(), 5);
      return soFilmes.subList(0, quantidade);
  }

   public List<Avaliacao> listarSeriesRecentes(){
       List<Avaliacao> todas = getTodasAvaliacoes();
       List<Avaliacao> soSeries = new ArrayList<>();

       for (Avaliacao av : todas) {
           if (av.get_midia().get_tipo().equalsIgnoreCase("Série")) {
               soSeries.add(av);
           }
       }
       if (!soSeries.isEmpty()) {
           mergeSort(soSeries, 0, soSeries.size() - 1);
       }
       int quantidade = Math.min(soSeries.size(), 5);
       return soSeries.subList(0, quantidade);
   }

   public List<Avaliacao> listarSeriesAvaliados(){
       List<Avaliacao> todas = getTodasAvaliacoes();
       List<Avaliacao> soSeries = new ArrayList<>();

       for (Avaliacao av : todas) {
           if (av.get_midia().get_tipo().equalsIgnoreCase("Série")) {
               soSeries.add(av);
           }
       } return soSeries;
   }
    public List<Avaliacao> listarFilmesAvaliados(){
        List<Avaliacao> todas = getTodasAvaliacoes();
        List<Avaliacao> soFilmes = new ArrayList<>();

        for (Avaliacao av : todas) {
            if (av.get_midia().get_tipo().equalsIgnoreCase("Filme")) {
                soFilmes.add(av);
            }
        } return soFilmes;
    }

    private void mergeSort(List<Avaliacao> lista, int inicio, int fim) {
        if (inicio < fim) {
            int meio = inicio + (fim - inicio) / 2;

            mergeSort(lista, inicio, meio);
            mergeSort(lista, meio + 1, fim);
            merge(lista, inicio, meio, fim);
        }
    }
public void merge(List<Avaliacao> lista, int inicio, int meio, int fim){
    int n1 = meio - inicio + 1;
    int n2 = fim - meio;

    List<Avaliacao> esquerda = new ArrayList<>();
    List<Avaliacao> direita = new ArrayList<>();
    for (int i = 0; i < n1; i++) {
        esquerda.add(lista.get(inicio + i));
    }
    for (int j = 0; j < n2; j++) {
        direita.add(lista.get(meio + 1 + j));
    }

    int i =0;
    int j = 0;
    int k = inicio;

    while(i<n1 && j< n2){
        Avaliacao avEsquerda = esquerda.get(i);
        Avaliacao avDireita = direita.get(j);

        if (!avEsquerda.get_data_da_avaliacao().isBefore(avDireita.get_data_da_avaliacao())) {
            lista.set(k, avEsquerda);
            i++;
        } else {
            lista.set(k, avDireita);
            j++;
        }
        k++;
    }
    while (i < n1) {
        lista.set(k, esquerda.get(i));
        i++;
        k++;
    }

    while (j < n2) {
        lista.set(k, direita.get(j));
        j++;
        k++;
    }
}

public boolean editarAvaliacao(String tituloMidia, int novaNota, String novoComentario){
        Avaliacao avEditar = buscarAvalicao(tituloMidia);

        if (avEditar == null){
            return false;
        }
        avaliacoesUsuario.remover(avEditar);
    avEditar.set_nota(novaNota);
    avEditar.set_comentario(novoComentario);
    avEditar.set_data_da_avaliacao(LocalDate.now());

    avaliacoesUsuario.inserir(avEditar);
    System.out.println("Avaliação editada");
    return true;
}
}


