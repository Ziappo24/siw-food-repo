package it.uniroma3.siw.model;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Entity
public class Ingrediente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;


	private String urlImage;

	private String unitaDiMisura;

	@Column(length = 2000)
	private String descrizione;
	
	
	@Transient
	private MultipartFile immagine;
    

	@ElementCollection
	/*Questa annotazione viene utilizzata per indicare che la mappa non è una semplice collezione Java, 
	 * ma deve essere trattata come una collezione di elementi incorporati (embedded) nella stessa entità. 
	 * In altre parole, @ElementCollection dice a JPA che la mappa deve essere mappata come una collezione 
	 * di elementi di valore associati alla classe Ingrediente.*/
	 @CollectionTable(name = "quantita_ricetta", joinColumns = @JoinColumn(name = "ingrediente_id"))
	/*Questa annotazione specifica il nome della tabella che verrà utilizzata per memorizzare la collezione 
	 * di elementi. Nel nostro caso, la tabella si chiama quantita_per_ricetta. L'elemento joinColumns all'interno 
	 * di @CollectionTable specifica la colonna che verrà utilizzata per unire (join) questa tabella con la tabella 
	 * della classe che contiene la collezione, ovvero Ingrediente.*/

	@Column(name = "quantita")
	private Map<Long, Integer> quantitaToRicetta;

	@ManyToMany
    private Set<Ingrediente> ingredientiUtilizzati;
	

	public Set<Ingrediente> getIngredientiUtilizzati() {
		return ingredientiUtilizzati;
	}

	public void setIngredientiUtilizzati(Set<Ingrediente> ingredientiUtilizzati) {
		this.ingredientiUtilizzati = ingredientiUtilizzati;
	}

	public Map<Long, Integer> getQuantitaToRicetta() {
		return quantitaToRicetta;
	}

	public void setQuantitaToRicetta(Map<Long, Integer> quantitaToRicetta) {
		this.quantitaToRicetta = quantitaToRicetta;
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Ingrediente i = (Ingrediente) o;
		return Objects.equals(nome, i.nome);
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
