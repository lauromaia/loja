package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.RelatorioDeVendaVo;

public class PedidoDao {
	
	private EntityManager em;

	public PedidoDao(EntityManager em) {
		
		this.em = em;
	}
	
	public void cadastrar(Pedido pedido) {
		this.em.persist(pedido);
	}
	
	public BigDecimal valorTotalVendido() {
		String JPQL = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.em.createQuery(JPQL, BigDecimal.class)
				.getSingleResult();
	}
	
	public List<RelatorioDeVendaVo> relatorioDeVendas(){
		String JPQL = "SELECT new br.com.alura.loja.modelo.RelatorioDeVendaVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC";
		return this.em.createQuery(JPQL, RelatorioDeVendaVo.class)
				.getResultList();
	}
	
	public Pedido buscarPedidoPorId(Long id) {
		return this.em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
				.setParameter("id", id)
				.getSingleResult();
	}
}
