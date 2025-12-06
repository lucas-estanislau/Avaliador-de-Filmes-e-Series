package br.ufrn.repo.exception;

//excecao especifica para quantidade de campos insuficientes em filmes ou series 
public class NumeroCamposException extends Exception {
    public NumeroCamposException(String mensagem) {
        super(mensagem);
    }
}