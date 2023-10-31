package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.TremNotFundException;
import com.estacao.ferroviaria.model.Tren;
import com.estacao.ferroviaria.repository.TrenRepository;

@Service
public class TremService {
	@Autowired
	private TrenRepository tremRepository;

	public void save(Tren trem) {
		tremRepository.save(trem);
	}

	public List<Tren> listTrem() {
		return tremRepository.findAll();
	}

	public Tren getCampaign(Long id) throws TremNotFundException {
		Optional<Tren> trem = tremRepository.findById(id);
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
