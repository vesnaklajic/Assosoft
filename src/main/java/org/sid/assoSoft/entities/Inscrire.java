package org.sid.assoSoft.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Inscrire {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
private Long Inscrireid; 
@ManyToOne
private Personne personne;  
@ManyToOne
private Exercice exercice; 
}
