package com.tcc.lojavirtual.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tcc.lojavirtual.domain.Categoria;
import com.tcc.lojavirtual.domain.Pedido;
import com.tcc.lojavirtual.dto.CategoriaDTO;
import com.tcc.lojavirtual.service.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id){
		Pedido obj = pedidoService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Pedido>> findAll(){
		List<Pedido> list = pedidoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Pedido obj){
		
		obj = pedidoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getCodigoPedido()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
