package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.EstacaoNotFundException;
import com.estacao.ferroviaria.exception.TrainNotFundException;
import com.estacao.ferroviaria.model.Estacao;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.repository.EstacaoRepository;

@Service
public class EstacaoService {
	@Autowired
	private EstacaoRepository estacaoRepository;
	
	public void save(Estacao estacao) {
		estacaoRepository.save(estacao);
	}

	public List<Estacao> listEstacao() {
		return estacaoRepository.findAll();
	}

	public Estacao getEstacao(Long id) throws EstacaoNotFundException {
		Optional<Estacao> estacao = estacaoRepository.findById(id);
		if (estacao.isPresent()) {
			return estacao.get();
		}
		throw new EstacaoNotFundException("Could not find any estacao with ID" + id);
	}

	public void deleteEstacao(Long id) throws EstacaoNotFundException {
		Long idEstacao = estacaoRepository.countById(id);
		if (idEstacao == null || idEstacao == 0) {
			throw new EstacaoNotFundException("Could not find any estacao with ID" + id);
		}
		estacaoRepository.deleteById(id);
	}

}
