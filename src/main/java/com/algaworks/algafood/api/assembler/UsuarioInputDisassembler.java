package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Usuario toDomainObject(UsuarioInput input) {
		return mapper.map(input, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInput input, Usuario usuario) {
		mapper.map(input, usuario);
	}
}
