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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zup.postBlog.model.Endereco;
import com.zup.postBlog.model.Usuario;
import com.zup.postBlog.repository.EnderecoRepository;

@RestController
@RequestMapping("postBlog/endereco")
@CrossOrigin("*")
public class EnderecoController {
	
	@Autowired EnderecoRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getEnderecoById(@Valid @PathVariable long id){
		Optional<Endereco> endereco = repository.findById(id);
		if(endereco.isPresent()) {
			Usuario usuario = repository.findById(id).get().getUsuario();
			Link selfLink = new Link("http://localhost:8080/postBlog/usuario/busca/" + usuario.getCpf());
			usuario.add(selfLink);
			
			List<Endereco> listaEndereco = usuario.getMeusEnderecos();
			for (Endereco enderecos : listaEndereco) {
				Link linkEndereco = new Link("http://localhost:8080/postBlog/endereco/" + enderecos.getId());
				enderecos.add(linkEndereco);
			}
			return ResponseEntity.status(200).body(repository.findById(id).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereco pesquisado não se encontra em nosso sistema.");
		}
	}

}
