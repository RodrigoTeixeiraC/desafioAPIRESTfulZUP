package com.zup.postBlog.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "endereco")
public class Endereco extends EntityModel<Endereco> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank (message = "O campo logradouro não pode estar vazio!")
	private String logradouro;
	
	@NotNull (message = "O campo numero não pode estar vazio!")
	private long numero;
	
	
	private String complemento;
	
	@NotBlank (message = "O campo bairro não pode estar vazio!")
	private String bairro;
	
	@NotBlank (message = "O campo cidade não pode estar vazio!")
	private String cidade;
	
	@NotBlank (message = "O campo cidade não pode estar vazio!")
	private String estado;
	
	@NotNull (message = "O campo CEP não pode estar vazio!")
	private long cep;
	
	@ManyToOne
	@JsonIgnoreProperties("endereco")
	private Usuario usuario;

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Endereco setGetId(long id) {
		this.id = id;
		Endereco endereco = new Endereco();
		return endereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public long getCep() {
		return cep;
	}

	public void setCep(long cep) {
		this.cep = cep;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
