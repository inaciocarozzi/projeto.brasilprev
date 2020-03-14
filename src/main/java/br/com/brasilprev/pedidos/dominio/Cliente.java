package br.com.brasilprev.pedidos.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.brasilprev.pedidos.exception.InvalidFieldException;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;

	private String nome;

	private String email;

	private String senha;
	
	@ManyToOne
	private Endereco endereco;
	
	public Cliente() {}
	
	public Cliente(String nome, String email, String senha, Endereco endereco) {
		if (nome == null || nome.isEmpty()) {
			throw new InvalidFieldException("Campo nome não deve ser nulo para o cliente.");
		}
		
		if (email == null || email.isEmpty()) {
			throw new InvalidFieldException("Campo email não deve ser nulo para o cliente.");
		}
		
		if (senha == null || senha.isEmpty()) {
			throw new InvalidFieldException("Campo senha não deve ser nulo para o cliente.");
		}
		
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}
	
	public void atualizar(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}
}
