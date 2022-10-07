package br.com.alura.loja.dao;

import javax.persistence.Entity;

import br.com.alura.loja.modelo.Produto;


@Entity
public class Livro extends Produto {
	private String autor;
	private Integer quantidadePaginas;

	public Livro(String autor, Integer quantidadePaginas) {
		super();
		this.autor = autor;
		this.quantidadePaginas = quantidadePaginas;
	}

	public Livro() {
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(Integer quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}

}
