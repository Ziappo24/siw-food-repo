package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;

@Service
public class RicettaService {
	@Autowired
	private RicettaRepository ricettaRepository;

	public Ricetta findById(Long id) {
		return ricettaRepository.findById(id).get();
	}

	public Iterable<Ricetta> findAll() {
		return ricettaRepository.findAll();
	}

	public Ricetta save(Ricetta ricetta) {
		return ricettaRepository.save(ricetta);
	}

	public Iterable<Ricetta> findByCuoco(Cuoco cuoco) {
		return ricettaRepository.findByCuoco(cuoco);
	}
	
	public void deleteById(Long id) {
        ricettaRepository.deleteById(id);
    }
}
