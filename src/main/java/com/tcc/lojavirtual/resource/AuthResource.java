package com.tcc.lojavirtual.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lojavirtual.security.JWTUtil;
import com.tcc.lojavirtual.security.UserSecurity;
import com.tcc.lojavirtual.service.UserService;

/*
  Atualiza o Token que está próximo de expirar, 
 é necessário que token atual ainda esteja válido
  * */

@RestController
@RequestMapping(value = "auth")
public class AuthResource {
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		// Liberando o Header Authorization para o Cors (Authentication é um nome de header personalizado e precisa ser liberado)
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}
	/*
	Futuramente implementar o esqueci a senha
	@Autowired
	private AuthService service; 
	 
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDTO) {
		service.sendNewPassword(objDTO.getEmail());
		return ResponseEntity.noContent().build();
	}*/
}
