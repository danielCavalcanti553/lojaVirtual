package com.tcc.lojavirtual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tcc.lojavirtual.service.ProfileTestDados;

@SpringBootApplication
public class LojavirtualApplication implements CommandLineRunner{
	
	@Autowired
	private ProfileTestDados profileTestDados;
	
	public static void main(String[] args) {
		SpringApplication.run(LojavirtualApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		
		
	}
}
