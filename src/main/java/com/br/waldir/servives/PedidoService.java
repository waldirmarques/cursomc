package com.br.waldir.servives;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.waldir.domain.Pedido;
import com.br.waldir.repositories.PedidoRepository;
import com.br.waldir.servives.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class PedidoService {
	
	@Autowired //Essa anotação faz com que o objeto seja estanciado assim como está.
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional <Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

}