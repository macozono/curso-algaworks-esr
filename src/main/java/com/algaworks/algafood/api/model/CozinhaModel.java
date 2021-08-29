package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Relation(collectionRelation = "cozinhas")
public class CozinhaModel extends RepresentationModel<CozinhaModel> {

	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(example = "1")
	private Long id;
	@JsonView(RestauranteView.Resumo.class)
	@ApiModelProperty(example = "Brasileira")
	private String nome;
}
