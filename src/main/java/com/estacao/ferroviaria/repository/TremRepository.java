package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Trem;

public interface TremRepository extends JpaRepository<Trem, Long> {

	Long countById(Long id);

}
