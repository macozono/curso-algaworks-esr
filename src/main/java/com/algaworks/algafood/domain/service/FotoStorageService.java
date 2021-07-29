package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto); 
	
	void remover(String nomeFoto);
	
	FotoRecuperada recuperar(String nomeFoto);
	
	default String gerarNomeArquivo(String nomeOriginal) {
		return UUID.randomUUID().toString() + "_" + nomeOriginal;
	}
	
	default void substituir(String nomeArquivoAntigo, NovaFoto novaFoto) {
		this.armazenar(novaFoto);
		
		if (nomeArquivoAntigo != null) {
			this.remover(nomeArquivoAntigo);
		}
	}
	
	@Getter
	@Builder
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;
		private String contentType;
	}
	
	@Getter
	@Builder
	class FotoRecuperada {
		private InputStream inputStream;
		private String url;
		
		public boolean temUrl() {
			return this.url != null;
		}
		
		public boolean temInputStream() {
			return this.inputStream != null;
		}
	}
}
