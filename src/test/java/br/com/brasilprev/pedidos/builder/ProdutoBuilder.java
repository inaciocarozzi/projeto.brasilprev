package br.com.brasilprev.pedidos.builder;

import br.com.vvtest.pedidos.dominio.Categoria;
import br.com.vvtest.pedidos.dominio.Produto;

public class ProdutoBuilder {

	private Categoria categoria = CategoriaBuilder.umaCategoria().build();

	private String nome = "Sabão";
	
	private Double preco = 17.99;
	
	private Integer quantidade = 2;
	
	private String descricao = "Sabão em pó";
	
	private String urlFoto = "";

	private ProdutoBuilder() {
	}

	public static ProdutoBuilder umProduto() {
		return new ProdutoBuilder();
	}

	public Produto build() {
		return new Produto(categoria, nome, preco, quantidade, descricao, urlFoto);
	}
}
