package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Don;
import org.sid.assoSoft.entities.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DonRepository extends JpaRepository<Don,Long> {
	
public Don findByPersonneContains(Personne personne) ;
public Don findByAssociationContains(Association association) ;
public Page<Don> findByPersonnePersoNomContains(String mc,Pageable pageable);
}
