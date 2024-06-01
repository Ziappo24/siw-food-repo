package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;

public interface RicettaRepository extends CrudRepository<Ricetta, Long> {
	
	@Query("SELECT r FROM Ricetta r WHERE LOWER(r.nome) = LOWER(?1)")
	public List<Ricetta> findByNome(String nome);

	public boolean existsByNomeAndCuoco(String nome, Cuoco cuoco);
	
	public List<Ricetta> findByCuoco(Cuoco cuoco);
}
