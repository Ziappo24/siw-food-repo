package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.CuocoRepository;
import it.uniroma3.siw.repository.RicettaRepository;
import it.uniroma3.siw.repository.UserRepository;
import it.uniroma3.siw.service.CuocoService;

@Controller
public class CuocoController {

	private static String UPLOAD_DIR = "C:\\Users\\EDOARDO\\Desktop\\FOR SISW\\siw-food-repo\\siw-food-2\\src\\main\\resources\\static\\images";

	@Autowired
	CuocoRepository cuocoRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CuocoService cuocoService;

	@Autowired
	CredentialsRepository credentialsRepository;

	@Autowired
	RicettaRepository ricettaRepository;

	@GetMapping("/cuoco/{id}")
	public String getCuoco(@PathVariable("id") Long id, Model model) {
		Cuoco cuoco = cuocoService.findById(id);
		model.addAttribute("cuoco", cuoco);
		return "cuoco.html";
	}

	@GetMapping("/cuochi")
	public String ShowCuoco(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "cuochi.html";
	}

	@GetMapping(value = "/admin/formNewCuoco")
	public String formNewCuoco(Model model) {
		model.addAttribute("cuoco", new Cuoco());
		return "admin/formNewCuoco.html";
	}

	@GetMapping("/admin/manageCuochi")
	public String ShowCuocoAdmin(Model model) {
		model.addAttribute("cuochi", this.cuocoService.findAll());
		return "/admin/manageCuochi.html";
	}

	@GetMapping(value = "/admin/indexCuoco")
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
	public String newCuoco(@ModelAttribute("cuoco") Cuoco cuoco, @RequestParam("immagine") MultipartFile file,
			Model model) {
		if (!cuocoRepository.existsByNomeAndCognome(cuoco.getNome(), cuoco.getCognome())) {
			if (!file.isEmpty()) {
				try {
					// Salva il file sul server
					String fileName = StringUtils.cleanPath(file.getOriginalFilename());
					Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
					Files.write(path, file.getBytes());
					cuoco.setUrlImage(fileName);

					// Salva il cuoco
					this.cuocoService.save(cuoco);

					model.addAttribute("cuoco", cuoco);
					return "cuoco";
				} catch (IOException e) {
					e.printStackTrace();
					model.addAttribute("messaggioErrore", "Errore durante il salvataggio dell'immagine");
					return "formNewCuoco";
				}
			} else {
				model.addAttribute("messaggioErrore", "Il file dell'immagine è vuoto");
				return "formNewCuoco";
			}
		} else {
			model.addAttribute("messaggioErrore", "Questo cuoco esiste già");
			return "formNewCuoco";
		}
	}

	@GetMapping(value = "/admin/deleteCuoco/{cuocoId}")
	public String deleteCuocoAdmin(@PathVariable("cuocoId") Long cuocoId, Model model) {
		Cuoco cuoco = cuocoService.findById(cuocoId);
		User userAssociato = userRepository.findByNomeAndCognome(cuoco.getNome(), cuoco.getCognome());

		if (userAssociato != null) {
			Credentials credentialsAssociate = credentialsRepository.findById(userAssociato.getId()).get();
			if (credentialsAssociate.getRole().toString() == "CUOCO") {
				if (cuoco.getRicette() != null) {
					Ricetta ricetta = ricettaRepository.findByCuocoId(cuocoId);
					ricetta.setCuoco(null);
				}
				userRepository.deleteById(userAssociato.getId());
				credentialsRepository.deleteById(credentialsAssociate.getId());
			}
			cuocoRepository.deleteById(cuocoId);
		} else {
			cuocoRepository.deleteById(cuocoId);
		}
		return "redirect:/admin/manageCuochi";
	}

}
