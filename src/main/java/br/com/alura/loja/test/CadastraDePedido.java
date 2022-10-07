package br.com.alura.loja.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.modelo.RelatorioDeVendaVo;
import br.com.alura.loja.util.JPAUtil;

public class CadastraDePedido {

	public static void main(String[] args) {
		popularBancoDeDados();
		
		
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		ClienteDao clienteDao = new ClienteDao(em);
		ProdutoDao produtoDao = new ProdutoDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);
		
		Cliente cliente = clienteDao.buscaPorId(1l);
		
		Produto produto = produtoDao.buscaPorId(1l);
		Produto produto2 = produtoDao.buscaPorId(2l);
		Produto produto3 = produtoDao.buscaPorId(3l);
		
		
		Pedido pedido = new Pedido(cliente);
		pedido.adicionarItem(new ItemPedido(10, produto, pedido));
		pedido.adicionarItem(new ItemPedido(5, produto2, pedido));
		pedido.adicionarItem(new ItemPedido(3, produto3, pedido));
		
		Pedido pedido2 = new Pedido(cliente);
		
		pedido2.adicionarItem(new ItemPedido(4, produto, pedido2));
		pedido2.adicionarItem(new ItemPedido(5, produto2, pedido2));
		pedido2.adicionarItem(new ItemPedido(10, produto3, pedido2));
		pedidoDao.cadastrar(pedido);
		pedidoDao.cadastrar(pedido2);
		
		em.getTransaction().commit();
		
		BigDecimal valorTotal = pedidoDao.valorTotalVendido();
		
		System.out.println("Valor total do pedido: " + valorTotal);
		
		List<RelatorioDeVendaVo> relatorioDeVendas = pedidoDao.relatorioDeVendas();
		
		relatorioDeVendas.forEach(System.out::print);
		
		
		
		

		
		
	}

	private static void popularBancoDeDados() {
		Categoria celulares = new Categoria("CELULARES");
		Categoria informatica = new Categoria("INFORMATICA");
		Categoria videogames = new Categoria("VIDEOGAMES");
		Produto videogame = new Produto("PS5", "Playstation 5", videogames, new BigDecimal("4000"));
		Produto notebook = new Produto("Acer 514", "Notebook Acer", informatica, new BigDecimal("3600"));
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", celulares, new BigDecimal("800"));
		Cliente cliente = new Cliente("Fulano", "123456");
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		
		
		em.getTransaction().begin();
		
		clienteDao.cadastrar(cliente);
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		produtoDao.cadastrar(notebook);
		produtoDao.cadastrar(videogame);
		categoriaDao.cadastrar(videogames);
		categoriaDao.cadastrar(informatica);
		
		
		
		em.getTransaction().commit();
		em.close();
	}

}
