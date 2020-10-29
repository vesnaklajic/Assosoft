package org.sid.assoSoft.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Url implements Serializable{
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long urlId;
	private String url;	
	@ManyToOne
	private UrlType url_type;
	@ManyToOne
	private Association association;
}