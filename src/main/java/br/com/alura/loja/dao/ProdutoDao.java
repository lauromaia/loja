package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		
		this.em = em;
	}
	
	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}
	
	public void atualizar(Produto produto) {
		this.em.merge(produto);
	}
	
	public void remover(Produto produto) {
		produto = em.merge(produto);
		this.em.remove(produto);
	}
	
	public Produto buscaPorId(Long id) {
		return this.em.find(Produto.class, id);
	}
	
	public List<Produto> buscarTodos(){
		String JPQL = "SELECT p FROM Produto p";
		return this.em.createQuery(JPQL, Produto.class).getResultList();
	}
	
	public List<Produto> buscarPorNome(String nome){
		String JPQL = "SELECT p FROM Produto p WHERE p.nome = :nome";
		return this.em.createQuery(JPQL, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String nome){
		String JPQL = "SELECT p FROM Produto p WHERE p.categoria.nome = :categoria";
		return this.em.createQuery(JPQL, Produto.class)
				.setParameter("categoria", nome)
				.getResultList();
	}
	
	public BigDecimal buscarPrecoPorNomeDoProduto(String nome){
		String JPQL = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
		return this.em.createQuery(JPQL, BigDecimal.class)
				.setParameter("nome", nome)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorParametros(String nome, 
			BigDecimal preco, LocalDate data) {
		String jpql = "SELECT p FROM Produto p WHERE 1=1 ";
		if (nome != null && !nome.trim().isEmpty()) {
			jpql += " AND p.nome = :nome ";
		}
		if (preco != null) {
			jpql += " AND p.preco = :preco ";
		}
		if (data != null) {
			jpql += " AND p.data = :data ";
		}
		TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		if (preco != null) {
			query.setParameter("preco", preco);
		}
		if (data != null) {
			query.setParameter("data", data);
		}
		
		return query.getResultList();
	}
	
	public List<Produto> buscarPorParametrosComCriteria(String nome, 
			BigDecimal preco, LocalDate dataCadastro) {
	
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
	



}
