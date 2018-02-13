package com.tcc.lojavirtual.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.dto.ClienteDTO;
import com.tcc.lojavirtual.dto.ClienteNewDTO;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.service.exception.DataIntegrityException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;;

@Service
public class ClienteService {

	@Autowired
	private BCryptPasswordEncoder passCrypt;
	
	@Autowired
	private ClienteRepository clienteRepository;

	public Cliente find(Integer id) {
		Cliente obj = clienteRepository.findOne(id);
		if (obj == null)
			throw new NotFoundObjectException("Cliente Não encontrado! " + id + ", Tipo " + Cliente.class.getName());
		return obj;
	}

	public Cliente insert(Cliente obj) {
		obj.setCodigoCliente(null);
		return clienteRepository.save(obj);
	}

	public Cliente fromNewDTO(ClienteNewDTO obj) {
		Cliente c = new Cliente(null, obj.getCpf(), obj.getNome(), obj.getEndereco(), obj.getMunicipio(),
				obj.getEstado(), obj.getTelefone(), obj.getEmail(), passCrypt.encode(obj.getSenha()));
		return c;
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPage, String order, String direction) {
		PageRequest pageRequest = new PageRequest(page, linesPage, Direction.valueOf(direction), order);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getCodigoCliente());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	

	public Cliente fromDTO(ClienteDTO objDto) {

		Cliente c = new Cliente(null, objDto.getNome(), objDto.getNome(), objDto.getEndereco(), objDto.getMunicipio(),
				objDto.getEstado(), objDto.getTelefone(), objDto.getEmail(), objDto.getSenha());

		return c;
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.delete(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos");
		}
	}

}
