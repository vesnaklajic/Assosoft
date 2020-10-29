package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.util.Collection;

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
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Adresse implements Serializable{
	public Adresse(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long adresseId;
	private String  rue;
	private String cp;
	private String ville;
	@OneToMany(mappedBy="adresse")
	public Collection<Association> associations;
	@OneToMany(mappedBy="adresse")
	public Collection<Personne> personnes;
	@OneToMany(mappedBy="adresse")
	private Collection <Situer> situers;
	
	
}
