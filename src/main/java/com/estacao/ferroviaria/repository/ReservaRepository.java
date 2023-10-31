package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long>{

	Long countById(Long id);

}
