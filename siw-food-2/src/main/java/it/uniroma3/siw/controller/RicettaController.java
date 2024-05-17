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
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.RicettaRepository;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.RicettaService;
import jakarta.validation.Valid;

@Controller
public class RicettaController {
	
	@Autowired RicettaRepository ricettaRepository;
	
	@Autowired RicettaService ricettaService;
	
	@Autowired RicettaValidator ricettaValidator;
	
	@Autowired CuocoService cuocoService;

	
	
	@GetMapping("/ricetta/{id}")
	public String getRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", this.ricettaRepository.findById(id).get());
		return "ricetta.html";
	}

	@GetMapping("/admin/manageRicette")
	public String ShowRicetta(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "/admin/manageRicette.html";
	}
	
	@GetMapping("/cuoco/manageRicette")
	public String ShowRicettaCuoco(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "/cuoco/manageRicette.html";
	}
	
	@GetMapping(value = "/admin/formNewRicetta")
	public String formNewRicetta(Model model) {
		model.addAttribute("ricetta", new Ricetta());
		return "/admin/formNewRicetta.html";
	}
	
	@PostMapping("/admin/ricetta")
	public String newRicetta(@Valid @ModelAttribute("ricetta") Ricetta ricetta, BindingResult bindingResult, Model model) {
		this.ricettaValidator.validate(ricetta, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.ricettaService.save(ricetta);
			model.addAttribute("ricetta", ricetta);
			return "ricetta.html";
		} else {
			return "/admin/formNewRicetta.html";
		}
	}
	
	@GetMapping(value="/cuoco/formNewRicetta")
	public String formNewRicettaCuoco(Model model) {
		Ricetta ricetta = new Ricetta();
	    model.addAttribute("ricetta", ricetta);
		return "cuoco/formNewRicetta.html";
	}
	
	@PostMapping("/cuoco/ricetta")
	public String newRicettaCuoco(@Valid @ModelAttribute("ricetta") Ricetta ricetta, BindingResult bindingResult, Model model) {
		this.ricettaValidator.validate(ricetta, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.ricettaRepository.save(ricetta); 
			model.addAttribute("ricetta", ricetta);
			return "ricetta.html";
		} else {
			return "cuoco/formNewRicetta.html"; 
		}
	}
	
	@GetMapping(value = "/admin/addCuoco/{idRicetta}")
	public String addArtist(@PathVariable("idRicetta") Long ricettaId, Model model) {
		model.addAttribute("cuochi", cuocoService.findAll());
		model.addAttribute("ricetta", ricettaService.findById(ricettaId));
		// Ritorna il nome della pagina HTML da visualizzare
		return "/admin/cuocoToAdd.html";
	}

	@GetMapping(value = "/admin/formUpdateRicetta/{id}")
	public String formUpdateRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", ricettaService.findById(id));
		return "admin/formUpdateRicetta.html";
	}
	
	@GetMapping(value = "/admin/setCuocoToRicetta/{cuocoId}/{ricettaId}")
	public String setDirectorToMovie(@PathVariable("cuocoId") Long cuocoId, @PathVariable("ricettaId") Long ricettaId, Model model) {

		Cuoco cuoco = this.cuocoService.findById(cuocoId);
		Ricetta ricetta = this.ricettaService.findById(ricettaId);
		ricetta.setCuoco(cuoco);
		this.ricettaService.save(ricetta);

		model.addAttribute("ricetta", ricetta);
		return "admin/formUpdateRicetta.html";
	}
	
	
	

}
