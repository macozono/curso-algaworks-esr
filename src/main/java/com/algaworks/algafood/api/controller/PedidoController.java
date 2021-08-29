package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDissambler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.api.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.PedidoRepository;
import com.algaworks.algafood.domain.service.EmissaoPedidoService;
import com.algaworks.algafood.infrastructure.spec.PedidosSpec;
import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping(value = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

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
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
	
//	@GetMapping
//	public List<PedidoResumoModel> listar() {
//		List<Pedido> todosPedidos = repository.findAll();
//		return resumoAssembler.toCollectionModel(todosPedidos);
//	}
	
//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required=false) String campos) {
//		List<Pedido> todosPedidos = repository.findAll();
//		List<PedidoResumoModel> pedidosModel = resumoAssembler.toCollectionModel(todosPedidos);
//		
//		MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
//		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//		
//		filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//		
//		if (StringUtils.isNotBlank(campos)) {
//			filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//		}
//		
//		pedidosWrapper.setFilters(filterProvider);
//		
//		return pedidosWrapper;
//	}
	
	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);
		Page<Pedido> pedidosPage = repository.findAll(PedidosSpec.usandoFiltro(filtro), pageableTraduzido);
		
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		return pagedResourcesAssembler.toModel(pedidosPage, resumoAssembler);
	} 
	
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = service.buscarOuFalhar(codigoPedido);
		return assembler.toModel(pedido);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
	    try {
	        Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

	        // TODO pegar usuário autenticado
	        novoPedido.setCliente(new Usuario());
	        novoPedido.getCliente().setId(1L);

	        novoPedido = emissaoPedido.emitir(novoPedido);

	        return pedidoModelAssembler.toModel(novoPedido);
	    } catch (EntidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
					"nomeCliente", "cliente.nome",
					"restaurante.nome", "restaurante.nome",
					"codigo", "codigo",
					"valorTotal", "valorTotal"
					);
		
		return PageableTranslator.pageableTranslate(apiPageable, mapeamento);
	}
}
