package com.estacao.ferroviaria.model;

import java.time.LocalTime;
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
    private LocalTime horarioPartida;
	@Column(nullable = false)
    private LocalTime horarioChegada;
	@Column(nullable = false)
    private boolean atraso;

    @OneToOne
    @JoinColumn(name = "trem_id")
    private Train trem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getHorarioPartida() {
		return horarioPartida;
	}

	public void setHorarioPartida(LocalTime horarioPartida) {
		this.horarioPartida = horarioPartida;
	}

	public LocalTime getHorarioChegada() {
		return horarioChegada;
	}

	public void setHorarioChegada(LocalTime horarioChegada) {
		this.horarioChegada = horarioChegada;
	}

	public boolean isAtraso() {
		return atraso;
	}

	public void setAtraso(boolean atraso) {
		this.atraso = atraso;
	}

	public Train getTrem() {
		return trem;
	}

	public void setTrem(Train trem) {
		this.trem = trem;
	}

    
    
}
