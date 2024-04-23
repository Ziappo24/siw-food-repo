package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.List;

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
		
		private LocalDate dataDiNascita;
		
		@OneToMany(mappedBy = "cuoco")
		private List<Ricetta> ricette;

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

		public LocalDate getDataDiNascita() {
			return dataDiNascita;
		}

		public void setDataDiNascita(LocalDate dataDiNascita) {
			this.dataDiNascita = dataDiNascita;
		}

		public List<Ricetta> getRicette() {
			return ricette;
		}

		public void setRicette(List<Ricetta> ricette) {
			this.ricette = ricette;
		}


		
		
}
