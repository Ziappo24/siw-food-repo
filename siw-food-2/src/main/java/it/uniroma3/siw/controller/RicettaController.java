package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.RicettaValidator;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;
import it.uniroma3.siw.service.RicettaService;
import jakarta.validation.Valid;

@Controller
public class RicettaController {
	
	@Autowired RicettaRepository ricettaRepository;
	
	@Autowired RicettaService ricettaService;
	@Autowired RicettaValidator ricettaValidator;
	
	
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
	
	@GetMapping(value = "/admin/formNewRicetta")
	public String formNewRicetta(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		return "/admin/formNewRicetta.html";
	}
	
	@PostMapping("/ricette")
	public String newRicetta(@Valid @ModelAttribute("ricetta") Ricetta ricetta, BindingResult bindingResult, Model model) {
		this.ricettaValidator.validate(ricetta, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.ricettaService.save(ricetta);
			model.addAttribute("ricetta", ricetta);
			return "redirect:ricetta/"+ricetta.getId();
		} else {
			return "/adminformNewRicetta.html";
		}
	}

}
