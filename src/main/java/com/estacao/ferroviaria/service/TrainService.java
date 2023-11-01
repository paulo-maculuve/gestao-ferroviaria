package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.TrainNotFundException;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.repository.TrainRepository;

@Service
public class TrainService {
	@Autowired
	private TrainRepository tremRepository;

	public void save(Train trem) {
		tremRepository.save(trem);
	}

	public List<Train> listTrain() {
		return tremRepository.findAll();
	}

	public Train getTrain(Long id) throws TrainNotFundException {
		Optional<Train> trem = tremRepository.findById(id);
		if (trem.isPresent()) {
			return trem.get();
		}
		throw new TrainNotFundException("Could not find any trem with ID" + id);
	}

	public void deleteTrain(Long id) throws TrainNotFundException {
		Long idTrem = tremRepository.countById(id);
		if (idTrem == null || idTrem == 0) {
			throw new TrainNotFundException("Could not find any trem with ID" + id);
		}
		tremRepository.deleteById(id);
	}

}
