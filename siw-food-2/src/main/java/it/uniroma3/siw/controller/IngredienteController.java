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

import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.repository.IngredienteRepository;
import it.uniroma3.siw.service.IngredienteService;


@Controller
public class IngredienteController {
	
	@Autowired IngredienteRepository ingredienteRepository;
	
	@Autowired IngredienteService ingredienteService;
	
	private static String UPLOAD_DIR = "C:\\Users\\EDOARDO\\Desktop\\FOR SISW\\siw-food-repo\\siw-food-2\\src\\main\\resources\\static\\images";
	
	
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
	
	
	@PostMapping("/searchIngredienti")
	public String searchIngredienti(Model model, @RequestParam String nome) {
		model.addAttribute("ingredienti", this.ingredienteRepository.findByNome(nome));
		return "ingredienti.html";
	}
	

	@PostMapping("admin/searchIngredienti")
	public String searchIngredientiAdmin(Model model, @RequestParam String nome) {
		model.addAttribute("ingredienti", this.ingredienteRepository.findByNome(nome));
		return "/admin/manageIngredienti.html";
	}

	
	@GetMapping("/admin/manageIngredienti")
	public String ShowIngredientiAdmin(Model model) {
		model.addAttribute("ingredienti", this.ingredienteService.findAll());
		return "/admin/manageIngredienti.html";
	}
	
	@GetMapping(value = "/admin/formNewIngrediente")
	public String formNewIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		return "/admin/formNewIngrediente.html";
	}
	
	@PostMapping("/admin/ingrediente")
	public String newIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente, 
	                             @RequestParam("immagine") MultipartFile file, Model model) {
	    if (!ingredienteRepository.existsByNome(ingrediente.getNome())) {
	        if (!file.isEmpty()) {
	            try {
	                // Salva il file sul server
	                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	                Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
	                Files.write(path, file.getBytes());
	                ingrediente.setUrlImage(fileName);

	                // Salva l'ingrediente
	                this.ingredienteService.save(ingrediente);
	                model.addAttribute("ingrediente", ingrediente);
	                return "ingrediente.html";
	            } catch (IOException e) {
	                e.printStackTrace();
	                model.addAttribute("messaggioErrore", "Errore durante il salvataggio dell'immagine");
	                return "formNewIngrediente";
	            }
	        } else {
	            model.addAttribute("messaggioErrore", "Il file dell'immagine è vuoto");
	            return "formNewIngrediente";
	        }
	    } else {
	        model.addAttribute("messaggioErrore", "Questo ingrediente esiste già");
	        return "formNewIngrediente";
	    }
	}

	
	@GetMapping(value="/cuoco/formNewIngrediente")
	public String formNewIngredienteCuoco(Model model) {
	    model.addAttribute("ingrediente", new Ingrediente());
		return "cuoco/formNewIngrediente.html";
	}
	
	@PostMapping("/cuoco/ingrediente")
	public String newIngredienteCuoco(@ModelAttribute("ingrediente") Ingrediente ingrediente, 
	                                  @RequestParam("immagine") MultipartFile file, Model model) {
	    if (!ingredienteRepository.existsByNome(ingrediente.getNome())) {
	        if (!file.isEmpty()) {
	            try {
	                // Salva il file sul server
	                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	                Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
	                Files.write(path, file.getBytes());
	                ingrediente.setUrlImage(fileName);

	                // Salva l'ingrediente
	                this.ingredienteService.save(ingrediente);
	                model.addAttribute("ingrediente", ingrediente);
	                return "ingrediente.html";
	            } catch (IOException e) {
	                e.printStackTrace();
	                model.addAttribute("messaggioErrore", "Errore durante il salvataggio dell'immagine");
	                return "cuoco/formNewIngrediente";
	            }
	        } else {
	            model.addAttribute("messaggioErrore", "Il file dell'immagine è vuoto");
	            return "cuoco/formNewIngrediente";
	        }
	    } else {
	        model.addAttribute("messaggioErrore", "Questo ingrediente esiste già");
	        return "cuoco/formNewIngrediente";
	    }
	}

	
	@GetMapping(value = "/admin/deleteIngrediente/{ingredienteId}")
	public String deleteIngredienteAdmin(@PathVariable("ingredienteId") Long ingredienteId, Model model) {
		ingredienteService.deleteById(ingredienteId);
        return "redirect:/admin/manageIngredienti";
	}
}
