package br.ufrn.repo.sistema;

import br.ufrn.repo.exception.NumeroCamposException;
import br.ufrn.repo.audiovisual.Filme;
import br.ufrn.repo.audiovisual.Serie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Leitor {

    private ArrayList<Filme> filmes = new ArrayList<>();
    private ArrayList<Serie> series = new ArrayList<>();

    public ArrayList<Filme> getFilmes() {
        return filmes;
    }

    public ArrayList<Serie> getSeries() {
        return series;
    }

    public void LerArquivo(String caminhoArquivo) {

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;

            while ((linha = br.readLine()) != null) {

                if (linha.isBlank()) continue;

                try {
                    String[] campo = linha.split("\\|");
                    String tipo = campo[0].trim();

                    if (tipo.equalsIgnoreCase("FILME")) {
                        if (campo.length < 8) {
                            throw new NumeroCamposException("campo insuficientes para FILME: " + linha);
                        }

                        String titulo = campo[1].trim();
                        String genero = campo[2].trim();
                        String sinopse = campo[3].trim();
                        int classificacao_indicativa = Integer.parseInt(campo[4].trim());
                        LocalDate data_de_lancamento = LocalDate.parse(campo[5].trim());
                        String diretor = campo[6].trim();
                        String duracao = campo[7].trim();

                        Filme filme = new Filme(titulo, genero, sinopse, classificacao_indicativa, data_de_lancamento, diretor, duracao);
                        filmes.add(filme);

                    } else if (tipo.equalsIgnoreCase("SERIE")) {
                        if (campo.length < 7) {
                            throw new NumeroCamposException("campo insuficientes para SERIE: " + linha);
                        }

                        String titulo = campo[1].trim();
                        String genero = campo[2].trim();
                        String sinopse = campo[3].trim();
                        int classificacao_indicativa = Integer.parseInt(campo[4].trim());
                        LocalDate data_de_lancamento = LocalDate.parse(campo[5].trim());
                        int quantidade_de_episodios = Integer.parseInt(campo[6].trim());

                        Serie serie = new Serie(titulo, genero, sinopse, classificacao_indicativa, data_de_lancamento, quantidade_de_episodios);
                        series.add(serie);
                    }

                } catch (NumeroCamposException e) {
                    System.err.println("Erro na quantidade de campo: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Erro de formato no numero: " + linha  +  e.getMessage());
                } catch (DateTimeParseException e) {
                System.err.println("Erro no formato de data: " + linha);
                }
            }
            
            System.out.println("Dados carregados do arquivo " + caminhoArquivo);

        } catch (IOException e) {
            System.out.println("Erro ao abrir arquivo: " + e.getMessage());
        }
    }
}