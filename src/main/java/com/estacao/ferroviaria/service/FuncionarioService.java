package com.estacao.ferroviaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estacao.ferroviaria.exception.FuncionarioNotFundException;
import com.estacao.ferroviaria.exception.TrainNotFundException;
import com.estacao.ferroviaria.model.Funcionario;
import com.estacao.ferroviaria.model.Train;
import com.estacao.ferroviaria.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	
	public List<Funcionario> listFunc(){
		return funcionarioRepository.findAll();
	}
	
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	
	public Funcionario getFunc(Long id) throws FuncionarioNotFundException {
		Optional<Funcionario> func = funcionarioRepository.findById(id);
		if (func.isPresent()) {
			return func.get();
		}
		throw new FuncionarioNotFundException("Could not find any trem with ID" + id);
	}

	public void deleteFunc(Long id) throws FuncionarioNotFundException {
		Long idFunc = funcionarioRepository.countById(id);
		if (idFunc == null || idFunc == 0) {
			throw new FuncionarioNotFundException("Could not find any trem with ID" + id);
		}
		funcionarioRepository.deleteById(id);
	}
	

}
