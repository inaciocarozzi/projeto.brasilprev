package br.com.brasilprev.pedidos.service.pedido;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.pedidos.dao.ClienteDAO;
import br.com.brasilprev.pedidos.dao.PedidoDAO;
import br.com.brasilprev.pedidos.dominio.Cliente;
import br.com.brasilprev.pedidos.dominio.Endereco;
import br.com.brasilprev.pedidos.dominio.Pedido;
import br.com.brasilprev.pedidos.exception.ClienteNotFoundException;
import br.com.brasilprev.pedidos.exception.PedidoNotFoundException;
import br.com.brasilprev.pedidos.rest.controller.RespostaDeSucesso;
import br.com.brasilprev.pedidos.service.cliente.ClienteDTO;
import br.com.brasilprev.pedidos.service.endereco.EnderecoDTO;

@Service
public class PedidoService implements IPedidoService {

	private PedidoDAO pedidoDAO;
	
	private ClienteDAO clienteDAO;

	@Autowired
	public PedidoService(PedidoDAO pedidoDAO, ClienteDAO clienteDAO) {
		this.pedidoDAO = pedidoDAO;
		this.clienteDAO = clienteDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PedidoDTO> listar() {
		List<Pedido> pedidos = pedidoDAO.findAll();
		List<PedidoDTO> pedidosDTO = pedidos.stream()
				.map(this::montarPedidoDTO).collect(Collectors.toList());

		return pedidosDTO;
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
	public RespostaDeSucesso salvar(CriacaoPedidoDTO criacaoPedidoDTO) {
		Optional<Cliente> clienteOptional = clienteDAO.findById(criacaoPedidoDTO.idCliente);
		
		if (!clienteOptional.isPresent()) {
			throw new ClienteNotFoundException(criacaoPedidoDTO.idCliente);
		}
		
		Pedido pedido = pedidoDAO.save(new Pedido(criacaoPedidoDTO.data, clienteOptional.get(), 
				criacaoPedidoDTO.status, criacaoPedidoDTO.sessao));
		
		return new RespostaDeSucesso(pedido.getIdPedido());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PedidoDTO> buscar(Long id) {
		Optional<Pedido> pedido = pedidoDAO.findById(id);
		return pedido.map(this::montarPedidoDTO);
	}

	@Override
	public void atualizar(CriacaoPedidoDTO criacaoPedidoDTO, Long id) {
		Optional<Pedido> pedido = pedidoDAO.findById(id);

		pedido.orElseThrow(() -> new PedidoNotFoundException(id));

        pedido.get().atualizar(criacaoPedidoDTO.data, criacaoPedidoDTO.status, criacaoPedidoDTO.sessao);
        pedidoDAO.save(pedido.get());
	}

	@Override
	public void deletar(Long id) {
		pedidoDAO.deleteById(id);
	}
}
