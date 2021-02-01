package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	MENSAGEM_INCONSISTENTE("/mensagem-inconsistente", "Mensagem inconsistente"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema");
	
	private String title;
	private String uri;
	
	private static final String BASE_URL = "https://algafood.com.br";
	
	ProblemType(String path, String title) {
		this.uri = BASE_URL + path;
		this.title = title;
	}
}
