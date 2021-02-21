package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@ResponseBody
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;
	
//	@Autowired
//	private SmartValidator validator;

	@GetMapping
	public List<RestauranteModel> listar() {
		return restauranteModelAssembler.toCollection(this.restauranteRepository.findAll()); 
	}
	
	@GetMapping("/{idRestaurante}")
	public RestauranteModel buscar(@PathVariable Long idRestaurante) {
		Restaurante restaurante = cadastroRestauranteService.buscar(idRestaurante);
		RestauranteModel restauranteModel = restauranteModelAssembler.toModel(restaurante);
		
		return restauranteModel;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
			
		}  catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{idRestaurante}")
	@ResponseStatus(HttpStatus.OK)
	public RestauranteModel atualizar(@PathVariable Long idRestaurante, @RequestBody RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			Restaurante restauranteAtual = cadastroRestauranteService.buscar(idRestaurante);
			
			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
			
			return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
		}  catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
//	@PatchMapping("/{idRestaurante}")
//	public RestauranteModel atualizarParcial(@PathVariable Long idRestaurante, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
//		Restaurante restauranteAtual = cadastroRestauranteService.buscar(idRestaurante);
//		
//		merge(campos, restauranteAtual, request);
//		validar(restauranteAtual, "restaurante");
//			
//		return atualizar(idRestaurante, restauranteAtual);
//	}
	
//	private void validar(Restaurante restaurante, String objectName) {
//		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
//		validator.validate(restaurante, bindingResult);
//		
//		if (bindingResult.hasErrors()) {
//			throw new ValidacaoException(bindingResult);
//		}
//	}

//	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
//		ObjectMapper mapper = new ObjectMapper();
//		
//		mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
//		
//		ServletServerHttpRequest servletHttpRequest = new ServletServerHttpRequest(request);
//		
//		try {
//			Restaurante restauranteOrigem = mapper.convertValue(dadosOrigem, Restaurante.class);
//			
//			dadosOrigem.forEach((chave, valor) -> {
//				Field field = ReflectionUtils.findField(Restaurante.class, chave);
//				field.setAccessible(true);
//				
//				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
//				ReflectionUtils.setField(field, restauranteDestino, novoValor);
//			});			
//		} catch (IllegalArgumentException e) {
//			Throwable rootCause = ExceptionUtils.getRootCause(e);
//			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletHttpRequest);
//		}
//	}
}