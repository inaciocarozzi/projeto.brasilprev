package br.com.brasilprev.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.pedidos.dominio.Produto;

public interface ProdutoDAO extends JpaRepository<Produto, Long> {
}	