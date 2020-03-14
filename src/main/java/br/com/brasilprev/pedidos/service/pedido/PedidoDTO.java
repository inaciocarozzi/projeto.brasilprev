package br.com.brasilprev.pedidos.service.pedido;

import java.util.Date;

import br.com.brasilprev.pedidos.service.cliente.ClienteDTO;

public class PedidoDTO {

	public Long idPedido;

	public Date data;

	public ClienteDTO cliente;

	public String status;

	public String sessao;
}
