package org.sid.assoSoft.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.sid.assoSoft.dao.AdherentRepository;
import org.sid.assoSoft.dao.AssociationRepository;
import org.sid.assoSoft.dao.ExerciceRepository;
import org.sid.assoSoft.dao.InscrireRepository;
import org.sid.assoSoft.dao.PersonneRepository;
import org.sid.assoSoft.dao.StatutRepository;
import org.sid.assoSoft.dao.TypeRepository;
import org.sid.assoSoft.entities.Adherent;
import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Exercice;
import org.sid.assoSoft.entities.Inscrire;
import org.sid.assoSoft.entities.Personne;
import org.sid.assoSoft.entities.Statut;
import org.sid.assoSoft.entities.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PersonneController {
	
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private StatutRepository statutRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private AdherentRepository adherentRepository;
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private InscrireRepository inscrireRepository;
	@Autowired
	private ExerciceRepository exerciceRepository;
	
		
	//************** Edition des clients via la table Adherent OK
	 	@GetMapping("/listAdherents")
		public String menbers(Model model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="motCle",defaultValue="") String mc) {
		
		Page <Adherent> adhList = 
					 adherentRepository.findByPersonnePersoNomContains(mc,PageRequest.of(page, 10));

			model.addAttribute("MenberList",adhList);
			model.addAttribute("pages",new int[adhList.getTotalPages()]);
			model.addAttribute("currentPage",page);
			model.addAttribute("motCle",mc);
			model.addAttribute("standardDate", new Date());	
			return "testListAdh";
		}
	//************** Edition d'une personne via la table Adherent
	
	
	@GetMapping("/testeditAdherent")
	public String testeditMenber(Model model,Long id){
		Adherent adherent = adherentRepository.findById(id).get();
		//Adherent adherent =adherentRepository.findByPersonneContains(personne);
		List<Association> associations = associationRepository.findAll();
		List<Statut> sList = statutRepository.findAll();
		//List<Adherent> adherentList = adherentRepository.findAll();
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		model.addAttribute("adherent",adherent);
		model.addAttribute("statutList",sList);
		// Pbl au niveau de la date de naissance renvoyer par la base de donnée AA/MM/JJ au lieu de JJ/MM/AA
		
		return "testeditAdherent";
	}		
	//*********************  Sauvegarde edition OK avec table Situer********************************
	@PostMapping("/testeditClientSsave")
	// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
	// dans
	// Binding result
	// Chaque Objet doit avoir sont @Valid et sont BindingResult
	// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
	// personne
	// ça marche pas il faut mettre @ Valid avant
	public String testupdatePersonne(Model model, @Valid Personne personne, BindingResult bindingResultPersonne, Association association,
			BindingResult bindingResultAssociation,Statut statut,Exercice exercice) {

		// Si il y a des erreurs il me renvoie le formulaire
		if (bindingResultPersonne.hasErrors()||bindingResultAssociation.hasErrors())
			return "formNewAdh";
		

		// je recupère le type (Adhérent)
		Type type = typeRepository.findById((long) 1).get();
		personne.setType(type);
		personne.setStatut(statut);
		personneRepository.save(personne);
model.addAttribute("MenberList", adherentRepository.findAll());

		return "redirect:/listAdherents";
	}
	/// ADHESION RETOUR  SUR  LIST  ADHERENT TABLE ADHERENT OK
		@PostMapping("/adhesionSave")
		// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
		// dans
		// Binding result
		// Chaque Objet doit avoir sont @Valid et sont BindingResult
		// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
		// personne
		// ça marche pas il faut mettre @ Valid avant
		public String savePersonne(Model model, @Valid Personne personne, BindingResult bindingResultPersonne, Association association,
				BindingResult bindingResultAssociation) {

			// Si il y a des erreurs il me renvoie le formulaire
			if (bindingResultPersonne.hasErrors()||bindingResultAssociation.hasErrors())
				return "formNewAdh";

			// je récupère le statut "NonActif" par son id
			// je dois caster l'id qui est un attribut de type long
			// .get pour recuperer l'objet
			Statut statut = statutRepository.findById((long) 2).get();

			// je recupère le type (Adhérent)
			Type type = typeRepository.findById((long) 1).get();

			// Pour faire le lien entre la personne de type adherent creer
			// et l'asso j'utilise la classse d'association adherent
			

			personne.setStatut(statut);
			personne.setType(type);
			Adherent adherent = new Adherent();
			adherent.setPersonne(personne);
			adherent.setAssociation(association);

			personneRepository.save(personne);

			adherentRepository.save(adherent);

			return "redirect:/listAdherents";
		}
	
	////*******************************FIN TEST   ****************************
	@GetMapping("/editAdherent")
	public String editMenber(Model model,Long id){
		Adherent adherent = adherentRepository.findById(id).get();
		//Adherent adherent =adherentRepository.findByPersonneContains(personne);
		List<Association> associations = associationRepository.findAll();
		//List<Adherent> adherentList = adherentRepository.findAll();
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		model.addAttribute("adh",adherent);
		//model.addAttribute("adh",adherent);
		//model.addAttribute("adherentList ",adherentList );
		
		return "editAdherent";
	}		
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("personne", new Personne());
		
		return "login";
	}
	@PostMapping("/loginCheck")
	// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
	// dans
	// Binding result
	// Chaque Objet doit avoir sont @Valid et sont BindingResult
	// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
	// personne
	// ça marche pas il faut mettre @ Valid avant
	public String checkLog(Model model, @Valid Personne personne,
			BindingResult bindingResultPersonne) {
		
		//findByPersoLog(personne.getPersoLog());
		// Si il y a des erreurs il me renvoie le formulaire
		if (bindingResultPersonne.hasErrors())
			return "login";

		
		

		return "redirect:/index";
	}
	// ENREGISTREMENT D UN NOUVEAU MENBRE OK **************************
	@GetMapping("/adhesion")
	public String adhesion(Model model) {
		// j'instancie l'objet avec un constructeur à vide
		// je l'ajoute dans le model pour le récuperer dans la vue html

		model.addAttribute("personne", new Personne());

		List<Association> associations = associationRepository.findAll();
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		return "formNewAdh";
	}
	//*****List Inscrit Test
	
			//************** Edition des Inscrits via la table Inscrit OK
		 	@GetMapping("/listInscrits")
			public String inscrits(Model model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="motCle",defaultValue="") String mc) {
			
			Page <Inscrire> inscritList = 
						 inscrireRepository.findByPersonnePersoNomContains(mc,PageRequest.of(page, 10));

				model.addAttribute("MenberList",inscritList);
				model.addAttribute("pages",new int[inscritList.getTotalPages()]);
				model.addAttribute("currentPage",page);
				model.addAttribute("motCle",mc);
					
				return "testListInscrit";
			}
		//************** Edition d'une personne inscrite via la table Inscrire OK
		
		
		@GetMapping("/testeditInscrit")
		public String testeditInscrit(Model model,Long id){
			Inscrire inscrit = inscrireRepository.findById(id).get();
			//Adherent adherent =adherentRepository.findByPersonneContains(personne);
			List<Association> associations = associationRepository.findAll();
			List<Statut> sList = statutRepository.findAll();
			List<Type> typeListe=typeRepository.findAll();
			List<Exercice> ListExercice= exerciceRepository.findAll();
			//List<Adherent> adherentList = adherentRepository.findAll();
			// j'ajoute dans le model la liste des associations recuperer depuis la base
			model.addAttribute("ListAssos", associations);
			model.addAttribute("exList", ListExercice);
			model.addAttribute("adherent",inscrit);
			model.addAttribute("statutList",sList);
			model.addAttribute("typeListe",typeListe);
			// Pbl au niveau de la date de naissance renvoyer par la base de donnée AA/MM/JJ au lieu de JJ/MM/AA
			
			return "editInscrit";
		}		
			
		//*********************  Sauvegarde edition NOK avec table Inscrire********************************
			@PostMapping("/editInscritSave")
			// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
			// dans
			// Binding result
			// Chaque Objet doit avoir sont @Valid et sont BindingResult
			// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
			// personne
			// ça marche pas il faut mettre @ Valid avant
			public String testupdateInscrit(Model model, @Valid Personne personne, BindingResult bindingResultPersonne,Statut statut) {

				// Si il y a des erreurs il me renvoie le formulaire
				if (bindingResultPersonne.hasErrors())
					return "formActivityRegister";
				

				// je recupère le type (participant)
				Type type = typeRepository.findById((long) 4).get();
				personne.setType(type);
				personne.setStatut(statut);
				personneRepository.save(personne);
				/*inscrire.setExercice(exercice);
				inscrireRepository.save(inscrire);
				exercice.setAssociation(association);
				*/
				
				
		model.addAttribute("MenberList", inscrireRepository.findAll());

				return "redirect:/listInscrits";
			}
			
	

	/*ADHESION RETOUR  SUR  LIST CLIENT OK
		@PostMapping("/adhesionSave")
		// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
		// dans
		// Binding result
		// Chaque Objet doit avoir sont @Valid et sont BindingResult
		// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
		// personne
		// ça marche pas il faut mettre @ Valid avant
		public String savePersonne(Model model, @Valid Personne personne, BindingResult bindingResultPersonne, Association association,
				BindingResult bindingResultAssociation) {

			// Si il y a des erreurs il me renvoie le formulaire
			if (bindingResultPersonne.hasErrors()||bindingResultAssociation.hasErrors())
				return "formNewAdh";

			// je récupère le statut "NonActif" par son id
			// je dois caster l'id qui est un attribut de type long
			// .get pour recuperer l'objet
			Statut statut = statutRepository.findById((long) 2).get();

			// je recupère le type (Adhérent)
			Type type = typeRepository.findById((long) 1).get();

			// Pour faire le lien entre la personne de type adherent creer
			// et l'asso j'utilise la classse d'association adherent
			

			personne.setStatut(statut);
			personne.setType(type);
			Adherent adherent = new Adherent();
			adherent.setPersonne(personne);
			adherent.setAssociation(association);

			personneRepository.save(personne);

			adherentRepository.save(adherent);

			return "redirect:/listClients";
		}
		*/
	@GetMapping("/deleteClient")
	public String delete(Model model,Long id,int page,String motCle) {
		//1--Recupération de l'objet personne sélectionné
		personneRepository.findById(id).get();
		// 2-- Recupération de l'object adherent associé 
		adherentRepository.findByPersonneContains(personneRepository.findById(id).get());
		//3-- Récupération de l'ID  Adherent associé
		adherentRepository.findByPersonneContains(personneRepository.findById(id).get()).getId();
		// 4-- Suppression de l'adherent associé
		adherentRepository.deleteById(adherentRepository.findByPersonneContains(personneRepository.findById(id).get()).getId());
		
		// ***** ATTENTION *** Il faut aussi supprimer  l'object Inscrire associé,  la personne a pu s'inscrire à une activité
		//5-- Recupération de l'object Inscrire associé
		inscrireRepository.findByPersonneContains(personneRepository.findById(id).get());
		// 6 --- Recupération de l'ID de l'object Inscrire associé
		inscrireRepository.findByPersonneContains(personneRepository.findById(id).get()).getInscrireid();
		//7 -- Suppression de l'objet inscrire associé à la personne
		inscrireRepository.deleteById(inscrireRepository.findByPersonneContains(personneRepository.findById(id).get()).getInscrireid());
		// 8-- Supression du client
		personneRepository.deleteById(id);
		
		return "redirect:/listClients?page="+page+"&motCle="+motCle;
	}
			
	@GetMapping("/editClient")
	public String editClients(Model model,Long id){
		Personne personne = personneRepository.findById(id).get();
		//Adherent adherent =adherentRepository.findByPersonneContains(personne);
		List<Association> associations = associationRepository.findAll();
		//List<Adherent> adherentList = adherentRepository.findAll();
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		model.addAttribute("personne",personne);
		//model.addAttribute("adh",adherent);
		//model.addAttribute("adherentList ",adherentList );
		
		return "editAdh";
	}		
	@PostMapping("/editClientSsave")
	// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
	// dans
	// Binding result
	// Chaque Objet doit avoir sont @Valid et sont BindingResult
	// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
	// personne
	// ça marche pas il faut mettre @ Valid avant
	public String updatePersonne(Model model, @Valid Personne personne, BindingResult bindingResultPersonne, Association association,
			BindingResult bindingResultAssociation) {

		// Si il y a des erreurs il me renvoie le formulaire
		if (bindingResultPersonne.hasErrors()||bindingResultAssociation.hasErrors())
			return "formNewAdh";
		Statut statut = statutRepository.findById((long) 2).get();

		// je recupère le type (Adhérent)
		Type type = typeRepository.findById((long) 1).get();

		
		

		personneRepository.save(personne);

		

		return "redirect:/listClients";
	}
	

	
	}

