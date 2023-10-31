package com.estacao.ferroviaria.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date dataReserva;
    
    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;

    @Column(nullable = false)
    @ManyToOne
    @JoinColumn(name = "trem_id")
    private Trem trem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataReserva() {
		return dataReserva;
	}

	public void setDataReserva(Date dataReserva) {
		this.dataReserva = dataReserva;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Trem getTrem() {
		return trem;
	}

	public void setTrem(Trem trem) {
		this.trem = trem;
	}
    
    
}
