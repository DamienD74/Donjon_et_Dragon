package gameManager;

import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import classe.*;
import donjon.SalleEnnemi;
import donjon.SalleEvenement;
import donjon.SalleObjet;
import donjon.SalleVide;
import ennemi.Ennemi;
import ennemi.ListeEnnemi;
import equipement.Equipement;
import equipement.arme.ListeArme;
import equipement.arme.ListeSort;
import equipement.consommable.ListePotion;
import equipement.protection.ListeBouclier;
import exception.EntreeInvalideException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class GameManager{

    public List<Personnage> listePersonnage = new ArrayList<>();
    private List<SalleVide> listeSalleVide = new ArrayList<>();
    private List<SalleEnnemi> listeSalleEnnemi = new ArrayList<>();
    private List<SalleEnnemi> listeSalleBoss = new ArrayList<>();
    private List<SalleObjet> listeSalleObjet = new ArrayList<>();
    private List<SalleEvenement> listeSalleEvenement = new ArrayList<>();
    private static boolean hasReqExit = false;
    private boolean estNewGame = false;
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
    	
        try {
        	BaseDeDonnee.recupererHeros(gm);                
    	}catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}catch (SQLException e){
    		e.printStackTrace();
        }
        
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
    		} catch (IllegalStateException e) {
    			 hasReqExit = true;
    			 System.out.println(e);
    			 e.printStackTrace();
    			 } catch (EntreeInvalideException e) {
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
        ListeArme.ajouterArme("Epée en Or", 16);
        ListeArme.ajouterArme("Epée en Titane", 21);
        ListeArme.ajouterArme("Epée Enchanté", 27);
        ListeArme.ajouterArme("Epée Suprême", 36);
        ListeArme.ajouterArme("Epée en Diamant", 42);
        ListeArme.ajouterArme("Epée pourfendeuse", 64);
        ListeArme.ajouterArme("Excalibur", 80);
    }

    void initialisationBouclier()
    {
        ListeBouclier.ajouterBouclier("Bouclier Rouillé", 3);
        ListeBouclier.ajouterBouclier("Bouclier en bois", 2);
        ListeBouclier.ajouterBouclier("Bouclier Tonneau", 1);
        ListeBouclier.ajouterBouclier("Bouclier en fer", 4);
        ListeBouclier.ajouterBouclier("Bouclier en Or", 5);
        ListeBouclier.ajouterBouclier("Bouclier en Titane", 6);
        ListeBouclier.ajouterBouclier("Bouclier Suprême", 7);
        ListeBouclier.ajouterBouclier("Bouclier en Diamant", 8);
        ListeBouclier.ajouterBouclier("Bouclier purificateur", 9);
        ListeBouclier.ajouterBouclier("Bouclier d'égide", 10);
    }

    void initialisationSort()
    {
        ListeSort.ajouterSort("Boule De Feu", 15);
        ListeSort.ajouterSort("Stalactite", 12);
        ListeSort.ajouterSort("Prout", 8);
        ListeSort.ajouterSort("Lame Spectrale", 16);
        ListeSort.ajouterSort("Rayon Saint", 24);
        ListeSort.ajouterSort("Explosion", 31);
        ListeSort.ajouterSort("Kill", 38);
        ListeSort.ajouterSort("Glaciation", 46);
        ListeSort.ajouterSort("Abîme", 57);
        ListeSort.ajouterSort("Feu Sacré", 76);
        ListeSort.ajouterSort("Extermination", 100);
    }

    void initialisationPotion()
    {
        ListePotion.ajouterPotion("Potion de soin", 10, false);
        ListePotion.ajouterPotion("Potion de degat", 20, true);
        ListePotion.ajouterPotion("Grande potion de soin", 20, false);
        ListePotion.ajouterPotion("Potion d'explosion", 30, true);
        ListePotion.ajouterPotion("Gigantesque potion de soin", 30, false);
        ListePotion.ajouterPotion("Potion de purification", 70, true);
        ListePotion.ajouterPotion("Potion de soin Ultime", 50, false);
        ListePotion.ajouterPotion("Potion sacré", 110, true);
        ListePotion.ajouterPotion("Potion d'anihilation", 150, true);
    }
    
    void initialisationEnnemi()
    {
    	ListeEnnemi.ajouterEnnemi("Diablotin", 12, 7, "/img/Diablotin.png");
    	ListeEnnemi.ajouterEnnemi("Succube", 18, 4, "/img/Succube.png");
    	ListeEnnemi.ajouterEnnemi("Zombie", 32, 2, "/img/Zombie.jpg");
    	ListeEnnemi.ajouterEnnemi("Esprit", 14, 6, "/img/Esprit.png");
    	ListeEnnemi.ajouterEnnemi("ZombieArmée", 40, 4, "/img/ZombieArmee.png");
    	ListeEnnemi.ajouterEnnemi("Démon Inférieur", 20, 5, "/img/DemonInferieur.jpg");
    	ListeEnnemi.ajouterEnnemi("Vouivre", 28, 8, "/img/Vouivre.png");
    	ListeEnnemi.ajouterEnnemi("Elfe Noir", 22, 10, "/img/ElfeNoir.png");
    	ListeEnnemi.ajouterEnnemi("Démon", 34, 7, "/img/Demon.png");
    	ListeEnnemi.ajouterEnnemi("Blob", 240, 2, "/img/Blob.jpg");
    	ListeEnnemi.ajouterEnnemi("Mangeur d'Esprit", 64, 15, "/img/MangeurDesprit.jpg");
    	ListeEnnemi.ajouterEnnemi("DémonSupérieur", 80, 12, "/img/DemonSuperieur.jpg");
    	ListeEnnemi.ajouterBoss("Géant", 100, 7, "/img/Géant.png");
    	ListeEnnemi.ajouterBoss("Tyrannoeil", 220, 10, "/img/Tyrannoeil.png");
    	ListeEnnemi.ajouterBoss("Tiamat", 540, 22, "/img/UltimeDragon.jpg");
    }
    
    void initialisationSalleVide()
    {
    	SalleVide.ajouterSalleVide("Cette salle est vide");
    }
    
    void initialisationSalleEnnemi()
    {
    	SalleEnnemi.ajouterSalleEnnemi("Attention ! Un diablotin vous attaque !", ListeEnnemi.listeEnnemi.get(0));
    	SalleEnnemi.ajouterSalleEnnemi("Ne vous faites pas avoir par ses charmes une succube se trouve devant vous !", ListeEnnemi.listeEnnemi.get(1));
    	SalleEnnemi.ajouterSalleEnnemi("Un zombie fait attention il va te mordre !", ListeEnnemi.listeEnnemi.get(2));
    	SalleEnnemi.ajouterSalleEnnemi("C'est un des nombreux esprit qui hante ce donjon il est dangeureux !", ListeEnnemi.listeEnnemi.get(3));
    	SalleEnnemi.ajouterSalleEnnemi("Depuis quand un zombie peut tenir une épée attention !", ListeEnnemi.listeEnnemi.get(4));
    	SalleEnnemi.ajouterSalleEnnemi("Un démon inférieur !", ListeEnnemi.listeEnnemi.get(5));
    	SalleEnnemi.ajouterSalleEnnemi("Une vouivre ! Attention a ce petit dragon il est très aggressif", ListeEnnemi.listeEnnemi.get(6));
    	SalleEnnemi.ajouterSalleEnnemi("Un elfe noir ! Il est surement la pour contrôler les vouivres du donjon !", ListeEnnemi.listeEnnemi.get(7));
    	SalleEnnemi.ajouterSalleEnnemi("Un démon il doit y en avoir plein ici !", ListeEnnemi.listeEnnemi.get(8));
    	SalleEnnemi.ajouterSalleEnnemi("C'est un blob ! il n'est pas très dangeureux mais très dure a tuer !", ListeEnnemi.listeEnnemi.get(9));
    	SalleEnnemi.ajouterSalleEnnemi("Un mangeur d'esprit ! attention a ne pas te faire controler l'esprit !", ListeEnnemi.listeEnnemi.get(10));
    	SalleEnnemi.ajouterSalleEnnemi("Attention c'est un démon supérieur !", ListeEnnemi.listeEnnemi.get(11));
    	SalleEnnemi.ajouterSalleBoss("Un géant ici ? Comment est-il rentré ?", ListeEnnemi.listeBoss.get(0));
    	SalleEnnemi.ajouterSalleBoss("Quelle Catastrophe un Tyrannoeil ! Fuyez !", ListeEnnemi.listeBoss.get(1));
    	SalleEnnemi.ajouterSalleBoss("C'est le Tiamat ! Sa puissance est démesuré ! Vous n'avez aucune chance !", ListeEnnemi.listeBoss.get(2));
    }
    
    void initialisationSalleObjet()
    {
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée rouillé ! (10)", ListeArme.listeArme.get(0));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une dague rouillé ! (7)", ListeArme.listeArme.get(1));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé un bâton ! (5)", ListeArme.listeArme.get(2));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée en fer ! (12)", ListeArme.listeArme.get(3));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée en or ! (16)", ListeArme.listeArme.get(4));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée en titane ! (21)", ListeArme.listeArme.get(5));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée enchanté ! (27)", ListeArme.listeArme.get(6));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée suprême ! (36)", ListeArme.listeArme.get(7));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée en diamant ! (42)", ListeArme.listeArme.get(8));
    	SalleObjet.ajouterSalleArme("Vous avez trouvé une épée pourfendeuse ! (64)", ListeArme.listeArme.get(9));
    	SalleObjet.ajouterSalleArme("Incroyable vous avez trouvé Excalibur ! (80)", ListeArme.listeArme.get(10));
    	
    	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier rouillé ! (3)", ListeBouclier.listeBouclier.get(0));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier en bois ! (2)", ListeBouclier.listeBouclier.get(1));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier tonneau ! (1)", ListeBouclier.listeBouclier.get(2));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier en fer ! (4)", ListeBouclier.listeBouclier.get(3));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier en or ! (5)", ListeBouclier.listeBouclier.get(4));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier en titane ! (6)", ListeBouclier.listeBouclier.get(5));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier duprême ! (7)", ListeBouclier.listeBouclier.get(6));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier en diamant ! (8)", ListeBouclier.listeBouclier.get(7));
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier purificateur ! (9)", ListeBouclier.listeBouclier.get(8));
     	SalleObjet.ajouterSalleBouclier("Incroyable vous avez trouvé le Bouclier D'Egide ! (10)", ListeBouclier.listeBouclier.get(9));
     	
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort boule de feu ! (15)", ListeSort.listeSort.get(0));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort stalactite ! (12)", ListeSort.listeSort.get(1));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort prout ! (8)", ListeSort.listeSort.get(2));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort lame spectrale ! (16)", ListeSort.listeSort.get(3));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort rayon saint ! (24)", ListeSort.listeSort.get(4));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort explosion ! (31)", ListeSort.listeSort.get(5));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort kill ! (38)", ListeSort.listeSort.get(6));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort glaciation ! (46)", ListeSort.listeSort.get(7));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort abîme ! (57)", ListeSort.listeSort.get(8));
       	SalleObjet.ajouterSalleSort("Vous avez trouvé un sort feu sacré ! (76)", ListeSort.listeSort.get(9));
       	SalleObjet.ajouterSalleSort("Incroyable vous avez trouvé le sort Extermination ! (100)", ListeSort.listeSort.get(10));
       	
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une potion de soin ! (10)", ListePotion.listePotion.get(0));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une potion de degat ! (20)", ListePotion.listePotion.get(1));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une grande potion de soin ! (20)", ListePotion.listePotion.get(2));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une potion d'explosion ! (30)", ListePotion.listePotion.get(3));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une gigantesque potion de soin ! (30)", ListePotion.listePotion.get(4));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une potion de purification ! (70)", ListePotion.listePotion.get(5));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une potion de soin ultime ! (50)", ListePotion.listePotion.get(6));
    	SalleObjet.ajouterSallePotion("Vous avez trouvé une potion sacré ! (110)", ListePotion.listePotion.get(7));
    	SalleObjet.ajouterSallePotion("Incroyable vous avez trouvé une potion d'anihilation ! (150)", ListePotion.listePotion.get(8));
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

    void menu () throws IllegalStateException, EntreeInvalideException
    {
    	do
    	{        	
    		System.out.println("NewGame - Commencer une nouvelle partie");
    		if (listePersonnage.size() > 0)
    		{
        		System.out.println("Continuer - Continuer la partie en cours");
    		}
    		System.out.println("Quittez - Quittez le jeu. (Utilisable en cours de partie pour quitter le jeu");
    		System.out.println("Menu - Utilisable en cours de partie pour revenir au menu");
            String value = sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }
            
            if (value.startsWith("NewGame"))
            {
            	if (listePersonnage.size() > 0)
            	{
            		estNewGame = true;
                    do
                    {
                        try {
                        	validationNouvellePartie();
                		} catch (EntreeInvalideException e) {
               			     System.out.println(e);
                		}
                    }
                    while(estNewGame);
            	}
            	else
            	{
                    do
                    {
                        try {
                            choixClasse();
                		} catch (EntreeInvalideException e) {
               			     System.out.println(e);
                		}
                    }
                    while(estChoixClasse);
            	}
            }
            else if (value.startsWith("Continuer") && listePersonnage.size() > 0) 
            {
                do
                {
                    try {
                        avantPartie();
            		} catch (EntreeInvalideException e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
            else if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
            	throw new EntreeInvalideException("Entrée invalide");
            }
    	}while(!hasReqExit);     
    }

    void avantPartie() throws IllegalStateException, EntreeInvalideException
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
	        
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }
	        
	        if (value.startsWith("Ajouter"))
	        {
                do
                {
                    try {
                        choixClasse();            
            		} 
                    catch (EntreeInvalideException e){
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
	    	        	initialisationDonjon();
	    	        	jeu(listePersonnage.get(i));
	          			return;
	          		}
	          	}
            	throw new EntreeInvalideException("Entrée invalide");
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
	                    try {
	                        BaseDeDonnee.deleteHero(listePersonnage.get(i));                 
	                	}catch (ClassNotFoundException e){
	                		e.printStackTrace();
	                	}catch (SQLException e){
	                		e.printStackTrace();
	                    }
	        			listePersonnage.remove(i);
	          			return;
	        		}
	        		else if (value.startsWith("Modifier " + (i + 1)))
	        		{
	                    System.out.println("");
	                    System.out.println("Choisissez un autre nom? ");
	                    String name = sc.nextLine();                  
	                    listePersonnage.get(i).setNom(name);
	                
	                    try {
	                    	BaseDeDonnee.updateHero(listePersonnage.get(i));;                
	                	}catch (ClassNotFoundException e){
	                		e.printStackTrace();
	                	}catch (SQLException e){
	                		e.printStackTrace();
	                    }
	          			return;
	        		}
	          	}
            	throw new EntreeInvalideException("Entrée invalide");
	        }
    	}while(!hasReqExit && estAvantPartie);     
    }
    
    void initialisationDonjon()
    {
    	JSONObject jsonObject = null;
        try {     
            jsonObject =  (JSONObject) new JSONTokener(new FileReader("src/fichierJson/donjonFacile.json")).nextValue();                               
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        JSONArray salleVide = (JSONArray) jsonObject.get("salleVide");
        
        for (int i = 0; i < salleVide.length(); i++)
        {
            listeSalleVide.add(SalleVide.listeSalleVide.get(salleVide.optInt(i)));
        }  
        
        JSONArray salleEnnemi = (JSONArray) jsonObject.get("salleEnnemi");
        
        for (int i = 0; i < salleEnnemi.length(); i++)
        {
            listeSalleEnnemi.add(SalleEnnemi.listeSalleEnnemi.get(salleEnnemi.optInt(i)));
        }  
        
        JSONArray salleBoss = (JSONArray) jsonObject.get("boss");
        
        for (int i = 0; i < salleBoss.length(); i++)
        {
            listeSalleBoss.add(SalleEnnemi.listeSalleBoss.get(salleBoss.optInt(i)));
        }  
        
        JSONArray salleArme = (JSONArray) jsonObject.get("salleArme");
        
        for (int i = 0; i < salleArme.length(); i++)
        {
            listeSalleObjet.add(SalleObjet.listeSalleArme.get(salleArme.optInt(i)));
        }  
        
        JSONArray salleBouclier = (JSONArray) jsonObject.get("salleBouclier");
        
        for (int i = 0; i < salleBouclier.length(); i++)
        {
            listeSalleObjet.add(SalleObjet.listeSalleBouclier.get(salleBouclier.optInt(i)));
        } 
        
        JSONArray salleSort = (JSONArray) jsonObject.get("salleSort");
        
        for (int i = 0; i < salleSort.length(); i++)
        {
            listeSalleObjet.add(SalleObjet.listeSalleSort.get(salleSort.optInt(i)));
        } 
        
        JSONArray sallePotion = (JSONArray) jsonObject.get("sallePotion");
        
        for (int i = 0; i < sallePotion.length(); i++)
        {
            listeSalleObjet.add(SalleObjet.listeSallePotion.get(sallePotion.optInt(i)));
        } 
        
        JSONArray salleEvenement = (JSONArray) jsonObject.get("salleEvenement");
        
        for (int i = 0; i < salleEvenement.length(); i++)
        {
            listeSalleEvenement.add(SalleEvenement.listeSalleEvenement.get(salleEnnemi.optInt(i)));
        }  
    }
    
    void validationNouvellePartie() throws EntreeInvalideException
    {
        System.out.println("");
        System.out.println("Etes vous sur de vouloir recommencer une partie ?");
        System.out.println("Toute votre progression sera supprimer !");
        System.out.println("Oui - Recommencer une nouvelle partie");
        System.out.println("Non - J'ai changé d'avis");
        
        String value = sc.nextLine();  
        
        if (value.startsWith("Oui"))
        {
            try {
            	BaseDeDonnee.deleteAllHeros();                
        	}catch (ClassNotFoundException e){
        		e.printStackTrace();
        	}catch (SQLException e){
        		e.printStackTrace();
            }
            
            listePersonnage = new ArrayList<>();
    		estNewGame = false;
        }
        else if (value.startsWith("Non"))
        {
    		estNewGame = false;
        	return;
        }
        else if (value.startsWith("Menu"))
        {
    		estNewGame = false;
            return;
        }
        else if (value.startsWith("Quittez"))
        {
            quittez();
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }

    void choixClasse () throws IllegalStateException, EntreeInvalideException
    {
    	do
    	{
        	estChoixClasse = true;
            System.out.println("");
            System.out.println("Choisissez votre classe Guerrier ou Magicien ?");

            String value = sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }           

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
            	throw new EntreeInvalideException("Entrée invalide");
            }
    	}while(!hasReqExit);    
    }

    void creationGuerrier() throws IllegalStateException
    {   	
    	do
    	{
            System.out.println("");
            System.out.println("Vous avez choisi guerrier. Quelles et votre nom ? ");
            String value = sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }

            if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
                Guerrier guerrier = Guerrier.initialisation(value);
         
                try {
                    BaseDeDonnee.creerHero(guerrier);                 
            	}catch (ClassNotFoundException e){
            		e.printStackTrace();
            	}catch (SQLException e){
            		e.printStackTrace();
                }

                System.out.println("");
                System.out.println("Initialisation de vos statistique et objet de départ :");
                
                guerrier.montrerStat();

                listePersonnage.add(guerrier);

                do
                {
                    try {
                        avantPartie();
            		} catch (EntreeInvalideException e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
    	}while(!hasReqExit);    
    }

    void creationMagicien() throws IllegalStateException
    {
    	do
    	{
            System.out.println("");
            System.out.println("Vous avez choisi un magicien. Quelles et votre nom ? ");
            String value = sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }

            if (value.startsWith("Quittez"))
            {
                quittez();
            }
            else
            {
                Magicien magicien = Magicien.initialisation(value);
                
                try {
                    BaseDeDonnee.creerHero(magicien);                 
            	}catch (ClassNotFoundException e){
            		e.printStackTrace();
            	}catch (SQLException e){
            		e.printStackTrace();
                }

                System.out.println("");
                System.out.println("Initialisation de vos statistique et objet de départ :");
                
                magicien.montrerStat();

                listePersonnage.add(magicien);

                do
                {
                    try {
                        avantPartie();
            		} catch (EntreeInvalideException e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
    	}while(!hasReqExit);    
    }
    
    void sauvegarde(Personnage perso)
    {
        try {
        	BaseDeDonnee.updateHero(perso);;                
    	}catch (ClassNotFoundException e){
    		e.printStackTrace();
    	}catch (SQLException e){
    		e.printStackTrace();
        }
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
		estNewGame = false;
    }
    
    void jeu(Personnage perso) throws IllegalStateException
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
               		} catch (EntreeInvalideException e) {
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
               		} catch (EntreeInvalideException e) {
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
                   		} catch (EntreeInvalideException e) {
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
                   		} catch (EntreeInvalideException e) {
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
                   		} catch (EntreeInvalideException e) {
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
                   		} catch (EntreeInvalideException e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);          
                }
        	}
    	} while (estPartie);

    }
    
    void commencement() throws IllegalStateException, EntreeInvalideException
    {
        System.out.println("");
        System.out.println("Vous arrivez devant l'entrée d'un donjon !");
        System.out.println("Entrer - Entrer dans le donjon");
        System.out.println("Fuir - Fuir lâchement le donjon");
        
        String value = sc.nextLine();
        
        if (value == null)
        {
        	throw new IllegalStateException("Sc fermée");
        }
        
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
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void salleVide(Personnage perso) throws IllegalStateException ,EntreeInvalideException
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
        
        if (value == null)
        {
        	throw new IllegalStateException("Sc fermée");
        }
        
        if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
            estDansSalle = false;
            salleSelectionne = false;
        }
        else if (value.startsWith("Fuir"))
        {
        	sauvegarde(perso);
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
        	sauvegarde(perso);
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void salleEnnemi(Personnage perso) throws IllegalStateException, EntreeInvalideException
    {
    	if (!salleSelectionne)
    	{
            int max = listeSalleEnnemi.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            
            salleEnnemi = listeSalleEnnemi.get(nbSalleActuelle);
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
        
        if (value == null)
        {
        	throw new IllegalStateException("Sc fermée");
        }
     
        if (value.startsWith("Combattre") && !ennemiVaincu)
        {
        	ennemiVaincu = false;
    		do
    		{
          		 try {
                 	combat(perso, ennemiActuelle);
           		} catch (EntreeInvalideException e) {
           			 System.out.println(e);
           		}
    		} while (!ennemiVaincu);   
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
        	sauvegarde(perso);
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
        	sauvegarde(perso);
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void salleObjet(Personnage perso) throws IllegalStateException, EntreeInvalideException
    {   
    	if (!salleSelectionne)
    	{
            int max = listeSalleObjet.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            salleSelectionne = true;
    	}
     
        SalleObjet salleObjet = listeSalleObjet.get(nbSalleActuelle);
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
        
        if (value == null)
        {
        	throw new IllegalStateException("Sc fermée");
        }
        
        if (value.startsWith("Equiper") && perso.canUseEquipement(equipement) && objetDisponible)
        {
        	objetDisponible = false;
        	perso.useEquipement(equipement);
        }
        else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
        {
        	((Magicien) perso).utiliserPotionSoin();
        }
        else if (value.startsWith("Avancer"))
        {
            estDansSalle = false;
            salleSelectionne = false;
        }
        else if (value.startsWith("Fuir"))
        {
        	sauvegarde(perso);
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
        	sauvegarde(perso);
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void salleEvenement(Personnage perso) throws IllegalStateException, EntreeInvalideException
    {
    	if (!salleSelectionne)
    	{
            int max = listeSalleEvenement.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            salleSelectionne = true;
    	}
    	
        SalleEvenement salleEvenement = listeSalleEvenement.get(nbSalleActuelle);
		    
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
       
        if (perso.getHp() > 0)
        {
            System.out.println("Avancer - Avancer à la salle suivante");
            System.out.println("Info - Voir les Statistiques");
            System.out.println("Fuir - Fuir lâchement le donjon");
            String value = sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }
            
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
            }
            else if (value.startsWith("Potion") && perso instanceof Magicien && ((Magicien) perso).getPotion() != null && !((Magicien) perso).getPotionEstDegat())
            {
            	((Magicien) perso).utiliserPotionSoin();
            }
            else if (value.startsWith("Avancer"))
            {
                estDansSalle = false;
                salleSelectionne = false;
            }
            else if (value.startsWith("Fuir"))
            {
            	sauvegarde(perso);
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
            	sauvegarde(perso);
            	quittez();
            }
            else if (value.startsWith("Menu"))
            {
            	sauvegarde(perso);
                estDansSalle = false;
            	estPartie = false;
                salleSelectionne = false;
            	return;
            }
            else
            {
            	throw new EntreeInvalideException("Entrée invalide");
            }
        }
        else
        {
            System.out.println("Vous n'avez pas survécu !");
            
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
            
            try {
                BaseDeDonnee.deleteHero(perso);                 
        	}catch (ClassNotFoundException e){
        		e.printStackTrace();
        	}catch (SQLException e){
        		e.printStackTrace();
            }
			listePersonnage.remove(perso);
        }
    }
    
    void salleBoss(Personnage perso) throws IllegalStateException, EntreeInvalideException
    {
    	if (!salleSelectionne)
    	{
            int max = listeSalleBoss.size() -1;
            int min = 0;
            int range = (max - min) + 1;
            nbSalleActuelle = (int)((Math.random() * range) + min);
            
            salleEnnemi = listeSalleBoss.get(nbSalleActuelle);
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
        
        if (value == null)
        {
        	throw new IllegalStateException("Sc fermée");
        }
     
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
    	
    		if (perso.getHp() > 0)
    		{
                System.out.println("");
                System.out.println("Félicitation vous avez vaincu le donjon !");
    		}

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
        	sauvegarde(perso);
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
        	sauvegarde(perso);
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void combat(Personnage perso, Ennemi ennemi) throws IllegalStateException, EntreeInvalideException
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
        
        if (value == null)
        {
        	throw new IllegalStateException("Sc fermée");
        }

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
        }
        else if (value.startsWith("Ennemi"))
        {
        	ennemi.montrerStat();
        }
        else if (value.startsWith("Quittez"))
        {
        	sauvegarde(perso);
        	quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
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
                    
                    try {
                        BaseDeDonnee.deleteHero(perso);                 
                	}catch (ClassNotFoundException e){
                		e.printStackTrace();
                	}catch (SQLException e){
                		e.printStackTrace();
                    }
        			listePersonnage.remove(perso);
        			
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
                   
                    try {
                        BaseDeDonnee.deleteHero(perso);                 
                	}catch (ClassNotFoundException e){
                		e.printStackTrace();
                	}catch (SQLException e){
                		e.printStackTrace();
                    }
        			listePersonnage.remove(perso);
        			
                	ennemiVaincu = true;
                    estDansSalle = false;
                	estPartie = false;
                    salleSelectionne = false;
        		}
        	}
        }
    }
}