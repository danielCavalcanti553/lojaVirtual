package com.tcc.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Pedido;
import com.tcc.lojavirtual.repository.PedidoRepository;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;;
@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer id) {
		Pedido obj = pedidoRepository.findOne(id);
		
		if(obj==null) {
			throw new NotFoundObjectException("Pedido Não encontrado! " +
					id);
			}
		
		return obj;
	}
	
	public List<Pedido> findAll() {
		return pedidoRepository.findAll();
	}
	

	
}
