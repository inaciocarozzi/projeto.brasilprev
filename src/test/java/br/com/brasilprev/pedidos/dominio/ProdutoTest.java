package br.com.brasilprev.pedidos.dominio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brasilprev.pedidos.builder.CategoriaBuilder;
import br.com.vvtest.pedidos.dominio.Categoria;
import br.com.vvtest.pedidos.dominio.Produto;
import br.com.vvtest.pedidos.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
public class ProdutoTest {
	private Categoria categoria;

	private String nome;

	private Double preco;

	private Integer quantidade;

	private String descricao;

	private String urlFoto;
	
    @Before
    public void init(){
    	categoria = CategoriaBuilder.umaCategoria().build();
    	nome = "Sabão";
    	preco = 18.55;
    	quantidade = 100;
    	descricao = "Sabão de lavar roupa";
    	urlFoto = "imagens/foto-sabado.png";
    }

    @Test
    public void deveCriarProduto(){
    	Produto produto = new Produto(categoria, nome, preco, quantidade, descricao, urlFoto);
    	
    	Assert.assertEquals(categoria, produto.getCategoria());
    	Assert.assertEquals(nome, produto.getNome());
    	Assert.assertEquals(preco, produto.getPreco());
    	Assert.assertEquals(quantidade, produto.getQuantidade());
    	Assert.assertEquals(descricao, produto.getDescricao());
    	Assert.assertEquals(urlFoto, produto.getUrlFoto());
    }

    @Test
    public void naoDeveCriarProdutoSemCategoria(){
    	Categoria categoriaNula = null;
    	String nomeVazio = "";
    	Double precoNulo = null;
    	Integer quantidadeNula = null;
    	String descricaoVazia = "";
    	String urlFotoVazia = "";
    	
    	try {
    		new Produto(categoriaNula, nome, preco, quantidade, descricao, urlFoto);
    		new Produto(categoria, nomeVazio, preco, quantidade, descricao, urlFoto);
    		new Produto(categoria, nome, precoNulo, quantidade, descricao, urlFoto);
    		new Produto(categoria, nome, preco, quantidadeNula, descricao, urlFoto);
    		new Produto(categoria, nome, preco, quantidade, descricaoVazia, urlFoto);
    		new Produto(categoria, nome, preco, quantidade, descricao, urlFotoVazia);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidFieldException);
		}
    }
}
