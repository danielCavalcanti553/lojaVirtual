package com.tcc.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria insert(Categoria obj) {
		obj.setCodigoCategoria(null);
		return categoriaRepository.save(obj);
	}
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
}
