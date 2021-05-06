package com.zup.postBlog.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.EntityModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario extends EntityModel<Usuario> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 3, max = 100)
	@NotBlank (message = "O campo nome não pode ser vazio!")
	private String nome;
	
	@Column(unique=false)
	@NotBlank (message = "O campo E-mail não pode ser vazio!")
	private String email;
	
	@Column(unique=true)
	@NotNull (message = "O campo CPF não pode ser vazio!")
	private long cpf;
	
	@NotNull (message = "O campo Data de nascimento de  não pode ser vazio!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	@OneToMany (mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("usuario")
	private List<Endereco> meusEnderecos = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCpf() {
		return cpf;
	}

	public void setCpf(long cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Endereco> getMeusEnderecos() {
		return meusEnderecos;
	}

	public void setMeusEnderecos(List<Endereco> meusEnderecos) {
		this.meusEnderecos = meusEnderecos;
	}
	
	
	
}
