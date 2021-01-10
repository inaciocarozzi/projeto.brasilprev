package br.com.vvtest.pedidos.rest.controller.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.vvtest.pedidos.rest.controller.RespostaDeSucesso;
import br.com.vvtest.pedidos.service.categoria.CategoriaDTO;
import br.com.vvtest.pedidos.service.categoria.ICategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/v1/categoria")
@Api(value = "Serviços da categoria")
public class CategoriaController {

	private ICategoriaService categoriaService;
	
	@Autowired
    public CategoriaController(ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todas as categorias")
    public ResponseEntity<List<CategoriaDTO>> listar() {
        return new ResponseEntity<>(categoriaService.listar(), HttpStatus.OK);
    }
	
    @PostMapping
    @ApiOperation(value = "Insere uma nova categoria")
    public ResponseEntity<RespostaDeSucesso> salvar(@RequestBody CategoriaDTO categoriaDTO) {
        return new ResponseEntity<>(categoriaService.salvar(categoriaDTO), HttpStatus.CREATED);
    }
	
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca uma categoria")
    public ResponseEntity<CategoriaDTO> buscar(@PathVariable Long id) {
        return categoriaService.buscar(id)
                .map(categoriaDTO -> new ResponseEntity<>(categoriaDTO, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
    @ApiOperation(value = "Atualiza uma categoria")
    public ResponseEntity atualizar(@RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id) {
    	categoriaService.atualizar(categoriaDTO, id);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta uma categoria")
    public ResponseEntity deletar(@PathVariable Long id) {
    	categoriaService.deletar(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
