package org.sid.assoSoft.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.sid.assoSoft.dao.AdherentRepository;
import org.sid.assoSoft.dao.AssociationRepository;
import org.sid.assoSoft.dao.CategorieRepository;
import org.sid.assoSoft.dao.DonRepository;
import org.sid.assoSoft.dao.PersonneRepository;
import org.sid.assoSoft.dao.StatutRepository;
import org.sid.assoSoft.dao.TypeRepository;
import org.sid.assoSoft.entities.Adherent;
import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Categorie;
import org.sid.assoSoft.entities.Don;
import org.sid.assoSoft.entities.Personne;
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
public class DonController {

	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private StatutRepository statutRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private DonRepository donRepository;
	@Autowired
	private AssociationRepository associationRepository;

	// Affichage de l'ensemble des Dons avec pagination OK

	@GetMapping("/donList")
	public String don(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {
		Page<Don> pageDons = donRepository.findByPersonnePersoNomContains(mc,PageRequest.of(page, 10));

		model.addAttribute("ListDons", pageDons.getContent());
		model.addAttribute("pages", new int[pageDons.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("motCle", mc);

		return "donLists";
	}

	// Formulaire de creation de don OK
	@GetMapping("/donate")
	public String donate(Model model) {
		// j'instancie l'objet avec un constructeur à vide
		// je l'ajoute dans le model pour le récuperer dans la vue html
		// ajout de l'heure automatique
		model.addAttribute("serverTime", new Date());
		model.addAttribute("personne", new Personne());
		model.addAttribute("don", new Don());
		List<Association> associations = associationRepository.findAll();
		// j'ajoute dans le model la liste des associations recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		return "formNewDon";
	}
// ************Enregistrement d'un don  OK   *************************************

	@PostMapping("donate/save")
	// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
	// dans
	// Binding result
	// Chaque Objet doit avoir sont @Valid et sont BindingResult
	// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
	// personne
	// ça marche pas il faut mettre @ Valid avant
	public String saveDon(Model model, @Valid Personne personne, @Valid Don don, Association association,
			BindingResult bindingResultPersonne, BindingResult bindingResultDon) {

		// Si il y a des erreurs il me renvoie le formulaire
		if (bindingResultPersonne.hasErrors() || bindingResultDon.hasErrors())
			return "formNewDon";

		// je récupère le statut "NonActif" par son id
		// je dois caster l'id qui est un attribut de type long
		// .get pour recuperer l'objet
		Statut statut = statutRepository.findById((long) 2).get();

		// je recupère le type (Donateur)
		Type type = typeRepository.findById((long) 6).get();

		// Pour faire le lien entre la personne de type adherent creer
		// et l'asso j'utilise la classse d'association adherent

		personne.setStatut(statut);
		personne.setType(type);

		don.setPersonne(personne);
		don.setDate(new Date());
		don.setDonValid(false);
		don.setAssociation(association);
		don.setStatut(statut);
		personneRepository.save(personne);

		donRepository.save(don);

		return "redirect:/donList";
	}

	// ************** Suppression d'un don Ok ****************************

	@GetMapping("/deleteDon")
	public String delete(Model model, Long id, int page, String motCle) {

		Don donPersonne = donRepository.findById(id).get();

		// Long IdPersonne=donPersonne.getPersonne().getPersoId();

		donRepository.deleteById(id);
		// personneRepository.deleteById(IdPersonne);

		return "redirect:/donList?page=" + page + "&motCle=" + motCle;
	}

	// ******************** Edition d'un don Ok ************************//

	@GetMapping("/editDon")
	public String editProduit(Model model, Long id) {
		// Recupération du don par son id dans la base
		Don don = donRepository.findById(id).get();
		// Recupération des statuts dans la base de données pour le menu deroulant afin
		// d'éviter les fautes de frappes
		List<Statut> sList = statutRepository.findAll();

		don.getAssociation();
		don.getPersonne();

		// Recupération des associations dans la base de données pour le menu deroulant
		// afin d'éviter les fautes de frappes
		List<Association> associations = associationRepository.findAll();
		// ajout dans le model la liste des associations , des dons et des statuts
		// recuperer depuis la base
		model.addAttribute("ListAssos", associations);
		model.addAttribute("don", don);
		model.addAttribute("statutList", sList);
		return "editDon";
	}

	// *********Modification d'un don OK ***************************//
	@PostMapping("donate/saveEdit")
	// @Valid valid les donnes de l'objet personne, et les resultat sont stocker
	// dans
	// Binding result
	// Chaque Objet doit avoir sont @Valid et sont BindingResult
	// exemple si on met BindingResult bindingResultPersonne, @Valid Personne
	// personne
	// ça marche pas il faut mettre @ Valid avant
	public String saveDonEdit(Model model, @Valid Personne personne, @Valid Don don, Association association,
			BindingResult bindingResultPersonne, BindingResult bindingResultDon, Statut statut) {

		// Si il y a des erreurs il me renvoie le formulaire
		if (bindingResultPersonne.hasErrors() || bindingResultDon.hasErrors())
			return "formNewDon";

		// Récupération du statut "NonActif" par son id (2)
		// on doit caster l'id qui est un attribut de type long
		// puis récupérer l'objet par la methode get()
		Statut statutP = statutRepository.findById((long) 2).get();

		// idem pour le type de personne (Donateur (6))
		Type type = typeRepository.findById((long) 6).get();

		// Mise a jour d'un don dans la base

		personne.setStatut(statutP);
		personne.setType(type);
		don.setPersonne(personne);
		don.setDate(new Date());
		don.setDonValid(false);
		don.setAssociation(association);
		don.setStatut(statut);
		personneRepository.save(personne);
		donRepository.save(don);

		return "redirect:/donList";
	}
}
