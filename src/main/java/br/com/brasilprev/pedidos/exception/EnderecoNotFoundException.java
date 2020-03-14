package br.com.brasilprev.pedidos.exception;

public class EnderecoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnderecoNotFoundException(Long id) {
        super("Endereço de id " + id + " não encontrado.");
    }

}