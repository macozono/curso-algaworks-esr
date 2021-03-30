package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;

@Component
public class GrupoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Grupo toDomainObject(GrupoInput input) {
		return mapper.map(input, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInput input, Grupo grupo) {
		mapper.map(input, grupo);
	}
}
