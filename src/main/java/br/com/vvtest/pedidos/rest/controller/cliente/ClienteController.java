package br.com.vvtest.pedidos.rest.controller.cliente;

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
import br.com.vvtest.pedidos.service.cliente.ClienteDTO;
import br.com.vvtest.pedidos.service.cliente.CriacaoClienteDTO;
import br.com.vvtest.pedidos.service.cliente.IClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/v1/cliente")
@Api(value = "Serviços do cliente")
public class ClienteController {

	private IClienteService clienteService;
	
	@Autowired
    public ClienteController(IClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos os cliente")
    public ResponseEntity<List<ClienteDTO>> listar() {
        return new ResponseEntity<>(clienteService.listar(), HttpStatus.OK);
    }
	
    @PostMapping
    @ApiOperation(value = "Insere um novo cliente")
    public ResponseEntity<RespostaDeSucesso> salvar(@RequestBody CriacaoClienteDTO criacaoClienteDTO) {
        return new ResponseEntity<>(clienteService.salvar(criacaoClienteDTO), HttpStatus.CREATED);
    }
	
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um cliente")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Long id) {
    	return clienteService.buscar(id)
                .map(clienteDTO -> new ResponseEntity<>(clienteDTO, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um cliente")
    public ResponseEntity atualizar(@RequestBody ClienteDTO clienteDTO, @PathVariable Long id) {
    	clienteService.atualizar(clienteDTO, id);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um cliente")
    public ResponseEntity deletar(@PathVariable Long id) {
    	clienteService.deletar(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
