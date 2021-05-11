package gameManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import donjon.SalleEnnemi;
import donjon.SalleEvenement;
import donjon.SalleObjet;
import donjon.SalleVide;
import ennemi.ListeEnnemi;
import equipement.arme.ListeArme;
import equipement.arme.ListeSort;
import equipement.consommable.ListePotion;
import equipement.protection.ListeBouclier;

public class Initialisation {

    public static void initialisationArme()
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

    public static void initialisationBouclier()
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

    public static void initialisationSort()
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

    public static void initialisationPotion()
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
    
    public static void initialisationEnnemi()
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
    
    public static void initialisationSalleVide()
    {
    	SalleVide.ajouterSalleVide("Cette salle est vide");
    }
    
    public static void initialisationSalleEnnemi()
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
    
    public static void initialisationSalleObjet()
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
     	SalleObjet.ajouterSalleBouclier("Vous avez trouvé un bouclier suprême ! (7)", ListeBouclier.listeBouclier.get(6));
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
    
    public static void initialisationSalleEvenement()
    {
    	SalleEvenement.ajouterSalleEvenement("Super ! une potion de vitalité vous pouvez aumenter de 4 points votre hp max", false, false, true, 4);
    	SalleEvenement.ajouterSalleEvenement("Super ! une potion d' icare vous pouvez aumenter de 8 points votre hp max", false, false, true, 8);
    	SalleEvenement.ajouterSalleEvenement("Une fontaire de soin vous pouvez récupéré 8 points de vie", true, false, false, 10);
    	SalleEvenement.ajouterSalleEvenement("Une fontaire de soin féérique vous pouvez récupéré 20 points de vie", true, false, false, 20);
    	SalleEvenement.ajouterSalleEvenement("Oh non un piège de pointe vous perdez 3 points de vie", false, true, false, 3);
    	SalleEvenement.ajouterSalleEvenement("Oh non un piège des haches tombes sur vous ! Vous perdez 6 points de vie", false, true, false, 6);
    }
    
    public static void initialisationSallePossibleDonjon(GameManager gm, JSONObject jsonObject)
    {       
    	gm.listeSalleVide = new ArrayList<>();
    	gm.listeSalleEnnemi = new ArrayList<>();
    	gm.listeSalleBoss = new ArrayList<>();
    	gm.listeSalleObjet = new ArrayList<>();
    	gm.listeSalleEvenement = new ArrayList<>();
    	
        JSONArray salleVide = (JSONArray) jsonObject.get("salleVide");
        
        for (int i = 0; i < salleVide.length(); i++)
        {
            gm.listeSalleVide.add(SalleVide.listeSalleVide.get(salleVide.optInt(i)));
        }  
        
        JSONArray salleEnnemi = (JSONArray) jsonObject.get("salleEnnemi");
        
        for (int i = 0; i < salleEnnemi.length(); i++)
        {
            gm.listeSalleEnnemi.add(SalleEnnemi.listeSalleEnnemi.get(salleEnnemi.optInt(i)));
        }  
        
        JSONArray salleBoss = (JSONArray) jsonObject.get("boss");
        
        for (int i = 0; i < salleBoss.length(); i++)
        {
            gm.listeSalleBoss.add(SalleEnnemi.listeSalleBoss.get(salleBoss.optInt(i)));
        }  
        
        JSONArray salleArme = (JSONArray) jsonObject.get("salleArme");
        
        for (int i = 0; i < salleArme.length(); i++)
        {
            gm.listeSalleObjet.add(SalleObjet.listeSalleArme.get(salleArme.optInt(i)));
        }  
        
        JSONArray salleBouclier = (JSONArray) jsonObject.get("salleBouclier");
        
        for (int i = 0; i < salleBouclier.length(); i++)
        {
            gm.listeSalleObjet.add(SalleObjet.listeSalleBouclier.get(salleBouclier.optInt(i)));
        } 
        
        JSONArray salleSort = (JSONArray) jsonObject.get("salleSort");
        
        for (int i = 0; i < salleSort.length(); i++)
        {
            gm.listeSalleObjet.add(SalleObjet.listeSalleSort.get(salleSort.optInt(i)));
        } 
        
        JSONArray sallePotion = (JSONArray) jsonObject.get("sallePotion");
        
        for (int i = 0; i < sallePotion.length(); i++)
        {
            gm.listeSalleObjet.add(SalleObjet.listeSallePotion.get(sallePotion.optInt(i)));
        } 
        
        JSONArray salleEvenement = (JSONArray) jsonObject.get("salleEvenement");
        
        for (int i = 0; i < salleEvenement.length(); i++)
        {
            gm.listeSalleEvenement.add(SalleEvenement.listeSalleEvenement.get(salleEvenement.optInt(i)));
        }  
        
        int nbSalle = Integer.parseInt((String) jsonObject.get("nombreSalle"));
        String difficulte = (String) jsonObject.get("difficulte");
        
        initialisationDonjon(gm, nbSalle, difficulte);
    }
    
    public static void initialisationDonjon(GameManager gm, int nbSalle, String difficulte)
    {
    	String cheminDuFichier = "src/fichierJson/donjon.json";
    	List<String> listeEcriture = new ArrayList<>();
    	String ecriture = null;  	
    	
		File file = new File(cheminDuFichier);
		
    	for (int i = 0; i < nbSalle; i++)
    	{            
            int max = 100;
            int min = 1;
            int range = (max - min) + 1;
            int nombre = (int)(Math.random() * range) + min;
            
    		if (i == nbSalle - 1)
    		{
                int max2 = gm.listeSalleBoss.size() -1;
                int min2 = 0;
                int range2 = (max2 - min2) + 1;
                int nb = (int)((Math.random() * range2) + min2);
                
            	ecriture = "\"" + i +"\":[{\"salle\": \"salleBoss\",\"nombre\": \""+ nb + "\"}]";
    		}
            else if (nombre <= 25)
            {
                int max2 = gm.listeSalleEnnemi.size() -1;
                int min2 = 0;
                int range2 = (max2 - min2) + 1;
                int nb = (int)((Math.random() * range2) + min2);
                
            	ecriture = "\"" + i +"\":[{\"salle\": \"salleEnnemi\",\"nombre\": \""+ nb + "\"}]";
            }
            else if (nombre <= 50)
            {
                int max2 = gm.listeSalleObjet.size() -1;
                int min2 = 0;
                int range2 = (max2 - min2) + 1;
                int nb = (int)((Math.random() * range2) + min2);
                
            	ecriture = "\"" + i +"\":[{\"salle\": \"salleObjet\",\"nombre\": \""+ nb + "\"}]";
            }
            else if (nombre <= 75)
            {
                int max2 = gm.listeSalleEvenement.size() -1;
                int min2 = 0;
                int range2 = (max2 - min2) + 1;
                int nb = (int)((Math.random() * range2) + min2);
                
            	ecriture = "\"" + i +"\":[{\"salle\": \"salleEvenement\",\"nombre\": \""+ nb + "\"}]";
            }
            else
            {            
            	ecriture = "\"" + i +"\":[{\"salle\": \"salleVide\",\"nombre\": \""+ 0 + "\"}]";
            } 
        	listeEcriture.add(ecriture);
    	}
    	
		try {
			if (!file.exists())
				file.createNewFile();
			FileWriter writer = new FileWriter(file);
			
			writer.write("{");
			writer.write("\n");		
			writer.write("\"salleActuelle\": \"" + 0 + "\",");
			writer.write("\n");		
			writer.write("\"nombreSalle\": \"" + nbSalle + "\",");
			writer.write("\n");		
			writer.write("\"difficulte\": \"" + difficulte + "\",");
			writer.write("\n");		
			
			for(int i = 0; i < listeEcriture.size(); i++)
			{
				if (i != listeEcriture.size() && i != 0)
				{
					writer.write(",");
					writer.write("\n");
				}
				writer.write(listeEcriture.get(i));
			}
			
			writer.write("\n");	
			writer.write("}");
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.out.println("Erreur: impossible de créer le fichier '"
					+ cheminDuFichier + "'");
		}
    }
}
