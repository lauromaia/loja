package br.com.alura.loja.test;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class PerformanceConsultas {

	public static void main(String[] args) {
		popularBancoDeDados();
		EntityManager em = JPAUtil.getEntityManager();
		PedidoDao pedidoDao = new PedidoDao(em);
		
		Pedido pedido = pedidoDao.buscarPedidoPorId(1l);
		
		em.close();
		System.out.println(pedido.getCliente().getNome());

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
