package com.zup.postBlog.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.zup.postBlog.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByCpf(long cpf);
	
	Optional<Usuario> findByEmail(String email);

}
