package br.com.brasilprev.pedidos.dominio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brasilprev.pedidos.dominio.Endereco;
import br.com.brasilprev.pedidos.exception.InvalidFieldException;

@RunWith(SpringRunner.class)
public class EnderecoTest {
    private String rua;
    private String cidade;
    private String bairro;
    private Integer cep;
    private String estado;

    @Before
    public void Init(){
        rua = "Barra do Garças";
        cidade = "Alto Garças";
        bairro = "Novo Horizonte";
        cep = 78770000;
        estado = "MT";
    }

    @Test
    public void deveCriarUmEndereco(){
        Endereco endereco = new Endereco(rua, cidade, bairro, cep, estado);

        Assert.assertEquals(rua, endereco.getRua());
        Assert.assertEquals(bairro, endereco.getBairro());
        Assert.assertEquals(cep, endereco.getCep());
        Assert.assertEquals(cidade, endereco.getCidade());
        Assert.assertEquals(estado, endereco.getEstado());
    }

    @Test
    public void naoDeveCriarEndereco(){
    	String ruaNula = null;
        String cidadeVazia = "";
        String bairroNulo = null;
        Integer cepNulo = null;
        String estadoVazio = "";

        
        try {
        	new Endereco(ruaNula, cidade, bairro, cep, estado);
        	new Endereco(rua, cidadeVazia, bairro, cep, estado);
        	new Endereco(rua, cidade, bairroNulo, cep, estado);
        	new Endereco(rua, cidade, bairro, cepNulo, estado);
        	new Endereco(rua, cidade, bairro, cep, estadoVazio);
		} catch (Exception e) {
			Assert.assertTrue(e instanceof InvalidFieldException);
		}
        
    }
}
