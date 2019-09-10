
package com.br.waldir.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.waldir.domain.Cliente;
import com.br.waldir.dto.ClienteDTO;
import com.br.waldir.servives.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResouces {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> listar(@PathVariable Integer id) {
		Cliente obj = service.find(id);
		return ResponseEntity.ok(obj);	
	}
	
	//@RequestMapping(method=RequestMethod.POST) //adiciona uma nova categoria
	//public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDto){// throws ObjectNotFoundException{
	//	Cliente obj = service.fromDTO(objDto);
	//	obj = service.insert(obj);
	//	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
	//			buildAndExpand(obj.getId()).toUri();
	//	return ResponseEntity.created(uri).build();
	//}
	
	@RequestMapping(value="/{id}",method=RequestMethod.PUT) //atualizar uma categoria
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto,@PathVariable Integer id){
		Cliente obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE) //Deleta categoria
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET) //lista todas as categoria
	public ResponseEntity<List<ClienteDTO>> findPage() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET) //lista todas as categoria
	public ResponseEntity<Page<ClienteDTO>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction){
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}
}