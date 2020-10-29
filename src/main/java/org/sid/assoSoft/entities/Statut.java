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
public class Statut {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long statutId;
	private String statutLabel;
	@OneToMany(mappedBy="statut")
	private Collection<Personne> personnes;
	@OneToMany(mappedBy="statut")
	private Collection<Association> associations; 
	@OneToMany(mappedBy="statut")
	private Collection<Exercice> exercices;
	@OneToMany(mappedBy="statut")
	private Collection<Don> dons;
	
}
