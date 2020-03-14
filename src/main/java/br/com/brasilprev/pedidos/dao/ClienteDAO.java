package br.com.brasilprev.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.pedidos.dominio.Cliente;

public interface ClienteDAO extends JpaRepository<Cliente, Long> {
}