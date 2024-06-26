package it.uniroma3.siw.model;


import java.util.Objects;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Ricetta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nome;
	
	private String urlImage;
	
	/* mettere la possibilità di scrivere tanto*/
	@Column(length = 2000)
	private String descrizione;
	
	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Cuoco cuoco;
	
	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private Set<Ingrediente> ingredientiUtilizzati;
	
	@Transient
	private MultipartFile immagine;

	public Set<Ingrediente> getIngredientiUtilizzati() {
		return ingredientiUtilizzati;
	}

	public void setIngredientiUtilizzati(Set<Ingrediente> ingredientiUtilizzati) {
		this.ingredientiUtilizzati = ingredientiUtilizzati;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Cuoco getCuoco() {
		return cuoco;
	}

	public void setCuoco(Cuoco cuoco) {
		this.cuoco = cuoco;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(nome, cuoco);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ricetta r = (Ricetta) o;
		return Objects.equals(nome, r.nome) && Objects.equals(cuoco, r.cuoco);
	}

	

}
