package br.com.vvtest.pedidos.service.produto;

import java.util.List;
import java.util.Optional;

import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;

public interface IProdutoService {

	public List<ProdutoDTO> listar();
	
    public RespostaDeSucesso salvar(CriacaoProdutoDTO produtoDTO);
	
    public Optional<ProdutoDTO> buscar(Long id);
    
    public void atualizar(ProdutoDTO produtoDTO, Long id);
    
    public void deletar(Long id);
}
