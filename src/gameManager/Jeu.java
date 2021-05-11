package gameManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import classe.Magicien;
import classe.Personnage;
import donjon.SalleEnnemi;
import donjon.SalleEvenement;
import donjon.SalleObjet;
import donjon.SalleVide;
import ennemi.Ennemi;
import equipement.Equipement;
import exception.EntreeInvalideException;

public class Jeu {

	GameManager gm;
	
    private boolean objetDisponible;
    private boolean evenementUtiliser;
    public boolean ennemiVaincu;
    
    private Ennemi ennemiActuelle = new Ennemi();
    private SalleEnnemi salleEnnemi;
    
    private int nbSalleActuelle;
    
    private int nbSalle;
    
    boolean estPartie = false;
    boolean estDansSalle = false;
    boolean salleSelectionne = false;
    
    JSONObject jsonObject = null;
    
    void jeu(GameManager gm, Personnage perso) throws IllegalStateException
    {
    	if (!estPartie)
    	{
            try {
            	jsonObject =  (JSONObject) new JSONTokener(new FileReader("src/fichierJson/donjon.json")).nextValue();        
    		} 
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
    	}
    	
    	if (this.gm == null)
    	{
        	this.gm = gm;
    	}
    	
    	estPartie = true;
		nbSalle = Integer.parseInt((String)jsonObject.get("salleActuelle"));
		
    	do
    	{        	
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
        	else if (nbSalle == Integer.parseInt((String)jsonObject.get("nombreSalle")))
        	{
        		estDansSalle = true;
               	ennemiVaincu = false;
 
        		int salleReduite = nbSalle -1;
                String salleActuelle = "" + salleReduite + "";  
                JSONArray salleJson = (JSONArray) jsonObject.get(salleActuelle);
                JSONObject salleObject = (JSONObject) salleJson.get(0); 
                System.out.println(salleObject);
                
        		do
        		{
              		 try {
                 		salleBoss(perso, salleObject);
               		} catch (EntreeInvalideException e) {
               			 System.out.println(e);
               		}
        		} while (estDansSalle && !ennemiVaincu);
        	}
        	else  
        	{
                System.out.println("");
                System.out.println("Vous arrivez à la salle " + nbSalle);
                
        		estDansSalle = true;

        		int salleReduite = nbSalle -1;
                String salleActuelle = "" + salleReduite + "";  
                JSONArray salleJson = (JSONArray) jsonObject.get(salleActuelle);
                JSONObject salleObject = (JSONObject) salleJson.get(0); 
                System.out.println(salleObject);
                
                String valueSalle = (String) salleObject.get("salle");
                
                if (valueSalle.equals("salleEnnemi"))
                {
                	ennemiVaincu = false;
            		do
            		{
                  		 try {
                         	salleEnnemi(perso, salleObject);
                   		} catch (EntreeInvalideException e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle || !ennemiVaincu);
                }
                else if (valueSalle.equals("salleObjet"))
                {
                	objetDisponible = true;
            		do
            		{
                  		 try {
                         	salleObjet(perso, salleObject);
                   		} catch (EntreeInvalideException e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);
                }
                else if (valueSalle.equals("salleEvenement"))
                {
                   	evenementUtiliser = false;
            		do
            		{
                  		 try {
                         	salleEvenement(perso, salleObject);
                   		} catch (EntreeInvalideException e) {
                   			 System.out.println(e);
                   		}
            		} while (estDansSalle);
                }
                else
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
        	}
        	nbSalle++ ;
    	} while (estPartie);

    }
    
    void commencement() throws IllegalStateException, EntreeInvalideException
    {
        System.out.println("");
        System.out.println("Vous arrivez devant l'entrée d'un donjon !");
        System.out.println("Entrer - Entrer dans le donjon");
        System.out.println("Fuir - Fuir lâchement le donjon");
        
        String value = gm.sc.nextLine();
        
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
        	gm.quittez();
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
        String value = gm.sc.nextLine();
        
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
        	gm.quittez();
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
    
    void salleEnnemi(Personnage perso, JSONObject salleObject) throws IllegalStateException, EntreeInvalideException
    {
    	if (!salleSelectionne)
    	{               
            salleEnnemi = gm.listeSalleEnnemi.get(Integer.parseInt((String)salleObject.get("nombre")));
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
        String value = gm.sc.nextLine();
        
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
        	ennemiVaincu = true;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        }
        else if (value.startsWith("Quittez"))
        {
        	sauvegarde(perso);
        	gm.quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	ennemiVaincu = true;
        }
        else
        {
        	throw new EntreeInvalideException("Entrée invalide");
        }
    }
    
    void salleObjet(Personnage perso, JSONObject salleObject) throws IllegalStateException, EntreeInvalideException
    {   
    	if (!salleSelectionne)
    	{
            salleSelectionne = true;
    	}        
        SalleObjet salleObjet = gm.listeSalleObjet.get(Integer.parseInt((String)salleObject.get("nombre")));
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
        String value = gm.sc.nextLine();
        
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
        	gm.quittez();
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
    
    void salleEvenement(Personnage perso, JSONObject salleObject) throws IllegalStateException, EntreeInvalideException
    {
    	if (!salleSelectionne)
    	{
            salleSelectionne = true;          
    	}
    	          
        SalleEvenement salleEvenement = gm.listeSalleEvenement.get(Integer.parseInt((String)salleObject.get("nombre"))); 
      
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
            String value = gm.sc.nextLine();
            
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
            	gm.quittez();
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
            gm.listePersonnage.remove(perso);
        }
    }
    
    void salleBoss(Personnage perso, JSONObject salleObject) throws IllegalStateException, EntreeInvalideException
    {
    	if (!salleSelectionne)
    	{           
            salleEnnemi = gm.listeSalleBoss.get(Integer.parseInt((String)salleObject.get("nombre")));
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
        String value = gm.sc.nextLine();
        
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
        	ennemiVaincu = true;
        }
        else if (value.startsWith("Info"))
        {
        	perso.montrerStat();
        }
        else if (value.startsWith("Quittez"))
        {
        	sauvegarde(perso);
        	gm.quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	ennemiVaincu = true;
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
        String value = gm.sc.nextLine();
        
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
        	gm.quittez();
        }
        else if (value.startsWith("Menu"))
        {
        	sauvegarde(perso);
            estDansSalle = false;
        	estPartie = false;
            salleSelectionne = false;
        	ennemiVaincu = true;
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
                    gm.listePersonnage.remove(perso);
        			
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
                    gm.listePersonnage.remove(perso);
        			
                	ennemiVaincu = true;
                    estDansSalle = false;
                	estPartie = false;
                    salleSelectionne = false;
        		}
        	}
        }
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
}
