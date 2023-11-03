package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.HorarioNotFundException;
import com.estacao.ferroviaria.model.Horario;
import com.estacao.ferroviaria.repository.HorarioRepository;

@Service
public class HorarioService {

	@Autowired
	private HorarioRepository horarioRepository;
	
	public void save(Horario horario) {
		horarioRepository.save(horario);
	}

	public List<Horario> listHorario() {
		return horarioRepository.findAll();
	}

	public Horario getHorario(Long id) throws HorarioNotFundException {
		Optional<Horario> horario = horarioRepository.findById(id);
		if (horario.isPresent()) {
			return horario.get();
		}
		throw new HorarioNotFundException("Could not find any hoarario with ID" + id);
	}

	public void deleteHorario(Long id) throws HorarioNotFundException {
		Long idHorario = horarioRepository.countById(id);
		if (idHorario == null || idHorario == 0) {
			throw new HorarioNotFundException("Could not find any hoarario with ID" + id);
		}
		horarioRepository.deleteById(id);
	}
}
