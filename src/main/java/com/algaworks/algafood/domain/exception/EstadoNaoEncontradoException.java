package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1121040781122377512L;

	private static final String MSG_ESTADO_NAO_EXISTE = "Não existe um estado com o código %d.";
	
	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public EstadoNaoEncontradoException(Long estadoId) {
		this(String.format(MSG_ESTADO_NAO_EXISTE, estadoId));
	}
}
