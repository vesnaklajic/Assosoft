package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Adherent;
import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Long>{
	public Adherent findByPersonneContains(Personne personne)	;
	public Adherent findByAssociationContains(Association association)	;
	public Page<Adherent> findByPersonnePersoNom(String mc,Pageable pageable)	;
	public Page<Adherent> findByPersonnePersoNomContains(String mc,Pageable pageable)	;
	
	
}
