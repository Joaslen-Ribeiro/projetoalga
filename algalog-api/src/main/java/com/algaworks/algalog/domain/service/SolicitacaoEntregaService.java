package com.algaworks.algalog.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;

import com.algaworks.algalog.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {


	
	@Autowired
	private CatalogoClienteService catalogoClienteService;

	private EntregaRepository entregaRepository;
	

	
	@Transactional  //
	public Entrega solicitar( Entrega entrega) {
	Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());	
			
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDETE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		
		return entregaRepository.save(entrega);
	}
	
	
	
	
	
	
	
	
	
	
	
	public SolicitacaoEntregaService(EntregaRepository entregaRepository) {
		super();
		this.entregaRepository = entregaRepository;
	}
	
	
	
	
	
	
	
}
