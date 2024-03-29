package com.algaworks.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Setter
@Getter
@Relation(collectionRelation = "cidades")
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> {

	@ApiModelProperty(value = "ID da cidade", example = "1")
    private Long idCidade;
	@ApiModelProperty(example = "São Paulo")
    private String nomeCidade;
	
	private Long idEstado;
	private String nomeEstado;
//    private EstadoModel estado;	
} 