package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface TypeRepository extends JpaRepository<Type, Long>{

}
