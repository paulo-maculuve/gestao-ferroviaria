package com.estacao.ferroviaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class Estacao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 100)
	private String nome;
	@Column(nullable = false, length = 100)
	private String localizacaoGeografica;

	@OneToMany(mappedBy = "estacaoPartida")
	private List<Rota> rotasPartida;

	@OneToMany(mappedBy = "estacaoDestino")
	private List<Rota> rotasDestino;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocalizacaoGeografica() {
		return localizacaoGeografica;
	}

	public void setLocalizacaoGeografica(String localizacaoGeografica) {
		this.localizacaoGeografica = localizacaoGeografica;
	}

	public List<Rota> getRotasPartida() {
		return rotasPartida;
	}

	public void setRotasPartida(List<Rota> rotasPartida) {
		this.rotasPartida = rotasPartida;
	}

	public List<Rota> getRotasDestino() {
		return rotasDestino;
	}

	public void setRotasDestino(List<Rota> rotasDestino) {
		this.rotasDestino = rotasDestino;
	}

	
	
}
