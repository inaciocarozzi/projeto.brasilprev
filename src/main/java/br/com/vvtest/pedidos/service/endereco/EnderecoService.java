package br.com.vvtest.pedidos.service.endereco;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vvtest.pedidos.dao.EnderecoDAO;
import br.com.vvtest.pedidos.dominio.Endereco;
import br.com.vvtest.pedidos.exception.EnderecoNotFoundException;
import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;

@Service
public class EnderecoService implements IEnderecoService {

	private EnderecoDAO enderecoDAO;

	@Autowired
	public EnderecoService(EnderecoDAO enderecoDAO) {
		this.enderecoDAO = enderecoDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<EnderecoDTO> listar() {
		List<Endereco> enderecos = enderecoDAO.findAll();
		List<EnderecoDTO> enderecosDTO = enderecos.stream()
				.map(this::montarEnderecoDTO).collect(Collectors.toList());

		return enderecosDTO;
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
	public RespostaDeSucesso salvar(EnderecoDTO enderecoDTO) {
		Endereco endereco = enderecoDAO.save(new Endereco(
				enderecoDTO.rua, enderecoDTO.cidade, enderecoDTO.bairro, 
				enderecoDTO.cep, enderecoDTO.estado));
		
		return new RespostaDeSucesso(endereco.getIdEndereco());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<EnderecoDTO> buscar(Long id) {
		Optional<Endereco> endereco = enderecoDAO.findById(id);

		return endereco.map(this::montarEnderecoDTO);
	}

	@Override
	public void atualizar(EnderecoDTO enderecoDTO, Long id) {
		Optional<Endereco> endereco = enderecoDAO.findById(id);

		endereco.orElseThrow(() -> new EnderecoNotFoundException(id));

        endereco.get().atualizar(
				enderecoDTO.rua, enderecoDTO.cidade, enderecoDTO.bairro, 
				enderecoDTO.cep, enderecoDTO.estado);
        
        enderecoDAO.save(endereco.get());
	}

	@Override
	public void deletar(Long id) {
		enderecoDAO.deleteById(id);
	}
}
