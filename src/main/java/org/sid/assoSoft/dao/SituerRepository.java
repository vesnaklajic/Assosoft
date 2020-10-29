package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Adresse;
import org.sid.assoSoft.entities.Exercice;
import org.sid.assoSoft.entities.Situer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituerRepository extends JpaRepository<Situer, Long>{
public Adresse findByExerciceContains(Exercice exercice);


}
