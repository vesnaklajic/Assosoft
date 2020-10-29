package org.sid.assoSoft.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="url_type")
@Data @AllArgsConstructor @NoArgsConstructor
public class UrlType {	
	@Id	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long urlTypeIdid;
	private String label;
	@OneToMany(mappedBy="url_type")
	private Collection<Url> urls;
}