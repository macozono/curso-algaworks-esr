package com.algaworks.algafood.api.assembler;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteInputDisassembler {

	public Restaurante toDomainObject(RestauranteInput input) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(input.getNome());
		restaurante.setTaxaFrete(input.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		
		cozinha.setId(input.getCozinha().getId());
		restaurante.setCozinha(cozinha);
		
		return restaurante;
	}
}
