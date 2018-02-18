package com.tcc.lojavirtual.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tcc.lojavirtual.domain.Cliente;
import com.tcc.lojavirtual.repository.ClienteRepository;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;


@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	private Random rand = new Random();
	
	//@Autowired
	//private EmailService emailService;
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente==null) {
			throw new NotFoundObjectException("Email não encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(be.encode(newPass));
		
		clienteRepository.save(cliente);
		//emailService.sendNewPasswordEmail(cliente,newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0; i<10;i++) {
			vet[i] = ramdomChar();
		}
		return new String(vet);
	}

	private char ramdomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {// gera digito
			
			return (char) (rand.nextInt(10)+48);
			
		}else if(opt ==1) { // gera letra maíuscula
			
			return (char) (rand.nextInt(26)+65);
		}else { // gera letra minúscula
			
			return (char) (rand.nextInt(26)+97);
		}
		
	}
	
	

}
