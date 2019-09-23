package br.com.jpa.ui;

import java.util.List;
import java.util.Scanner;

import br.com.jpa.models.Pessoa;
import br.com.jpa.services.impl.PessoaService;
import br.com.jpa.services.interfaces.CrudService;
import br.com.jpa.services.interfaces.PessoaBuscaNome;

public class Main {
	public static void main(String[] args) {
		int opcao = 0;
		while (opcao != 6) {
			Scanner sc = new Scanner(System.in);
			System.out.println("\nEscolha uma ação : ");
			System.out.println("Listar pessoa");
			System.out.println("Inserir pessoa");
			System.out.println("Atualizar pessoa");
			System.out.println("Excluir pessoa");
			System.out.println("Pesquisar pessoa por nome");
			System.out.println("Sair");
			System.out.println("\nSua opção: ");
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				listarPessoas();
				break;
			case 2:
				inserirPessoa();
				break;
			case 3:
				atualizarPessoa();
				break;
			case 4:
				deletarPessoa();
				break;
			case 5:
				pesquisarPessoaNome();
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + opcao);
			}
		}
		System.out.println("Tchau!");
	}

	private static void pesquisarPessoaNome() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\n ** Pesquisa de pessoa por nome");
		System.out.println(" - Digite o nome a ser pesquisado");
		String nomePesq = sc.nextLine();
		PessoaBuscaNome pessoaService = new PessoaService();
		pessoaService.searchByName(nomePesq).forEach(pessoa -> {
			System.out.println(String.format(" - (%d), %s %s - %d anos", pessoa.getId(), pessoa.getNome(),
					pessoa.getSobrenome(), pessoa.getIdade()));
		});
	}

	private static void deletarPessoa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("** Remoção de pessoa **");
		System.out.println("Digite o id da pessoa a ser removida: ");
		int idPes = sc.nextInt();
		sc.nextLine();
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		pessoaService.deleteById(idPes);
		System.out.println("Pessoa removida com sucesso!");
	}

	private static void atualizarPessoa() {
		Scanner sc = new Scanner(System.in);
		System.out.println("**Atualização de Pessoa**");
		System.out.print("Digite o id da pessoa a ser atualizada");
		int idPessoa = sc.nextInt();
		sc.nextLine();
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		Pessoa pessoaAtual = pessoaService.byId(idPessoa);
		if (pessoaAtual != null) {
			System.out.println("Pessoa encontrada");
			System.out.println(String.format("-- Nome: %s", pessoaAtual.getNome()));
			System.out.println(String.format("-- Sobrenome: %s", pessoaAtual.getSobrenome()));
			System.out.println(String.format("-- Idade: %d\n", pessoaAtual.getIdade()));
			System.out.println(" -- Novo nome: ");
			pessoaAtual.setNome(sc.nextLine());
			System.out.println(" -- Novo sobrenome: ");
			pessoaAtual.setSobrenome(sc.nextLine());
			System.out.println(" -- Nova idade: ");
			pessoaAtual.setIdade(sc.nextInt());
			pessoaService.update(pessoaAtual);
			System.out.println("Pessoa atualizada com sucesso!");
		} else {
			System.out.println("Não existem pessoas com esse id");
		}
	}

	private static void inserirPessoa() {
		System.out.println("\n ** Incluindo pessoa **");
		Scanner sc = new Scanner(System.in);
		Pessoa novaPessoa = new Pessoa();
		System.out.println("Nome: ");
		novaPessoa.setNome(sc.nextLine());
		System.out.println("Sobrenome: ");
		novaPessoa.setSobrenome(sc.nextLine());
		System.out.println("Idade: ");
		novaPessoa.setIdade(sc.nextInt());
		sc.nextLine();
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		pessoaService.insert(novaPessoa);
		System.out.println("Pessoa inserida com sucesso!!");
	}

	private static void listarPessoas() {
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		System.out.println("*****CRUD PESSOAS*****");
		System.out.println("> LISTA DE PESSOAS CADASTRADAS");
		try {
			List<Pessoa> pessoas = pessoaService.all();
			pessoas.forEach(pessoa -> { // expressão lambida
				System.out.println(String.format(" - (%d), %s %s - %d anos", pessoa.getId(), pessoa.getNome(),
						pessoa.getSobrenome(), pessoa.getIdade()));
			});
			if (pessoas.isEmpty()) {
				System.out.println("não existem pessoas cadastradas");
			}
		} catch (Exception e) {
			System.out.println(" erro --------------------------------------------");
			e.printStackTrace();
		}
	}
}
