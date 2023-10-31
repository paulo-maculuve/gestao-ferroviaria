package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Passageiro;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long>{

}
