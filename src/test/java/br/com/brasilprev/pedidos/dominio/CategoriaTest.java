package br.com.brasilprev.pedidos.dominio;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.vvtest.pedidos.dominio.Categoria;
import br.com.vvtest.pedidos.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
public class CategoriaTest {
	
    @Test
    public void deveCriarUmaCategoria(){
        String nome = "categoria";

        Categoria categoria = new Categoria(nome);

        Assert.assertEquals(nome, categoria.getNome());
    }

    @Test
    public void naoDeveCriarUmaCategoriaSemNome(){
        String nome = "";

        try {
        	new Categoria(nome);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidFieldException);
		}
    }
}
