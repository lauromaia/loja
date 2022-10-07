package br.com.alura.loja.test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastraProdutoNovoTest {

	public static void main(String[] args) {
//
		cadastrarNovoProduto();
//		EntityManager em = JPAUtil.getEntityManager();
//		ProdutoDao produtoDao = new ProdutoDao(em);
//		CategoriaDao categoriaDao = new CategoriaDao(em);
//		Categoria categoria = new Categoria("Informatica");
//
////		Long id = 1l;
////		Produto produto = produtoDao.buscaPorId(id);
////		System.out.println(produto.getNome());
//
//		List<Produto> produtos = produtoDao.buscarTodos();
//
////		BigDecimal precoDoProduto = produtoDao.buscarPrecoPorNomeDoProduto("Samsung A31");
////		System.out.println(precoDoProduto);

	}

	private static void cadastrarNovoProduto() {
		cadastrarNovoProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		
		Produto p = produtoDao.buscaPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtoDao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getNome()));
	
		BigDecimal precoDoProduto = produtoDao.buscarPrecoPorNomeDoProduto("Xiaomi Redmi");
		System.out.println("Preco do Produto: " +precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi", "Muito legal", celulares, new BigDecimal("800"));
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		categoriaDao.cadastrar(celulares);
		produtoDao.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
