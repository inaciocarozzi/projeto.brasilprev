package br.com.vvtest.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvtest.pedidos.dominio.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}	