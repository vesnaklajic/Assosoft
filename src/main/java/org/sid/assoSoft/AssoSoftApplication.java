package org.sid.assoSoft;

import org.sid.assoSoft.entities.Association;
import org.sid.assoSoft.service.IAssoSoftInit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
public class AssoSoftApplication implements CommandLineRunner{
	
	@Autowired
	private IAssoSoftInit assoSoftInitService;

	public static void main(String[] args) {
		SpringApplication.run(AssoSoftApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		assoSoftInitService.initCategorie();
		assoSoftInitService.initStatut();
		assoSoftInitService.initUrlType();
		assoSoftInitService.initGroupe();
		assoSoftInitService.initType();
		assoSoftInitService.initAdmin();
		assoSoftInitService.initAdresse();
		assoSoftInitService.initMessage();
		assoSoftInitService.initAssociation();
		assoSoftInitService.initPersonne();
		assoSoftInitService.initDon();
		assoSoftInitService.initExercice();
		assoSoftInitService.initInscrire();
		assoSoftInitService.initAdherent();
		assoSoftInitService.initSituer();
		
	}

	
	
}
