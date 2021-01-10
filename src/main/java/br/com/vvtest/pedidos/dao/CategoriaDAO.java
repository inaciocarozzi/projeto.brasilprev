package br.com.vvtest.pedidos.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.vvtest.pedidos.dominio.Categoria;

public interface CategoriaDAO extends JpaRepository<Categoria, Long> {
}