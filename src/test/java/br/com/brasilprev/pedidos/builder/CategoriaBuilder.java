package br.com.brasilprev.pedidos.builder;

import br.com.vvtest.pedidos.dominio.Categoria;

public class CategoriaBuilder {
    private String nome = "categoria";

    private CategoriaBuilder(){
    }

    public static CategoriaBuilder umaCategoria(){
        return new CategoriaBuilder();
    }

    public Categoria build(){
        return new Categoria(nome);
    }
}
