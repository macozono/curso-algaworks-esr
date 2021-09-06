package com.algaworks.algafood.api.v1.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.algaworks.algafood.api.exceptionhandler.Problem;
import com.algaworks.algafood.api.v1.model.CidadeModel;
import com.algaworks.algafood.api.v1.model.input.CidadeInput;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

	@ApiOperation(value = "Lista cidades")
	public CollectionModel<CidadeModel> listar();

	@ApiOperation(value = "Busca uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "ID da cidade inválido.", response = Problem.class),
		@ApiResponse(code = 404, message = "Cidade não encontrada.", response = Problem.class)
	})
	CidadeModel buscar(@ApiParam(value = "ID de uma cidade", example = "1") Long cidadeId);
	
	@ApiOperation(value = "Cadastra uma cidade")
	@ApiResponses({
		@ApiResponse(code = 201, message = "Cidade cadastrada."),
	})
	CidadeModel adicionar(@ApiParam(name = "Corpo", value = "Representação de uma nova cidade") CidadeInput cidadeInput);
	
	@ApiOperation(value = "Cadastra uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Cidade atualizada."),
		@ApiResponse(code = 404, message = "Cidade não encontrada.", response = Problem.class)
	})
	CidadeModel atualizar(
			@ApiParam(value = "Id de uma cidade", example = "1") Long cidadeId, 
			@ApiParam(name = "Corpo", value = "Representação de uma cidade com novos dados") CidadeInput cidadeInput);
	
	@ApiOperation(value = "Remove uma cidade por ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Cidade excluída."),
		@ApiResponse(code = 404, message = "Cidade não encontrada.", response = Problem.class)
	})
	void remover(@ApiParam(value = "Id de uma cidade", example = "1") Long cozinhaId);
}
