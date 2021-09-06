package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoDisassembler {

	@Autowired
	private ModelMapper mapper;
	
	public FormaPagamento toDomainObject(FormaPagamentoInput input) {
		return mapper.map(input, FormaPagamento.class);
	}
	
	public void copyToDomainObject(FormaPagamentoInput input, FormaPagamento formaPagamento) {
		mapper.map(input, formaPagamento);
	}
}