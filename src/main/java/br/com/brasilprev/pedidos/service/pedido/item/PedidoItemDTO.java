package br.com.brasilprev.pedidos.service.pedido.item;

import br.com.brasilprev.pedidos.dominio.Produto;
import br.com.brasilprev.pedidos.service.pedido.PedidoDTO;

public class PedidoItemDTO {
	
	public Long idItem;

	public PedidoDTO pedido;

	public Produto produto;

	public Integer quantidade;

	public Double valor;
}
