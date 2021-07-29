package com.algaworks.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties storageProperties;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
		
		try {
			FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar arquivo.", e);
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return storageProperties.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
	}

	@Override
	public void remover(String nomeFoto) {
		Path path = getArquivoPath(nomeFoto);
		
		try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			throw new StorageException("Não foi possível excluir arquivo.", e);
		}
	}

	@Override
	public FotoRecuperada recuperar(String nomeFoto) {
		Path path = getArquivoPath(nomeFoto);
		
		try {
			return FotoRecuperada.builder().inputStream(Files.newInputStream(path)).build();
		} catch (IOException e) {
			throw new StorageException("Não foi possível recuperar arquivo.", e);
		}
	}
}
