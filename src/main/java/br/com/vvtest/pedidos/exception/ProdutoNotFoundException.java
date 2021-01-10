package br.com.vvtest.pedidos.exception;

public class ProdutoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProdutoNotFoundException(Long id) {
        super("Produto de id " + id + " não encontrado.");
    }

}