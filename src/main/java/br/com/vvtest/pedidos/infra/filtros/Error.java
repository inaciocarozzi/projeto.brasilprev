package br.com.vvtest.pedidos.infra.filtros;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Error {
	public String mensagem;
    public Integer status;
    @JsonFormat(pattern = "dd-MM-yyyy")
    public LocalDateTime data;
}
