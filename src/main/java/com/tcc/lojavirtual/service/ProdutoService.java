package com.tcc.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Produto;
import com.tcc.lojavirtual.repository.ProdutoRepository;
import com.tcc.lojavirtual.service.exception.ObjectNotFoundException;;
@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto find(Integer id) {
		Produto obj = produtoRepository.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Produto Não encontrado! " +
					id + ", Tipo " + Produto.class.getName());
			}
		
		return obj;
	}
	
	public Produto insert(Produto obj) {
		obj.setCodigoProduto(null);
		return categoriaRepository.save(obj);
	}
	
}
