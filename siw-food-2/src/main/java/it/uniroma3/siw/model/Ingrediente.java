package it.uniroma3.siw.model;


import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;

	private Integer quantita;

	
	private String urlImage;
	
	private String unitaDiMisura;

	

	@Column(length = 2000)
	private String descrizione;
	
	

	@ManyToMany
    private Set<Ingrediente> ingredientiUtilizzati;
	

	public Set<Ingrediente> getIngredientiUtilizzati() {
		return ingredientiUtilizzati;
	}

	public void setIngredientiUtilizzati(Set<Ingrediente> ingredientiUtilizzati) {
		this.ingredientiUtilizzati = ingredientiUtilizzati;
	}
	
	public String getUnitaDiMisura() {
		return unitaDiMisura;
	}

	public void setUnitaDiMisura(String unitaDiMisura) {
		this.unitaDiMisura = unitaDiMisura;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(nome, quantita);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ingrediente i = (Ingrediente) o;
		return Objects.equals(nome, i.nome) && Objects.equals(quantita, i.quantita);
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

}
