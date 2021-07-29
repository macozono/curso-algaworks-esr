package com.algaworks.algafood.infrastructure.service.storage;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.core.storage.StorageProperties;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class S3FotoStorageService implements FotoStorageService {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			var caminhoArquivo = getCaminho(novaFoto.getNomeArquivo());
			var objectMetadata = new ObjectMetadata();
			
			objectMetadata.setContentType(novaFoto.getContentType());
			
			var putObject = new PutObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo,
					novaFoto.getInputStream(), objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead);
			
			amazonS3.putObject(putObject);
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar arquivo para Amazon S3", e.getCause());
		}
	}

	@Override
	public void remover(String nomeFoto) {
		try {
			String caminhoArquivo = getCaminho(nomeFoto);
			
			var deleteObjectRequest = new DeleteObjectRequest(storageProperties.getS3().getBucket(), caminhoArquivo);
			
			amazonS3.deleteObject(deleteObjectRequest);			
		} catch (Exception e) {
			throw new StorageException("Não foi possível remover arquivo para Amazon S3", e.getCause());
		}

	}

	@Override
	public FotoRecuperada recuperar(String nomeFoto) {
		String caminhoArquivo = getCaminho(nomeFoto);
		URL url = amazonS3.getUrl(storageProperties.getS3().getBucket(), caminhoArquivo);
		
		return FotoRecuperada.builder().url(url.toString()).build();
	}
	
	private String getCaminho(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}
}