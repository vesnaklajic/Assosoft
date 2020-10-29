--// TYPE//
INSERT INTO  Type VALUES ( 'Visiteur');
INSERT INTO  Type VALUES ( 'Adhérent');
INSERT INTO  Type VALUES ( 'Contact');
INSERT INTO  Type VALUES ( 'Modérateur');

--//GROUPE//
INSERT INTO  Groupe VALUES ( 'Activité');
INSERT INTO  Groupe VALUES ( 'Service');

--//ADMIN//
INSERT INTO `admin` (`ADMINLOG`, `ADMINMDP`, `ADMINDATENAISS`, `ADMINNOM`, `ADMINPRENOM`, `ADMINTEL`, `ADMINEMAIL`) VALUES
('pierre2020', 'pierreHer19', '25.04.1980.', 'HERMES', 'Pierre', '06.05.75.30.04', 'pierre.hermes@gmail.com'),
('dupontG2019', 'dupontGeo19', '15.01.1974.', 'DUPONT', 'Georges', '07.01.05.95.01', 'georges.dupont@gmail.com'),
('monicaR', 'monicaCol', '27.05.1985.', 'RIOS', 'Monica', '01.05.45.81.25', 'monica.rios@gmail.com'),
('albonB', 'deboital19', '18.12.1996.', 'DEBOIT', 'Albon', '05.08.98.56.01', 'albonDeboit@gmail.com');

--//URLTYPE//
INSERT INTO `urltype` (`URLLABEL`) VALUES
('SiteWeb'),
('FB'),
('Twitter'),
('Google'),
('LinkedIn');

--//CATEGORIE//
INSERT INTO `categorie` (`CATEGORIEID`, `CATEGORIELABEL`) VALUES
('sportive'),
('religieuses'),
('cultuelles '),
('agriculturel'),
('economic');

--//STATUS//
INSERT INTO  Status VALUES ('Actif');
INSERT INTO  Status VALUES ('Inactif');

--//ADRESSE//
--INSERT INTO  Adresse VALUES (ADRESSE, CP, VILLE)
INSERT INTO  Adresse VALUES ( '32 Rue Rottembourg','75012',' Paris');
INSERT INTO  Adresse VALUES ( '8 r Gymnase ','57190','Florange');
INSERT INTO  Adresse VALUES  ( '31 rue du Commandant Mouchotte, ','94160 ','Saint Mandé ');
INSERT INTO  Adresse VALUES  ( '10 rue Mongenot ','94160','Saint Mandé');
INSERT INTO  Adresse VALUES  ( '44 rue de la Roche ','95300','Pontoise');
INSERT INTO  Adresse VALUES  ( '11 avenue Lamartine', '92600 ','Asnières-sur-Seine');
INSERT INTO  Adresse VALUES  ( '11 avenue de la Marne ','94160','Saint Mandé');
INSERT INTO  Adresse VALUES  ( '91 boulevard Voltaire ','92600','Asnières-sur-Seine');
INSERT INTO  Adresse VALUES  ( '237 rue du Président Salvador Allendé ', '92700 ','Colombes');
INSERT INTO  Adresse VALUES  ( '11 avenue de la Marne ','94160','Saint Mandé');
INSERT INTO  Adresse VALUES  ( '10 rue Pierre Brossolette ','92600','Asnières-sur-Seine'); 

--//PERSONNE//
--INSERT INTO Personne VALUES (ADRESSEID, TYPEID, PRSNOM, PRSPRENOM, PRSDATENAISS, PRSLOG, PRSMDP, PRSEMAIL, PRSTEL)
INSERT INTO Personne VALUES ( ?,?, 'HUGO','Victor','08.08.1985.','victorH','miserie','victor.hugo@gmail.com','07.01.58.02.27');
INSERT INTO Personne VALUES ( ?,?, 'RAVEL','Moris','17.02.1968.','morisR','morisson','moris.ravel@gmail.com','08.05.95.27.06');
INSERT INTO Personne VALUES ( ?,?, 'GUINOT','Anne','29.11.1994.','annG','annica','anne.G@gmail.com','01.17.47.35.07');
INSERT INTO Personne VALUES ( ?,?, 'BONO','Sabine','06.12.2001.','sabineB','bonoU2','s.Bono@gmail.com','02.25.21.54.14');
INSERT INTO Personne VALUES ( ?,?, 'ROSE','Axel','28.10.2000.','axelR','novemberA','axelRose@gmail.com','08.32.85.32.52');

--//ASSOCIATION//
--INSERT INTO ASSOCIATION VALUES (CATEGORIEID, STATUTID, ADRESSEID, ASSOCIATIONNOM, RNA,  TEL, EMAIL);
INSERT INTO ASSOCIATION VALUES (?, 'Club d''Athletisme Montrouge', ?, athletisme-montrouge@gmail.com, "W751212517", ?, '0185465748');
INSERT INTO ASSOCIATION VALUES (?, 'Clamart Cyclisme', ?, clamart-cyclisme@gmail.com, 'W751314527', ?, '0194543567');
INSERT INTO ASSOCIATION VALUES (?, 'Le Panier Bio', ?, panierbio@gmail.com, 'W752345538', ?, '0165342376');

--//URL//
--INSERT INTO URL VALUES (ASSOCIATIONID, URLTYPEID, URL);
INSERT INTO URL VALUES (1, 1, 'https://www.montrouge-athletisme.fr/');
INSERT INTO URL VALUES (1, 2, 'https://www.facebook.com/MontrougeAthle/');

--INSERT INTO  Adherer VALUES (PRSID, ASSOCIATIONID);

--//DON//
--INSERT INTO Don VALUES (DONID, ASSOCIATION, PRSID, DONMONTANT, DONDATE, DONVALID, DONDESCRIPTION)
INSERT INTO Don VALUES ( ?,?, '100','24.02.2020.','valider','argent');
INSERT INTO Don VALUES ( ?,?, '200','12.01.2020.','nonvalider','argent');
INSERT INTO Don VALUES ( ?,?, '500','25.12.2019.','nonvalider','argent');
INSERT INTO Don VALUES ( ?,?, '300','17.11.2019.','valider','argent');
INSERT INTO Don VALUES ( ?,?, '150','21.11.2019.','valider','argent');
INSERT INTO Don VALUES ( ?,?, '400','05.12.2019.','nonvalider','ordinateur');
INSERT INTO Don VALUES ( ?,?, '250','25.01.2019.','nonvalider','projector');

--//Message//
--INSERT INTO  Message VALUES (PRSID, ASSOCIATION, MSGTXT)
INSERT INTO  Message VALUES ( '?','?', 'Bonjour serait il possible de payer en mensualité ');
INSERT INTO  Message VALUES ( '?','?', 'Bonjour , Faites vous des tarifs preferentiels pour les enfants? ');

--//EXERCICE//
--INSERT INTO  Exercice VALUES (ASSOCIATIONID, GROUPEID, EXNOM, EXDESC, EXDATEDEB, EXDATEFIN, EXHORAIRE, EXGENRE)
INSERT INTO  Exercice VALUES ( '?','Football Benjamin1','Cours en groupe avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Football', '?');
INSERT INTO  Exercice VALUES ( '?','Football Benjamin2','Cours en groupe avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Football', ?);
INSERT INTO  Exercice VALUES ( '?','Football Poussin2','Cours en groupe avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Football', ?);
INSERT INTO  Exercice VALUES ( '?','Football Poussin1','Cours en groupe avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Football', ?);
INSERT INTO  Exercice VALUES ( '?','Basket Benjamin2','Cours en groupe avec entraineur certifié ',25/09/2020, 25/06/2021,'Horaire: 12h - 14h','Basketball', ?);
INSERT INTO  Exercice VALUES ( '?','Basket Benjamin1','Cours en groupe avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Basketball', ?);
INSERT INTO  Exercice VALUES ( '?','course Benjamin2','Cours en groupe avec entraineur certifié ',25/09/2020, 25/06/2021,'Horaire: 12h - 14h','Course', ?);
INSERT INTO  Exercice VALUES ( '?','Course Senior','Cours en groupe avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Course', ?);
INSERT INTO  Exercice VALUES ( '?','Course junior','Cours en groupe avec entraineur certifié',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Course', ?);
INSERT INTO  Exercice VALUES ( '?','Peinture Senior','Cours en individuel avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Peinture', ?);
INSERT INTO  Exercice VALUES ( '?','Sculture junior','Cours en individuel avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Culture', ?);
INSERT INTO  Exercice VALUES ( '?','Peinture enfant','Cours en individuel avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Peinture', ?);
INSERT INTO  Exercice VALUES ( '?','Sculture enfant','Cours en individuel avec entraineur certifié ',25/09/2020,25/06/2021,'Horaire: 12h - 14h','Culture', ?);

--//SITUER//
--INSERT INTO Situer Values (ADRESSEID, EXID)

--//S_INSCRIRE//
--INSERT INTO s_inscrire Values (PRSID, EXID)
