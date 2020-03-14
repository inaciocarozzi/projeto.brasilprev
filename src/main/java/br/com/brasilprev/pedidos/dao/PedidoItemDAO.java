package br.com.brasilprev.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.pedidos.dominio.PedidoItem;

public interface PedidoItemDAO extends JpaRepository<PedidoItem, Long> {
	
}