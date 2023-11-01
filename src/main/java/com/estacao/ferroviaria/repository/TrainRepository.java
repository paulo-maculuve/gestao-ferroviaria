package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {

	Long countById(Long id);

}
