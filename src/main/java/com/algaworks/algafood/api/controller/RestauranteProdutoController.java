package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.ProdutoInput;
import com.algaworks.algafood.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.openapi.controller.RestauranteProdutoControllerOpenApi;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private CadastroProdutoService produtoService;
	
	@Autowired
	private CadastroRestauranteService restauranteService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId, @RequestParam(required=false) boolean incluirInativos) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		
		List<Produto> todosProdutos = null; 
				
		if (incluirInativos) {
			todosProdutos = produtoService.produtosPorRestaurante(restaurante);	
		} else {
			todosProdutos = produtoService.produtosAtivosPorRestaurante(restaurante);
		}
		
		return produtoModelAssembler.toCollectionModel(todosProdutos);
	}
	
	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
		
		return produtoModelAssembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Long restauranteId, @RequestBody @Valid ProdutoInput input) {
		Restaurante restaurante = restauranteService.buscar(restauranteId);
		
		Produto produto = produtoInputDisassembler.toDomainObject(input);
		produto.setRestaurante(restaurante);
		
		produto = produtoService.salvar(produto);
		return produtoModelAssembler.toModel(produto);
	}
	
	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long restauranteId, @PathVariable Long produtoId, @RequestBody @Valid ProdutoInput input) {
		Produto produtoAtual = produtoService.buscarOuFalhar(restauranteId, produtoId);
		produtoInputDisassembler.copyDomainObject(input, produtoAtual);
		
		produtoAtual = produtoService.salvar(produtoAtual);
		return produtoModelAssembler.toModel(produtoAtual);
	}
}
