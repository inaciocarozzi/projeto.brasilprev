package br.com.vvtest.pedidos.service.categoria;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.vvtest.pedidos.dao.CategoriaDAO;
import br.com.vvtest.pedidos.dominio.Categoria;
import br.com.vvtest.pedidos.exception.CategoriaNotFoundException;
import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;

@Service
public class CategoriaService implements ICategoriaService {

	private CategoriaDAO categoriaDAO;

	@Autowired
	public CategoriaService(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoriaDTO> listar() {
		List<Categoria> categorias = categoriaDAO.findAll();
		List<CategoriaDTO> categoriasDTO = categorias.stream()
				.map(this::montarCategoriaDTO).collect(Collectors.toList());

		return categoriasDTO;
	}

	private CategoriaDTO montarCategoriaDTO(Categoria categoria) {
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO.id = categoria.getIdCategoria();
		categoriaDTO.nome = categoria.getNome();

		return categoriaDTO;
	}

	@Override
	public RespostaDeSucesso salvar(CategoriaDTO categoriaDTO) {
		Categoria categoria = categoriaDAO.save(new Categoria(categoriaDTO.nome));
		
		return new RespostaDeSucesso(categoria.getIdCategoria());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<CategoriaDTO> buscar(Long id) {
		Optional<Categoria> categoria = categoriaDAO.findById(id);

		return categoria.map(this::montarCategoriaDTO);
	}

	@Override
	public void atualizar(CategoriaDTO categoriaDTO, Long id) {
		Optional<Categoria> categoria = categoriaDAO.findById(id);

		categoria.orElseThrow(() -> new CategoriaNotFoundException(id));

        categoria.get().atualizar(categoriaDTO.nome);
        categoriaDAO.save(categoria.get());
	}

	@Override
	public void deletar(Long id) {
		categoriaDAO.deleteById(id);
	}
}
