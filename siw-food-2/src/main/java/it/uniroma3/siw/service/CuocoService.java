package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.repository.CuocoRepository;

@Service
public class CuocoService {

		@Autowired CuocoRepository cuocoRepository;
		public Cuoco findById(Long id) {
			return cuocoRepository.findById(id).get();
		}
		
		public Iterable<Cuoco> findAll() {
			return cuocoRepository.findAll();
		}
		
		public Cuoco save(Cuoco cuoco) {
			return cuocoRepository.save(cuoco);
		}
		
		
		public Object findByName(String nome) {
			return cuocoRepository.findByNome(nome);
		}
		
		public Object findByNomeAndCognome(String nome, String cognome) {
			return cuocoRepository.findByNome(nome);
		}
		
		public void deleteById(Long id) {
	        cuocoRepository.deleteById(id);
	    }
		
}
