package br.com.brasilprev.pedidos.builder;

import br.com.brasilprev.pedidos.dominio.Endereco;

public class EnderecoBuilder {
    private String rua = "Rua Feliz";
    private String bairro = "Bairro Alegre";
    private String cidade = "Cidade Felicidade";
    private String estado = "Estado Estranho";
    private Integer cep = 79011470;

    private EnderecoBuilder(){
    }

    public static EnderecoBuilder umEndereco() {
        return new EnderecoBuilder();
    }

    public Endereco build(){
        return new Endereco(rua, cidade, bairro, cep, estado);
    }
}
