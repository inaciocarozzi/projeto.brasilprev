package br.com.vvtest.pedidos.exception;

public class ItemNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(Long id) {
        super("Item de id " + id + " não encontrado.");
    }

}