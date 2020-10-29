package org.sid.assoSoft.dao;


import org.sid.assoSoft.entities.Inscrire;
import org.sid.assoSoft.entities.Personne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscrireRepository extends JpaRepository<Inscrire, Long>{
	public Inscrire findByPersonneContains(Personne personne);
	public Page<Inscrire> findByPersonnePersoNomContains(String mc,Pageable pageable)	;
}
