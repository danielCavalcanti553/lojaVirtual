package com.tcc.lojavirtual.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.dto.CategoriaDTO;
import com.tcc.lojavirtual.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value="/produtos",method=RequestMethod.GET)
	public ResponseEntity<List<Categoria>> findAllAndProduto(){
		List<Categoria> list = categoriaService.findAll();
		return ResponseEntity.ok().body(list);
	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll(){
		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> dto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dto);
	
	}
	
}
