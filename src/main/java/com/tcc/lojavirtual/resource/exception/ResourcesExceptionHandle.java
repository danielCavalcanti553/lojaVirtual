package com.tcc.lojavirtual.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.tcc.lojavirtual.service.exception.DataIntegrityException;
import com.tcc.lojavirtual.service.exception.NotFoundObjectException;

@Controller
@ControllerAdvice
public class ResourcesExceptionHandle {

	@ExceptionHandler(NotFoundObjectException.class)
	public ResponseEntity<StandardError> notFoundObject(NotFoundObjectException e, HttpServletRequest request){
		StandardError erro = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request){
		ErrorValidation erro = new ErrorValidation(HttpStatus.BAD_REQUEST.value(), "Erro de Validação", System.currentTimeMillis());
		
		for(FieldError f : e.getBindingResult().getFieldErrors()) {
			erro.setError(f.getField(), f.getDefaultMessage());
		}
		
		return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> argumentNotValid(HttpMessageNotReadableException e, HttpServletRequest request){
		
		JsonMappingException jme = (JsonMappingException) e.getCause();
	    String msgErro =  jme.getPath().get(0).getFieldName() + " Inválida";
		
		StandardError erro = new StandardError(HttpStatus.BAD_REQUEST.value(), msgErro + e.getCause(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
		

	}
	
	//
	
}
