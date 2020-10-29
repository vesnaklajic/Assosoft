package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
/* Annotation Lombock  pour insertion des Getter/Setter et Constructeur */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Personne implements Serializable{
	@Id @GeneratedValue(strategy =GenerationType.IDENTITY )
	private Long persoId ;
	@Column(length=75)
	private String persoNom;
	private String persoPrenom;
	 //@DateTimeFormat(pattern = "dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private Date  persoDateNaiss;
	private String persoLog;
	private String persoPsw;
	private String persoEmail ;
	private String persoTel;
	@OneToMany(mappedBy = "personne")
	
	private Collection<Don> dons;
	@OneToMany(mappedBy="personne")
	
	private Collection <Adherent> adherents;
	@ManyToOne
	private Statut statut;
	@OneToMany(mappedBy="personne")
	
	private Collection<Message> messages;
	@ManyToOne
	private Adresse adresse;
	@OneToMany(mappedBy="personne")
	
	private Collection <Inscrire> inscrires;
	@ManyToOne
	private Type type;

}
