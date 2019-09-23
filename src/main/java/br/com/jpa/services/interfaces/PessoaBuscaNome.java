package br.com.jpa.services.interfaces;

import java.util.List;

import br.com.jpa.models.Pessoa;

public interface PessoaBuscaNome extends CrudService<Pessoa, Integer>{
	
	List<Pessoa> searchByName(String name);
	
	
}
