package com.algaworks.algafood.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class Problem {

	@ApiModelProperty(example = "400")
	private Integer status;
	@ApiModelProperty(example = "https://algafood.com.br/dados-invalidos")
	private String type;
	@ApiModelProperty(example = "Dados inválidos")
	private String title;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento corretamente.")
	private String detail;
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento corretamente.")
	private String userMessage;
	@ApiModelProperty(example = "2019-12-01T18:10:23.70844Z")
	private OffsetDateTime timestamp;
	@ApiModelProperty(value = "Objetos ou campos que geraram o erro.")
	private List<Object> objects;

	@ApiModel("ObjetoProblema")
	@Getter
	@Builder
	public static class Object {
		@ApiModelProperty(example = "preco")
		private String name;
		@ApiModelProperty(example = "Preço é obrigatório.")
		private String userMessage;
	}
}