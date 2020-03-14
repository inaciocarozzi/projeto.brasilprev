package br.com.brasilprev.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.pedidos.dominio.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {
}