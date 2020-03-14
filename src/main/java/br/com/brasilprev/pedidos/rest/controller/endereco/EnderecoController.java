package br.com.brasilprev.pedidos.rest.controller.endereco;

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

import br.com.brasilprev.pedidos.rest.controller.RespostaDeSucesso;
import br.com.brasilprev.pedidos.service.endereco.EnderecoDTO;
import br.com.brasilprev.pedidos.service.endereco.IEnderecoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/v1/endereco")
@Api(value = "Serviços do endereço")
public class EnderecoController {

	private IEnderecoService enderecoService;
	
	@Autowired
    public EnderecoController(IEnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos os endereços")
    public ResponseEntity<List<EnderecoDTO>> listar() {
		return new ResponseEntity<>(enderecoService.listar(), HttpStatus.OK);
    }
	
    @PostMapping
    @ApiOperation(value = "Insere um novo endereço")
    public ResponseEntity<RespostaDeSucesso> salvar(@RequestBody EnderecoDTO enderecoDTO) {
    	return new ResponseEntity<>(enderecoService.salvar(enderecoDTO), HttpStatus.CREATED);
    }
	
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um endereço")
    public ResponseEntity<EnderecoDTO> buscar(@PathVariable Long id) {
    	return enderecoService.buscar(id)
                .map(endereoDTO -> new ResponseEntity<>(endereoDTO, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um endereço")
    public ResponseEntity atualizar(@RequestBody EnderecoDTO enderecoDTO, @PathVariable Long id) {
    	enderecoService.atualizar(enderecoDTO, id);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um endereço")
    public ResponseEntity deletar(@PathVariable Long id) {
    	enderecoService.deletar(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
