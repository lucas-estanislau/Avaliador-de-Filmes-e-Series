package br.ufrn.repo.sistema;

import java.time.LocalDate;
import java.util.Scanner;

import br.ufrn.repo.annotations.FuncionamentoMetodo;
import br.ufrn.repo.annotations.InfoAutor;
import br.ufrn.repo.audiovisual.Midia;
import br.ufrn.repo.avaliacao.Avaliacao;

public class Menu {
    private Scanner sc;
    private boolean verificadorAvaliacao;
    private boolean verificadorVisualizar;
    private Sistema sistema;

    public Menu() {
        lerAnotacaoInfoAutor();
        this.sc = new Scanner(System.in);
        this.verificadorAvaliacao = true;
        this.verificadorVisualizar = false;
        this.sistema = new Sistema();
    }

    public void exibir() {
        while (verificadorAvaliacao) {
            menuAvaliacao();;
            int opcao = lerOpcao();
            switchCaseAvaliacao(opcao);
        }
        sc.close();
    }

    private void menuAvaliacao() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Adicionar Avaliação");
        System.out.println("2. Visualizar Avaliação");
        System.out.println("3. Editar Avaliação");
        System.out.println("4. Deletar Avaliação");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void menuVisualizarAvaliacao() {
        System.out.println("\n========== MENU ==========");
        System.out.println("1. Visualizar filmes avaliados");
        System.out.println("2. Visualizar séries avaliados");
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
        switch (opcao) {
            case 1:
                adicionarAvaliacao();
                break;
            case 2:
                this.verificadorVisualizar = true;
                while (verificadorVisualizar) {
                    menuVisualizarAvaliacao();
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
        switch (opcao) {
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
        System.out.println("\n========== ADICIONAR AVALIAÇÃO ==========\n");
        Scanner sc = new Scanner(System.in);

        System.out.print("> Qual filme/série você quer avaliar? ");
        String midia = sc.nextLine();

        Midia midiaEncontrada = sistema.buscarMidia(midia);
        System.out.println("");
        System.out.println(midiaEncontrada); // Mostra as informações da mídia
        System.out.print("\nDeseja avaliar " + midiaEncontrada.get_titulo() + " (s/n)? "); // Confirmação
        String opcao = sc.nextLine();

        switch (opcao) {
            case "s":
                Integer nota = sistema.notaAvaliacao();

                System.out.println("Digite seu comentário: ");
                String comentario = sc.nextLine();

                LocalDate data = LocalDate.now(); // Pega a data atual

                Avaliacao novaAvaliacao = new Avaliacao(midiaEncontrada, nota, comentario, data); // Cria a avaliação com os parametros
                sistema.adicionarAvaliacao(novaAvaliacao); // Adiciona a avaliação na árvore
                break;

            case "n":
                break;

            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }

    private Avaliacao editarAvaliacao() {
        System.out.println("\n========== EDITAR AVALIAÇÃO ==========\n");

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Editar Filmes");
        System.out.println("2. Editar Series");
        System.out.println("3. Voltar ao menu anterior");
        System.out.print("> Escolha uma opção: ");
        int opcao = Integer.parseInt(sc.nextLine());

        switch (opcao){
            case 1:
                sistema.imprimirFilmesAvaliados();
                System.out.print("Digite qual item deseja editar: ");
                int indiceFilme = Integer.parseInt(sc.nextLine()) - 1;
                Integer novaNota = sistema.notaAvaliacao();
                break;
            case 2:
                sistema.imprimirSeriesAvaliados();
                System.out.print("Digite qual item deseja editar: ");
                int indiceSerie = Integer.parseInt(sc.nextLine());
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
        return null;
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
        sistema.imprimirFilmesAvaliados();
    }

    private void exibirSeries() {
        System.out.println("========== SÉRIES ==========");
        sistema.imprimirSeriesAvaliados();
    }

    @FuncionamentoMetodo(funcionamento="Ler os elementos da anotação InfoAutor via reflexão e imprime-os.")
    private void lerAnotacaoInfoAutor() {
        Class<Avaliacao> clazz = Avaliacao.class;
        InfoAutor anot = clazz.getAnnotation(InfoAutor.class);
        imprimirAnotacao(anot);
    }
    private void imprimirAnotacao(InfoAutor anot) {
        System.out.println("Autores: " + anot.nome());
        System.out.println("Data: " + anot.data());
        System.out.println("Versão projeto: " + anot.versaoProjeto());
    }
}
