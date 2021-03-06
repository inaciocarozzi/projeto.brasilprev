package br.com.vvtest.pedidos.service.cliente;

import java.util.List;
import java.util.Optional;

import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;

public interface IClienteService {

	public List<ClienteDTO> listar();
	
    public RespostaDeSucesso salvar(CriacaoClienteDTO criacaoClienteDTO);
	
    public Optional<ClienteDTO> buscar(Long id);
    
    public void atualizar(ClienteDTO clienteDTO, Long id);
    
    public void deletar(Long id);

}
