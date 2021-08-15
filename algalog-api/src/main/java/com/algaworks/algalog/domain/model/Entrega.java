package com.algaworks.algalog.domain.model;

import java.math.BigDecimal;

import java.time.OffsetDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;

import com.algaworks.algalog.domain.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from= Default.class, to = ValidationGroups.ClienteId.class)       //converte qual validacao ira usar 
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@Valid
	@NotNull
	@Embedded    //abstrair os dados de uma outra classe
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	@JsonProperty(access= Access.READ_ONLY)
	@Enumerated(EnumType.STRING)  // vai ficar na coluna o texto da situaçao da entrega ex: finaliz...   cancelada... pendente... 
	private StatusEntrega status;
	
	@JsonProperty(access= Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	
	@JsonProperty(access= Access.READ_ONLY)    // só leitura // pra evitar que o consumidor coloque uma data por conta propria
	private OffsetDateTime finalizacao;
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Destinatario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Destinatario destinatario) {
		this.destinatario = destinatario;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}

	public StatusEntrega getStatus() {
		return status;
	}

	public void setStatus(StatusEntrega status) {
		this.status = status;
	}

	public OffsetDateTime getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(OffsetDateTime dataPedido) {
		this.dataPedido = dataPedido;
	}

	public OffsetDateTime getFinalizacao() {
		return finalizacao;
	}

	public void setFinalizacao(OffsetDateTime finalizacao) {
		this.finalizacao = finalizacao;
	}

	
	
	
	
}
