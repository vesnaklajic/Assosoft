package org.sid.assoSoft.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.assoSoft.dao.AdherentRepository;
import org.sid.assoSoft.dao.AdresseRepository;
import org.sid.assoSoft.dao.AssociationRepository;
import org.sid.assoSoft.dao.CategorieRepository;
import org.sid.assoSoft.dao.DonRepository;
import org.sid.assoSoft.dao.ExerciceRepository;
import org.sid.assoSoft.dao.GroupeRepository;
import org.sid.assoSoft.dao.InscrireRepository;
import org.sid.assoSoft.dao.PersonneRepository;
import org.sid.assoSoft.dao.SituerRepository;
import org.sid.assoSoft.dao.StatutRepository;
import org.sid.assoSoft.dao.TypeRepository;
import org.sid.assoSoft.entities.Adherent;
import org.sid.assoSoft.entities.Adresse;
import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Categorie;
import org.sid.assoSoft.entities.Don;
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
public class AssociationController {
	@Autowired
	private ExerciceRepository exerciceRepository;
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
	private CategorieRepository categorieRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private SituerRepository situerRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private InscrireRepository inscrireRepository;
	@Autowired
	private DonRepository donRepository;
	
	@GetMapping("/index")
	public String dex(Model model,@RequestParam(name="page",defaultValue = "0") int page,
			@RequestParam(name="motCle",defaultValue="") String mc) {
		Page<Association> pageAssos = 
				associationRepository.findByAssoNomContains(mc, PageRequest.of(page, 5));

		model.addAttribute("ListAssos",pageAssos.getContent());
		model.addAttribute("pages",new int[pageAssos.getTotalPages()]);
		model.addAttribute("currentPage",page);
		model.addAttribute("motCle",mc);
		
		return "associations";
		
	}
	@GetMapping("/delete")
	public String delete(Model model,Long id,int page,String motCle) {
		
		associationRepository.deleteById(id);
		
		return "redirect:/index?page="+page+"&motCle="+motCle;
	}
	@GetMapping("/editAsso")
	public String editAssociation(Model model,Long id){
		Association association =associationRepository.findById(id).get();
		
		List <Association> assoList= associationRepository.findAll(); 
		List<Categorie> categories = categorieRepository.findAll();
		List<Statut> statutList=statutRepository.findAll();
		model.addAttribute("association",association);
		model.addAttribute("assoList",assoList);
		model.addAttribute("catList", categories);
		model.addAttribute("statutList",statutList);
		return "editAsso";
	}
	//@RequestMapping(value = "/Inscription", method = RequestMethod.GET)
	//Pareil
	@GetMapping("/CreationAsso")
	public String inscription(Model model) {
		 	// j'instancie l'objet avec un constructeur à vide
			// je l'ajoute dans le model pour le récuperer dans la vue html
			model.addAttribute("association", new Association());
			model.addAttribute("adresse", new Adresse());
			model.addAttribute("personne", new Personne());
			
			
			List<Categorie> categories = categorieRepository.findAll();
			// j'ajoute dans le model la liste des categories recuperer depuis la base
			model.addAttribute("categories", categories);
		return "formNewAssos";
	}
	
	
	@PostMapping("/AssoSav")
	public String saveAsso(Model model,  @Valid Association association, BindingResult bindingResultAssociation, @Valid Personne personne, BindingResult bindingResultPersonne,Categorie categorie,@Valid Adresse adresse ,BindingResult bindingResultAdresse) {
		
		// Si il y a des erreurs il me renvoie le formulaire
		if( bindingResultAssociation.hasErrors()) 
			return "formNewAssos";
		
		//je récupère le statut "NonActif" par son id
		// je doit cast l'id qui est un attribut de type long
		// .get pour recuperer l'objet
		Statut statut = statutRepository.findById((long) 2).get();
		
		Type type = typeRepository.findById((long) 5).get();
		
		//Pour faire le lien entre la personne de type représentant creer
		// et l'asso j'utilise la classse d'association adherent
		
		adresseRepository.save(adresse);
		personne.setType(type);
		personne.setStatut(statut);
		personneRepository.save(personne);
		association.setStatut(statut);
		association.setCategorie(categorie);
		association.setAdresse(adresse);
		
		associationRepository.save(association);
		Adherent adherent = new Adherent();
		adherent.setPersonne(personne);
		adherent.setAssociation(association);
		adherentRepository.save(adherent);
		return "redirect:/index";
	}
	
	
	@PostMapping("/AssoSavEdit")
	public String saveAssoEdit(Model model,  @Valid Association association, BindingResult bindingResultAssociation, Categorie categorie,@Valid Adresse adresse ,BindingResult bindingResultAdresse,Statut statut) {
		
		// Si il y a des erreurs il me renvoie le formulaire
		if( bindingResultAssociation.hasErrors()) 
			return "formNewAssos";
		
		
		
		//Pour faire le lien entre la personne de type représentant creer
		// et l'asso j'utilise la classse d'association adherent
		
		adresseRepository.save(adresse);
		association.setStatut(statut);
		association.setCategorie(categorie);
		association.setAdresse(adresse);
		associationRepository.save(association);
		
		
		return "redirect:/index";
	}
//*******************  STATISTICS*************************************
	@GetMapping("/statistics")
	public String data(Model model) {
		
		Long nbAdherentTotal=adherentRepository.count();
		Long nbInscriptionTotal=inscrireRepository.count();
		Long nbDonTotal= donRepository.count();
		Long nbVisiteurTotal=personneRepository.count();
		
		

		return "statistics";
	}
	
}
