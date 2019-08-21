package com.br.waldir.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.waldir.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResouces {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat01 = new Categoria(1,"Informática");
		Categoria cat02 = new Categoria(2,"Escritório");
		
		List <Categoria> lista = new ArrayList<>();
		lista.add(cat01);
		lista.add(cat02);
		
		return lista;
	}
}
