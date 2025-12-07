package br.ufrn.repo.exception;

import br.ufrn.repo.annotations.FuncionamentoClasse;

@FuncionamentoClasse(funcionamento="Classe que é uma exceção específica para quantidade de campos insuficientes em filmes ou séries.")
 
public class NumeroCamposException extends Exception {
 
	private static final long serialVersionUID = 1L;

	public NumeroCamposException(String mensagem) {
        super(mensagem);
    }
}