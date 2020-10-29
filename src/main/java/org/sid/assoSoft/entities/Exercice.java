package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor  @ToString
public class Exercice implements Serializable{
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long exerciceId;
	private String nom;
	private String description;
	
	private Date dateDebut;
	
	private Date dateFin;
	//changement de type string au lieu de date
	private String horaire;
	private String genre;
	private Integer quantite;
	
	@ManyToOne 
	private Groupe groupe;
	
	@OneToMany(mappedBy="exercice")
	private Collection <Situer> situers;
	
	@ManyToOne
	private Association association;
	
	@OneToMany(mappedBy="exercice")
	private Collection <Inscrire> inscrits;
	
	@ManyToOne
	private Statut statut;
	
	
}
