package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
/* Annotation Lombock  pour insertion des Getter/Setter et Constructeur */
@Data @AllArgsConstructor @NoArgsConstructor 
public class Don implements Serializable{
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long donId ;	
	private double donMontant;
	private Date date;
	private boolean donValid;
	private String donDescription;
	@ManyToOne
	private Personne personne;
	@ManyToOne
	private Association association;
	@ManyToOne
	private Statut statut;
	
	
	
}
