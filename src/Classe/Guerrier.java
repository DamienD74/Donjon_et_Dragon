package Classe;

import Ennemi.Ennemi;
import Equipement.Equipement;
import Equipement.Arme.Arme;
import Equipement.Protection.Bouclier;
import Equipement.Arme.ListeArme;
import Equipement.Protection.ListeBouclier;

public class Guerrier extends Personnage {

    private String arme;
    private String bouclier;
    private int armure;
    
	public void initialisation(Guerrier guerrier)
    {
        guerrier.setImage("/img/Guerrier.jpg");
        guerrier.setHpMax(35);
        guerrier.setHpMin(30);

        guerrier.setHpDepart(guerrier.getHpMin(), guerrier.getHpMax());
        
        guerrier.setHpMax(guerrier.getHp());

        guerrier.choixArmeDepart(guerrier);
        guerrier.choixBouclierDepart(guerrier);
    }

    public Guerrier choixArmeDepart(Guerrier guerrier)
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        guerrier.setArme(ListeArme.listeArme.get(nombre).getNom());
        guerrier.setDegat(ListeArme.listeArme.get(nombre).getDegat());
        return guerrier;
    }

    public Guerrier choixBouclierDepart(Guerrier guerrier)
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        guerrier.setBouclier(ListeBouclier.listeBouclier.get(nombre).getNom());
        guerrier.setArmure(ListeBouclier.listeBouclier.get(nombre).getArmure());
        return guerrier;
    }
    
    public void montrerStat()
    {
        System.out.println("");
        System.out.println("Nom: " + getNom());
        System.out.println("Hp: " + getHp() + " (" + getHpMax() + ")");
        System.out.println("Arme: " + getArme() + " (" + getDegat() + ")");
        System.out.println("Bouclier: " + getBouclier() + " (" + getArmure() + ")");
    }

	public void useEquipement(Equipement e) 
	{
		if (e instanceof Arme)
		{
			setArme(e.getNom());
			setDegat(((Arme) e).getDegat());
		}
		else
		{
			setBouclier(e.getNom());
			setArmure(((Bouclier) e).getArmure());
		}
	}

	public boolean canUseEquipement(Equipement e) 
	{
		if (e.getCLasse() == "Arme" || e.getCLasse() == "Bouclier")
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	public boolean subirDegat(int degat) 
	{
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;
        
		int degatSubit;
		
        if (nombre == 0)
        {
        	degatSubit = degat - ((degat * getArmure()) / 10);
            System.out.println("Vous avez réussi a parer l'attaque ! Vous ne subissez que " + degatSubit + " de dégat.");
        }
        else
        {
        	degatSubit = degat;
            System.out.println("Vous subissez " + degatSubit + " de dégat.");
        }


		
		setHp(getHp() - degatSubit);
		if (getHp() <= 0)
		{
			return true;
		}
		else
		{			
			return false;
		}
	}

	public boolean utiliserSecondaire(Personnage perso, Ennemi ennemi) 
	{
		return false;
	}
	
    public String getArme()
    {
        return this.arme;
    }

    public void setArme(String arme)
    {
        this.arme = arme;
    }

    public String getBouclier()
    {
        return this.bouclier;
    }

    public void setBouclier(String bouclier)
    {
        this.bouclier = bouclier;
    }

    public int getArmure()
    {
        return this.armure;
    }

    public void setArmure(int armure)
    {
        this.armure = armure;
    }
}
