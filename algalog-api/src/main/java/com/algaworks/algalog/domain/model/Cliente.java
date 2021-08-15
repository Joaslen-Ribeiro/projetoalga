package com.algaworks.algalog.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.algalog.domain.ValidationGroups;

import lombok.EqualsAndHashCode;



//@Getter // metodo de gerar getters e setters sem precisar deixar ele visivel embaixo dos atributos
//@Setter


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
//@Table(name= "cliente")
public class Cliente {
	
	@NotNull(groups = ValidationGroups.ClienteId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 4 , max =40)
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String telefone;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	

}
