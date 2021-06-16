package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDissambler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.domain.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private EmissaoPedidoService service;
	
	@Autowired
	private PedidoModelAssembler assembler;
	
	@Autowired
	private PedidoResumoModelAssembler resumoAssembler;
	
	@Autowired
	private PedidoInputDissambler pedidoInputDisassembler;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	

	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmar(pedidoId);
	}
}
