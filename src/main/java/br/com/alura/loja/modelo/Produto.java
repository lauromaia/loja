package br.com.alura.loja.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produtos")
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto {
	
	public Produto() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate data =  LocalDate.now();
	private BigDecimal preco;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Categoria categoria;
	
	
	public Produto(String nome, String descricao, Categoria categoria, BigDecimal preco) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.preco = preco;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	

}
