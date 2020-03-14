package br.com.brasilprev.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.pedidos.dominio.Endereco;

public interface EnderecoDAO extends JpaRepository<Endereco, Long> {
}