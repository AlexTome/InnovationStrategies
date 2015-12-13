package com.innovation.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.innovation.entity.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	List<Usuario> findAll();
}
