package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import lombok.Getter;
import lombok.Setter;

@Relation(collectionRelation = "estatisticas")
@Getter
@Setter
public class EstatisticasModel extends RepresentationModel<EstatisticasModel> {

}
