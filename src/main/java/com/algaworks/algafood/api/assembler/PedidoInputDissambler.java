package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoInputDissambler {

	@Autowired
	private ModelMapper mapper;
	
	public Pedido toDomainObject(PedidoInput pedidoInput) {
		return mapper.map(pedidoInput, Pedido.class);
	}
	
	public void copyToDomainObject(PedidoInput pedidoInput, Pedido pedido) {
		mapper.map(pedidoInput, pedido);
	}
}
