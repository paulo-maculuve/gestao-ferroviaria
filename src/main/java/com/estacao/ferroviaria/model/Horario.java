package com.estacao.ferroviaria.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Horario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
    private Date horarioPartida;
	@Column(nullable = false)
    private Date horarioChegada;
	@Column(nullable = false)
    private boolean atraso;

    @OneToOne
    @JoinColumn(name = "trem_id")
    private Tren trem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getHorarioPartida() {
		return horarioPartida;
	}

	public void setHorarioPartida(Date horarioPartida) {
		this.horarioPartida = horarioPartida;
	}

	public Date getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(Date horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public boolean isAtraso() {
		return atraso;
	}

	public void setAtraso(boolean atraso) {
		this.atraso = atraso;
	}

	public Tren getTrem() {
		return trem;
	}

	public void setTrem(Tren trem) {
		this.trem = trem;
	}

    
    
}
