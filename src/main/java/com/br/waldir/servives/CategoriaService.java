package com.br.waldir.servives;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.Categoria;
import com.br.waldir.repositories.CategoriaRepository;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class CategoriaService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional <Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
