
package com.br.waldir.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.waldir.domain.Pedido;
import com.br.waldir.servives.PedidoService;

@RestController
@RequestMapping(value="/categorias")
public class PedidoResouces {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		
		Pedido obj = service.buscar(id);
		
		return ResponseEntity.ok(obj);
		
	}
}