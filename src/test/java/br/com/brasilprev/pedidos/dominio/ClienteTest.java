package br.com.brasilprev.pedidos.dominio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brasilprev.pedidos.builder.EnderecoBuilder;
import br.com.vvtest.pedidos.dominio.Cliente;
import br.com.vvtest.pedidos.dominio.Endereco;
import br.com.vvtest.pedidos.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
public class ClienteTest {
    private String nome;
    private String email;
    private String senha;
    private Endereco endereco;

    @Before
    public void Init(){
        nome = "Guilherme";
        email = "guilherme@teste.com";
        senha = "paraiso";
        endereco = EnderecoBuilder.umEndereco().build();
    }

    @Test
    public void deveCriarCliente(){
    	Cliente cliente = new Cliente(nome, email, senha, endereco);
        
        Assert.assertEquals(nome, cliente.getNome());
        Assert.assertEquals(email, cliente.getEmail());
        Assert.assertEquals(senha, cliente.getSenha());
        Assert.assertEquals(endereco, cliente.getEndereco());
    }

    @Test
    public void naoDeveCriarCliente(){
        String nomeNulo = null;
        
        String emailEmBranco = "";
        
        String senhaNula = null;
        
        try {
        	new Cliente(nomeNulo, email, senha, endereco);
        	new Cliente(nome, emailEmBranco, senha, endereco);
        	new Cliente(nome, email, senhaNula, endereco);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidFieldException);
		}
    }
}
