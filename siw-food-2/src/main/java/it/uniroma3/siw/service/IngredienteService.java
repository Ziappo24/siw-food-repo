package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;

@Service
public class IngredienteService {
	
	@Autowired IngredienteRepository ingredienteRepository;
	
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
	
	public Iterable<Ingrediente> findByQuantita(Integer quantita) {
		return ingredienteRepository.findByQuantita(quantita);
	}
	
	public void deleteById(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
