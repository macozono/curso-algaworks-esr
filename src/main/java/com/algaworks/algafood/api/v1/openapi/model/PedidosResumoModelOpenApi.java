package com.algaworks.algafood.api.v1.openapi.model;

import org.springframework.hateoas.Links;

import com.algaworks.algafood.api.v1.model.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@ApiModel("PedidosModel")
@Getter
@Setter
public class PedidosResumoModelOpenApi {

	private PedidoResumoEmbeddedModelOpenApi _embedded;
	private Links _links;
	private PageModelOpenApi page;

	@ApiModel("PedidoResumoEmbeddedModel")
	@Data
	private class PedidoResumoEmbeddedModelOpenApi {
			
		private PedidoResumoModel pedidoResumo;
	}
}
