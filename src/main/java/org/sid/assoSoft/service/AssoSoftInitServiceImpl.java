package org.sid.assoSoft.service;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.sid.assoSoft.dao.AdherentRepository;
import org.sid.assoSoft.dao.AdminRepository;
import org.sid.assoSoft.dao.AdresseRepository;
import org.sid.assoSoft.dao.AssociationRepository;
import org.sid.assoSoft.dao.CategorieRepository;
import org.sid.assoSoft.dao.DonRepository;
import org.sid.assoSoft.dao.ExerciceRepository;
import org.sid.assoSoft.dao.GroupeRepository;
import org.sid.assoSoft.dao.InscrireRepository;
import org.sid.assoSoft.dao.MessageRepository;
import org.sid.assoSoft.dao.PersonneRepository;
import org.sid.assoSoft.dao.SituerRepository;
import org.sid.assoSoft.dao.StatutRepository;
import org.sid.assoSoft.dao.TypeRepository;
import org.sid.assoSoft.dao.UrlRepository;
import org.sid.assoSoft.dao.UrlTypeRepository;
import org.sid.assoSoft.entities.Adherent;
import org.sid.assoSoft.entities.Admin;
import org.sid.assoSoft.entities.Adresse;
import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.entities.Categorie;
import org.sid.assoSoft.entities.Don;
import org.sid.assoSoft.entities.Exercice;
import org.sid.assoSoft.entities.Groupe;
import org.sid.assoSoft.entities.Inscrire;
import org.sid.assoSoft.entities.Message;
import org.sid.assoSoft.entities.Personne;
import org.sid.assoSoft.entities.Situer;
import org.sid.assoSoft.entities.Statut;
import org.sid.assoSoft.entities.Type;
import org.sid.assoSoft.entities.Url;
import org.sid.assoSoft.entities.UrlType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssoSoftInitServiceImpl implements IAssoSoftInit {

	@Autowired
	private StatutRepository statutRepository;
	@Autowired
	private CategorieRepository categorieRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private AdherentRepository adherentRepository;
	@Autowired
	private AssociationRepository associationRepository;
	@Autowired
	private ExerciceRepository exerciceRepository;
	@Autowired
	private GroupeRepository groupeRepository;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdresseRepository adresseRepository;
	@Autowired
	private DonRepository donRepository;
	@Autowired
	private InscrireRepository inscrireRepository;
	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private SituerRepository situerRepository;
	@Autowired
	private TypeRepository typeRepository;
	@Autowired
	private UrlRepository urlRepository;
	@Autowired
	private UrlTypeRepository urlTypeRepository;

	
	@Override
	public void initDon() {
		List<Statut> statuts=statutRepository.findAll();
		String[] montants = { "100", "20", "30", "40", "50", "60", "75", "88", "95", "10" };
		personneRepository.findAll().forEach(pers -> {
			associationRepository.findAll().forEach(ass -> {
				Don don = new Don();
				don.setDonMontant(new Random().nextInt(montants.length) * 10);
				don.setDonValid(false);
				don.setDate(new Date());
				don.setPersonne(pers);
				don.setAssociation(ass);
				don.setStatut(statuts.get(new Random().nextInt(statuts.size())));
				donRepository.save(don);
			});
		});
	}

	@Override
	public void initCategorie() {
		Stream.of("sportif", "religieux", "culturelles", "agricoles", "economique").forEach(categorieLabel -> {
			Categorie categorie = new Categorie();
			categorie.setCategorieLabel(categorieLabel);
			categorieRepository.save(categorie);
		});
	}

	@Override
	public void initStatut() {
		Stream.of("Actif", "NonActif").forEach(nameStatut -> {
			Statut statut = new Statut();
			statut.setStatutLabel(nameStatut);
			statutRepository.save(statut);
		});
	}

	@Override
	public void initMessage() {
		Stream.of("blabla1", "blabla2", "blabla3", "blabla4").forEach(tekstMessage -> {
			Message message = new Message();
			message.setTxt(tekstMessage);
			messageRepository.save(message);
		});

	}

	@Override
	public void initAdresse() {
		String[] adresses = new String[] { "5, rue de la Pompe", "5, rue Victor Hugo", "109, rue Victor Hugo","17 av jules vernes"
				,"17 rue de la boetie","27 rue balzac"};
		String [] cps=new String [] {"93330","95200", "78220","92330","91200","94320" };
		Stream.of("Paris", "Versailles", "Montreuil","Vincenne","Asniere","Clichy",
				"Bagneux","Sceaux","Antony","Saint denis" ).forEach(ville -> {
			Adresse adresse = new Adresse();
			
			adresse.setRue(adresses[new Random().nextInt(adresses.length)]);
			adresse.setCp(cps[new Random().nextInt(cps.length)]);
			adresse.setVille(ville);
			adresseRepository.save(adresse);
		});
	}

	@Override
	public void initPersonne() {

		String[] prenoms = { "Mikeal", "Michel", "Henry", " Omar", "Louis", "Louisa", "Mohamed", "Patricia", "JC",
				"Sophie", "Luc", "Naruto", "Saïd", "Donald" };

		String[] tels = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0" };
		List<Type> types = typeRepository.findAll();
		List<Adresse> adr = adresseRepository.findAll();
		List<Statut> statuts=statutRepository.findAll();
		Stream.of("Omaz", "Milo", "Escobar", "Van", "Pargeot", "Michel", "Edouard", "Tran", "Lancelot", "Jonhson",
				"Donovan", "Makhlouf", "Diaye", "Diallo", "Dupont", "Balkany", "Bond", "Trump", "yahoo", "Google",
				"Belverdere", "Pinot", "West", "Fad").forEach(nom -> {
					Personne personne = new Personne();
					personne.setPersoNom(nom);
					personne.setPersoPrenom(prenoms[new Random().nextInt(prenoms.length)]);
					personne.setPersoEmail(personne.getPersoNom() + personne.getPersoPrenom() + "@gmail.com");
					personne.setPersoLog(personne.getPersoNom() + personne.getPersoPrenom());
					personne.setPersoPsw(personne.getPersoNom() + (personne.getPersoPrenom()).charAt(0));
					personne.setPersoTel("06" + new Random().nextInt(tels.length+1) + new Random().nextInt(tels.length+1)
							+ new Random().nextInt(tels.length) + new Random().nextInt(tels.length)
							+ new Random().nextInt(tels.length) + new Random().nextInt(tels.length));
					personne.setType(types.get(new Random().nextInt(types.size())));
					personne.setAdresse(adr.get(new Random().nextInt(adr.size())));
					personne.setStatut(statuts.get(new Random().nextInt(statuts.size())));
					personneRepository.save(personne);
				});
	}

	@Override
	public void initType() {
		Stream.of("Adherent", "Visiteur", "Moderateur", "Participant", "Representant Legal","Donateur").forEach(v -> {
			Type type = new Type();
			type.setTypeLabel(v);
			typeRepository.save(type);
		});
	}

	@Override
	public void initAdmin() {
		
		String[] email = new String[] { "pierreH@gmail.com", "jean2020@gmail.com", "j.Vi@gmail.com" };
		List<Statut> statuts=statutRepository.findAll();
		Stream.of("Pierre", "Jean", "Jaques").forEach(nameAdmin -> {
			Stream.of("060584", "054008", "054545").forEach(t -> {
				Admin admin = new Admin();
				admin.setNom(nameAdmin);
				admin.setTel(t);
				admin.setEmail(email[new Random().nextInt(email.length)]);
				
				adminRepository.save(admin);
			});
		});
	}

	@Override
	public void initGroupe() {
		Stream.of("Service", "Activite").forEach(g -> {
			Groupe groupe = new Groupe();
			groupe.setNom(g);
			groupeRepository.save(groupe);
		});
	}

	@Override
	public void initExercice() {
		List<Association> assos = associationRepository.findAll();
		List<Groupe> gr = groupeRepository.findAll();
		List<Statut> statuts=statutRepository.findAll();
		String[] desc = new String[] { "desc1", "descr2", "descr3", "descr4", "descr5" };
		String[] age = new String[] { "adolescent", "jeune", "junior", "senior" };
		int[] qty = new int[] { 20, 30, 50, 40, 35, 45 };
		String[] horaires=new String[] {"10h","11h","12","14h","16h","17h","18h","20h","19h"};

		Stream.of("Foot", "Basket", "Concert", "Opera", "Messe", "Athletism").forEach(nameExercice -> {
			Exercice exercice = new Exercice();
			exercice.setDateDebut(new Date());
			exercice.setHoraire(horaires[new Random().nextInt(horaires.length)]);
			exercice.setDateFin(new Date());
			exercice.setGenre(nameExercice);
			exercice.setNom(exercice.getGenre() + " " + age[new Random().nextInt(age.length)]);
			exercice.setDescription(desc[new Random().nextInt(desc.length)]);
			exercice.setQuantite(qty[new Random().nextInt(qty.length)]);
			exercice.setGroupe(gr.get(new Random().nextInt(gr.size())));
			exercice.setAssociation(assos.get(new Random().nextInt(assos.size())));
			exercice.setStatut(statuts.get(new Random().nextInt(statuts.size())));
			
			exerciceRepository.save(exercice);
		});
	}

	// Categorie
	// Adresse
	// Statut
	// Adherent
	@Override
	public void initAssociation() {
		String[] name = new String[] { "Club d''Athletisme Montrouge", "Clamart Cyclisme", "Le Panier Bio" };
		String[] rna = new String[] { "W751212517", "W751314527", "W752345538" };
		String[] tel = new String[] { "0185465748", "0194543567", "0165342376" };
		String[] email = new String[] { "athletisme-montrouge@gmail.com", "clamart-cyclisme@gmail.com",
				"panierbio@gmail.com" };
		List<Categorie >categories=categorieRepository.findAll();
		List<Adresse> adresses=adresseRepository.findAll();
		List<Statut> statuts=statutRepository.findAll();
		for (int i = 0; i < name.length; i++) {
			Association association = new Association();
			association.setAssoNom(name[i]);
			association.setRna(rna[i]);
			association.setAssoTel(tel[i]);
			association.setAssoEmail(email[i]);
			association.setCategorie(categories.get(new Random().nextInt(categories.size())));
			association.setAdresse(adresses.get(new Random().nextInt(categories.size())));
			association.setStatut(statuts.get(new Random().nextInt(statuts.size())));
			associationRepository.save(association);
		}
	}

	@Override
	public void initUrl() {
		
		Stream.of("").forEach(nameUrl -> {
			Url url = new Url();
			url.setUrl(nameUrl);
			urlRepository.save(url);
		});
	}

	@Override
	public void initUrlType() {
		Stream.of("Facebook", "Twitter", "SiteWeb", "Video", "Photo").forEach(nameUrlType -> {
			UrlType urltype = new UrlType();
			urltype.setLabel(nameUrlType);
			urlTypeRepository.save(urltype);
		});
	}

	@Override
	public void initAdherent() {
		List<Personne> perso = personneRepository.findAll();
		List<Association> assos = associationRepository.findAll();
		perso.forEach(p -> {
			Adherent adh = new Adherent();
			adh.setAssociation(assos.get(new Random().nextInt(assos.size())));
			adh.setPersonne(p);
			adherentRepository.save(adh);
		});
	}

	@Override
	public void initSituer() {
		List<Exercice> exerc = exerciceRepository.findAll();
		List<Adresse> adr = adresseRepository.findAll();
		exerc.forEach(e -> {
			Situer situer = new Situer();
			situer.setExercice(e);
			situer.setAdresse(adr.get(new Random().nextInt(adr.size())));
			situerRepository.save(situer);
		});
	}

	@Override
	public void initInscrire() {
		List<Personne> perso = personneRepository.findAll();
		List<Exercice> exerc = exerciceRepository.findAll();
		perso.forEach(p -> {
			Inscrire inscr = new Inscrire();
			inscr.setExercice(exerc.get(new Random().nextInt(exerc.size())));
			inscr.setPersonne(p);
			inscrireRepository.save(inscr);
		});
	}

	@Override
	public Adresse findAdresseEx(Exercice exercice) {
		
		return situerRepository.findByExerciceContains(exercice);
	}
	

}
