package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.v1.model.PermissaoModel;
import com.algaworks.algafood.api.v1.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService grupoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoAssembler;
	
	@Autowired
	private AlgaSecurity algaSecurity;
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
	    
	    CollectionModel<PermissaoModel> permissoesModel 
	        = permissaoAssembler.toCollectionModel(grupo.getPermissoes())
	            .removeLinks();
	    
	    permissoesModel.add(algaLinks.linkToGrupoPermissoes(grupoId));
	    
	    if (algaSecurity.podeEditarUsuariosGruposPermissoes()) {
	        permissoesModel.add(algaLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
	    
	        permissoesModel.getContent().forEach(permissaoModel -> {
	            permissaoModel.add(algaLinks.linkToGrupoPermissaoDesassociacao(
	                    grupoId, permissaoModel.getId(), "desassociar"));
	        });
	    }
	    
	    return permissoesModel;
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@CheckSecurity.UsuariosGruposPermissoes.PodeEditar
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
}
