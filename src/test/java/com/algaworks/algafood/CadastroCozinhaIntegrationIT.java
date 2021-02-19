package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CadastroCozinhaIntegrationIT {

	@Autowired
	private CadastroCozinhaService service;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome("Chinesa");
		
		cozinha = service.salvar(cozinha);
		
		assertThat(cozinha).isNotNull();
		assertThat(cozinha.getId()).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha cozinha = new Cozinha();
		cozinha.setNome(null);
		
		try {
			cozinha = service.salvar(cozinha);
		} catch (ConstraintViolationException e) {
			assertThat(e).isInstanceOf(ConstraintViolationException.class);
		}
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		try {
			service.excluir(1L);
		} catch (EntidadeEmUsoException e) {
			assertThat(e).isInstanceOf(EntidadeEmUsoException.class);
		}
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		try {
			service.excluir(10L);
		} catch (EntidadeNaoEncontradaException e) {
			assertThat(e).isInstanceOf(EntidadeNaoEncontradaException.class);
		}
	}
}
