package br.com.vvtest.pedidos.service.categoria;

import java.util.List;
import java.util.Optional;

import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;

public interface ICategoriaService {

	public List<CategoriaDTO> listar();
	
    public RespostaDeSucesso salvar(CategoriaDTO categoriaDTO);
	
    public Optional<CategoriaDTO> buscar(Long id);
    
    public void atualizar(CategoriaDTO categoriaDTO, Long id);
    
    public void deletar(Long id);
}
