package org.sid.assoSoft.entities;

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
public class Situer {
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long situerId;
	@ManyToOne
	private Exercice exercice;
	@ManyToOne
	private Adresse adresse;
}
