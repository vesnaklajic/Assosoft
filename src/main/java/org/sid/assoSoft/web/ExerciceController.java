package org.sid.assoSoft.web;

import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.sid.assoSoft.dao.AdresseRepository;
import org.sid.assoSoft.dao.AssociationRepository;
import org.sid.assoSoft.dao.CategorieRepository;
import org.sid.assoSoft.dao.ExerciceRepository;
import org.sid.assoSoft.dao.GroupeRepository;
import org.sid.assoSoft.dao.InscrireRepository;
import org.sid.assoSoft.dao.PersonneRepository;
import org.sid.assoSoft.dao.SituerRepository;
import org.sid.assoSoft.dao.StatutRepository;
import org.sid.assoSoft.dao.TypeRepository;
import org.sid.assoSoft.entities.Adresse;
import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Categorie;
import org.sid.assoSoft.entities.Exercice;
import org.sid.assoSoft.entities.Groupe;
import org.sid.assoSoft.entities.Inscrire;
import org.sid.assoSoft.entities.Personne;
import org.sid.assoSoft.entities.Situer;
import org.sid.assoSoft.entities.Statut;
import org.sid.assoSoft.entities.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ExerciceController {
	@Autowired
	private ExerciceRepository exerciceRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private StatutRepository statutRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private SituerRepository situerRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private InscrireRepository inscrireRepository;
	
//************** Edition des exercices via la table Situer OK
	
	
	@GetMapping("/test")
	public String test(Model model,@RequestParam(name="page",defaultValue = "0") int page,@RequestParam(name="motCle",defaultValue="") String mc) {
		 
		Page<Situer> situerLlist=situerRepository.findAll(PageRequest.of(page, 10));
		model.addAttribute("List", situerLlist);
		model.addAttribute("pages",new int[situerLlist.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("motCle",mc);
		return "testsituer";
	}
	
//************* Affichage d'un exercice via la table Situer OK
	
			@GetMapping("/editExercices")
			public String editSit(Model model,Long id){
				Situer situer =situerRepository.findById(id).get();
				//List<Situer> genreList=situerRepository.findAll();
				List<Association> associations = associationRepository.findAll();
				List<Statut> sList = statutRepository.findAll();
				List<Groupe>listType=groupeRepository.findAll();
				model.addAttribute("ListAssos", associations);
				model.addAttribute("a",situer);
				model.addAttribute("statutList",sList);
				model.addAttribute("typeList",listType);
				//model.addAttribute("genreList",genreList);
				return "editExercice";
			}
			
			
			// ***********Creation d'une activité dans une association  OK *************
			
			@GetMapping("/createExercice")
			public String createnExercice(Model model) {
				 	// j'instancie l'objet avec un constructeur à vide
					// je l'ajoute dans le model pour le récuperer dans la vue html
				model.addAttribute("exercice", new Exercice());
				model.addAttribute("adresse", new Adresse());
				List<Association> associations = associationRepository.findAll();
				List<Groupe> groupe = groupeRepository.findAll();
				model.addAttribute("groupeList", groupe);
				// j'ajoute dans le model la liste des associations recuperer depuis la base
				model.addAttribute("ListAssos", associations);
					
				return "formNewExercice";
			}
			
			
			// ***********ENREGISTREMENT D' UNE ACTIVITE__ OK******************
			@PostMapping("/saveExercice")
			//@Valid valid les donnes de l'objet personne, et les resultat sont stocker dans
			//Binding result
			//Chaque Objet doit avoir sont @Valid et sont BindingResult
			//exemple si on met BindingResult bindingResultPersonne, @Valid Personne personne
			// ça marche pas il faut mettre @ Valid avant
			//public String saveExercice(Model model,@Valid Exercice exercice, BindingResult bindingResultExercice,@Valid Adresse adresse ,BindingResult bindingResultAdresse, @Valid Association association, BindingResult bindingResultAssociation,@Valid Groupe groupe,BindingResult bindingResultGroupe) {
			public String saveExercice(Model model,@Valid Exercice exercice, BindingResult bindingResultExercice,@Valid Adresse adresse ,BindingResult bindingResultAdresse,@Valid Association association , BindingResult bindingResultAssociation,Groupe groupe) {
			Statut statut = statutRepository.findById((long) 2).get();
				exercice.setStatut(statut);
				exercice.setGroupe(groupe);
				exercice.setAssociation(association);
				Situer situerExercice=new Situer();
				situerExercice.setAdresse(adresse);
				situerExercice.setExercice(exercice);
				adresseRepository.save(adresse);
				exerciceRepository.save(exercice);
				situerRepository.save(situerExercice);
			
				return "redirect:/test";
			}
			
	// *************  Save edition via table NOK PBL de date!!!!!!!!!!!!!!!!!!!!!!!!		
	@PostMapping("/saveEdit")
	//@Valid valid les donnes de l'objet personne, et les resultat sont stocker dans
	//Binding result
	//Chaque Objet doit avoir sont @Valid et sont BindingResult
	//exemple si on met BindingResult bindingResultPersonne, @Valid Personne personne
	// ça marche pas il faut mettre @ Valid avant
	public String saveEdite(Model model,@Valid Exercice exercice, BindingResult bindingResultExercice,@Valid Adresse adresse ,BindingResult bindingResultAdresse,@Valid Association association , BindingResult bindingResultAssociation,Groupe groupe,Statut statut) {
		
		exercice.setStatut(statut);
		exercice.setGroupe(groupe);
		exercice.setAssociation(association);
		exerciceRepository.save(exercice);
		adresseRepository.save(adresse);
		model.addAttribute("ExercicesList", situerRepository.findAll());
		
		/*Situer situerExercice=new Situer();
		situerExercice.setAdresse(adresse);
		situerExercice.setExercice(exercice); */
		
		//situerRepository.save(situerExercice);
	
		return "redirect:/test";
		
	}
	
	
	//***********************S'inscrire à une activité   **********************************
	
	@GetMapping("/doExercice")
	public String inscriptionExercice(Model model) {
		 	// j'instancie l'objet avec un constructeur à vide
			// je l'ajoute dans le model pour le récuperer dans la vue html
		model.addAttribute("personne", new Personne());
		List<Personne> listPers = personneRepository.findAll();
		model.addAttribute("ListUsers", listPers);
			List<Exercice> exercices = exerciceRepository.findAll();
			/* exercices.forEach(ex -> {
			ex.getExerciceId();
			ex.getNom();
			ex.getAssociation();
			ex.getGenre();
			});*/
			//model.addAttribute("exo", exercices);
			// j'ajoute dans le model la liste des exercices recuperer depuis la base
			model.addAttribute("exerciceList", exercices);
			List<Association> associations = associationRepository.findAll();
			// j'ajoute dans le model la liste des associations recuperer depuis la base
			model.addAttribute("ListAssos", associations);
			List<Categorie> categories = categorieRepository.findAll();
			// j'ajoute dans le model la liste des categories recuperer depuis la base
			model.addAttribute("categories", categories);
			List<Situer> situerList = situerRepository.findAll();
			model.addAttribute("situerList", situerList);
		return "formActivityRegister";
	}
	
	// *************ENREGISTREMENT  D'UNE INSCRIPTION A UNE ACTIVITE__ OK PBL IINSCRITTION DANS TOUS LES CAS ADHERENT OU NON ET DONC CREATION DE DOUBLON A TRAITER******************
	
		@PostMapping("/saveDoExercice")
		//@Valid valid les donnes de l'objet personne, et les resultat sont stocker dans
		//Binding result
		//Chaque Objet doit avoir sont @Valid et sont BindingResult
		//exemple si on met BindingResult bindingResultPersonne, @Valid Personne personne
		// ça marche pas il faut mettre @ Valid avant
		public String saveDoExercice(Model model,@Valid Personne personne,
				BindingResult bindingResultPersonne, @Valid Exercice exercice) {
			
			// Si il y a des erreurs il me renvoie le formulaire
			if(bindingResultPersonne.hasErrors()) 
				return "formNewExercice";
			
			//je récupère le statut "NonActif" par son id
			// je doit cast l'id qui est un attribut de type long
			// .get pour recuperer l'objet
			Statut statut = statutRepository.findById((long) 2).get();
			Type role= typeRepository.findById((long) 4).get();
			personne.setStatut(statut);
			personne.setType(role);
			personneRepository.save(personne);	
			//Pour faire le lien entre l'exercice et l'adresse , on utilise la table Situer 
			//On fixe l'adresse avec l'exercice dans la table Situer
			Inscrire inscrireExercice=new Inscrire();
			inscrireExercice.setPersonne(personne);
			inscrireExercice.setExercice(exercice);
			inscrireRepository.save(inscrireExercice);
			
			
			return "redirect:/test";
		}
	
	
	
	@GetMapping("/ExerciceList")
	public String List(Model model,@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="motCle",defaultValue="") String mc) {
		Page<Exercice> pageActivites = 
				exerciceRepository.findByNomContains(mc, PageRequest.of(page, 5));
		List <Adresse> adresseList=adresseRepository.findAll();
		List<Situer> situerLlist=situerRepository.findAll();
        
		//Situer situers=situerRepository.findByExerciceContains(?);
		model.addAttribute("ExercicesList",pageActivites.getContent());
		model.addAttribute("pages",new int[pageActivites.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("motCle",mc);
		model.addAttribute("adList",adresseList);
		
		
		
		return "ExerciceList";
		
	}
	//*************************TEST *****************************************
	
	@GetMapping("/TestcreateExercice")
	public String createnEx(Model model) {
		 	// j'instancie l'objet avec un constructeur à vide
			// je l'ajoute dans le model pour le récuperer dans la vue html
		model.addAttribute("exercice", new Exercice());
		
		model.addAttribute("groupe", new Groupe());
		List<Association> associations = associationRepository.findAll();
		List<Groupe> groupe = groupeRepository.findAll();
		model.addAttribute("groupeList", groupe);
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		List<Groupe> groupes = groupeRepository.findAll();
		// j'ajoute dans le model la liste des groupes recuperer depuis la base
					model.addAttribute("groupes", groupes);
		return "CreationEx";
	}
	@PostMapping("/TestsaveExercice")
	//@Valid valid les donnes de l'objet personne, et les resultat sont stocker dans
	//Binding result
	//Chaque Objet doit avoir sont @Valid et sont BindingResult
	//exemple si on met BindingResult bindingResultPersonne, @Valid Personne personne
	// ça marche pas il faut mettre @ Valid avant
	//public String saveExercice(Model model,@Valid Exercice exercice, BindingResult bindingResultExercice,@Valid Adresse adresse ,BindingResult bindingResultAdresse, @Valid Association association, BindingResult bindingResultAssociation,@Valid Groupe groupe,BindingResult bindingResultGroupe) {
	public String saveEx(Model model,@Valid Exercice exercice, BindingResult bindingResultExercice,@Valid Association association , BindingResult bindingResultAssociation, @Valid Groupe groupe,BindingResult bindingResultGroupe) {
		Statut statut = statutRepository.findById((long) 2).get();
	    exercice.setGroupe(groupe);
		exercice.setAssociation(association);
		exercice.setStatut(statut);
		
		exerciceRepository.save(exercice);
	
		return "redirect:/ExerciceList";
	}
	
	//***********************************************************************************
	/* @GetMapping("/ExerciceList")
	public String List(Model model,@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="motCle",defaultValue="") String mc) {
		Page<Exercice> pageActivites = 
				exerciceRepository.findByNomContains(mc, PageRequest.of(page, 5));
		List <Adresse> adresseList=adresseRepository.findAll();
		List <Situer> situers=situerRepository.findAll();
        
		//Situer situers=situerRepository.findByExerciceContains(?);
		model.addAttribute("ExercicesList",pageActivites.getContent());
		model.addAttribute("pages",new int[pageActivites.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("motCle",mc);
		model.addAttribute("adList",adresseList);
		model.addAttribute("situers",situers);
		//model.addAttribute("situerList",findByExerciceContains(pageActivites.getContent().get(index)));
		
		
		return "ExerciceList";
		
	}
	*/
	
	/*
	 // Affichage d'un exercice
	 
	@GetMapping("/editExercices")
	public String editEx(Model model,Long id){
		Exercice exercice =exerciceRepository.findById(id).get();
		//Recupération de  l'object Adresse associée à l object Exercice via l'entité Situer
		
		//Adresse adresse =situerRepository.findByExerciceContains(exercice);
		List<Association> associations = associationRepository.findAll();
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		model.addAttribute("exercice",exercice);
		
		return "editExercice";
	}
	*/

	
	
	/*@PostMapping("/saveExercice")
	//@Valid valid les donnes de l'objet personne, et les resultat sont stocker dans
	//Binding result
	//Chaque Objet doit avoir sont @Valid et sont BindingResult
	//exemple si on met BindingResult bindingResultPersonne, @Valid Personne personne
	// ça marche pas il faut mettre @ Valid avant
	public String saveExercice(Model model, @Valid Exercice exercice, BindingResult bindingResultExercice, 
			@Valid Association association, BindingResult bindingResultAssociation,@Valid Adresse adresse, BindingResult bindingResultAdresse,@Valid Groupe groupe, BindingResult bindingResultGroupe) {
		
		// Si il y a des erreurs il me renvoie le formulaire
		if(bindingResultExercice.hasErrors() || bindingResultAssociation.hasErrors()||bindingResultAdresse.hasErrors()) 
			return "formNewExercice";
		
		//je récupère le statut "NonActif" par son id
		// je doit cast l'id qui est un attribut de type long
		// .get pour recuperer l'objet
		Statut statut = statutRepository.findById((long) 2).get();
		
				
		//Pour faire le lien entre l'exercice et l'adresse , on utilise la table Situer 
		//On fixe l'adresse avec l'exercice dans la table Situer
		Situer situerExercice=new Situer();
		situerExercice.setAdresse(adresse);
		situerExercice.setExercice(exercice);
		situerRepository.save(situerExercice);
		exercice.setGroupe(groupe);
		exercice.setAssociation(association);
		exercice.setStatut(statut);
	
		adresseRepository.save(adresse);
		exerciceRepository.save(exercice);
		
		
		return "redirect:/ExerciceList";
	}
	*/
	
	@GetMapping("/deleteEx")
	public String deleteEx(Model model,Long id,int page,String motCle) {
		exerciceRepository.deleteById(id);
		
		return "redirect:/ExerciceList?page="+page+"&motCle="+motCle;
	}
}
