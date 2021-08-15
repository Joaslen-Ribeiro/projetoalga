package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@Service      // regras de negocio, representa um serviço
public class CatalogoClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		
		return clienteRepository.findById(clienteId)
		.orElseThrow(() -> new NegocioException("cliente não encontrado!"));
		
	}
	
	
	@Transactional      // se algo der errado na transaçao, todas as operaçoes sao descartadas
	public Cliente salvar(Cliente cliente) {
	 boolean emailEmUso =	clienteRepository.findByEmail(cliente.getEmail())
			 .stream()
			 .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if(emailEmUso) {
			throw new NegocioException("Ja existe um cliente com este email!");
		}
		return clienteRepository.save(cliente);
	}
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
