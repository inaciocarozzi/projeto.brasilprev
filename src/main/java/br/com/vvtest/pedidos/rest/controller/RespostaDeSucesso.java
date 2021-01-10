package br.com.vvtest.pedidos.rest.controller;

public class RespostaDeSucesso {

	private Long id;

	public RespostaDeSucesso() {}

	public RespostaDeSucesso(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
