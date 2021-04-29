package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CadastroFormaPagamentoService formaPagamento;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	public Restaurante buscar(Long id) {
		Restaurante restaurante = restauranteRepository.findById(id)
				.orElseThrow(() -> new RestauranteNaoEncontradoException(id));
		
		return restaurante;
	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cadastroCozinha.buscar(cozinhaId);
		Cidade cidade = cadastroCidade.buscar(cidadeId);
		
		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);
		
		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Long restauranteId) {
		Restaurante restauranteAtual = buscar(restauranteId);
		restauranteAtual.ativar();
	}
	
	@Transactional
	public void inativar(Long restauranteId) {
		Restaurante restauranteAtual = buscar(restauranteId);
		restauranteAtual.inativar();
	}
	
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = this.buscar(restauranteId);
		
		FormaPagamento formaPagamento = this.formaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.removerFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId) {
		Restaurante restaurante = this.buscar(restauranteId);
		
		FormaPagamento formaPagamento = this.formaPagamento.buscarOuFalhar(formaPagamentoId);
		restaurante.adicionarFormaPagamento(formaPagamento);
	}
	
	@Transactional
	public void abrir(Long restauranteId) {
		Restaurante restaurante = this.buscar(restauranteId);
		restaurante.abrir();
	}
	
	@Transactional
	public void fechar(Long restauranteId) {
		Restaurante restaurante = this.buscar(restauranteId);
		restaurante.fechar();
	}
	
	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = this.buscar(restauranteId);
		Usuario usuario = this.cadastroUsuario.buscarOuFalhar(usuarioId);
		
		restaurante.removerResponsavel(usuario);
	}
	
	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId) {
		Restaurante restaurante = this.buscar(restauranteId);
		Usuario usuario = this.cadastroUsuario.buscarOuFalhar(usuarioId);
		
		restaurante.adicionarResponsavel(usuario);
	}
	
	@Transactional
	public void ativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::ativar);
	}
	
	@Transactional
	public void inativar(List<Long> restauranteIds) {
		restauranteIds.forEach(this::inativar);
	}
}