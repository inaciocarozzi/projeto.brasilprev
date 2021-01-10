package br.com.vvtest.pedidos.service.pedido.item;

import br.com.vvtest.pedidos.dominio.Produto;
import br.com.vvtest.pedidos.service.pedido.PedidoDTO;

public class PedidoItemDTO {
	
	public Long idItem;

	public PedidoDTO pedido;

	public Produto produto;

	public Integer quantidade;

	public Double valor;
}
