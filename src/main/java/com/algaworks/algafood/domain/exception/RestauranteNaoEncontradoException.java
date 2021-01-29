package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = "Restaurante com o código %d não encontrado.";
	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId));
	}
}
