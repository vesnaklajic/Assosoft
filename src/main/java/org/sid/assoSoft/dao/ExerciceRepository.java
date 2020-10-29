package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Exercice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
	public Page<Exercice> findByNomContains(String mc,Pageable pageable);
	
}
