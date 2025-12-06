package br.ufrn.repo.audiovisual;

import java.time.LocalDate;

import br.ufrn.repo.annotations.FuncionamentoClasse;
import br.ufrn.repo.annotations.FuncionamentoMetodo;
import br.ufrn.repo.annotations.InfoAutor;

@InfoAutor(nome = "Bianca Jennifer, Isabele Ferreira, Flávia Jamily, Juvam Rodrigues, Lucas Estanislau", data = "05/12/2025",
versaoProjeto = 1.0)

@FuncionamentoClasse(funcionamento="Classe Mãe.")
public abstract class Midia implements Comparable<Midia> {
	protected String titulo;
	protected String genero;
	protected String sinopse;
	protected int classificacao_indicativa;
	protected LocalDate data_de_lancamento;

	public Midia(String titulo, String genero, String sinopse, int classificacao_indicativa,
			LocalDate data_de_lancamento) {
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

	@FuncionamentoMetodo(funcionamento = "Método compara dois objetos do tipo Midia de acordo com a data de lançamento. "
			+ "Assim, ajudará a ordenar em ordem crescente e decrescente a lista de mídias presente na classe Sistema")
	@Override
	public int compareTo(Midia midia2) {
		return this.data_de_lancamento.compareTo(midia2.get_data_de_lancamento());
		// Retorna valor positivo(=>1) se this.data_de_lancamento é mais nova
		// Retorna 0 se this.data_de_lancamento tem data igual a
		// midia2.data_de_lancamento
		// Retorna valor negativo(<=-1) se this.data_de_lancamento é mais antiga
	}

}
