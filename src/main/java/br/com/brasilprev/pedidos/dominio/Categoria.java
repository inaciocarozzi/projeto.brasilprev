package br.com.brasilprev.pedidos.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.brasilprev.pedidos.exception.InvalidFieldException;

@Entity
public class Categoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCategoria;

	private String nome;
	
	public Categoria() {}
	
	public Categoria(String nome) {
		if (nome == null || nome.isEmpty()) {
			throw new InvalidFieldException("Campo nome não deve ser nulo na categoria.");
		}
		
		this.nome = nome;
	}

	public void atualizar(String nome) {
		this.nome = nome;
	}
	
	public Long getIdCategoria() {
		return idCategoria;
	}

	public String getNome() {
		return nome;
	}
}
