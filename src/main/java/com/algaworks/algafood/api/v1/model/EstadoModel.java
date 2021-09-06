package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Relation(collectionRelation = "estados")
public class EstadoModel extends RepresentationModel<EstadoModel> {

	@ApiModelProperty(value = "ID da cidade", example = "1")
    private Long id;
	@ApiModelProperty(example = "São Paulo")
    private String nome;
}