package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cuoco;

public interface CuocoRepository extends CrudRepository<Cuoco, Long>{

		public List<Cuoco> findByNome(String nome);
		
		public boolean existByNomeAndCognome(String nome, String cognome);
}
