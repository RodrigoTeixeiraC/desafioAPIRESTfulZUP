package com.zup.postBlog.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.postBlog.model.Usuario;
import com.zup.postBlog.model.Endereco;
import com.zup.postBlog.repository.EnderecoRepository;
import com.zup.postBlog.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private @Autowired UsuarioRepository repositoryUsuario;
	private @Autowired EnderecoRepository repositoryEndereco;

	public Optional<Usuario> cadastrarUsuario(Usuario novoUsuario) {
		Optional<Usuario> cpfExistente = repositoryUsuario.findByCpf(novoUsuario.getCpf());
		if (cpfExistente.isPresent()) {
			return Optional.empty();
		}
		Optional<Usuario> emailExistente = repositoryUsuario.findByEmail(novoUsuario.getEmail());
		if (emailExistente.isPresent()) {
			return Optional.empty();
		}
		Optional<Usuario> usuarioCadastrado = Optional.ofNullable(repositoryUsuario.save(novoUsuario));
		if (usuarioCadastrado.isPresent()) {
			return usuarioCadastrado;
		} else {
			return Optional.empty();
		}
	}

	public Optional<Endereco> cadastrarEndereco(Endereco novoEndereco, long cpf) {
		Optional<Usuario> usuarioExistente = repositoryUsuario.findByCpf(cpf);
		if (usuarioExistente.isPresent()) {
			novoEndereco.setUsuario(usuarioExistente.get());
			return Optional.ofNullable(repositoryEndereco.save(novoEndereco));
		} else {
			return Optional.empty();
		}
	}
}
