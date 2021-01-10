package br.com.vvtest.pedidos.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.vvtest.pedidos.exception.InvalidFieldException;

@Entity(name = "pedido_item")
public class PedidoItem implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido", nullable = false)
	@JsonBackReference
	private Pedido pedido;

	@ManyToOne
	private Produto produto;

	private Integer quantidade;

	private Double valor;
	
	public PedidoItem() {}
	
	public PedidoItem(Pedido pedido, Produto produto, Integer quantidade, Double valor) {
		if (pedido == null) {
			throw new InvalidFieldException("Pedido não pode ser nulo no Item.");
		}
		
		if (produto == null) {
			throw new InvalidFieldException("Produto não pode ser nulo no Item.");
		}
		
		if (quantidade == null) {
			throw new InvalidFieldException("Quantidade não pode ser nulo no Item.");
		}
		
		if (valor == null) {
			throw new InvalidFieldException("Valor não pode ser nulo no Item.");
		}
		
		this.pedido = pedido;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
	public void atualizar(Integer quantidade, Double valor) {
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Long getIdItem() {
		return idItem;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Double getValor() {
		return valor;
	}
}
