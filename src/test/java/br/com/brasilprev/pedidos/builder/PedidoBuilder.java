package br.com.brasilprev.pedidos.builder;

import java.util.Date;

import br.com.brasilprev.pedidos.dominio.Cliente;
import br.com.brasilprev.pedidos.dominio.Pedido;

public class PedidoBuilder {

	private Date data = new Date();
	
    private Cliente cliente = ClienteBuilder.umCliente().build();

    private String status = "status";
    
    private String sessao = "sessão";
    
    private PedidoBuilder(){
    }

    public static PedidoBuilder umPedido() {
        return new PedidoBuilder();
    }

    public Pedido build(){
        return new Pedido(data, cliente, status, sessao);
    }
}
