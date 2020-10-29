package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Association implements Serializable{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long assoId;
	private String assoNom;
	private String rna;
	private String assoTel;
	private String assoEmail;
	@ManyToOne
	private Categorie categorie;
	@OneToMany(mappedBy="association")
	private Collection<Url> urls;
	@OneToMany(mappedBy="association")
	
	private Collection<Exercice> exercices;
	@ManyToOne
	private Adresse adresse;
	@OneToMany(mappedBy="association")
	
	private Collection<Message> messages;
	@ManyToOne
	private Statut statut;
	@OneToMany(mappedBy="association")
	
	private Collection<Adherent> adherents;
	@OneToMany(mappedBy="association")
	
	private Collection<Don> dons;	
}
