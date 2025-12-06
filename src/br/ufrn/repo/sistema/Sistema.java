package br.ufrn.repo.sistema;

import br.ufrn.repo.audiovisual.Filme;
import br.ufrn.repo.audiovisual.Midia;
import br.ufrn.repo.audiovisual.Serie;
import br.ufrn.repo.avaliacao.Avaliacao;
import br.ufrn.repo.avltree.ArvoreAVL;

import java.io.File; 
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
    public List<Midia> getListaMidias() {
        return listaMidias;
    }

    private void carregarDados() {

        Leitor leitor = new Leitor();
        leitor.LerArquivo("Avaliador-de-Filmes-e-Series/sr/database/dados.txt");

        listaMidias.addAll(leitor.getFilmes());
        listaMidias.addAll(leitor.getSeries());

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


