package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;

public class CategoriaDao {
	
	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		
		this.em = em;
	}
	
	public void cadastrar(Categoria categoria) {
		this.em.persist(categoria);
	}
	
	public void atualizar(Categoria categoria) {
		this.em.merge(categoria);
	}
	
	public void remover(Categoria categoria) {
		categoria = this.em.merge(categoria);
		this.em.remove(categoria);
	}
	
	public Categoria buscaPorId(Long id) {
		return this.em.find(Categoria.class, id);
	}
	
	public List<Categoria> buscarTodos(){
		String JPQL = "SELECT P FROM Categoria p";
		return this.em.createQuery(JPQL, Categoria.class).getResultList();
	}
}