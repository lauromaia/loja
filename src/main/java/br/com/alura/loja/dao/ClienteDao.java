package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Produto;

public class ClienteDao {
	
	private EntityManager em;

	public ClienteDao(EntityManager em) {
		
		this.em = em;
	}
	
	public void cadastrar(Cliente cliente) {
		this.em.persist(cliente);
	}
	
	
	public Cliente buscaPorId(Long id) {
		return this.em.find(Cliente.class, id);
	}
	
	
}
