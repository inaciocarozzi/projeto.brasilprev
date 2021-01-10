package br.com.vvtest.pedidos.service.pedido.item;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vvtest.pedidos.dao.PedidoDAO;
import br.com.vvtest.pedidos.dao.PedidoItemDAO;
import br.com.vvtest.pedidos.dao.ProdutoDAO;
import br.com.vvtest.pedidos.dominio.Cliente;
import br.com.vvtest.pedidos.dominio.Endereco;
import br.com.vvtest.pedidos.dominio.Pedido;
import br.com.vvtest.pedidos.dominio.PedidoItem;
import br.com.vvtest.pedidos.dominio.Produto;
import br.com.vvtest.pedidos.exception.ItemNotFoundException;
import br.com.vvtest.pedidos.exception.PedidoNotFoundException;
import br.com.vvtest.pedidos.exception.ProdutoNotFoundException;
import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;
import br.com.vvtest.pedidos.service.cliente.ClienteDTO;
import br.com.vvtest.pedidos.service.endereco.EnderecoDTO;
import br.com.vvtest.pedidos.service.pedido.PedidoDTO;

@Service
public class PedidoItemService implements IPedidoItemService {

	private PedidoItemDAO pedidoItemDAO;
	
	private PedidoDAO pedidoDAO;
	
	private ProdutoDAO produtoDAO;
	
	@Autowired
	public PedidoItemService(PedidoItemDAO pedidoItemDAO, PedidoDAO pedidoDAO, ProdutoDAO produtoDAO) {
		this.pedidoItemDAO = pedidoItemDAO;
		this.pedidoDAO = pedidoDAO;
		this.produtoDAO = produtoDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PedidoItemDTO> listar() {
		List<PedidoItem> pedidos = pedidoItemDAO.findAll();
		List<PedidoItemDTO> pedidosDTO = pedidos.stream()
				.map(this::montarPedidoItemDTO).collect(Collectors.toList());

		return pedidosDTO;
	}
	
	private PedidoItemDTO montarPedidoItemDTO(PedidoItem pedidoItem) {
		PedidoItemDTO pedidoItemDTO = new PedidoItemDTO();
		pedidoItemDTO.idItem = pedidoItem.getIdItem();
		pedidoItemDTO.pedido = montarPedidoDTO(pedidoItem.getPedido());
		pedidoItemDTO.produto = pedidoItem.getProduto();
		pedidoItemDTO.quantidade = pedidoItem.getQuantidade();
		pedidoItemDTO.valor = pedidoItem.getValor();
		
		return pedidoItemDTO;
	}
	
	private PedidoDTO montarPedidoDTO(Pedido pedido) {
		PedidoDTO pedidoDTO = new PedidoDTO();
		
		if (pedido.getCliente() != null) {
			pedidoDTO.cliente = montarClienteDTO(pedido.getCliente());
		}
		
		pedidoDTO.data = pedido.getData();
		pedidoDTO.idPedido = pedido.getIdPedido();
		pedidoDTO.sessao = pedido.getSessao();
		pedidoDTO.status = pedido.getStatus();
		
		return pedidoDTO;
	}
	
	private ClienteDTO montarClienteDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.email = cliente.getEmail();
		
		if (cliente.getEndereco() != null) {
			clienteDTO.enderecoDTO = montarEnderecoDTO(cliente.getEndereco());
		}
		
		clienteDTO.idCliente = cliente.getIdCliente();
		clienteDTO.nome = cliente.getNome();
		clienteDTO.senha = cliente.getSenha();
		
		return clienteDTO;
	}
	
	private EnderecoDTO montarEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.idEndereco = endereco.getIdEndereco();
        enderecoDTO.estado = endereco.getEstado();
        enderecoDTO.cidade = endereco.getCidade();
        enderecoDTO.bairro = endereco.getBairro();
        enderecoDTO.rua = endereco.getRua();
        enderecoDTO.cep = endereco.getCep();

        return enderecoDTO;
    }

	@Override
	public RespostaDeSucesso salvar(CriacaoPedidoItemDTO criacaoPedidoDTO) {
		Optional<Pedido> pedidoOptional = pedidoDAO.findById(criacaoPedidoDTO.idPedido);
		
		if (!pedidoOptional.isPresent()) {
			throw new PedidoNotFoundException(criacaoPedidoDTO.idPedido);
		}
		
		Optional<Produto> produtoOptional = produtoDAO.findById(criacaoPedidoDTO.idProduto);
		
		if (!produtoOptional.isPresent()) {
			throw new ProdutoNotFoundException(criacaoPedidoDTO.idProduto);
		}
		
		PedidoItem pedidoItem = pedidoItemDAO.save(new PedidoItem(pedidoOptional.get(), 
				produtoOptional.get(), criacaoPedidoDTO.quantidade, criacaoPedidoDTO.valor));
		
		return new RespostaDeSucesso(pedidoItem.getIdItem());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PedidoItemDTO> buscar(Long id) {
		Optional<PedidoItem> pedidoItem = pedidoItemDAO.findById(id);
		return pedidoItem.map(this::montarPedidoItemDTO);
	}

	@Override
	public void atualizar(CriacaoPedidoItemDTO criacaoPedidoItemDTO, Long id) {
		Optional<PedidoItem> pedidoItem = pedidoItemDAO.findById(id);

		pedidoItem.orElseThrow(() -> new ItemNotFoundException(id));

        pedidoItem.get().atualizar(criacaoPedidoItemDTO.quantidade, criacaoPedidoItemDTO.valor);
        pedidoItemDAO.save(pedidoItem.get());
	}

	@Override
	public void deletar(Long id) {
		pedidoItemDAO.deleteById(id);
	}

	@Override
	public List<PedidoItemDTO> listar(Long idPedido) {
		Optional<Pedido> pedido = pedidoDAO.findById(idPedido);
		
		if (!pedido.isPresent()) {
			throw new PedidoNotFoundException(idPedido);
		}
		
		List<PedidoItem> itensPedido = pedido.get().getItens();
		List<PedidoItemDTO> itensDTO = itensPedido.stream()
				.map(this::montarPedidoItemDTO).collect(Collectors.toList());

		return itensDTO;
	}
}
