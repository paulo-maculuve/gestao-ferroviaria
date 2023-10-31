package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Long countById(Long id);

}
