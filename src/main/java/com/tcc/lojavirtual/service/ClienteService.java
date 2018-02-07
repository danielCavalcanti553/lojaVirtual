package com.tcc.lojavirtual.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.service.exception.ObjectNotFoundException;;
@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	
	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		
		if(obj==null) {
			throw new ObjectNotFoundException("Cliente Não encontrado! " +
					id + ", Tipo " + Cliente.class.getName());
			}
		
		return obj;
	}
	
	public Cliente insert(Cliente obj) {
		obj.setCodigoCliente(null);
		return clienteRepository.save(obj);
	}
	
}
