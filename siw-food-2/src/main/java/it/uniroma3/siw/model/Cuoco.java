package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cuoco {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		private String nome;
		
		private String cognome;
		
		private String urlImage;
		
		private LocalDate nascita;
		
		@OneToMany(mappedBy = "cuoco")
		private List<Ricetta> ricette;
		
		public Cuoco() {
			this.ricette = new LinkedList<>();
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
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


		public List<Ricetta> getRicette() {
			return ricette;
		}

		public void setRicette(List<Ricetta> ricette) {
			this.ricette = ricette;
		}
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		public LocalDate getNascita() {
			return nascita;
		}

		public void setNascita(LocalDate nascita) {
			this.nascita = nascita;
		}
		
		@Override
		public int hashCode() {
		    return Objects.hash(nome, cognome);
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Cuoco cuoco = (Cuoco) o;
			return Objects.equals(nome, cuoco.nome) && Objects.equals(cognome, cuoco.cognome);
		}



		
		
}
