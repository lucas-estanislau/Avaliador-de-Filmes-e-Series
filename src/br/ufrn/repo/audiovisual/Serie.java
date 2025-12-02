package br.ufrn.repo.audiovisual;

import java.time.LocalDate;

public class Serie extends Midia {
	protected int quantidade_de_episodios;
	
	public Serie(String titulo, String genero, String sinopse, int classificacao_indicativa, LocalDate data_de_lancamento, int quantidade_de_episodios) {
		super(titulo, genero, sinopse, classificacao_indicativa, data_de_lancamento);
		this.quantidade_de_episodios = quantidade_de_episodios;
	}
	
	@Override
	public void exibir_informacoes() {
		System.out.println("-------------INFORMAÇÕES SOBRE A SÉRIE---------------");
		System.out.println("Série: " + this.titulo);
		System.out.println("Gênero: " + this.genero);
		System.out.println("Sinopse: " + this.sinopse);
		System.out.println("Classificação indicativa: " + this.classificacao_indicativa);
		System.out.println("Data de lançamento: " + this.data_de_lancamento);
		System.out.println("Quantidade de episódios: " + this.quantidade_de_episodios);
		System.out.println("------------------------------------------------------\n");
		
	}
	
	@Override
	public String get_tipo() {
		return "Série";
	}
}
