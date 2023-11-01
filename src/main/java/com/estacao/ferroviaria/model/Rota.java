package com.estacao.ferroviaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
@Entity
public class Rota {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false, length = 100)
    private String estacaoPartida;
	@Column(nullable = false, length = 100)
    private String estacaoDestino;
	@Column(nullable = false)
    private double distancia;
	@Column(nullable = false)
    private int tempoEstimadoViagem;

    @OneToMany(mappedBy = "rota")
    private List<Train> trens;

	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstacaoPartida() {
		return estacaoPartida;
	}

	public void setEstacaoPartida(String estacaoPartida) {
		this.estacaoPartida = estacaoPartida;
	}

	public String getEstacaoDestino() {
		return estacaoDestino;
	}

	public void setEstacaoDestino(String estacaoDestino) {
		this.estacaoDestino = estacaoDestino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public int getTempoEstimadoViagem() {
		return tempoEstimadoViagem;
	}

	public void setTempoEstimadoViagem(int tempoEstimadoViagem) {
		this.tempoEstimadoViagem = tempoEstimadoViagem;
	}

	public List<Train> getTrens() {
		return trens;
	}

	public void setTrens(List<Train> trens) {
		this.trens = trens;
	}
    
    
}
