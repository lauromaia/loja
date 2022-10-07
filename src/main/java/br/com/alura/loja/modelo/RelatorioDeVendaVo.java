package br.com.alura.loja.modelo;

import java.time.LocalDate;

public class RelatorioDeVendaVo {
	private String nome;
	private Long quantidade;
	private LocalDate data;
	public String getNome() {
		return nome;
	}
	public RelatorioDeVendaVo(String nome, Long quantidade, LocalDate data) {
		super();
		this.nome = nome;
		this.quantidade = quantidade;
		this.data = data;
	}
	@Override
	public String toString() {
		return "RelatorioDeVendaVo [nome=" + nome + ", quantidade=" + quantidade + ", data=" + data + "]";
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public LocalDate getData() {
		return data;
	}
	
}
