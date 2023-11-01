package com.estacao.ferroviaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
public class Train {
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String numeroIdentificacao;
	@Column(nullable = false)
	private int capacidadePassageiros;
	@OneToMany(mappedBy = "trem")
	private List<Horario> horarios;
	@ManyToOne
	@JoinColumn(name = "rota_id")
	private Rota rota;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumeroIdentificacao() {
		return numeroIdentificacao;
	}
	public void setNumeroIdentificacao(String numeroIdentificacao) {
		this.numeroIdentificacao = numeroIdentificacao;
	}
	public int getCapacidadePassageiros() {
		return capacidadePassageiros;
	}
	public void setCapacidadePassageiros(int capacidadePassageiros) {
		this.capacidadePassageiros = capacidadePassageiros;
	}
	public List<Horario> getHorarios() {
		return horarios;
	}
	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	public Rota getRota() {
		return rota;
	}
	public void setRota(Rota rota) {
		this.rota = rota;
	}
	
	
	

}
