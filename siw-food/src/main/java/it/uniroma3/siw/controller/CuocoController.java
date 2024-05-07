package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.repository.CuocoRepository;
import it.uniroma3.siw.service.CuocoService;

@Controller
public class CuocoController {
	
	@Autowired CuocoRepository cuocoRepository;
	
	@Autowired CuocoService cuocoService;
	
	
	@GetMapping("/cuoco/{id}")
	public String getCuoco(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cuoco", this.cuocoRepository.findById(id).get());
		return "cuoco.html";
	}

	@GetMapping("/cuochi")
	public String ShowCuoco(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuochi.html";
	}
	@GetMapping("/login")
	public String getLogin() {
		return "login.html";
	}
}
