package br.com.vvtest.pedidos.dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.vvtest.pedidos.exception.InvalidFieldException;

@Entity
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEndereco;
	
	private String rua;

	private String cidade;

	private String bairro;

	private Integer cep;

	private String estado;
	
	public Endereco() {}

	public Endereco(String rua, String cidade, String bairro, Integer cep, String estado) {
		if (rua == null || rua.isEmpty()) {
			throw new InvalidFieldException("Campo rua não deve ser nulo para o Endereço.");
		}
		
		if (cidade == null || cidade.isEmpty()) {
			throw new InvalidFieldException("Campo cidade não deve ser nulo para o Endereço.");
		}
		
		if (bairro == null || bairro.isEmpty()) {
			throw new InvalidFieldException("Campo bairro não deve ser nulo para o Endereço.");
		}
		
		if (cep == null) {
			throw new InvalidFieldException("Campo cep não deve ser nulo para o Endereço.");
		}
		
		if (estado == null || estado.isEmpty()) {
			throw new InvalidFieldException("Campo estado não deve ser nulo para o Endereço.");
		}
		
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
	}
	
	public void atualizar (String rua, String cidade, String bairro, Integer cep, String estado) {
		this.rua = rua;
		this.cidade = cidade;
		this.bairro = bairro;
		this.cep = cep;
		this.estado = estado;
	}

	public String getRua() {
		return rua;
	}
	
	public Long getIdEndereco() {
		return idEndereco;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public Integer getCep() {
		return cep;
	}

	public String getEstado() {
		return estado;
	}
}
