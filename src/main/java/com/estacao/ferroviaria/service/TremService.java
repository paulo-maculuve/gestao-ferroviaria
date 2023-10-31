package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.TremNotFundException;
import com.estacao.ferroviaria.model.Trem;
import com.estacao.ferroviaria.repository.TremRepository;

@Service
public class TremService {
	@Autowired
	private TremRepository tremRepository;

	public void save(Trem trem) {
		tremRepository.save(trem);
	}

	public List<Trem> listTrem() {
		return tremRepository.findAll();
	}

	public Trem getCampaign(Long id) throws TremNotFundException {
		Optional<Trem> trem = tremRepository.findById(id);
		if (trem.isPresent()) {
			return trem.get();
		}
		throw new TremNotFundException("Could not find any trem with ID" + id);
	}

	public void deleteTrem(Long id) throws TremNotFundException {
		Long idTrem = tremRepository.countById(id);
		if (idTrem == null || idTrem == 0) {
			throw new TremNotFundException("Could not find any trem with ID" + id);
		}
		tremRepository.deleteById(id);
	}

}
