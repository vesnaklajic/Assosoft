package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
/* Annotation Lombock  pour insertion des Getter/Setter et Constructeur */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Type implements Serializable{
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long typeId ;
	@Column(length=75)
	private String typeLabel;	
	@OneToMany (mappedBy="type")
	private Collection<Personne> personnes; 
}
