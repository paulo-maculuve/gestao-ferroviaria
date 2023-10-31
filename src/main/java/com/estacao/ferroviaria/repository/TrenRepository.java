package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Tren;

public interface TrenRepository extends JpaRepository<Tren, Long> {

	Long countById(Long id);

}
