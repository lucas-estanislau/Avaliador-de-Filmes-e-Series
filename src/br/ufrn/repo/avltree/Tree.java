package br.ufrn.repo.avltree;

public interface Tree <T extends Comparable<T>>{

	public void inserir(T valor); //Insere um filme ou série na árvore
	public void remover(T valor); //Remove um filme ou série na árvore
	public T buscar(T valor); //Busca um elemento específico
	public java.util.List<T> emOrdem(); //Uma lista que estará em ordem
    boolean estaVazia(); //Verifica se a árvore está vazia
    public int getTamanho();
	
}

