package com.zup.postBlog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.postBlog.model.Endereco;
import com.zup.postBlog.model.Usuario;
import com.zup.postBlog.repository.UsuarioRepository;
import com.zup.postBlog.service.UsuarioService;

@RestController
@RequestMapping("/postBlog/usuario")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private UsuarioService services;

	@Autowired
	UsuarioRepository repository;

	@PostMapping("/novo")
	public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario novoUsuario) {
		
			Optional<Usuario> usuarioCriado = services.cadastrarUsuario(novoUsuario);
		
		if (!usuarioCriado.isEmpty()) {
			Usuario usuario = usuarioCriado.get();
			Link selfLink = new Link("http://localhost:8080/postBlog/usuario/busca/" + usuario.getCpf());
			usuario.add(selfLink);
			return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Usuario já existente no sistema, tente outro Email ou CPF!");
		}
		
	}

	@PostMapping("/{cpf}/novo/endereco")
	public ResponseEntity<?> cadastrarEndereco(@Valid @RequestBody Endereco novoEndereco,
			@PathVariable(value = "cpf") Long cpf) {
		Optional<Endereco> enderecoCriado = services.cadastrarEndereco(novoEndereco, cpf);
		{
			if (!enderecoCriado.isEmpty()) {
				Endereco endereco = enderecoCriado.get();
				Link selfLink = new Link("http://localhost:8080/postBlog/endereco/" + endereco.getId());
				endereco.add(selfLink);
				
				Link linkUsuario = new Link("http://localhost:8080/postBlog/usuario/busca/" + endereco.getUsuario().getCpf());
				endereco.getUsuario().add(linkUsuario);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(endereco);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("O endereço não foi cadastrado, CPF ou Endereco invalidos.");
			}

		}
	}

	@GetMapping("/busca/{cpf}")
	public ResponseEntity<?> getUsuario(@Valid @PathVariable long cpf) {
		Optional<Usuario> usuario = repository.findByCpf(cpf);
		{
			if (usuario.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("CPF pesquisado não se encontra em nosso sistema.");
			} else {
				List<Endereco> listaEndereco = usuario.get().getMeusEnderecos();
				for (Endereco enderecos : listaEndereco) {
					Link selfLink = new Link("http://localhost:8080/postBlog/endereco/" + enderecos.getId());
					enderecos.add(selfLink);
				}
				
				Link selfLink = new Link("http://localhost:8080/postBlog/usuario/busca" + usuario.get().getCpf());
				usuario.get().add(selfLink);
				return ResponseEntity.status(200).body(repository.findByCpf(cpf));
			}
		}
	}

}
