package br.com.vvtest.pedidos.service.pedido.item;

import java.util.List;
import java.util.Optional;

import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;

public interface IPedidoItemService {

	public List<PedidoItemDTO> listar();
	
    public RespostaDeSucesso salvar(CriacaoPedidoItemDTO pedidoDTO);
    
    public void atualizar(CriacaoPedidoItemDTO criacaoPedidoDTO, Long id);
    
    public void deletar(Long id);

	public List<PedidoItemDTO> listar(Long idPedido);

	public Optional<PedidoItemDTO> buscar(Long id);
}
