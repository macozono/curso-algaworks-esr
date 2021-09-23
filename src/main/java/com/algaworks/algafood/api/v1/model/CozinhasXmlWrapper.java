package com.algaworks.algafood.api.v1.model;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

//@JacksonXmlRootElement(localName = "cozinhas")
//@Data
public class CozinhasXmlWrapper {

	// anotação indica que o atributo não pode ser nulo e obriga a ser informado via construtor...
//	@NonNull
//	@JacksonXmlProperty(localName = "cozinha")
//	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cozinha> cozinhas;
}
