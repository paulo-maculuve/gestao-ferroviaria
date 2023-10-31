package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.FuncionarioNotFundException;
import com.estacao.ferroviaria.exception.TremNotFundException;
import com.estacao.ferroviaria.model.Funcionario;
import com.estacao.ferroviaria.model.Trem;
import com.estacao.ferroviaria.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	public List<Funcionario> list(){
		return funcionarioRepository.findAll();
	}
	
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	
	public Funcionario getCampaign(Long id) throws FuncionarioNotFundException {
		Optional<Funcionario> func = funcionarioRepository.findById(id);
		if (func.isPresent()) {
			return func.get();
		}
		throw new FuncionarioNotFundException("Could not find any trem with ID" + id);
	}

	public void deleteTrem(Long id) throws FuncionarioNotFundException {
		Long idFunc = funcionarioRepository.countById(id);
		if (idFunc == null || idFunc == 0) {
			throw new FuncionarioNotFundException("Could not find any trem with ID" + id);
		}
		funcionarioRepository.deleteById(id);
	}
	

}
