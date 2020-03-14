package br.com.brasilprev.pedidos.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.brasilprev.pedidos.exception.InvalidFieldException;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPedido;

	private Date data;

	@ManyToOne
	private Cliente cliente;

	private String status;

	private String sessao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
	private List<PedidoItem> itens;
	
	public Pedido() {}
	
	public Pedido(Date data, Cliente cliente, String status, String sessao) {
		if (data == null) {
			throw new InvalidFieldException("Data não pode ser nula no Pedido.");
		}
		
		if (cliente == null) {
			throw new InvalidFieldException("Cliente não pode ser nulo no Pedido.");
		}
		
		if (status == null) {
			throw new InvalidFieldException("Status não pode ser nulo no Pedido.");
		}
		
		if (sessao == null) {
			throw new InvalidFieldException("Sessão não pode ser nulo no Pedido.");
		}
		
		this.data = data;
		this.cliente = cliente;
		this.status = status;
		this.sessao = sessao;
	}
	
	public void atualizar(Date data, String status, String sessao) {
		this.data = data;
		this.status = status;
		this.sessao = sessao;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public Date getData() {
		return data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getStatus() {
		return status;
	}

	public String getSessao() {
		return sessao;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}
}