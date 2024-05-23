package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.siw.model.Cuoco;
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
	
	@GetMapping(value="/admin/formNewCuoco")
	public String formNewCuoco(Model model) {
		model.addAttribute("cuoco", new Cuoco());
		return "admin/formNewCuoco.html";
	}
	
	@GetMapping("/admin/manageCuochi")
	public String ShowCuocoAdmin(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "/admin/manageCuochi.html";
	}
	
	@GetMapping(value="/admin/indexCuco")
	public String indexCuoco() {
		return "admin/indexCuoco.html";
	}
	
	@PostMapping("/searchCuochi")
	public String searchCuochi(Model model, @RequestParam String nome) {
		model.addAttribute("cuochi", this.cuocoRepository.findByNome(nome));
		return "cuochi.html";
	}
	
	@PostMapping("admin/searchCuochi")
	public String searchCuochiAdmin(Model model, @RequestParam String nome) {
		model.addAttribute("cuochi", this.cuocoRepository.findByNome(nome));
		return "/admin/manageCuochi.html";
	}
	
	@PostMapping("/admin/cuochi")
	public String newCuoco(@ModelAttribute("cuoco") Cuoco cuoco, Model model) {
		if (!cuocoRepository.existsByNomeAndCognome(cuoco.getNome(), cuoco.getCognome())) {
			this.cuocoService.save(cuoco);
			model.addAttribute("cuoco", cuoco);
			return "cuoco.html";
		} else {
			model.addAttribute("messaggioErrore", "Questo cuoco esiste già");
			return "/admin/formNewCuoco.html";
		}
	}
	
	@GetMapping(value = "/admin/deleteCuoco/{cuocoId}")
	public String deleteCuocoAdmin(@PathVariable("cuocoId") Long cuocoId, Model model) {
		cuocoService.deleteById(cuocoId);
        return "redirect:/admin/manageCuochi";
	}
}
