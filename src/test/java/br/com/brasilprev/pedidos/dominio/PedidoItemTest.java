package br.com.brasilprev.pedidos.dominio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brasilprev.pedidos.builder.PedidoBuilder;
import br.com.brasilprev.pedidos.builder.ProdutoBuilder;
import br.com.brasilprev.pedidos.dominio.Pedido;
import br.com.brasilprev.pedidos.dominio.PedidoItem;
import br.com.brasilprev.pedidos.dominio.Produto;
import br.com.brasilprev.pedidos.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
public class PedidoItemTest {
    private Produto produto;
    private Pedido pedido;
    private Integer quantidade;
    private Double valor;

    @Before
    public void Init(){
        produto = ProdutoBuilder.umProduto().build();
        pedido = PedidoBuilder.umPedido().build();
        quantidade = 2;
        valor = 100.98;
    }

    @Test
    public void deveCriarUmPedidoItem(){
        PedidoItem pedidoItem = new PedidoItem(pedido, produto, quantidade, valor);

        Assert.assertEquals(produto, pedidoItem.getProduto());
        Assert.assertEquals(pedido, pedidoItem.getPedido());
        Assert.assertEquals(quantidade, pedidoItem.getQuantidade());
        Assert.assertEquals(valor, pedidoItem.getValor());
    }

    @Test
    public void naoDeveCriarUmPedidoItem(){
    	Pedido pedidoNulo = null;
    	Produto produtoNulo = null;
        Integer quantidadeNula = null;
        Double valorNulo = null;

        try {
        	new PedidoItem(pedidoNulo, produto, quantidade, valor);
        	new PedidoItem(pedido, produtoNulo, quantidade, valor);
        	new PedidoItem(pedido, produto, quantidadeNula, valor);
        	new PedidoItem(pedido, produto, quantidade, valorNulo);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidFieldException);
		}
    }
}
