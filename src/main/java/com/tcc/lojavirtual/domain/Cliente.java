package com.tcc.lojavirtual.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

@Entity
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigoCliente;
	
	@NotEmpty(message="CPF obrigatório")
	@CPF
	private String cpf;
	
	@NotEmpty(message="Nome obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")	
	private String nome;
	
	@NotEmpty(message="Endereço obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String endereco;
	
	@NotEmpty(message="Município obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String municipio;
	
	@NotEmpty(message="Estado obrigatório")
	@Length(min=2,max=50,message = "Estado inválido!")
	private String estado;
	
	@NotEmpty(message="Telefone obrigatório")
	private String telefone;
	
	@NotEmpty(message="E-mail obrigatório")
	@Email
	private String email;
	
	@NotEmpty(message="Senha obrigatório")
	@Length(min=5,max=120,message = "O tamanho deve ser entre 5 e 80 caracteres!")
	private String senha;
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	
	
	public Cliente(Integer codigoCliente, String cpf, String nome, String endereco, String municipio, String estado,
			String telefone, String email, String senha) {
		super();
		this.codigoCliente = codigoCliente;
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
		this.municipio = municipio;
		this.estado = estado;
		this.telefone = telefone;
		this.email = email;
		this.senha = senha;
	}

	public Cliente() {
	}

	public Integer getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Integer codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		return true;
	}
	
	
	
	
	
}
