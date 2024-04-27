package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.repository.RicettaRepository;
import it.uniroma3.siw.service.RicettaService;

@Controller
public class RicettaController {
	
	@Autowired RicettaRepository ricettaRepository;
	
	@Autowired RicettaService ricettaService;
	
	
	@GetMapping("/ricetta/{id}")
	public String getRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", this.ricettaRepository.findById(id).get());
		return "ricetta.html";
	}

	@GetMapping("/ricette")
	public String Showricetta(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "ricette.html";
	}
	
}
