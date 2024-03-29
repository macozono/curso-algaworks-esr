package com.algaworks.algafood.domain.exception;

public class FotoProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -123619319475253885L;

	public FotoProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FotoProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		this(String.format("Não existe um cadastro de foto do produto com código %d para o restaurante de código %d",
				restauranteId, produtoId));
	}
}
