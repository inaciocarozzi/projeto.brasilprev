package br.com.vvtest.pedidos.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.vvtest.pedidos.exception.InvalidFieldException;

@Entity
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProduto;

	@ManyToOne
	private Categoria categoria;

	private String nome;

	private Double preco;

	private Integer quantidade;

	private String descricao;

	private String urlFoto;
	
	public Produto() {}
	
	public Produto(Categoria categoria, String nome, Double preco, Integer quantidade, String descricao, 
			String urlFoto) {
		
		if (categoria == null) {
			throw new InvalidFieldException("Categoria não pode ser nula no Produto.");
		}
		
		if (nome == null) {
			throw new InvalidFieldException("Nome não pode ser nulo no Produto.");
		}
		
		if (preco == null) {
			throw new InvalidFieldException("Preço não pode ser nulo no Produto.");
		}
		
		if (quantidade == null) {
			throw new InvalidFieldException("Quantidade não pode ser nula no Produto.");
		}
		
		if (descricao == null) {
			throw new InvalidFieldException("Descrição não pode ser nula no Produto.");
		}
		
		if (urlFoto == null) {
			throw new InvalidFieldException("URL da foto não pode ser nula no Produto.");
		}
		
		this.categoria = categoria;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.urlFoto = urlFoto;
	}
	
	public void atualizar(String nome, Double preco, Integer quantidade, String descricao,
			String urlFoto) {
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.urlFoto = urlFoto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public String getNome() {
		return nome;
	}

	public Double getPreco() {
		return preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getUrlFoto() {
		return urlFoto;
	}
}
