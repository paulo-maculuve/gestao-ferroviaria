package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.PassageiroNotFundException;
import com.estacao.ferroviaria.model.Passageiro;
import com.estacao.ferroviaria.repository.PassageiroRepository;

@Service
public class PassageiroService {
	@Autowired
	private PassageiroRepository passageiroRepository;
	
	public void save(Passageiro passageiro) {
		passageiroRepository.save(passageiro);
	}

	public List<Passageiro> listTrem() {
		return passageiroRepository.findAll();
	}

	public Passageiro getCampaign(Long id) throws PassageiroNotFundException {
		Optional<Passageiro> pass = passageiroRepository.findById(id);
		if (pass.isPresent()) {
			return pass.get();
		}
		throw new PassageiroNotFundException("Could not find any passageiros with ID" + id);
	}

	public void deleteTrem(Long id) throws PassageiroNotFundException {
		Long idPass = passageiroRepository.countById(id);
		if (idPass == null || idPass == 0) {
			throw new PassageiroNotFundException("Could not find any passageiros with ID" + id);
		}
		passageiroRepository.deleteById(id);
	}

}
