package br.com.brasilprev.pedidos.dominio;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brasilprev.pedidos.builder.ClienteBuilder;
import br.com.vvtest.pedidos.dominio.Cliente;
import br.com.vvtest.pedidos.dominio.Pedido;
import br.com.vvtest.pedidos.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
public class PedidoTest {
	private Date data;
	private Cliente cliente;
	private String status;
	private String sessao;
	
	@Before
    public void Init(){
        data = new Date();
        cliente = ClienteBuilder.umCliente().build();
        status = "ABERTO";
        sessao = "ESPORTE";
    }
	
    @Test
    public void deveCriarPedido(){
    	Pedido pedido = new Pedido(data, cliente, status, sessao);
    	
    	Assert.assertEquals(data, pedido.getData());
        Assert.assertEquals(cliente, pedido.getCliente());
        Assert.assertEquals(status, pedido.getStatus());
        Assert.assertEquals(sessao, pedido.getSessao());
    }

    @Test
    public void naoDeveCriarPedido(){
    	Date dataNula = null;
		Cliente clienteNulo = null;
		String statusVazio = "";
		String sessaoVazio = "";
		
		try {
			new Pedido(dataNula, cliente, status, sessao);
			new Pedido(data, clienteNulo, status, sessao);
			new Pedido(data, cliente, statusVazio, sessao);
			new Pedido(data, cliente, status, sessaoVazio);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidFieldException);
		}
    }
}
