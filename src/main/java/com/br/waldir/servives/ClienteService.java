package com.br.waldir.servives;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.Cliente;
import com.br.waldir.repositories.ClienteRepository;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class ClienteService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional <Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}