package com.maquinadebusca.app.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Link implements Serializable {
	static final long serialVersionUID = 1L;
	//Caso o @id for vazio ele faz o insert caso contrário faz o update
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	private String url;
	@Basic
	private LocalDateTime ultimaColeta;
	
	public Link() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public LocalDateTime getUltimaColeta() {
		return ultimaColeta;
	}
	public void setUltimaColeta(LocalDateTime ultimaColeta) {
		this.ultimaColeta = ultimaColeta;
	}
}
