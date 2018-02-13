package com.tcc.lojavirtual.resource.exception;

import java.util.ArrayList;
import java.util.List;



public class ErrorValidation extends StandardError{

	private static final long serialVersionUID = 1L;

	private List<FieldMessage> list = new ArrayList<>();

	public ErrorValidation(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
	}

	public List<FieldMessage> getErrors() {
		return list;
	}

	public void setError(String fieldName,String msg) {
		list.add(new FieldMessage(fieldName,msg));
	}
	
	
	
	
}
