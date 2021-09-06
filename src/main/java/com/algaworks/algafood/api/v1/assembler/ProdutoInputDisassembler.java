package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoInputDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public Produto toDomainObject(ProdutoInput input) {
		return mapper.map(input, Produto.class);
	}
	
	public void copyDomainObject(ProdutoInput input, Produto produto) {
		mapper.map(input, produto);
	}
}
