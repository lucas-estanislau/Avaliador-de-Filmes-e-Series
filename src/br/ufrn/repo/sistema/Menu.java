package br.ufrn.repo.sistema;

import java.time.LocalDate;
import java.util.Scanner;

import br.ufrn.repo.annotations.FuncionamentoClasse;
import br.ufrn.repo.annotations.FuncionamentoMetodo;
import br.ufrn.repo.annotations.InfoAutor;
import br.ufrn.repo.audiovisual.Midia;
import br.ufrn.repo.avaliacao.Avaliacao;

@FuncionamentoClasse(funcionamento = "Classe responsável por lidar com os menus que serão exibidos na classe Main.")
public class Menu {
	private Scanner sc;
	private boolean verificadorAvaliacao;
	private Sistema sistema;

	public Menu() {
		lerAnotacaoInfoAutor();
		this.sc = new Scanner(System.in);
		this.verificadorAvaliacao = true;
		this.sistema = new Sistema();
	}

	public void exibir() {
		while (verificadorAvaliacao) {
			menuAvaliacao();
			int opcao = lerOpcao();
			switchCaseAvaliacao(opcao);
		}
		sc.close();
	}

	private void menuAvaliacao() {
		System.out.println("\n========== MENU ==========\n");
		System.out.println("1. Adicionar Avaliação");
		System.out.println("2. Visualizar Avaliação");
		System.out.println("3. Editar Avaliação");
		System.out.println("4. Deletar Avaliação");
		System.out.println("0. Sair");
		System.out.print("Escolha uma opção: ");
	}

	private void menuOpcoesVisualizarAvaliacao() {
		System.out.println("\n========== MENU ==========\n");
		System.out.println("1. Visualizar avaliações recentes");
		System.out.println("2. Visualizar todas as avaliações");
		System.out.println("3. Voltar ao menu anterior");
		System.out.print("Escolha uma opção: ");
	}

	private void menuVisualizarAvaliacao() {
		System.out.println("\n========== MENU ==========\n");
		System.out.println("1. Visualizar avaliações de filmes");
		System.out.println("2. Visualizar avaliações de séries");
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
			menuOpcoesVisualizarAvaliacao();
			int opcaoVisualizacao = lerOpcao();
			switch (opcaoVisualizacao) {
			case 1:
				switchVisualizarAvaliacoesMaisRecentes();

				break;
			case 2:
				switchVisualizarTodasAvaliacoes();

				break;
			case 3:
				break;
			default:
				System.out.println("Erro: opção inválida.");
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

	@FuncionamentoMetodo(funcionamento = "Método do menu de adicionar avaliações.")
	private void adicionarAvaliacao() {
		System.out.println("\n========== ADICIONAR AVALIAÇÃO ==========\n");
		try {
			System.out.print("> Qual filme/série você quer avaliar? ");
			String midia = sc.nextLine();

			Midia midiaEncontrada = sistema.buscarMidia(midia);
			System.out.println("");

			if (sistema.buscarAvalicao(midiaEncontrada.get_titulo()) == null) {
				System.out.println(midiaEncontrada);
				System.out.print("\nDeseja avaliar " + midiaEncontrada.get_titulo() + " (s/n)? ");
				String opcao = sc.nextLine();

				switch (opcao) {
				case "s":
					Integer nota = sistema.notaAvaliacao();
					System.out.println("Digite seu comentário: ");
					String comentario = sc.nextLine();
					LocalDate data = LocalDate.now();
					Avaliacao novaAvaliacao = new Avaliacao(midiaEncontrada, nota, comentario, data);
					sistema.adicionarAvaliacao(novaAvaliacao);
					System.out.println("Avaliação adicionada com sucesso!");
					break;
				case "n":
					System.out.println("Avaliação cancelada.");
					break;
				default:
					System.out.println("Opção inválida! Tente novamente.");
				}
			} else {
				System.out.println("Erro: Avaliação de filme ou série já adicionada! Edite-a se desejar.");
			}
		} catch (NullPointerException e) {
			System.out.println("Erro: mídia não encontrada.");
		}
	}

	@FuncionamentoMetodo(funcionamento = "Método do menu de editar avaliações.")
	private void editarAvaliacao() {
		System.out.println("\n========== EDITAR AVALIAÇÃO ==========\n");
		System.out.println("1. Editar Filmes");
		System.out.println("2. Editar Séries");
		System.out.println("3. Voltar ao menu anterior");
		System.out.print("> Escolha uma opção: ");
		int opcao = lerOpcao();

		switch (opcao) {
		case 1:
			if (sistema.getFilmesAvaliados().isEmpty()) {
				System.out.println("\nNão há filmes avaliados.");
				break;
			}
			sistema.imprimirFilmesAvaliados();
			System.out.print("Digite qual item deseja editar: ");
			try {
				int indiceFilme = lerOpcao() - 1;
				Midia filmeEncontrado = sistema.getFilmesAvaliados().get(indiceFilme).get_midia();
				Integer novaNotaFilme = sistema.notaAvaliacao();
				System.out.println("Digite seu comentário: ");
				String novoComentarioFilme = sc.nextLine();
				sistema.editarAvaliacao(filmeEncontrado.get_titulo(), novaNotaFilme, novoComentarioFilme);
				System.out.println("Avaliação de filme editada com sucesso!");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nErro: Digite um índice válido!");
			}
			break;
		case 2:
			if (sistema.getSeriesAvaliados().isEmpty()) {
				System.out.println("\nNão há séries avaliadas.");
				break;
			}
			sistema.imprimirSeriesAvaliados();
			System.out.print("Digite qual item deseja editar: ");
			try {
				int indiceSerie = lerOpcao() - 1;
				Midia serieEncontrada = sistema.getSeriesAvaliados().get(indiceSerie).get_midia();
				Integer novaNotaSerie = sistema.notaAvaliacao();
				System.out.println("Digite seu comentário: ");
				String novoComentarioSerie = sc.nextLine();
				sistema.editarAvaliacao(serieEncontrada.get_titulo(), novaNotaSerie, novoComentarioSerie);
				System.out.println("Avaliação de série editada com sucesso!");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nErro: Digite um índice válido!");
			}
			break;
		case 3:
			break;
		default:
			System.out.println("\nOpção inválida! Tente novamente.");
		}
	}

	@FuncionamentoMetodo(funcionamento = "Método do menu de deletar avaliações.")
	private void deletarAvaliacao() {
		System.out.println("\n========== DELETAR AVALIAÇÃO ==========\n");
		System.out.println("1. Deletar Filmes");
		System.out.println("2. Deletar Séries");
		System.out.println("3. Voltar ao menu anterior");
		System.out.print("> Escolha uma opção: ");
		int opcao = lerOpcao();

		switch (opcao) {
		case 1:
			if (sistema.getFilmesAvaliados().isEmpty()) {
				System.out.println("\nNão há filmes avaliados.");
				break;
			}
			sistema.imprimirFilmesAvaliados();
			System.out.print("\nDigite qual item deseja deletar: ");
			try {
				int indiceFilme = lerOpcao() - 1;
				sistema.removerAvaliacao(sistema.getFilmesAvaliados().get(indiceFilme), opcao);
				System.out.println("Avaliação de filme deletada com sucesso!");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nErro: Digite um índice válido!");
			}
			break;
		case 2:
			if (sistema.getSeriesAvaliados().isEmpty()) {
				System.out.println("\nNão há séries avaliadas.");
				break;
			}
			sistema.imprimirSeriesAvaliados();
			System.out.print("\nDigite qual item deseja deletar: ");
			try {
				int indiceSerie = lerOpcao() - 1;
				sistema.removerAvaliacao(sistema.getSeriesAvaliados().get(indiceSerie), opcao);
				System.out.println("Avaliação de série deletada com sucesso!");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("\nErro: Digite um índice válido!");
			}
			break;
		case 3:
			break;
		default:
			System.out.println("\nOpção inválida! Tente novamente.");
		}
	}

	private void encerrar() {
		System.out.println("Encerrando programa...");
		verificadorAvaliacao = false;
	}

	@FuncionamentoMetodo(funcionamento = "Métodos do menu de visualizar avaliações.")
	private void exibirFilmes() {
		System.out.println("\n========== FILMES ==========");
		sistema.imprimirFilmesAvaliados();
	}

	private void exibirFilmesRecentes() {
		System.out.println("\n========== 5 AVALIAÇÕES MAIS RECENTES DE FILMES ==========");
		sistema.imprimirFilmesAvaliadosRecentemente();
	}

	private void exibirSeries() {
		System.out.println("\n========== SÉRIES ==========");
		sistema.imprimirSeriesAvaliados();
	}

	private void exibirSeriesRecentes() {
		System.out.println("\n========== 5 AVALIAÇÕES MAIS RECENTES DE SÉRIES ==========");
		sistema.imprimirSeriesAvaliadosRecentemente();
	}

	private void switchVisualizarTodasAvaliacoes() {
		menuVisualizarAvaliacao();
		int opcao = lerOpcao();
		switch (opcao) {
		case 1:
			if (sistema.getFilmesAvaliados().isEmpty()) {
				System.out.println("\nNão há filmes avaliados.");
				break;
			}
			exibirFilmes();
			break;

		case 2:
			if (sistema.getSeriesAvaliados().isEmpty()) {
				System.out.println("\nNão há séries avaliadas.");
				break;
			}
			exibirSeries();
			break;

		case 3:
			break;

		default:
			System.out.println("Opção inválida! Tente novamente.");
		}
	}

	private void switchVisualizarAvaliacoesMaisRecentes() {

		menuVisualizarAvaliacao();
		int opcao = lerOpcao();

		switch (opcao) {
		case 1:
			if (sistema.getFilmesAvaliados().isEmpty()) {
				System.out.println("\nNão há filmes avaliados.");
				break;
			}
			exibirFilmesRecentes();
			break;

		case 2:
			if (sistema.getSeriesAvaliados().isEmpty()) {
				System.out.println("\nNão há séries avaliadas.");
				break;
			}
			exibirSeriesRecentes();
			break;

		case 3:
			break;

		default:
			System.out.println("Opção inválida! Tente novamente.");
		}
	}

	@FuncionamentoMetodo(funcionamento = "Ler os elementos da anotação InfoAutor via reflexão e imprime-os.")
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
