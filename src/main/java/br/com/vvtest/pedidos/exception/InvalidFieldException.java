package br.com.vvtest.pedidos.exception;

public class InvalidFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidFieldException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
