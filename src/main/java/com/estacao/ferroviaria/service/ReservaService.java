package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.ReservaNotFundException;
import com.estacao.ferroviaria.model.Reserva;
import com.estacao.ferroviaria.repository.ReservaRepository;

@Service
public class ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	public void save(Reserva reserva) {
		reservaRepository.save(reserva);
	}

	public List<Reserva> listReserva() {
		return reservaRepository.findAll();
	}

	public Reserva getReserva(Long id) throws ReservaNotFundException {
		Optional<Reserva> reserva = reservaRepository.findById(id);
		if (reserva.isPresent()) {
			return reserva.get();
		}
		throw new ReservaNotFundException("Could not find any reserva with ID" + id);
	}

	public void deleteReserva(Long id) throws ReservaNotFundException {
		Long idReserva = reservaRepository.countById(id);
		if (idReserva == null || idReserva == 0) {
			throw new ReservaNotFundException("Could not find any reserva with ID" + id);
		}
		reservaRepository.deleteById(id);
	}
}
