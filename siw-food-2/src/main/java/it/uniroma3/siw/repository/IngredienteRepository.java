package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{
	
	@Query("SELECT i FROM Ingrediente i WHERE LOWER(i.nome) = LOWER(?1)")
	public List<Ingrediente> findByNome(String nome);
	
	
	public boolean existsByNome(String nome);
	
	@Query(value="select * "
	        + "from ingrediente i "
	        + "where i.id not in "
	        + "(select ri.ingredienti_utilizzati_id "
	        + "from ricetta_ingredienti_utilizzati ri "
	        + "where ri.ricetta_id = :ricettaId)", nativeQuery=true)
	public Iterable<Ingrediente> findIngredientiNotInRicetta(@Param("ricettaId") Long ricettaId);

}
