package it.uniroma3.siw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.IngredienteRepository;
import it.uniroma3.siw.repository.RicettaRepository;
import jakarta.transaction.Transactional;

@Service
public class IngredienteService {

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Autowired
	RicettaRepository ricettaRepository;

	public Ingrediente findById(Long id) {
		return ingredienteRepository.findById(id).get();
	}

	public Iterable<Ingrediente> findAll() {
		return ingredienteRepository.findAll();
	}

	public Ingrediente save(Ingrediente ingrediente) {
		return ingredienteRepository.save(ingrediente);
	}

	public Iterable<Ingrediente> findByName(String nome) {
		return ingredienteRepository.findByNome(nome);
	}

	public void deleteById(Long id) {
		ingredienteRepository.deleteById(id);
	}

//	@Transactional
//	public Ingrediente aggiungiQuantitaPerRicetta(Long ingredienteId, Long ricettaId, int quantita,
//			String quantitaUnita) {
//		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(ingredienteId);
//		Optional<Ricetta> ricettaOpt = ricettaRepository.findById(ricettaId);
//
//		if (ingredienteOpt.isPresent() && ricettaOpt.isPresent()) {
//			Ingrediente ingrediente = ingredienteOpt.get();
//			Ricetta ricetta = ricettaOpt.get();
//			ingrediente.getQuantitaToRicetta().put(ricetta, quantita);
//			ingrediente.setUnitaDiMisura(quantitaUnita);
//			ingredienteRepository.save(ingrediente); // Aggiorna l'ingrediente nel database
//			return ingrediente; // Restituisce l'ingrediente aggiornato
//		} else {
//			throw new RuntimeException("Ingrediente o Ricetta non trovati");
//		}
//	}
//
//	@Transactional
//	public Integer getQuantitaPerRicetta(Long ingredienteId, Long ricettaId) {
//		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(ingredienteId);
//		Optional<Ricetta> ricettaOpt = ricettaRepository.findById(ricettaId);
//
//		if (ingredienteOpt.isPresent() && ricettaOpt.isPresent()) {
//			Ingrediente ingrediente = ingredienteOpt.get();
//			Ricetta ricetta = ricettaOpt.get();
//			return ingrediente.getQuantitaToRicetta().getOrDefault(ricetta, 0); // Restituisce la quantità, se presente,
//																				// altrimenti 0
//		} else {
//			throw new RuntimeException("Ingrediente o Ricetta non trovati");
//		}
//	}

}
