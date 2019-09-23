package br.com.jpa.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;

import br.com.jpa.models.Pessoa;
import br.com.jpa.services.interfaces.CrudService;
import br.com.jpa.services.interfaces.PessoaBuscaNome;
import br.com.jpa.utils.JpaUtils;

public class PessoaService implements PessoaBuscaNome {

	@Override
	public List<Pessoa> all() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		EntityManager em = null;

		try {
			em = JpaUtils.getEntityManager();
			pessoas = em.createQuery("from Pessoa", Pessoa.class).getResultList(); // jpql ==> escrever consultar
																					// parecidas com sql mas alinhadas
																					// com as especificações da jpa
			return pessoas;
		} finally {
			if (em != null) {
				em.close(); // precisamos dar o close, pq as entidades que esse entitymanager gerencia estão
							// atachadas a ele.
			}
		}

	}

	@Override
	public Pessoa byId(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			return em.find(Pessoa.class, id);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public Pessoa insert(Pessoa entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity; // ele também reflete alterações que o banco de dados refletir a ele utilizando
							// a JPA.
		} finally {
			if (em != null) {
				em.close(); // precisamos dar o close, pq as entidades que esse entitymanager gerencia estão
							// atachadas a ele.
			}
		}

	}

	@Override
	public Pessoa update(Pessoa entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			// em.merge(entity); //com merge ele pega o counteudo e simplismente passa para
			// uma entidade que esta atachada no contexto da JPA.
			em.unwrap(Session.class).update(entity); // aqui estamos fazendo diretamente pelo hibernate e a vantagem de
														// utilizar esse metodo é ele não faz um select no banco,
														// reduzindo o numero de consultas
			em.getTransaction().commit();
			return entity;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void delete(Pessoa entity) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public void deleteById(Integer id) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
			Pessoa pessoaRemovida = em.find(Pessoa.class, id);
			if (pessoaRemovida != null) {
				em.getTransaction().begin();
				em.remove(pessoaRemovida);
				em.getTransaction().commit();
			}
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public List<Pessoa> searchByName(String name) {
		EntityManager em = null;
		try {
			em = JpaUtils.getEntityManager();
//			List<Pessoa> pessoas = em.createQuery("from Pessoa p where lower(p.nome) like "
//					+ "lower(concat('%', :nome, '%'))", Pessoa.class).setParameter("nome", name).getResultList();
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Pessoa> buscaPorNomeCriteria =  criteriaBuilder.createQuery(Pessoa.class);
			Root<Pessoa> raiz = buscaPorNomeCriteria.from(Pessoa.class);
			buscaPorNomeCriteria.where(criteriaBuilder.like(criteriaBuilder.lower(raiz.get("nome")), "%" + name.toLowerCase() + "%"));
 			List<Pessoa> pessoas = em.createQuery(buscaPorNomeCriteria).getResultList();
			return pessoas;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

}
