package br.com.brasilprev.pedidos.rest.controller.pedido.item;

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
import br.com.brasilprev.pedidos.service.pedido.item.CriacaoPedidoItemDTO;
import br.com.brasilprev.pedidos.service.pedido.item.IPedidoItemService;
import br.com.brasilprev.pedidos.service.pedido.item.PedidoItemDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/v1/pedido")
@Api(value = "Serviços de itens de pedido")
public class PedidoItemController {

	private IPedidoItemService pedidoItemService;
	
	@Autowired
    public PedidoItemController(IPedidoItemService pedidoItemService) {
		this.pedidoItemService = pedidoItemService;
	}
	
    @GetMapping("/{idPedido}/item")
    @ApiOperation(value = "Lista todos os itens de um pedido")
    public ResponseEntity<List<PedidoItemDTO>> listar(@PathVariable Long idPedido) {
    	return new ResponseEntity<>(pedidoItemService.listar(idPedido), HttpStatus.OK);
    }
    
    @PostMapping("{idPedido}/item")
    @ApiOperation(value = "Adiciona um item a um pedido")
    public ResponseEntity<RespostaDeSucesso> adicionar(@RequestBody CriacaoPedidoItemDTO criacaoPedidoItemDTO) {
    	return new ResponseEntity<>(pedidoItemService.salvar(criacaoPedidoItemDTO), HttpStatus.CREATED);
    }
    
    @GetMapping("/item/{id}")
    @ApiOperation(value = "Obtem um item específico")
    public ResponseEntity<PedidoItemDTO> buscar(@PathVariable Long id) {
    	return pedidoItemService.buscar(id)
                .map(pedidoItemDTO -> new ResponseEntity<>(pedidoItemDTO, HttpStatus.OK))
                .orElseGet(() ->  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @SuppressWarnings("rawtypes")
	@PutMapping("/item/{id}")
    @ApiOperation(value = "Atualiza um item")
    public ResponseEntity atualizar(@RequestBody CriacaoPedidoItemDTO criacaoPedidoItemDTO, @PathVariable Long id) {
    	pedidoItemService.atualizar(criacaoPedidoItemDTO, id);
    	return new ResponseEntity(HttpStatus.OK);
    }
    
    @SuppressWarnings("rawtypes")
	@DeleteMapping("/item/{id}")
    @ApiOperation(value = "Deleta um item")
    public ResponseEntity deletar(@PathVariable Long id) {
    	pedidoItemService.deletar(id);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}