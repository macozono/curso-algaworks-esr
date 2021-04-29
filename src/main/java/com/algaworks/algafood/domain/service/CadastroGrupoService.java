package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	//private static final String MSG_GRUPO_EM_USO = "Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository repository;
	
	@Autowired
	private CadastroPermissaoService permissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return this.repository.save(grupo);
	}
	
	public void excluir(Long grupoId) {
		try {
			this.repository.deleteById(grupoId);
			this.repository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
	}
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return this.repository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		
		grupo.removerPermissao(permissao);
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		
		grupo.adicionarPermissao(permissao);
	}
}
