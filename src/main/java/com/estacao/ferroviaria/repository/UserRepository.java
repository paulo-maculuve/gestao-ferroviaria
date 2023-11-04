package com.estacao.ferroviaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estacao.ferroviaria.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>{

	Long countById(Long id);

}
