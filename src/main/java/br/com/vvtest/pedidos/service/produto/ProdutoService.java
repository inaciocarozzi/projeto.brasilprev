package br.com.vvtest.pedidos.service.produto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vvtest.pedidos.dao.CategoriaDAO;
import br.com.vvtest.pedidos.dao.ProdutoDAO;
import br.com.vvtest.pedidos.dominio.Categoria;
import br.com.vvtest.pedidos.dominio.Produto;
import br.com.vvtest.pedidos.exception.ProdutoNotFoundException;
import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;
import br.com.vvtest.pedidos.service.categoria.CategoriaDTO;

@Service
public class ProdutoService implements IProdutoService {

	private ProdutoDAO produtoDAO;
	
	private CategoriaDAO categoriaDAO;

	@Autowired
	public ProdutoService(ProdutoDAO produtoDAO, CategoriaDAO categoriaDAO) {
		this.produtoDAO = produtoDAO;
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProdutoDTO> listar() {
		List<Produto> produtos = produtoDAO.findAll();
		List<ProdutoDTO> produtosDTO = produtos.stream()
				.map(this::montarProdutoDTO).collect(Collectors.toList());

		return produtosDTO;
	}

	private ProdutoDTO montarProdutoDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        
        if (produto.getCategoria() != null) {
        	produtoDTO.categoriaDTO = montarCategoriaDTO(produto.getCategoria());
        }
        
        produtoDTO.descricao = produto.getDescricao();
        produtoDTO.idProduto = produto.getIdProduto();
        produtoDTO.nome = produto.getNome();
        produtoDTO.preco = produto.getPreco();
        produtoDTO.quantidade = produto.getQuantidade();
        produtoDTO.urlFoto = produto.getUrlFoto();
        
        return produtoDTO;
    }
	
	private CategoriaDTO montarCategoriaDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.id = categoria.getIdCategoria();
		categoriaDTO.nome = categoria.getNome();
		
		return categoriaDTO;
	}

	@Override
	public RespostaDeSucesso salvar(CriacaoProdutoDTO criacaoProdutoDTO) {
		Optional<Categoria> categoriaOptional = categoriaDAO.findById(criacaoProdutoDTO.idCategoria);
		
		Categoria categoria = null;
		
		if (categoriaOptional.isPresent()) {
			categoria = categoriaOptional.get();
		}
		
		Produto produto = produtoDAO.save(new Produto(categoria, criacaoProdutoDTO.nome,
				criacaoProdutoDTO.preco, criacaoProdutoDTO.quantidade, criacaoProdutoDTO.descricao,
				criacaoProdutoDTO.urlFoto));
		
		return new RespostaDeSucesso(produto.getIdProduto());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ProdutoDTO> buscar(Long id) {
		Optional<Produto> produtoDTO = produtoDAO.findById(id);
		return produtoDTO.map(this::montarProdutoDTO);
	}

	@Override
	public void atualizar(ProdutoDTO produtoDTO, Long id) {
		Optional<Produto> produtoOptional = produtoDAO.findById(id);
		produtoOptional.orElseThrow(() -> new ProdutoNotFoundException(id));
		
		Produto produto = produtoOptional.get();
		produto.atualizar(produtoDTO.nome, produtoDTO.preco, produtoDTO.quantidade, 
				produtoDTO.descricao, produtoDTO.urlFoto);
		
		produtoDAO.save(produto);
	}

	@Override
	public void deletar(Long id) {
		produtoDAO.deleteById(id);
	}
}
