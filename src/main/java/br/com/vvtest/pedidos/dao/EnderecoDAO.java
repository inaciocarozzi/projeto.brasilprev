package br.com.vvtest.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvtest.pedidos.dominio.Endereco;

public interface EnderecoDAO extends JpaRepository<Endereco, Long> {
}