package it.uniroma3.siw.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco, Long>{

		public List<Cuoco> findByNome(String nome);
		
		public Optional<Cuoco> findById(Long Id);
		
		public Cuoco findByNomeAndCognome(String nome, String cognome);
		
		public boolean existsByNomeAndCognome(String nome, String cognome);

		
}
