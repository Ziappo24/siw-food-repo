package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import it.uniroma3.siw.controller.validator.RicettaValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Cuoco;
import it.uniroma3.siw.model.Ingrediente;
import it.uniroma3.siw.model.Ricetta;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.repository.CuocoRepository;
import it.uniroma3.siw.repository.IngredienteRepository;
import it.uniroma3.siw.repository.RicettaRepository;
import it.uniroma3.siw.service.CuocoService;
import it.uniroma3.siw.service.IngredienteService;
import it.uniroma3.siw.service.RicettaService;

import jakarta.validation.Valid;

@Controller
public class RicettaController {

	@Autowired
	RicettaRepository ricettaRepository;

	@Autowired
	RicettaService ricettaService;

	@Autowired
	RicettaValidator ricettaValidator;

	@Autowired
	CuocoService cuocoService;

	@Autowired
	CuocoRepository cuocoRepository;

	@Autowired
	IngredienteRepository ingredienteRepository;

	@Autowired
	IngredienteService ingredienteService;

	@Autowired
	CredentialsRepository credentialsRepository;

//	private static String UPLOAD_DIR = "C:\\Users\\EDOARDO\\Desktop\\FOR SISW\\siw-food-repo\\siw-food-2\\src\\main\\resources\\static\\images";
	private static String UPLOAD_DIR = "C:\\Users\\UTENTE\\Documents\\workspace-spring-tool-suite-4-4.22.0.RELEASE\\siw-food-repo\\siw-food-2\\src\\main\\resources\\static\\images";
	@GetMapping("/ricetta/{id}")
	public String getRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", this.ricettaRepository.findById(id).get());
		return "ricetta.html";
	}

	@GetMapping("/ricette")
	public String ShowRicettaIndex(Model model) {
		model.addAttribute("ricette", this.ricettaService.findAll());
		return "ricette.html";
	}

	@PostMapping("/searchRicette")
	public String searchRicette(Model model, @RequestParam String nome) {
		model.addAttribute("ricette", this.ricettaRepository.findByNome(nome));
		return "ricette.html";
	}

	@PostMapping("admin/searchRicette")
	public String searchRicetteAdmin(Model model, @RequestParam String nome) {
		model.addAttribute("ricette", this.ricettaRepository.findByNome(nome));
		return "/admin/manageRicette.html";
	}

	@PostMapping("cuoco/searchRicette")
	public String searchRicetteCuoco(Model model, @RequestParam String nome) {
		model.addAttribute("ricette", this.ricettaRepository.findByNome(nome));
		return "/cuoco/manageRicette.html";
	}

	@GetMapping("/admin/manageRicette")
	public String ShowRicettaAdmin(Model model) {
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
	public String newRicetta(@Valid @ModelAttribute("ricetta") Ricetta ricetta, BindingResult bindingResult,
			@RequestParam("immagine") MultipartFile file, Model model) {
		this.ricettaValidator.validate(ricetta, bindingResult);

		if (!bindingResult.hasErrors()) {
			if (!file.isEmpty()) {
				try {
					// Salva il file sul server
					String fileName = StringUtils.cleanPath(file.getOriginalFilename());
					Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
					Files.write(path, file.getBytes());
					ricetta.setUrlImage(fileName);

					// Salva la ricetta
					this.ricettaRepository.save(ricetta);

					model.addAttribute("ricetta", ricetta);
					return "ricetta";
				} catch (IOException e) {
					e.printStackTrace();
					model.addAttribute("messaggioErrore", "Errore durante il salvataggio dell'immagine");
					return "formNewRicetta";
				}
			} else {
				model.addAttribute("messaggioErrore", "Il file dell'immagine è vuoto");
				return "formNewRicetta";
			}
		} else {
			return "formNewRicetta";
		}
	}

	@GetMapping(value = "/cuoco/formNewRicetta/{username}")
	public String formNewRicettaCuoco(@PathVariable("username") String username, Model model) {
		Credentials tempUser = credentialsRepository.findByUsername(username);
		User currentUser = tempUser.getUser();
		Cuoco currentCuoco = this.cuocoRepository.findByNomeAndCognome(currentUser.getNome(), currentUser.getCognome());
		Ricetta ricetta = new Ricetta();
		model.addAttribute("cuoco", currentCuoco);
		model.addAttribute("cuocoId", currentCuoco.getId());
		model.addAttribute("ricetta", ricetta);
		model.addAttribute("userDetails", tempUser); // Aggiungi userDetails al modello
		return "cuoco/formNewRicetta.html";
	}

	@PostMapping("/cuoco/ricetta")
	public String newRicettaCuoco(@Valid @ModelAttribute("ricetta") Ricetta ricetta, BindingResult bindingResult,
			@RequestParam("username") String username, @RequestParam("immagine") MultipartFile file, Model model) {
		Credentials tempUser = credentialsRepository.findByUsername(username);
		User currentUser = tempUser.getUser();
		Cuoco currentCuoco = this.cuocoRepository.findByNomeAndCognome(currentUser.getNome(), currentUser.getCognome());
		ricetta.setCuoco(currentCuoco);

		this.ricettaValidator.validate(ricetta, bindingResult);
		if (!bindingResult.hasErrors()) {
			if (!file.isEmpty()) {
				try {
					// Salva il file sul server
					String fileName = StringUtils.cleanPath(file.getOriginalFilename());
					Path path = Paths.get(UPLOAD_DIR + File.separator + fileName);
					Files.write(path, file.getBytes());
					ricetta.setUrlImage(fileName);

					// Salva la ricetta
					this.ricettaRepository.save(ricetta);

					model.addAttribute("ricetta", ricetta);
					return "ricetta";
				} catch (IOException e) {
					e.printStackTrace();
					model.addAttribute("messaggioErrore", "Errore durante il salvataggio dell'immagine");
					return "formNewRicetta";
				}
			} else {
				model.addAttribute("messaggioErrore", "Il file dell'immagine è vuoto");
				return "formNewRicetta";
			}
		} else {
			return "formNewRicetta";
		}
	}

	@GetMapping(value = "/admin/addCuoco/{idRicetta}")
	public String addCuoco(@PathVariable("idRicetta") Long ricettaId, Model model) {
		model.addAttribute("cuochi", cuocoService.findAll());
		model.addAttribute("ricetta", ricettaRepository.findById(ricettaId).get());
		// Ritorna il nome della pagina HTML da visualizzare
		return "/admin/addCuoco.html";
	}

	@GetMapping(value = "/admin/formUpdateRicetta/{id}")
	public String formUpdateRicetta(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ricetta", ricettaRepository.findById(id).get());
		return "admin/formUpdateRicetta.html";
	}

	@GetMapping(value = "/cuoco/formUpdateRicetta/{id}/{username}")
	public String formUpdateRicettaCuoco(@PathVariable("id") Long id, @PathVariable("username") String username,
			Model model, RedirectAttributes redirectAttributes) {
		// Recupera l'utente dal repository
		Credentials tempUser = credentialsRepository.findByUsername(username);
		User currentUser = tempUser.getUser();

		// Recupera la ricetta dal repository
		Ricetta ricetta = ricettaRepository.findById(id).orElse(null);

		// Verifica se il cuoco della ricetta è il cuoco corrente
		if (ricetta == null || ricetta.getCuoco() == null || !ricetta.getCuoco().getNome().equals(currentUser.getNome())
				|| !ricetta.getCuoco().getCognome().equals(currentUser.getCognome())) {
			// Gestisci il caso di accesso non autorizzato
			redirectAttributes.addFlashAttribute("messaggioErrore",
					"Non puoi modificare questa ricetta perché non ti appartiene!");
			return "redirect:/cuoco/manageRicette";
		}

		// Aggiungi la ricetta al modello e restituisci la vista
		model.addAttribute("ricetta", ricetta);
		return "cuoco/formUpdateRicetta.html";
	}

	@GetMapping(value = "/admin/setCuocoToRicetta/{cuocoId}/{ricettaId}")
	public String setCuocoToRicetta(@PathVariable("cuocoId") Long cuocoId, @PathVariable("ricettaId") Long ricettaId,
			Model model) {

		Cuoco cuoco = this.cuocoService.findById(cuocoId);
		Ricetta ricetta = this.ricettaRepository.findById(ricettaId).get();
		ricetta.setCuoco(cuoco);
		this.ricettaRepository.save(ricetta);

		model.addAttribute("ricetta", ricetta);
		return "admin/formUpdateRicetta.html";
	}

	/* per aggiungere o togliere ingredienti a mo di lista */
	@GetMapping("/admin/updateIngredienti/{id}")
	public String updateIngredienti(@PathVariable("id") Long id, Model model) {

		List<Ingrediente> ingredientiToAdd = this.ingredientiToAdd(id);
		model.addAttribute("ingredientiToAdd", ingredientiToAdd);
		model.addAttribute("ricetta", this.ricettaRepository.findById(id).get());

		return "admin/addIngrediente.html";
	}

	@GetMapping("/cuoco/updateIngredienti/{id}")
	public String updateIngredientiCuoco(@PathVariable("id") Long id, Model model) {

		List<Ingrediente> ingredientiToAdd = this.ingredientiToAdd(id);
		model.addAttribute("ingredientiToAdd", ingredientiToAdd);
		model.addAttribute("ricetta", this.ricettaRepository.findById(id).get());

		return "cuoco/addIngrediente.html";
	}

	@GetMapping(value = "/admin/addIngredienteToRicetta/{ingredienteId}/{ricettaId}")
	public String addIngredienteToRicetta(@PathVariable("ingredienteId") Long ingredienteId,
			@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = this.ricettaRepository.findById(ricettaId).get();
		Ingrediente ingrediente = this.ingredienteRepository.findById(ingredienteId).get();
		Set<Ingrediente> ingredienti = ricetta.getIngredientiUtilizzati();
		ingredienti.add(ingrediente);
		this.ricettaRepository.save(ricetta);

		List<Ingrediente> ingredientiToAdd = ingredientiToAdd(ricettaId);

		model.addAttribute("ricetta", ricetta);
		model.addAttribute("ingredientiToAdd", ingredientiToAdd);

		return "admin/addIngrediente.html";
	}

	@GetMapping(value = "/cuoco/addIngredienteToRicetta/{ingredienteId}/{ricettaId}")
	public String addIngredienteToRicettaCuoco(@PathVariable("ingredienteId") Long ingredienteId,
			@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = this.ricettaRepository.findById(ricettaId).get();
		Ingrediente ingrediente = this.ingredienteRepository.findById(ingredienteId).get();
		Set<Ingrediente> ingredienti = ricetta.getIngredientiUtilizzati();
		ingredienti.add(ingrediente);
		this.ricettaRepository.save(ricetta);

		List<Ingrediente> ingredientiToAdd = ingredientiToAdd(ricettaId);

		model.addAttribute("ricetta", ricetta);
		model.addAttribute("ingredientiToAdd", ingredientiToAdd);

		return "cuoco/addIngrediente.html";
	}

	@GetMapping(value = "/admin/removeIngredienteFromRicetta/{ingredienteId}/{ricettaId}")
	public String removeIngredienteFromRicetta(@PathVariable("ingredienteId") Long ingredienteId,
			@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = this.ricettaRepository.findById(ricettaId).get();
		Ingrediente ingrediente = this.ingredienteRepository.findById(ingredienteId).get();
		Set<Ingrediente> ingredientiUtilizzati = ricetta.getIngredientiUtilizzati();
		ingredientiUtilizzati.remove(ingrediente);
		this.ricettaRepository.save(ricetta);

		List<Ingrediente> ingredientiToAdd = ingredientiToAdd(ricettaId);

		model.addAttribute("ricetta", ricetta);
		model.addAttribute("ingredientiToAdd", ingredientiToAdd);

		return "admin/addIngrediente.html";
	}

	@GetMapping(value = "/cuoco/removeIngredienteFromRicetta/{ingredienteId}/{ricettaId}")
	public String removeIngredienteFromRicettaCuoco(@PathVariable("ingredienteId") Long ingredienteId,
			@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = this.ricettaRepository.findById(ricettaId).get();
		Ingrediente ingrediente = this.ingredienteRepository.findById(ingredienteId).get();
		Set<Ingrediente> ingredientiUtilizzati = ricetta.getIngredientiUtilizzati();
		ingredientiUtilizzati.remove(ingrediente);
		this.ricettaRepository.save(ricetta);

		List<Ingrediente> ingredientiToAdd = ingredientiToAdd(ricettaId);

		model.addAttribute("ricetta", ricetta);
		model.addAttribute("ingredientiToAdd", ingredientiToAdd);

		return "cuoco/addIngrediente.html";
	}

	private List<Ingrediente> ingredientiToAdd(Long ricettaId) {
		List<Ingrediente> ingredientiToAdd = new ArrayList<>();

		for (Ingrediente i : ingredienteRepository.findIngredientiNotInRicetta(ricettaId)) {
			ingredientiToAdd.add(i);
		}
		return ingredientiToAdd;
	}

	@GetMapping(value = "/cuoco/updateQuantita/{ingredienteId}/{ricettaId}")
	public String formUpdateQuantita(@PathVariable("ricettaId") Long ricettaId,
			@PathVariable("ingredienteId") Long ingredienteId, Model model) {
		model.addAttribute("ricetta", ricettaRepository.findById(ricettaId).get());
		model.addAttribute("ingrediente", ingredienteRepository.findById(ingredienteId).get());
		return "/cuoco/updateQuantita.html";
	}

	@PostMapping(value = "/cuoco/updateQuantita")
	public String updateQuantita(@RequestParam("ingredienteId") Long ingredienteId,
			@RequestParam("ricettaId") Long ricettaId, @RequestParam("quantitaValore") Integer quantitaValore,
			@RequestParam("quantitaUnita") String quantitaUnita, Model model) {
		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(ingredienteId);
		Optional<Ricetta> ricettaOpt = ricettaRepository.findById(ricettaId);

		if (ingredienteOpt.isPresent() && ricettaOpt.isPresent()) {
			Ingrediente ingrediente = ingredienteOpt.get();
			Ricetta ricetta = ricettaOpt.get();
			Map<Long, Integer> quantitaToRicetta = ingrediente.getQuantitaToRicetta();
			quantitaToRicetta.put(ricetta.getId(), quantitaValore);
			ingrediente.setUnitaDiMisura(quantitaUnita);
			ingrediente.setQuantitaToRicetta(quantitaToRicetta);
			ingredienteRepository.save(ingrediente); // Aggiorna l'ingrediente nel database
			model.addAttribute("ricetta", ricettaRepository.findById(ricettaId).get());
			model.addAttribute("ingrediente", ingredienteRepository.findById(ingredienteId).get());
			model.addAttribute("quantitaValore", quantitaValore);
		}
		return "cuoco/formUpdateRicetta.html";
	}

	@GetMapping(value = "/admin/updateQuantita/{ingredienteId}/{ricettaId}")
	public String formUpdateQuantitaAdmin(@PathVariable("ricettaId") Long ricettaId,
			@PathVariable("ingredienteId") Long ingredienteId, Model model) {
		model.addAttribute("ricetta", ricettaRepository.findById(ricettaId).get());
		model.addAttribute("ingrediente", ingredienteRepository.findById(ingredienteId).get());
		return "/admin/updateQuantita.html";
	}

	@PostMapping(value = "/admin/updateQuantita")
	public String updateQuantitaAdmin(@RequestParam("ingredienteId") Long ingredienteId,
			@RequestParam("ricettaId") Long ricettaId, @RequestParam("quantitaValore") Integer quantitaValore,
			@RequestParam("quantitaUnita") String quantitaUnita, Model model) {
		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(ingredienteId);
		Optional<Ricetta> ricettaOpt = ricettaRepository.findById(ricettaId);

		if (ingredienteOpt.isPresent() && ricettaOpt.isPresent()) {
			Ingrediente ingrediente = ingredienteOpt.get();
			Ricetta ricetta = ricettaOpt.get();
			Map<Long, Integer> quantitaToRicetta = ingrediente.getQuantitaToRicetta();
			quantitaToRicetta.put(ricetta.getId(), quantitaValore);
			ingrediente.setUnitaDiMisura(quantitaUnita);
			ingrediente.setQuantitaToRicetta(quantitaToRicetta);
			ingredienteRepository.save(ingrediente); // Aggiorna l'ingrediente nel database
			model.addAttribute("ricetta", ricettaRepository.findById(ricettaId).get());
			model.addAttribute("ingrediente", ingredienteRepository.findById(ingredienteId).get());
			model.addAttribute("quantitaValore", quantitaValore);
		}
		return "admin/formUpdateRicetta.html";
	}

	public Integer getQuantitaPerRicetta(Long ingredienteId, Long ricettaId) {
		Optional<Ingrediente> ingredienteOpt = ingredienteRepository.findById(ingredienteId);
		Optional<Ricetta> ricettaOpt = ricettaRepository.findById(ricettaId);

		if (ingredienteOpt.isPresent() && ricettaOpt.isPresent()) {
			Ingrediente ingrediente = ingredienteOpt.get();
			Ricetta ricetta = ricettaOpt.get();
			return ingrediente.getQuantitaToRicetta().getOrDefault(ricetta, 0); // Restituisce la quantità, se presente,
																				// altrimenti 0
		} else {
			throw new RuntimeException("Ingrediente o Ricetta non trovati");
		}
	}

	@GetMapping(value = "/admin/deleteRicetta/{ricettaId}")
	public String deleteRicettaAdmin(@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = ricettaService.findById(ricettaId);
		ricetta.setIngredientiUtilizzati(null);
		ricettaService.deleteById(ricetta.getId());
		return "redirect:/admin/manageRicette";
	}

	@GetMapping(value = "/cuoco/deleteRicetta/{ricettaId}")
	public String deleteRicettaCuoco(@PathVariable("ricettaId") Long ricettaId, Model model) {
		Ricetta ricetta = ricettaService.findById(ricettaId);
		ricetta.setIngredientiUtilizzati(null);
		ricettaService.deleteById(ricetta.getId());
		return "redirect:/cuoco/manageRicette";
	}

}
