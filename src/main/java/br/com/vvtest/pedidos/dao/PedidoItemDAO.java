package br.com.vvtest.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvtest.pedidos.dominio.PedidoItem;

public interface PedidoItemDAO extends JpaRepository<PedidoItem, Long> {
	
}