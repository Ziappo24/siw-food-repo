package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.IngredienteRepository;
import it.uniroma3.siw.service.IngredienteService;


@Controller
public class IngredienteController {
	
	@Autowired IngredienteRepository ingredienteRepository;
	
	@Autowired IngredienteService ingredienteService;
	
	
	@GetMapping("/ingrediente/{id}")
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente", this.ingredienteRepository.findById(id).get());
		return "ingrediente.html";
	}

	@GetMapping("/ingredienti")
	public String ShowIngredienti(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "ingredienti.html";
	}
	
	@GetMapping("/formNewIngrediente")
	public String formNewIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ricetta());
		return "/admin/formNewIngrediente.html";
	}
	
	@PostMapping("/ingredienti")
	public String newRicetta(@ModelAttribute("ingrediente") Ingrediente ingrediente, Model model) {
		if (!ingredienteRepository.existsByNome(ingrediente.getNome())) {
			this.ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:ingrediente/"+ingrediente.getId();
		} else {
			model.addAttribute("messaggioErrore", "Questo ingrediente esiste già");
			return "/admin/formNewIngrediente.html";
		}
	}
}
