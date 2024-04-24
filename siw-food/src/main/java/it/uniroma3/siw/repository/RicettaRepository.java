package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Ricetta;

public interface RicettaRepository extends CrudRepository<Ricetta, Long> {
	
	public List<Ricetta> findByNome(String nome);

	public boolean existsByNomeAndCuoco(String nome, String nomeCuoco);
	
	public List<Ricetta> findByCuoco(String nomeCuoco);
}
