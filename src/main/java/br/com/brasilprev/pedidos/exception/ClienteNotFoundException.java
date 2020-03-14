package br.com.brasilprev.pedidos.exception;

public class ClienteNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNotFoundException(Long id) {
        super("Cliente de id " + id + " não encontrado.");
    }

}