package br.ufrn.repo.avaliacao;

import java.time.LocalDate;

import br.ufrn.repo.annotations.FuncionamentoMetodo;
import br.ufrn.repo.annotations.InfoAutor;
import br.ufrn.repo.audiovisual.Midia;

@InfoAutor(nome = "Bianca Jennifer, Isabele de Oliveira, Flávia Jamily, Juvam Rodrigues, Lucas Estanislau", data = "05/12/2025", 
versaoProjeto = 1.0)

public class Avaliacao implements Comparable<Avaliacao> {
	protected Midia midia;
	protected Integer nota;
	protected String comentario;
	protected LocalDate data_da_avaliacao;
	
	public Avaliacao(Midia midia, Integer nota, String comentario, LocalDate data_da_avaliacao) {
		this.midia = midia;
		this.nota = nota;
		this.comentario = comentario;
		this.data_da_avaliacao = data_da_avaliacao;
	}
	
	public void exibir_informacoes() {
		System.out.println("-------------INFORMAÇÕES SOBRE A AVALIAÇÃO---------------");
		System.out.println("Título: " + this.midia.get_titulo());
		System.out.println("Categoria: " + this.midia.get_tipo());
		System.out.println("Nota: " + this.nota);
		System.out.println("Comentário: " + this.comentario);
		System.out.println("Data da avaliação: " + this.data_da_avaliacao);
		System.out.println("---------------------------------------------------------\n");
	}
	
	public int get_nota() {
		return this.nota;
	}
	
	public Midia get_midia() {
		return this.midia;
	}
	
	public LocalDate get_data_da_avaliacao() {
		return this.data_da_avaliacao;
	}
	
	public void set_nota(int nota) {
		this.nota = nota;
	}
	
	public void set_comentario(String comentario) {
		this.comentario = comentario;
	}
	
	public void set_data_da_avaliacao(LocalDate data) {
		this.data_da_avaliacao = data;
	}
	
	@FuncionamentoMetodo(funcionamento = "Método compara dois objetos do tipo Avaliação de acordo com a nota.")
	@Override
	public int compareTo(Avaliacao avaliacao2) {
		return this.nota.compareTo(avaliacao2.get_nota());
		//Retorna =>1 se this.nota > avaliacao2.nota
		//Retorna 0 se this.nota == avaliacao2.nota
		//Retorna <=-1 se this.nota < avaliacao2.nota
	}
	
}
