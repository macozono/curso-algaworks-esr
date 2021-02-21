package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.algaworks.algafood.api.validation.Groups;
import com.algaworks.algafood.api.validation.Groups.CozinhaId;
import com.algaworks.algafood.api.validation.ValorZeroIncluiDescricao;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ValorZeroIncluiDescricao(descricaoField = "nome", valorField = "taxaFrete", descricaoObrigatoria = "Obrigatório informar que taxa frete é gratis")
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	@NotNull(groups = CozinhaId.class)
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
	private Cozinha cozinha;
	
	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", 
		joinColumns = @JoinColumn(name = "restaurante_id"),
		inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@Embedded
	private Endereco endereco;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;
	
	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();
}
