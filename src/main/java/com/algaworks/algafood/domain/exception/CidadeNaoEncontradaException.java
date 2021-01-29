package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com o código %d.";
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
	}
}