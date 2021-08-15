package com.algaworks.algalog.api.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor  // gera um construtor com todas as propriedades instanciadas nessa classe
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService;
	
	
	@GetMapping
	public List<Cliente> listar() {
	//Cliente cliente1 = new Cliente();  formas diferente de instanciar objetos
    //var		cliente1 = new Cliente();
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable long clienteId) {
	return clienteRepository.findById(clienteId)
	//		.map(cliente -> ResponseEntity.ok(cliente)) esse é um metodo
		.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());    // not found é e pra dar a resposta 404 que nao existe o metodo que quis fazer
		
		//Optional<Cliente> cliente= 	clienteRepository.findById(clienteId); esse é um metodo
		//if(cliente.isPresent()) {
				//return ResponseEntity.ok(cliente.get());
			//}
		//	return ResponseEntity.notFound().build();
	}
	
	@PostMapping             //@Valid nao vai deixar entrar na variavel vai anular se algum atributo tiver nulo 
	@ResponseStatus(HttpStatus.CREATED)    // é pra dar o status 201 que criou a requisiçao que foi feita
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) { // vai vincular o parametro do metodo a o corpo da requisiçao ou seja todos os atributos que estiverem na variavel 
	//	return clienteRepository.save(cliente);
		return catalogoClienteService.salvar(cliente);
	
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar (@PathVariable Long clienteId,@Valid @RequestBody Cliente cliente){ // @anotaçao é pra mostrar o caminho da variavel pode ser que nao exista
		if(!clienteRepository.existsById(clienteId)) {
		return ResponseEntity.notFound().build(); // se ele nao existir nao tem como atualizar = not found

		}
		  cliente.setId(clienteId);	   // forçando o cliente a ter um ID se nao ele cria um novo, no lugar de atualizar
		//  cliente = clienteRepository.save(cliente);
		cliente= catalogoClienteService.salvar(cliente);
		  
		  return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId ){  // é void porque nao precisar retornar um corpo na resposta
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build(); 
		}
	    //clienteRepository.deleteById(clienteId);
	   catalogoClienteService.excluir(clienteId);
	    return ResponseEntity.noContent().build();
	}  
	

}
