package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "itens_pedido")
public class ItemPedido {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidade;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;
	
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	
	public ItemPedido(Integer quantidade, Produto produto, Pedido pedido) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
		this.precoUnitario = produto.getPreco();
		
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public ItemPedido() {
	}

	public BigDecimal valorPedido() {
		return this.precoUnitario.multiply(new BigDecimal(quantidade));
	}


	
	
	
}
