package br.com.vvtest.pedidos.rest.controller.produto;

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
import br.com.vvtest.pedidos.service.produto.CriacaoProdutoDTO;
import br.com.vvtest.pedidos.service.produto.IProdutoService;
import br.com.vvtest.pedidos.service.produto.ProdutoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/v1/produto")
@Api(value = "Serviços do produto")
public class ProdutoController {

	private IProdutoService produtoService;
	
	@Autowired
    public ProdutoController(IProdutoService produtoService) {
		this.produtoService = produtoService;
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos os produtos")
    public ResponseEntity<List<ProdutoDTO>> listar() {
		return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
    }
	
    @PostMapping
    @ApiOperation(value = "Adiciona um produto")
    public ResponseEntity<RespostaDeSucesso> salvar(@RequestBody CriacaoProdutoDTO criacaoProdutoDTO) {
    	return new ResponseEntity<>(produtoService.salvar(criacaoProdutoDTO), HttpStatus.CREATED);
    }
	
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um produto")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
    	return produtoService.buscar(id)
                .map(produtoDTO -> new ResponseEntity<>(produtoDTO, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
	@SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um produto")
    public ResponseEntity atualizar(@RequestBody ProdutoDTO produtoDTO, @PathVariable Long id) {
    	produtoService.atualizar(produtoDTO, id);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um produto")
    public ResponseEntity deletar(@PathVariable Long id) {
    	produtoService.deletar(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
