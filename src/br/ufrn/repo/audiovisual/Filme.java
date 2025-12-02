package br.ufrn.repo.audiovisual;

import java.time.LocalDate;

public class Filme extends Midia {
	protected String diretor;
	protected String duracao;
	
	
	public Filme(String titulo, String genero, String sinopse, int classificacao_indicativa, LocalDate data_de_lancamento, String diretor, String duracao) {
		super(titulo, genero, sinopse, classificacao_indicativa, data_de_lancamento);
		this.diretor = diretor;
		this.duracao = duracao;
	}
	
	@Override
	public void exibir_informacoes() {
		System.out.println("-------------INFORMAÇÕES SOBRE O FILME---------------");
		System.out.println("Título: " + this.titulo);
		System.out.println("Gênero: " + this.genero);
		System.out.println("Sinopse: " + this.sinopse);
		System.out.println("Classificação indicativa: " + this.classificacao_indicativa);
		System.out.println("Data de lançamento: " + this.data_de_lancamento);
		System.out.println("Diretor: " + this.diretor);
		System.out.println("Duração: " + this.duracao);
		System.out.println("-----------------------------------------------------\n");
		
	}
	
	@Override
	public String get_tipo() {
		return "Filme";
	}
	
}
