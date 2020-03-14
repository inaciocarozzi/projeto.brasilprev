package br.com.brasilprev.pedidos.builder;

import br.com.brasilprev.pedidos.dominio.Cliente;
import br.com.brasilprev.pedidos.dominio.Endereco;

public class ClienteBuilder {
    private String nome = "Guilherme";
    private String email = "guilherme@teste.com";
    private String senha = "qwerty";
    private Endereco endereco = EnderecoBuilder.umEndereco().build();

    private ClienteBuilder(){
    }

    public static ClienteBuilder umCliente() {
        return new ClienteBuilder();
    }

    public Cliente build(){
    	return new Cliente(nome, email, senha, endereco);
    }
}
