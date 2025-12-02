package br.ufrn.repo.audiovisual;

import java.time.LocalDate;

public abstract class Midia {
	protected String titulo;
	protected String genero;
	protected String sinopse;
	protected int classificacao_indicativa;
	protected LocalDate data_de_lancamento;
	
	public Midia(String titulo, String genero, String sinopse, int classificacao_indicativa, LocalDate data_de_lancamento) {
		this.titulo = titulo;
		this.genero = genero;
		this.sinopse = sinopse;
		this.classificacao_indicativa = classificacao_indicativa;
		this.data_de_lancamento = data_de_lancamento;
	}
	
	public abstract void exibir_informacoes();
	public abstract String get_tipo();
	
	public LocalDate get_data_de_lancamento() {
		return this.data_de_lancamento;
	};
	
	public String get_titulo() {
		return this.titulo;
	};
	
	
}
