package org.sid.assoSoft.dao;

import java.util.List;

import org.sid.assoSoft.entities.Personne;
import org.sid.assoSoft.entities.Statut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatutRepository extends JpaRepository<Statut, Long>{

}
