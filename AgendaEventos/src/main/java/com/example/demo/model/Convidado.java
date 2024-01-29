package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"rg", "evento_id"}))
public class Convidado implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String rg;
	private String nomeConvidado;
	
	public Convidado() {
	    // Construtor vazio
	}

	
	@ManyToOne
	private Evento evento;

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getNomeConvidado() {
		return nomeConvidado;
	}

	public void setNomeConvidado(String nomeConvidado) {
		this.nomeConvidado = nomeConvidado;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

}
