package br.com.brasilprev.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.pedidos.dominio.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {
}