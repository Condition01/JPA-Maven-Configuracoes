package br.com.jpa.ui;

import java.util.List;

import br.com.jpa.models.Pessoa;
import br.com.jpa.services.impl.PessoaService;
import br.com.jpa.services.interfaces.CrudService;

public class Main {
	public static void main(String[] args) {
		CrudService<Pessoa, Integer> pessoaService = new PessoaService();
		System.out.println("*****CRUD PESSOAS*****");
		System.out.println("> LISTA DE PESSOAS CADASTRADAS");
		try {
			List<Pessoa> pessoas = pessoaService.all();
			pessoas.forEach(pessoa -> { //expressão lambida
				System.out.println(String.format(" - (%d), %s %s - %d anos", pessoa.getId(), 
						pessoa.getNome(), pessoa.getSobrenome(), pessoa.getIdade()));
			});
			if(pessoas.isEmpty()) {
				System.out.println("não existem pessoas cadastradas");
			}
		}catch (Exception e) {
			 System.out.println(" erro --------------------------------------------");
			e.printStackTrace();
		}
	}
}
