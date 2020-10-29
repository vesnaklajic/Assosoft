package org.sid.assoSoft.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Groupe {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long groupeId; 
	private String nom;
	
	@OneToMany (mappedBy="groupe")
	private Collection<Exercice> exercices;
	}
