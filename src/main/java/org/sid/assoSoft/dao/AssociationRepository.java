package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Association;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociationRepository extends JpaRepository<Association, Long>{
public Page<Association> findByAssoNomContains(String mc,Pageable pageable);

}
