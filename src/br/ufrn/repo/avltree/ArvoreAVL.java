package br.ufrn.repo.avltree;

import java.util.ArrayList;
import java.util.List;

public class ArvoreAVL <T extends Comparable<T>> implements Tree<T>{
    private No<T> raiz;
    private int tamanho = 0;

    public ArvoreAVL(){
        this.raiz = null;
    }
    public No<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(No<T> raiz) {
        this.raiz = raiz;
    }

    private int altura(No<T> no){
        if(no == null){
            return 0;
        } else{
           return no.getAltura();
        }
    }

    private int fatorBalanceamento(No<T> no){
        if (no == null) {
            return 0;
        } else{
        return altura(no.getEsquerdo()) - altura(no.getDireito());
    }
    }

    private No<T> rotacaoDireita(No<T> raizOriginal){
        No<T> novaRaiz = raizOriginal.getEsquerdo();
        No<T> filhoAdotado = novaRaiz.getDireito();
        novaRaiz.setDireito(raizOriginal);
        raizOriginal.setEsquerdo(filhoAdotado);

        int alturaOriginal = Math.max(altura(raizOriginal.getEsquerdo()), altura(raizOriginal.getDireito())) + 1;
        raizOriginal.setAltura(alturaOriginal);

        int alturaNova = Math.max(altura(novaRaiz.getEsquerdo()), altura(novaRaiz.getDireito())) + 1;
        novaRaiz.setAltura(alturaNova);
        return novaRaiz;

    }

    private No<T> rotacaoEsquerda(No<T> raizOriginal){
        No<T> novaRaiz = raizOriginal.getDireito();
        No<T> filhoAdotado = novaRaiz.getEsquerdo();
        
        novaRaiz.setEsquerdo(raizOriginal);
        raizOriginal.setDireito(filhoAdotado);

        int alturaOriginal = Math.max(altura(raizOriginal.getEsquerdo()), altura(raizOriginal.getDireito())) + 1;
        raizOriginal.setAltura(alturaOriginal);

        int alturaNova = Math.max(altura(novaRaiz.getEsquerdo()), altura(novaRaiz.getDireito())) + 1;
        novaRaiz.setAltura(alturaNova);
        return novaRaiz;
    }

    private No<T> balancear(No<T> noAvaliado){
       int fator = fatorBalanceamento(noAvaliado);

       if(fator > 1){
           if (fatorBalanceamento(noAvaliado.getEsquerdo()) < 0){
               noAvaliado.setEsquerdo(rotacaoEsquerda(noAvaliado.getEsquerdo()));
           } return rotacaoDireita(noAvaliado);

       } if(fator < -1 ){
           if(fatorBalanceamento(noAvaliado.getDireito()) > 0){
               noAvaliado.setDireito(rotacaoDireita(noAvaliado.getDireito()));
           } return rotacaoEsquerda(noAvaliado);
        } return noAvaliado;
    }

    private No<T> inserirRecursivo(No<T> no, T valor){
        if (no == null) {
            return new No<>(valor);
        }
        int comparacao = valor.compareTo(no.getValor());

        if (comparacao < 0) {
            no.setEsquerdo(inserirRecursivo(no.getEsquerdo(), valor));
        } else if (comparacao > 0) {
            no.setDireito(inserirRecursivo(no.getDireito(), valor));
        } else {
            return no;
        }
        no.setAltura(1 + Math.max(altura(no.getEsquerdo()), altura(no.getDireito())));
        return balancear(no);
    }

    private No<T> buscarRecursivo(No<T> no, T valor) {
        if (no == null) {
            return null;
        }

        int comparacao = valor.compareTo(no.getValor());

        if (comparacao == 0) {
            return no;
        }
        if (comparacao < 0) {
            return buscarRecursivo(no.getEsquerdo(), valor);
        } else {
            return buscarRecursivo(no.getDireito(), valor);
        }
    }
    private void percorrerEmOrdemRecursivo(No<T> no, List<T> lista) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.getEsquerdo(), lista);
            lista.add(no.getValor());
            percorrerEmOrdemRecursivo(no.getDireito(), lista);
        }
    }

    private No<T> removerRecursivo(No<T> no, T valor) {
        if (no == null) return null;

        int comparacao = valor.compareTo(no.getValor());

        if (comparacao < 0) {
            no.setEsquerdo(removerRecursivo(no.getEsquerdo(), valor));
        } else if (comparacao > 0) {
            no.setDireito(removerRecursivo(no.getDireito(), valor));
        } else {
            if ((no.getEsquerdo() == null) || (no.getDireito() == null)) {
                No<T> temp = null;
                if (no.getEsquerdo() != null) {
                    temp = no.getEsquerdo();
                } else {
                    temp = no.getDireito();
                }
                no = temp;
            } else {
                No<T> temp = menorNo(no.getDireito());
                no.setValor(temp.getValor());
                no.setDireito(removerRecursivo(no.getDireito(), temp.getValor()));
            }
        }

        if (no == null) {
            return null;
        }

        no.setAltura(Math.max(altura(no.getEsquerdo()), altura(no.getDireito())) + 1);
        return balancear(no);
    }

    private No<T> menorNo(No<T> no) {
        No<T> atual = no;
        while (atual.getEsquerdo() != null) {
            atual = atual.getEsquerdo();
        }
        return atual;
    }

    public void imprimirTree(No no){
        if(no!=null){
            imprimirTree(no.getEsquerdo());
            System.out.println(no.getValor());
            imprimirTree(no.getDireito());
        }
    }

    @Override
    public void inserir(T valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
        this.tamanho++;
    }

    @Override
    public void remover(T valor) {
        this.raiz = removerRecursivo(this.raiz, valor);
        if (this.tamanho > 0) this.tamanho--;
    }

    @Override
    public T buscar(T valor) {
        No<T> resultado = buscarRecursivo(this.raiz, valor);
        if (resultado != null) {
            return resultado.getValor();
        }
        return null;
    }

    @Override
    public List<T> emOrdem() {
        List<T> lista = new ArrayList<>();
        percorrerEmOrdemRecursivo(this.raiz, lista);
        return lista;
    }

    @Override
    public boolean estaVazia() {
        return this.raiz == null;
    }

    @Override
    public int getTamanho() {
        return this.tamanho;
    }

}