package br.com.brasilprev.pedidos.service.cliente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.pedidos.dao.ClienteDAO;
import br.com.brasilprev.pedidos.dao.EnderecoDAO;
import br.com.brasilprev.pedidos.dominio.Cliente;
import br.com.brasilprev.pedidos.dominio.Endereco;
import br.com.brasilprev.pedidos.exception.ClienteNotFoundException;
import br.com.brasilprev.pedidos.rest.controller.RespostaDeSucesso;
import br.com.brasilprev.pedidos.service.endereco.EnderecoDTO;

@Service
public class ClienteService implements IClienteService {

	private ClienteDAO clienteDAO;
	
	private EnderecoDAO enderecoDAO;
	
	@Autowired
	public ClienteService(ClienteDAO clienteDAO, EnderecoDAO enderecoDAO) {
		this.clienteDAO = clienteDAO;
		this.enderecoDAO = enderecoDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ClienteDTO> listar() {
		List<Cliente> clientes = clienteDAO.findAll();
		List<ClienteDTO> clientesDTO = clientes.stream()
				.map(this::montarClienteDTO).collect(Collectors.toList());

		return clientesDTO;
	}

	private ClienteDTO montarClienteDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.idCliente = cliente.getIdCliente();
        clienteDTO.nome = cliente.getNome();
        clienteDTO.email = cliente.getEmail();
        clienteDTO.senha = cliente.getSenha();
		
		Endereco endereco = cliente.getEndereco();
        
        if (endereco != null) {
        	EnderecoDTO enderecoDTO = montarEnderecoDTO(endereco);
            clienteDTO.enderecoDTO = enderecoDTO;
        }
        
        return clienteDTO;
    }
	
	private EnderecoDTO montarEnderecoDTO(Endereco endereco) {
        EnderecoDTO enderecoDTO = new EnderecoDTO();
        enderecoDTO.estado = endereco.getEstado();
        enderecoDTO.cidade = endereco.getCidade();
        enderecoDTO.bairro = endereco.getBairro();
        enderecoDTO.rua = endereco.getRua();
        enderecoDTO.cep = endereco.getCep();

        return enderecoDTO;
    }
	
	@Override
	public RespostaDeSucesso salvar(CriacaoClienteDTO criacaoClienteDTO) {
		Optional<Endereco> enderecoOptional = enderecoDAO.findById(criacaoClienteDTO.idEndereco);
		
		Endereco endereco = null;
		
		if (enderecoOptional.isPresent()) {
			endereco = enderecoOptional.get();
		}
		
		Cliente cliente = clienteDAO.save(new Cliente(
				criacaoClienteDTO.nome, criacaoClienteDTO.email, 
				criacaoClienteDTO.senha, endereco));
		
		return new RespostaDeSucesso(cliente.getIdCliente());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ClienteDTO> buscar(Long id) {
		Optional<Cliente> cliente = clienteDAO.findById(id);
		return cliente.map(this::montarClienteDTO);
	}

	@Override
	public void atualizar(ClienteDTO clienteDTO, Long id) {
		Optional<Cliente> clienteOptional = clienteDAO.findById(id);
		clienteOptional.orElseThrow(() -> new ClienteNotFoundException(id));

		Cliente cliente = clienteOptional.get();
		cliente.atualizar(clienteDTO.nome, clienteDTO.email, clienteDTO.senha);
		
        clienteDAO.save(cliente);
	}

	@Override
	public void deletar(Long id) {
		clienteDAO.deleteById(id);
	}

}
