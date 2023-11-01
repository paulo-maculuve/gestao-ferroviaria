package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.RotaNotFundException;
import com.estacao.ferroviaria.model.Reserva;
import com.estacao.ferroviaria.model.Rota;
import com.estacao.ferroviaria.repository.RotaRepository;

@Service
public class RotaService {

	@Autowired
	private RotaRepository rotaRepository;
	
	public void save(Rota rota) {
		rotaRepository.save(rota);
	}

	public List<Rota> listRota() {
		return rotaRepository.findAll();
	}

	public Rota getRota(Long id) throws RotaNotFundException {
		Optional<Rota> rota = rotaRepository.findById(id);
		if (rota.isPresent()) {
			return rota.get();
		}
		throw new RotaNotFundException("Could not find any rota with ID" + id);
	}

	public void deleteRota(Long id) throws RotaNotFundException {
		Long idRota = rotaRepository.countById(id);
		if (idRota == null || idRota == 0) {
			throw new RotaNotFundException("Could not find any rota with ID" + id);
		}
		rotaRepository.deleteById(id);
	}
}
