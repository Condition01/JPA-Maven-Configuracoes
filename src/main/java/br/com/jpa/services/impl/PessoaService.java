package br.com.jpa.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.jpa.models.Pessoa;
import br.com.jpa.services.interfaces.CrudService;
import br.com.jpa.utils.JpaUtils;

public class PessoaService implements CrudService<Pessoa, Integer>{

	@Override
	public List<Pessoa> all() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EntityManager em = null;
		
		try {
			em = JpaUtils.getEntityManager();
			pessoas = em.createQuery("from Pessoa", Pessoa.class).getResultList(); //jpql ==> escrever consultar parecidas com sql mas alinhadas com as especificações da jpa
			return pessoas;
		}finally {
			if(em != null) {
				em.close(); //precisamos dar o close, pq as entidades que esse entitymanager gerencia estão atachadas a ele. 
			}
		}

	}

	@Override
	public Pessoa byId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa insert(Pessoa entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pessoa update(Pessoa entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Pessoa entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	
	
}
