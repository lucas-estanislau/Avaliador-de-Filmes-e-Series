package br.ufrn.repo.sistema;

import java.util.Scanner;

public class Menu {
    private Scanner sc;
    private boolean verificadorAvaliacao;
    private boolean verificadorVisualizar;

    public Menu() {
        this.sc = new Scanner(System.in);
        this.verificadorAvaliacao = true;
        this.verificadorVisualizar = false;
    }

    public void exibir() {
        while(verificadorAvaliacao) {
            MenuAvaliacao();
            int opcao = lerOpcao();
            switchCaseAvaliacao(opcao);
        }
        sc.close();
    }

    private void MenuAvaliacao() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Adicionar Avaliação");
        System.out.println("2. Visualizar Avaliação");
        System.out.println("3. Editar Avaliação");
        System.out.println("4. Deletar Avaliação");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void MenuVisualizarAvaliacao() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Visualizar 5 filmes mais recentes");
        System.out.println("2. Visualizar 5 séries mais recentes");
        System.out.println("3. Voltar ao menu anterior");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void switchCaseAvaliacao(int opcao) {
        switch(opcao) {
            case 1:
                adicionarAvaliacao();
                break;
            case 2:
                this.verificadorVisualizar = true;
                while(verificadorVisualizar) {
                    MenuVisualizarAvaliacao();
                    opcao = lerOpcao();
                    switchCaseVisualizarAvaliacao(opcao);
                }
                break;
            case 3:
                editarAvaliacao();
                break;
            case 4:
                deletarAvaliacao();
                break;
            case 0:
                encerrar();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private void switchCaseVisualizarAvaliacao(int opcao) {
        switch(opcao) {
            case 1:
                exibirFilmes();
                break;
            case 2:
                exibirSeries();
                break;
            case 3:
                this.verificadorVisualizar = false;
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    // Métodos do menu de avaliação
    private void adicionarAvaliacao() {
        System.out.println("========== ADICIONAR AVALIAÇÃO ==========");
        // Adicionar o metodo de adicionar avaliação
    }

    private void visualizarAvaliacao() {
        System.out.println("========== VISUALIZAR AVALIAÇÕES ==========");
        // Implementação
    }

    private void editarAvaliacao() {
        System.out.println("========== EDITAR AVALIAÇÃO ==========");
        // Implementação
    }

    private void deletarAvaliacao() {
        System.out.println("========== DELETAR AVALIAÇÃO ==========");
        // Implementação
    }

    private void encerrar() {
        System.out.println("Encerrando programa...");
        this.verificadorAvaliacao = false;
    }

    // Métodos do menu de visualizar avaliações
    private void exibirFilmes() {
        System.out.println("========== FILMES ==========");
    }

    private void exibirSeries() {
        System.out.println("========== SÉRIES ==========");
    }
}
