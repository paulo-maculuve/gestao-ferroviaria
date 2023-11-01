package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Rota;

public interface RotaRepository extends JpaRepository<Rota, Long> {

	Long countById(Long id);

}
