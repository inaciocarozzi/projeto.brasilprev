package br.com.vvtest.pedidos.rest.controller.pedido;

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
import br.com.vvtest.pedidos.service.pedido.CriacaoPedidoDTO;
import br.com.vvtest.pedidos.service.pedido.IPedidoService;
import br.com.vvtest.pedidos.service.pedido.PedidoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/v1/pedido")
@Api(value = "Serviços do pedido")
public class PedidoController {

	private IPedidoService pedidoService;
	
	@Autowired
    public PedidoController(IPedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping
	@ApiOperation(value = "Lista todos os pedidos")
    public ResponseEntity<List<PedidoDTO>> listar() {
		return new ResponseEntity<>(pedidoService.listar(), HttpStatus.OK);
    }
	
    @PostMapping
    @ApiOperation(value = "Insere um novo pedido")
    public ResponseEntity<RespostaDeSucesso> salvar(@RequestBody CriacaoPedidoDTO criacaoPedidoDTO) {
    	return new ResponseEntity<>(pedidoService.salvar(criacaoPedidoDTO), HttpStatus.CREATED);
    }
	
    @GetMapping("/{id}")
    @ApiOperation(value = "Busca um pedido")
    public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id) {
    	return pedidoService.buscar(id)
                .map(pedidoDTO -> new ResponseEntity<>(pedidoDTO, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @SuppressWarnings("rawtypes")
	@PutMapping("/{id}")
    @ApiOperation(value = "Atualiza um pedido")
    public ResponseEntity atualizar(@RequestBody  CriacaoPedidoDTO criacaoPedidoDTO, @PathVariable Long id) {
    	pedidoService.atualizar(criacaoPedidoDTO, id);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta um pedido")
    public ResponseEntity deletar(@PathVariable Long id) {
    	pedidoService.deletar(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
