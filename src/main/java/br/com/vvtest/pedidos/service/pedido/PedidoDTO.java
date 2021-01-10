package br.com.vvtest.pedidos.service.pedido;

import java.util.Date;

import br.com.vvtest.pedidos.service.cliente.ClienteDTO;

public class PedidoDTO {

	public Long idPedido;

	public Date data;

	public ClienteDTO cliente;

	public String status;

	public String sessao;
}
