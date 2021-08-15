package com.algaworks.algalog.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable   /// é possivel usar ela como @Embedded em outra classe-- essa classe faz parte da classe entrega 
public class Destinatario {
	
	@NotBlank
	@Column(name= "destinario_nome")
	private String nome;
	
	@NotBlank
	@Column(name= "destinario_logradouro")
	private String logradouro;
	
	@NotBlank
	@Column(name= "destinario_numero")
	private String numero;
	
	@NotBlank
	@Column(name= "destinario_complemento")
	private String complemento;
	
	@NotBlank
	@Column(name= "destinario_bairro")
	private String bairro;
	
	
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	
	

}
