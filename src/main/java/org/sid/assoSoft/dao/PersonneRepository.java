package org.sid.assoSoft.dao;

import java.util.Date;
import java.util.List;

import org.sid.assoSoft.entities.Personne;
import org.sid.assoSoft.entities.Statut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonneRepository  extends JpaRepository<Personne, Long>{
	public Page<Personne> findByPersoNomContains(String mc,Pageable pageable);
	public String findByPersoLog(String log);
	public String findByPersoPsw(String mdp);
	public Date findByPersoDateNaiss(Date date);
	public Personne findByStatut(Statut statut);
	//public List<Personne> findByType(Type type);
}
