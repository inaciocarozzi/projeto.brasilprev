package br.com.vvtest.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvtest.pedidos.dominio.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}