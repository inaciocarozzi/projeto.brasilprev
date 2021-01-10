package br.com.vvtest.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvtest.pedidos.dominio.Pedido;

public interface PedidoDAO extends JpaRepository<Pedido, Long> {
}