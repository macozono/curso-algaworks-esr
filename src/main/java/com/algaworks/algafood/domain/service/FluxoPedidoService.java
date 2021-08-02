package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.repository.PedidoRepository;

@Service
public class FluxoPedidoService {

	@Autowired
	private EmissaoPedidoService emissaoPedidoService;
	
	// Injeção necessária para disparar evento de domínio.
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
		pedido.confirmar();
		
		// Necessário chamar o save, mesmo para uma entidade gerenciada
		// para que o evento de domínio seja disparado.
		pedidoRepository.save(pedido);
	}
	
	@Transactional
	public void cancelar(String codigoPedido) {
	    Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
	    pedido.cancelar();
	    
		// Necessário chamar o save, mesmo para uma entidade gerenciada
		// para que o evento de domínio seja disparado.
	    pedidoRepository.save(pedido);
	}

	@Transactional
	public void entregar(String codigoPedido) {
	    Pedido pedido = emissaoPedidoService.buscarOuFalhar(codigoPedido);
	    pedido.entregar();
	}
}
