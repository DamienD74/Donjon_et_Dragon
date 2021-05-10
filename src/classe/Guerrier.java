package classe;

import ennemi.Ennemi;
import equipement.Equipement;
import equipement.arme.Arme;
import equipement.arme.ListeArme;
import equipement.protection.Bouclier;
import equipement.protection.ListeBouclier;

public class Guerrier extends Personnage {

    private String arme;
    private String bouclier;
    private int armure;
    
    public Guerrier (int id, String nom, int hp, int hpMax, int degat, String image, String arme, String bouclier, int armure)
    {
    	super(id, nom, hp, hpMax, degat, image);
    	this.arme = arme;
    	this.bouclier = bouclier;
    	this.armure = armure;
    }
    
    public Guerrier (String nom, int hp, int hpMax, int degat, String image, String arme, String bouclier, int armure)
    {
    	super(nom, hp, hpMax, degat, image);
    	this.arme = arme;
    	this.bouclier = bouclier;
    	this.armure = armure;
    }
    
	public static Guerrier initialisation(String nom)
    {
		String image = "/img/Guerrier.jpg";
		int hpMax = 35;
		int hpMin = 30;
	
        int range = (hpMax - hpMin) + 1;
        int hp = (int)(Math.random() * range) + hpMin;
        
        hpMax = hp;
        
		Arme armeObj = choixArmeDepart();
		Bouclier bouclierObj = choixBouclierDepart();
		
		String arme = armeObj.getNom();
		int degat = armeObj.getDegat();
		
		String bouclier = bouclierObj.getNom();
		int armure = bouclierObj.getArmure();
        
        Guerrier guerrier = new Guerrier(nom, hp, hpMax, degat, image, arme, bouclier, armure);
        return guerrier;
    }

    public static Arme choixArmeDepart()
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        return ListeArme.listeArme.get(nombre);
    }

    public static Bouclier choixBouclierDepart()
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        return ListeBouclier.listeBouclier.get(nombre);
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
