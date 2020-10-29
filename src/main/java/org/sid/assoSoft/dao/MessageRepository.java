package org.sid.assoSoft.dao;

import org.sid.assoSoft.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{

}
