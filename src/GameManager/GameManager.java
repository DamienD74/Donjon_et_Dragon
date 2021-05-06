package GameManager;

import java.util.Scanner; 
import Classe.*;
import Donjon.SalleEnnemi;
import Donjon.SalleEvenement;
import Donjon.SalleObjet;
import Donjon.SalleVide;
import Equipement.Equipement;
import Equipement.Arme.ListeArme;
import Equipement.Arme.ListeSort;
import Equipement.Protection.ListeBouclier;
import Equipement.Consommable.ListePotion;
import Ennemi.Ennemi;
import Ennemi.ListeEnnemi;

import java.util.ArrayList;
import java.util.List;

public class GameManager{

    public List<Personnage> listePersonnage = new ArrayList<>();
    private static boolean hasReqExit = false;
    private boolean estChoixClasse = false;
    private boolean estAvantPartie = false;
    private boolean estPartie = false;
    private boolean estDansSalle = false;
    private boolean salleSelectionne = false;
    
    private boolean objetDisponible;
    private boolean evenementUtiliser;
    private boolean ennemiVaincu;
    
    private Ennemi ennemiActuelle = new Ennemi();
    private SalleEnnemi salleEnnemi;
    
    private int nbSalleActuelle;
    
    private int nbSalle = -1;

    public Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
    	
    	GameManager gm = new GameManager();
    	
    	gm.initialisationArme();
    	gm.initialisationBouclier();
    	gm.initialisationSort();
    	gm.initialisationPotion();
    	gm.initialisationEnnemi();
    	gm.initialisationSalleVide();
    	gm.initialisationSalleEnnemi();
    	gm.initialisationSalleObjet();
    	gm.initialisationSalleEvenement();
    	
        do
        {
            try {
    			gm.menu();
    		} catch (Exception e) {
    			 System.out.println(e);
    		}
        }
        while(!hasReqExit);
    }

    void initialisationArme()
    {
        ListeArme.ajouterArme("Epée Rouillé", 10);
        ListeArme.ajouterArme("Dague Rouillé", 7);
        ListeArme.ajouterArme("Bâton", 5);
        ListeArme.ajouterArme("Epée en fer", 12);
        ListeArme.ajouterArme("Epée pourfendeuse", 16);
        ListeArme.ajouterArme("Excalibur", 80);
    }

    void initialisationBouclier()
    {
        ListeBouclier.ajouterBouclier("Bouclier Rouillé", 3);
        ListeBouclier.ajouterBouclier("Bouclier en bois", 2);
        ListeBouclier.ajouterBouclier("Bouclier Tonneau", 1);
        ListeBouclier.ajouterBouclier("Bouclier en fer", 4);
        ListeBouclier.ajouterBouclier("Bouclier purificateur", 5);
        ListeBouclier.ajouterBouclier("Bouclier d'égide", 8);
    }

    void initialisationSort()
    {
        ListeSort.ajouterSort("Boule De Feu", 15);
        ListeSort.ajouterSort("Stalactite", 12);
        ListeSort.ajouterSort("Prout", 8);
        ListeSort.ajouterSort("Lame Spectrale", 16);
        ListeSort.ajouterSort("Rayon Saint", 24);
        ListeSort.ajouterSort("Extermination", 100);
    }

    void initialisationPotion()
    {
        ListePotion.ajouterPotion("Potion de soin", 10, false);
        ListePotion.ajouterPotion("Potion de degat", 20, true);
        ListePotion.ajouterPotion("Potion de soin ultime", 20, false);
        ListePotion.ajouterPotion("Potion d'explosion", 30, true);
        ListePotion.ajouterPotion("Potion d'anihilation", 150, true);
    }
    
    void initialisationEnnemi()
    {
    	ListeEnnemi.ajouterEnnemi("Diablotin", 12, 7, "/img/Diablotin.png");
    	ListeEnnemi.ajouterEnnemi("Succube", 18, 4, "/img/Succube.png");
    	ListeEnnemi.ajouterEnnemi("Zombie", 32, 2, "/img/Zombie.jpg");
    	ListeEnnemi.ajouterEnnemi("Esprit", 14, 6, "/img/Esprit.png");
    	ListeEnnemi.ajouterBoss("Tyrannoeil", 180, 10, "/img/Tyrannoeil.png");
    }
    
    void initialisationSalleVide()
    {
    	SalleVide.ajouterSalleVide("Cette salle est vide");
    }
    
    void initialisationSalleEnnemi()
    {
    	SalleEnnemi.ajouterSalleEnnemi("Attention ! Un diablotin vous attaque !", ListeEnnemi.listeEnnemi.get(0));
    	SalleEnnemi.ajouterSalleEnnemi("Ne vous faites pas avoir par ses charmes une succube se trouve devant vous !", ListeEnnemi.listeEnnemi.get(1));
    	SalleEnnemi.ajouterSalleEnnemi("Depuis quand un zombie peut tenir une épée attention !", ListeEnnemi.listeEnnemi.get(2));
    	SalleEnnemi.ajouterSalleEnnemi("C'est un des nombreux esprit qui hante ce donjon il est dangeureux !", ListeEnnemi.listeEnnemi.get(3));
    	SalleEnnemi.ajouterSalleBoss("Quelle Catastrophe un Tyrannoeil ! Fuyez !", ListeEnnemi.listeBoss.get(0));
    }
    
    void initialisationSalleObjet()
    {
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une épée rouillé ! (10)", ListeArme.listeArme.get(0));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une dague rouillé ! (7)", ListeArme.listeArme.get(1));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé un bâton ! (5)", ListeArme.listeArme.get(2));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une épée en fer ! (12)", ListeArme.listeArme.get(3));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une épée pourfendeuse ! (16)", ListeArme.listeArme.get(4));
    	SalleObjet.ajouterSalleObjet("Incroyable vous avez trouvé Excalibur ! (80)", ListeArme.listeArme.get(5));
    	
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé un bouclier rouillé ! (3)", ListeBouclier.listeBouclier.get(0));
     	SalleObjet.ajouterSalleObjet("Vous avez trouvé un bouclier en bois ! (2)", ListeBouclier.listeBouclier.get(1));
     	SalleObjet.ajouterSalleObjet("Vous avez trouvé un bouclier tonneau ! (1)", ListeBouclier.listeBouclier.get(2));
     	SalleObjet.ajouterSalleObjet("Vous avez trouvé un bouclier en fer ! (4)", ListeBouclier.listeBouclier.get(3));
     	SalleObjet.ajouterSalleObjet("Vous avez trouvé un bouclier purificateur ! (5)", ListeBouclier.listeBouclier.get(4));
     	SalleObjet.ajouterSalleObjet("Incroyable vous avez trouvé le Bouclier D'Egide ! (6)", ListeBouclier.listeBouclier.get(5));
     	
       	SalleObjet.ajouterSalleObjet("Vous avez trouvé un sort boule de feu ! (15)", ListeSort.listeSort.get(0));
       	SalleObjet.ajouterSalleObjet("Vous avez trouvé un sort stalactite ! (12)", ListeSort.listeSort.get(1));
       	SalleObjet.ajouterSalleObjet("Vous avez trouvé un sort prout ! (8)", ListeSort.listeSort.get(2));
       	SalleObjet.ajouterSalleObjet("Vous avez trouvé un sort lame spectrale ! (16)", ListeSort.listeSort.get(3));
       	SalleObjet.ajouterSalleObjet("Vous avez trouvé un sort rayon saint ! (24)", ListeSort.listeSort.get(4));
       	SalleObjet.ajouterSalleObjet("Incroyable vous avez trouvé le sort Extermination ! (100)", ListeSort.listeSort.get(5));
       	
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une potion de soin ! (10)", ListePotion.listePotion.get(0));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une potion de degat ! (20)", ListePotion.listePotion.get(1));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une potion de soin ultime ! (20)", ListePotion.listePotion.get(2));
    	SalleObjet.ajouterSalleObjet("Vous avez trouvé une potion d'explosion ! (30)", ListePotion.listePotion.get(3));
    	SalleObjet.ajouterSalleObjet("Incroyable vous avez trouvé une potion d'anihilation ! (150)", ListePotion.listePotion.get(4));
    }
    
    void initialisationSalleEvenement()
    {
    	SalleEvenement.ajouterSalleEvenement("Super ! une potion de vitalité vous pouvez aumenter de 4 points votre hp max", false, false, true, 4);
    	SalleEvenement.ajouterSalleEvenement("Super ! une potion d' icare vous pouvez aumenter de 8 points votre hp max", false, false, true, 8);
    	SalleEvenement.ajouterSalleEvenement("Une fontaire de soin vous pouvez récupéré 8 points de vie", true, false, false, 10);
    	SalleEvenement.ajouterSalleEvenement("Une fontaire de soin féérique vous pouvez récupéré 20 points de vie", true, false, false, 20);
    	SalleEvenement.ajouterSalleEvenement("Oh non un piège de pointe vous perdez 3 points de vie", false, true, false, 3);
    	SalleEvenement.ajouterSalleEvenement("Oh non un piège des haches tombes sur vous ! Vous perdez 6 points de vie", false, true, false, 6);
    }

    void menu () throws Exception
    {
    	do
    	{        	
    		System.out.println("Choisissez NewGame ou Quittez (vous pouvez quittez a tout moment), vous pourrez utiliser Menu a tout moment pour revenir au menu");

            String value = sc.nextLine();

            if (value.startsWith("NewGame"))
            {
                do
                {
                    try {
                        choixClasse();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estChoixClasse);
            }
            else if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
            	throw new Exception("Entrée invalide");
            }
    	}while(!hasReqExit);     
    }

    void avantPartie() throws Exception
    {
    	do
    	{
    		estAvantPartie = true;
 	        System.out.println("");
	        System.out.println("Voulez vous ajouter un autre personnage ? - Ajouter");
	        System.out.println("Selectionner votre personnage comme suit si vous voulez commencer la partie ? - (Commencer 1)");
	        System.out.println("Pour montrer la liste de vos personnage taper Liste");
	        System.out.println("Pour affichier les infos, supprimer ou modifier le nom de votre personnage");
	        System.out.println("Utiliser respectivement Info, Supprimer, Modifier suivie du numéro de votre personnage exemple(Info 1)");
	      
	        String value = sc.nextLine();
	        
	        if (value.startsWith("Ajouter"))
	        {
                do
                {
                    try {
                        choixClasse();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estChoixClasse);
	        }
	        else if (value.startsWith("Commencer"))
	        {
	          	for (int i = 0; i < listePersonnage.size(); i++)
	          	{
	          		if (value.startsWith("Commencer " + (i + 1)))
	          		{
	    	        	jeu(listePersonnage.get(i));
	        			return;
	          		}
	          	}
            	throw new Exception("Entrée invalide");
	        }
	        else if (value.startsWith("Liste"))
	        {
    			if (listePersonnage.size() == 0)
    			{
		            System.out.println("Vous n'avez aucun personnage pour le moment");  
    			}
    			else
    			{
    	          	for (int i = 0; i < listePersonnage.size(); i++)
    	          	{
    		            System.out.println((i + 1) + ": " + listePersonnage.get(i).getNom());   
    	          	}
    			}
	        }
	        else if (value.startsWith("Quittez"))
	        {
	            quittez();
	        }
	        else
	        {
	          	for (int i = 0; i < listePersonnage.size(); i++)
	          	{
	          		if (value.startsWith("Info " + (i + 1)))
	        		{
	          			listePersonnage.get(i).montrerStat();
	        			return;
	        		}
	        		else if (value.startsWith("Supprimer " + (i + 1)))
	        		{
	        			listePersonnage.get(i);
	        			return;
	        		}
	        		else if (value.startsWith("Modifier " + (i + 1)))
	        		{
	                    System.out.println("");
	                    System.out.println("Choisissez un autre nom? ");
	                    String name = sc.nextLine();                  
	                    listePersonnage.get(i).setNom(name);
	        			return;
	        		}
	          	}
            	throw new Exception("Entrée invalide");
	        }
    	}while(!hasReqExit && estAvantPartie);     
    }

    void choixClasse () throws Exception
    {
    	do
    	{
        	estChoixClasse = true;
            System.out.println("");
            System.out.println("Choisissez votre classe Guerrier ou Magicien ?");

            String value = sc.nextLine();

            if (value.startsWith("Guerrier"))
            {
                creationGuerrier();
            }
            else if (value.startsWith("Magicien"))
            {
                creationMagicien();
            }
            else if (value.startsWith("Menu"))
            {
            	estChoixClasse = false;
                return;
            }
            else if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
            	throw new Exception("Entrée invalide");
            }
    	}while(!hasReqExit);    
    }

    void creationGuerrier()
    {   	
    	do
    	{
            Guerrier guerrier = new Guerrier();
            guerrier.initialisation(guerrier);

            System.out.println("");
            System.out.println("Vous avez choisi guerrier. Quelles et votre nom ? ");
            String value = sc.nextLine();

            if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
                guerrier.setNom(value);
                System.out.println("");
                System.out.println("Initialisation de vos statistique et objet de départ :");
                
                guerrier.montrerStat();

                listePersonnage.add(guerrier);

                do
                {
                    try {
                        avantPartie();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
    	}while(!hasReqExit);    
    }

    void creationMagicien()
    {
    	do
    	{
            Magicien magicien = new Magicien();
            magicien.initialisation(magicien);

            System.out.println("");
            System.out.println("Vous avez choisi un magicien. Quelles et votre nom ? ");
            String value = sc.nextLine();

            if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
                magicien.setNom(value);

                System.out.println("");
                System.out.println("Initialisation de vos statistique et objet de départ :");
                
                magicien.montrerStat();

                listePersonnage.add(magicien);

                do
                {
                    try {
                        avantPartie();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
    	}while(!hasReqExit);    
    }

    void quittez ()
    {
        System.out.println("");
        System.out.println("Vous avez quitter le jeu !");
        sc.close();
        hasReqExit = true;
        estChoixClasse = false;
        estAvantPartie = false;
        estPartie = false;
        estDansSalle = false;
        salleSelectionne = false;
    }
    
    void jeu(Personnage perso)
    {
    	estPartie = true;
		nbSalle = 0;
		
    	do
    	{
        	nbSalle++ ;
        	
        	if (nbSalle == 0)
        	{
        		estDansSalle = true;
        		do
        		{
              		 try {
                   		commencement();
               		} catch (Exception e) {
               			 System.out.println(e);
               		}
        		} while (estDansSalle);
        	}
        	else if (nbSalle == 64)
        	{
        		estDansSalle = true;
               	ennemiVaincu = false;
        		do
        		{
              		 try {
                 		salleBoss(perso);
               		} catch (Exception e) {
               			 System.out.println(e);
               		}
        		} while (estDansSalle || !ennemiVaincu);
        	}
        	else  
        	{
                System.out.println("");
                System.out.println("Vous arrivez à la salle " + nbSalle);
                
                int max = 100;
                int min = 1;
                int range = (max - min) + 1;
                int nombre = (int)(Math.random() * range) + min;
                
        		estDansSalle = true;   
        		
                if (nombre <= 25)
                {
            		do
            		{
                  		 try {
                         	salleVide(perso);
                   		} catch (Exception e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);
                }
                else if (nombre <= 50)
                {
                	objetDisponible = true;
            		do
            		{
                  		 try {
                         	salleObjet(perso);
                   		} catch (Exception e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);
                }
                else if (nombre <= 75)
                {
                   	evenementUtiliser = false;
            		do
            		{
                  		 try {
                         	salleEvenement(perso);
                   		} catch (Exception e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);
                }
                else
                {
                	ennemiVaincu = false;
            		do
            		{
                  		 try {
                         	salleEnnemi(perso);
                   		} catch (Exception e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);          
                }
        	}
    	} while (estPartie);

    }
    
    void commencement() throws Exception
    {
        System.out.println("");
        System.out.println("Vous arrivez devant l'entrée d'un donjon !");
        System.out.println("Entrer - Entrer dans le donjon");
        System.out.println("Fuir - Fuir lâchement le donjon");
        
        String value = sc.nextLine();
        
        if (value.startsWith("Entrer"))
        {
            estDansSalle = false;
        	return;
        }
        else if (value.startsWith("Fuir"))
        {
            estDansSalle = false;
        	estPartie = false;
        	return;
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
        	return;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void salleVide(Personnage perso) throws Exception
    {	
    	if (!salleSelectionne)
    	{
            int max = SalleVide.listeSalleVide.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            salleSelectionne = true;
    	}
             
        System.out.println("");
        System.out.println(SalleVide.listeSalleVide.get(nbSalleActuelle).getMessageSalle());
        System.out.println("");
        if (perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
            System.out.println("Potion - Utiliser votre potion");    
        }
        System.out.println("Avancer - Avancer à la salle suivante");
        System.out.println("Info - Voir les Statistiques");
        System.out.println("Fuir - Fuir lâchement le donjon");
        String value = sc.nextLine();
        
        if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
            estDansSalle = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Fuir"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        	return;
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void salleEnnemi(Personnage perso) throws Exception
    {
    	if (!salleSelectionne)
    	{
            int max = SalleEnnemi.listeSalleEnnemi.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            
            salleEnnemi = SalleEnnemi.listeSalleEnnemi.get(nbSalleActuelle);
            Ennemi ennemiPrefab = salleEnnemi.getEnnemi();
            
            ennemiActuelle.setNom(ennemiPrefab.getNom());
            ennemiActuelle.setHp(ennemiPrefab.getHp());
            ennemiActuelle.setHpMax(ennemiPrefab.getHpMax());
            ennemiActuelle.setDegat(ennemiPrefab.getDegat());
            
            salleSelectionne = true;
    	}
        
        System.out.println("");
        
    	if (!ennemiVaincu)
    	{
            System.out.println("Un Ennemi !");
            System.out.println(salleEnnemi.getMessageSalle());
            System.out.println("");
            System.out.println("Combattre - Affronter l'ennemi");
    	}
    	else
    	{
            System.out.println("L'ennemi est vaincu vous pouvez avancer");
            System.out.println("");
    	}
    	
        if (perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
            System.out.println("Potion - Utiliser votre potion");    
        }
        System.out.println("Avancer - Avancer à la salle suivante");
        System.out.println("Info - Voir les Statistiques");
        System.out.println("Fuir - Fuir lâchement le donjon");
        String value = sc.nextLine();
     
        if (value.startsWith("Combattre") && !ennemiVaincu)
        {
        	ennemiVaincu = false;
    		do
    		{
          		 try {
                 	combat(perso, ennemiActuelle);
           		} catch (Exception e) {
           			 System.out.println(e);
           		}
    		} while (!ennemiVaincu);   
        	return;
        }
        else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
        	if (!ennemiVaincu)
        	{
                System.out.println("");
                System.out.println("Impossible un ennemi vous bloque le passage");
        	}
        	else
        	{
                estDansSalle = false;
                salleSelectionne = false;
        	}
        	return;
        }
        else if (value.startsWith("Fuir"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        	return;
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void salleObjet(Personnage perso) throws Exception
    {   
    	if (!salleSelectionne)
    	{
            int max = SalleObjet.listeSalleObjet.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            salleSelectionne = true;
    	}
     
        SalleObjet salleObjet = SalleObjet.listeSalleObjet.get(nbSalleActuelle);
        Equipement equipement = salleObjet.getEquipement();
		    
        System.out.println("");
        System.out.println(salleObjet.getMessageSalle());
        System.out.println("");
        
        if (perso.canUseEquipement(equipement) && objetDisponible)
        {
            System.out.println("Equiper - Equiper L'équipement");
        }
        else if (perso.canUseEquipement(equipement) && !objetDisponible)
        {
            System.out.println("Vous avez déjà équiper cette objet");
        }
        else 
        {
            System.out.println("Vous ne pouvez pas vous équiper de cette objet");
        }    
        
        if (perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
            System.out.println("Potion - Utiliser votre potion");    
        }
        System.out.println("Avancer - Avancer à la salle suivante");
        System.out.println("Info - Voir les Statistiques");
        System.out.println("Fuir - Fuir lâchement le donjon");
        String value = sc.nextLine();
        
        if (value.startsWith("Equiper") && perso.canUseEquipement(equipement) && objetDisponible)
        {
        	objetDisponible = false;
        	perso.useEquipement(equipement);
        	return;
        }
        else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
            estDansSalle = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Fuir"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        	return;
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void salleEvenement(Personnage perso) throws Exception
    {
    	if (!salleSelectionne)
    	{
            int max = SalleEvenement.listeSalleEvenement.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            salleSelectionne = true;
    	}
    	
        SalleEvenement salleEvenement = SalleEvenement.listeSalleEvenement.get(nbSalleActuelle);
		    
        System.out.println("");
        System.out.println(salleEvenement.getMessageSalle());
        System.out.println("");
        
        if (salleEvenement.getSalleDegat() && !evenementUtiliser)
        {     	
        	perso.subirDegatBrut(salleEvenement.getPuissance());
        	evenementUtiliser = true;
        }
        else if (salleEvenement.getSalleSoin() && !evenementUtiliser)
        {
            System.out.println("Utiliser - Boire dans la fontaine");
        }
        else if (salleEvenement.getSalleBonusVie() && !evenementUtiliser)
        {
            System.out.println("Utiliser - Boire la fiole");
        }
        
        if (perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
            System.out.println("Potion - Utiliser votre potion");    
        }
        System.out.println("Avancer - Avancer à la salle suivante");
        System.out.println("Info - Voir les Statistiques");
        System.out.println("Fuir - Fuir lâchement le donjon");
        String value = sc.nextLine();
    	
        if (value.startsWith("Utiliser") && (salleEvenement.getSalleSoin() || salleEvenement.getSalleBonusVie()) && !evenementUtiliser)
        {
        	if (salleEvenement.getSalleSoin())
        	{
        		perso.soin(salleEvenement.getPuissance());
        	}
        	else
        	{
        		perso.augmentationHpMax(salleEvenement.getPuissance());
        	}
        	evenementUtiliser = true;
        	return;
        }
        else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
            estDansSalle = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Fuir"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        	return;
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void salleBoss(Personnage perso) throws Exception
    {
    	if (!salleSelectionne)
    	{
            int max = SalleEnnemi.listeSalleBoss.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            
            salleEnnemi = SalleEnnemi.listeSalleBoss.get(nbSalleActuelle);
            Ennemi ennemiPrefab = salleEnnemi.getEnnemi();
            
            ennemiActuelle.setNom(ennemiPrefab.getNom());
            ennemiActuelle.setHp(ennemiPrefab.getHp());
            ennemiActuelle.setHpMax(ennemiPrefab.getHpMax());
            ennemiActuelle.setDegat(ennemiPrefab.getDegat());
            
            salleSelectionne = true;
    	}
        
        System.out.println("");
        
    	if (!ennemiVaincu)
    	{
            System.out.println("Un Ennemi !");
            System.out.println(salleEnnemi.getMessageSalle());
            System.out.println("");
            System.out.println("Combattre - Affronter l'ennemi");
    	}
    	else
    	{
            System.out.println("L'ennemi est vaincu vous pouvez avancer");
            System.out.println("");
    	}
    	
        if (perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
            System.out.println("Potion - Utiliser votre potion");    
        }
        System.out.println("Avancer - Avancer à la salle suivante");
        System.out.println("Info - Voir les Statistiques");
        System.out.println("Fuir - Fuir lâchement le donjon");
        String value = sc.nextLine();
     
        if (value.startsWith("Combattre") && !ennemiVaincu)
        {
        	ennemiVaincu = false;
    		do
    		{
          		 try {
                 	combat(perso, ennemiActuelle);
           		} catch (Exception e) {
           			 System.out.println(e);
           		}
    		} while (!ennemiVaincu); 
    
            System.out.println("");
            System.out.println("Félicitation vous avez vaincu le donjon !");
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
        	if (!ennemiVaincu)
        	{
                System.out.println("");
                System.out.println("Impossible un ennemi vous bloque le passage");
        	}
        	else
        	{
                estDansSalle = false;
                salleSelectionne = false;
        	}
        }
        else if (value.startsWith("Fuir"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void combat(Personnage perso, Ennemi ennemi) throws Exception
    {
        System.out.println("");
        System.out.println("Attaquer - Attaquer l'ennemi");     
        if (perso instanceof Magicien && ((Magicien) perso).getPotion() != null)
        {
            System.out.println("Potion - Utiliser votre potion");    
        }
        System.out.println("Avancer - Avancer à la salle suivante");
        System.out.println("Info - Voir les Statistiques");
        System.out.println("Ennemi - Voir les Statistiques de l'ennemi");
        String value = sc.nextLine();

        if (value.startsWith("Attaquer"))
        {
        	infligerDegat(perso, ennemi, false);
        }
        else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null)
        {
        	infligerDegat(perso, ennemi, true);
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        	return;
        }
        else if (value.startsWith("Ennemi"))
        {
        	ennemi.montrerStat();
        	return;
        }
        else if (value.startsWith("Quittez"))
        {
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	return;
        }
        else
        {
        	throw new Exception("Entrée invalide");
        }
    }
    
    void infligerDegat(Personnage perso, Ennemi ennemi, boolean utilisationSecondaire)
    {
        if (utilisationSecondaire)
        {
        	if (perso.utiliserSecondaire(perso, ennemi))
        	{
                System.out.println("L'ennemi n'a pas survécu Bravo !");
            	ennemiVaincu = true;
        	}
        	else
        	{
                System.out.println("Il reste " + ennemi.getHp() + " hp à l'ennemi");
                System.out.println("L'ennemi vous attaques !");
                
        		if (perso.subirDegat(ennemi.getDegat()))
        		{
                    System.out.println("Vous n'avez pas survécu !");
                	ennemiVaincu = true;
                    estDansSalle = false;
                	estPartie = false;
                    salleSelectionne = false;
        		}
        	}
        }
        else
        {
            System.out.println("Vous infligez " + perso.getDegat() + " de dégat à l'ennemi !");
            
            if (ennemi.subirDegat(perso.getDegat()))
        	{
                System.out.println("L'ennemi n'a pas survécu Bravo !");
            	ennemiVaincu = true;
        	}
        	else
        	{
                System.out.println("Il reste " + ennemi.getHp() + " hp à l'ennemi");
                System.out.println("L'ennemi vous attaques !");
                
        		if (perso.subirDegat(ennemi.getDegat()))
        		{
                    System.out.println("Vous n'avez pas survécu !");
                	ennemiVaincu = true;
                    estDansSalle = false;
                	estPartie = false;
                    salleSelectionne = false;
        		}
        	}
        }
    }
}