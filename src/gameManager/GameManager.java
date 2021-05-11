package gameManager;

import java.util.Scanner;

import classe.*;
import donjon.SalleEnnemi;
import donjon.SalleEvenement;
import donjon.SalleObjet;
import donjon.SalleVide;
import exception.EntreeInvalideException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameManager{

	private Menu menu = new Menu();
	public Jeu jeu = new Jeu();
	
    public List<Personnage> listePersonnage = new ArrayList<>();
    public List<SalleVide> listeSalleVide = new ArrayList<>();
    public List<SalleEnnemi> listeSalleEnnemi = new ArrayList<>();
    public List<SalleEnnemi> listeSalleBoss = new ArrayList<>();
    public List<SalleObjet> listeSalleObjet = new ArrayList<>();
    public List<SalleEvenement> listeSalleEvenement = new ArrayList<>();
    public boolean hasReqExit = false;
      
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
        
    	Initialisation.initialisationArme();
    	Initialisation.initialisationBouclier();
    	Initialisation.initialisationSort();
    	Initialisation.initialisationPotion();
    	Initialisation.initialisationEnnemi();
    	Initialisation.initialisationSalleVide();
    	Initialisation.initialisationSalleEnnemi();
    	Initialisation.initialisationSalleObjet();
    	Initialisation.initialisationSalleEvenement();
    	
        do
        {
            try {
    			gm.menu.menu(gm);
    		} catch (IllegalStateException e) {
    			 gm.hasReqExit = true;
    			 System.out.println(e);
    			 e.printStackTrace();
    			 } catch (EntreeInvalideException e) {
   			 System.out.println(e);
   		    }
        }
        while(!gm.hasReqExit);
    }

    void quittez ()
    {
        System.out.println("");
        System.out.println("Vous avez quitter le jeu !");
        sc.close();
        hasReqExit = true;
        menu.estChoixClasse = false;
        menu.estAvantPartie = false;
		menu.estNewGame = false;
        jeu.estPartie = false;
        jeu.estDansSalle = false;
        jeu.salleSelectionne = false;
        jeu.ennemiVaincu = true;
    }
}