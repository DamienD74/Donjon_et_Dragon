package gameManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONTokener;

import classe.Guerrier;
import classe.Magicien;
import classe.Personnage;
import exception.EntreeInvalideException;

public class Menu {

	GameManager gm;
	
    boolean estNewGame = false;
    boolean estChoixClasse = false;
    boolean estAvantPartie = false;
	
    void menu (GameManager gm) throws IllegalStateException, EntreeInvalideException
    {
    	if (this.gm == null)
    	{
        	this.gm = gm;
    	}
    	do
    	{        	
    		System.out.println("NewGame - Commencer une nouvelle partie");
    		if (gm.listePersonnage.size() > 0)
    		{
        		System.out.println("Continuer - Continuer la partie en cours");
    		}
    		System.out.println("Quittez - Quittez le jeu. (Utilisable en cours de partie pour quitter le jeu");
    		System.out.println("Menu - Utilisable en cours de partie pour revenir au menu");
            String value = gm.sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }
            
            if (value.startsWith("NewGame"))
            {
            	if (gm.listePersonnage.size() > 0)
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
            else if (value.startsWith("Continuer") && gm.listePersonnage.size() > 0) 
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
            	gm.quittez();
            }
            else
            {
            	throw new EntreeInvalideException("Entrée invalide");
            }
    	}while(!gm.hasReqExit);     
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
	      
	        String value = gm.sc.nextLine();
	        
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
	          	for (int i = 0; i < gm.listePersonnage.size(); i++)
	          	{
	          		if (value.startsWith("Commencer " + (i + 1)))
	          		{
	                    do
	                    {
	                        try {
	                            choixDifficulte(gm.listePersonnage.get(i));            
	                		} 
	                        catch (EntreeInvalideException e){
	               			     System.out.println(e);
	                        }
	                    }
	                    while(estChoixClasse);
	          		}
	          	}
            	throw new EntreeInvalideException("Entrée invalide");
	        }
	        else if (value.startsWith("Liste"))
	        {
    			if (gm.listePersonnage.size() == 0)
    			{
		            System.out.println("Vous n'avez aucun personnage pour le moment");  
    			}
    			else
    			{
    	          	for (int i = 0; i < gm.listePersonnage.size(); i++)
    	          	{
    		            System.out.println((i + 1) + ": " + gm.listePersonnage.get(i).getNom());   
    	          	}
    			}
	        }
	        else if (value.startsWith("Quittez"))
	        {
	        	gm.quittez();
	        }
	        else
	        {
	          	for (int i = 0; i < gm.listePersonnage.size(); i++)
	          	{
	          		if (value.startsWith("Info " + (i + 1)))
	        		{
	          			gm.listePersonnage.get(i).montrerStat();
	          			return;
	        		}
	        		else if (value.startsWith("Supprimer " + (i + 1)))
	        		{
	                    try {
	                        BaseDeDonnee.deleteHero(gm.listePersonnage.get(i));                 
	                	}catch (ClassNotFoundException e){
	                		e.printStackTrace();
	                	}catch (SQLException e){
	                		e.printStackTrace();
	                    }
	                    gm.listePersonnage.remove(i);
	          			return;
	        		}
	        		else if (value.startsWith("Modifier " + (i + 1)))
	        		{
	                    System.out.println("");
	                    System.out.println("Choisissez un autre nom? ");
	                    String name = gm.sc.nextLine();                  
	                    gm.listePersonnage.get(i).setNom(name);
	                
	                    try {
	                    	BaseDeDonnee.updateHero(gm.listePersonnage.get(i));;                
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
    	}while(!gm.hasReqExit && estAvantPartie);     
    }
    
    void choixDifficulte(Personnage perso) throws EntreeInvalideException
    {
        System.out.println("");
        System.out.println("Choisissez une difficulté");
        System.out.println("Facile / Normal / Difficile");
        String value = gm.sc.nextLine();  
        
    	JSONObject jsonObject = null;
        
        if (value.startsWith("Facile"))
        {
            try {
            	jsonObject =  (JSONObject) new JSONTokener(new FileReader("src/fichierJson/donjonFacile.json")).nextValue(); 
            	Initialisation.initialisationSallePossibleDonjon(gm, jsonObject);
            	gm.jeu.jeu(gm, perso);           
    		} 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (value.startsWith("Normal"))
        {
            try {
            	jsonObject =  (JSONObject) new JSONTokener(new FileReader("src/fichierJson/donjonMoyen.json")).nextValue(); 
            	Initialisation.initialisationSallePossibleDonjon(gm, jsonObject);
            	gm.jeu.jeu(gm, perso);           
    		} 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (value.startsWith("Difficile"))
        {
            try {
            	jsonObject =  (JSONObject) new JSONTokener(new FileReader("src/fichierJson/donjonDifficile.json")).nextValue(); 
            	Initialisation.initialisationSallePossibleDonjon(gm, jsonObject);
            	gm.jeu.jeu(gm, perso);           
    		} 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if (value.startsWith("Menu"))
        {
        	estNewGame = false;
        }
        else if (value.startsWith("Quittez"))
        {
        	gm.quittez();
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void validationNouvellePartie() throws EntreeInvalideException
    {
        System.out.println("");
        System.out.println("Etes vous sur de vouloir recommencer une partie ?");
        System.out.println("Toute votre progression sera supprimer !");
        System.out.println("Oui - Recommencer une nouvelle partie");
        System.out.println("Non - J'ai changé d'avis");
        
        String value = gm.sc.nextLine();  
        
        if (value.startsWith("Oui"))
        {
            try {
            	BaseDeDonnee.deleteAllHeros();                
        	}catch (ClassNotFoundException e){
        		e.printStackTrace();
        	}catch (SQLException e){
        		e.printStackTrace();
            }
            
            gm.listePersonnage = new ArrayList<>();
            estNewGame = false;
        }
        else if (value.startsWith("Non"))
        {
        	estNewGame = false;
        }
        else if (value.startsWith("Menu"))
        {
        	estNewGame = false;
        }
        else if (value.startsWith("Quittez"))
        {
        	gm.quittez();
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

            String value = gm.sc.nextLine();
            
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
            	gm.quittez();
            }
            else
            {
            	throw new EntreeInvalideException("Entrée invalide");
            }
    	}while(!gm.hasReqExit);    
    }

    void creationGuerrier() throws IllegalStateException
    {   	
    	do
    	{
            System.out.println("");
            System.out.println("Vous avez choisi guerrier. Quelles et votre nom ? ");
            String value = gm.sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }

            if (value.startsWith("Quittez"))
            {
            	gm.quittez();
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

                gm.listePersonnage.add(guerrier);

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
    	}while(!gm.hasReqExit);    
    }

    void creationMagicien() throws IllegalStateException
    {
    	do
    	{
            System.out.println("");
            System.out.println("Vous avez choisi un magicien. Quelles et votre nom ? ");
            String value = gm.sc.nextLine();
            
            if (value == null)
            {
            	throw new IllegalStateException("Sc fermée");
            }

            if (value.startsWith("Quittez"))
            {
            	gm.quittez();
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

                gm.listePersonnage.add(magicien);

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
    	}while(!gm.hasReqExit);    
    }
}
