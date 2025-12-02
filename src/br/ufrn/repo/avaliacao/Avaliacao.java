package br.ufrn.repo.avaliacao;

import java.time.LocalDate;
import br.ufrn.repo.audiovisual.Midia;

public class Avaliacao {
	protected Midia midia;
	protected int nota;
	protected String comentario;
	protected LocalDate data_da_avaliacao;
	
	public Avaliacao(Midia midia, int nota, String comentario, LocalDate data_da_avaliacao) {
		this.midia = midia;
		this.nota = nota;
		this.comentario = comentario;
		this.data_da_avaliacao = data_da_avaliacao;
	}
	
	public void exibir_informacoes() {
		System.out.println("-------------INFORMAÇÕES SOBRE A AVALIAÇÃO---------------");
		System.out.println("Título: " + this.midia.get_titulo());
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
		return this.get_data_da_avaliacao();
	}
	
}
