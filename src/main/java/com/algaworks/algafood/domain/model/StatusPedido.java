package com.algaworks.algafood.domain.model;

public enum StatusPedido {
	CRIADO("criado"),
    CONFIRMADO("confirmado"),
    ENTREGUE("entregue"),
    CANCELADO("cancelado");
    
    private String descricao;
	
	StatusPedido(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
