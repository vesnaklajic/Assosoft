package org.sid.assoSoft.service;

import org.sid.assoSoft.entities.Adresse;
import org.sid.assoSoft.entities.Exercice;

public interface IAssoSoftInit {

public void initCategorie();
public void initGroupe();
public void initType();
public void initStatut();
public void initUrl();
public void initUrlType();
public void initAdresse();
public void initAssociation();
public void initPersonne();
public void initExercice();
public void initSituer();
public void initAdherent();
public void initInscrire();
public void initDon();
public void initMessage();
public void initAdmin();
public Adresse findAdresseEx(Exercice exercice);

}
