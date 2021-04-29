package com.algaworks.algafood.infrastructure;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Permissao> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permissao> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Permissao> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Permissao> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Permissao> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Permissao> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Permissao getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Permissao> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Permissao> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Permissao> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Permissao> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Permissao> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Permissao entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Permissao> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Permissao> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Permissao> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Permissao> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Permissao> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
}