package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{
	
	public List<Ingrediente> findByNome(String nome);
	
	public List<Ingrediente> findByQuantita(Float quantita);
	
	public boolean existsByNome(String nome);
	
	@Query(value="select * "
	        + "from ingrediente i "
	        + "where i.id not in "
	        + "(select ir.ingredienti_utilizzati_id "
	        + "from ingrediente_ricette ir "
	        + "where ir.ricette_id = :ricettaId)", nativeQuery=true)
	public Iterable<Ingrediente> findIngredientiNotInRicetta(@Param("ricettaId") Long ricettaId);

}
