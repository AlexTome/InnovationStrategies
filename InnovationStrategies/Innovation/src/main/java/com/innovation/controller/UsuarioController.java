package com.innovation.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.innovation.entity.Usuario;
import com.innovation.model.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private HazelcastInstance hzInstance;
	
	public static final String HZ_MAP_NAME = "usuariosMap";
	@PostConstruct
	public void init() {
	   List<Usuario> usuarios = usuarioRepository.findAll();
	   IMap<Object, Usuario> usuariosMap= hzInstance.getMap(HZ_MAP_NAME);
	   for(Usuario user : usuarios){
		   usuariosMap.put(user.getName(), user);
	   }
	}
	
	@RequestMapping(value = "/name/{nombreUsuario}", method = RequestMethod.GET)
	public Usuario getUsuariobyName(@PathVariable("nombreUsuario") String nombre){
		Object user = hzInstance.getMap("usuariosMap").get(nombre);
		return user!=null?
				(Usuario)user
				:new Usuario();
	}
	
	@RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET)
	public Usuario getUsuariobyPhone(@PathVariable("phone") String phone){
		if(phone==null){
			return null;
		}
		Usuario usuario=null;
		for(Map.Entry<Object, Object> user : hzInstance.getMap("usuariosMap").entrySet()){
			usuario = user.getValue()!=null? (Usuario)user.getValue() : null;
			if(phone.equals(usuario.getPhone())){ 
				return usuario;
			}
		}
		return new Usuario();
	}
	
	@RequestMapping(value = "/company/{company}", method = RequestMethod.GET)
	public Usuario getUsuariobyCompany(@PathVariable("company") String company){
		if(company==null){
			return null;
		}
		Usuario usuario=null;
		for(Map.Entry<Object, Object> user : hzInstance.getMap("usuariosMap").entrySet()){
			usuario = user.getValue()!=null? (Usuario)user.getValue() : null;
			if(company.equals(usuario.getCompany())){ 
				return usuario;
			}
		}
		return new Usuario();
	}
	
	@RequestMapping(value = "/iban/{iban}", method = RequestMethod.GET)
	public Usuario getUsuariobyIBAN(@PathVariable("iban") String iban){
		if(iban==null){
			return null;
		}
		Usuario usuario=null;
		for(Map.Entry<Object, Object> user : hzInstance.getMap("usuariosMap").entrySet()){
			usuario = user.getValue()!=null? (Usuario)user.getValue() : null;
			if(iban.equals(usuario.getIban())){ 
				return usuario;
			}
		}
		return new Usuario();
	}
}
