package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	MENSAGEM_INCONSISTENTE("/mensagem-inconsistente", "Mensagem inconsistente");
	
	private String title;
	private String uri;
	
	private static final String BASE_URL = "https://algafood.com.br";
	
	ProblemType(String path, String title) {
		this.uri = BASE_URL + path;
		this.title = title;
	}
}
