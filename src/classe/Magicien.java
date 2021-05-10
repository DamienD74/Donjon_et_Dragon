package classe;

import ennemi.Ennemi;
import equipement.Equipement;
import equipement.arme.Arme;
import equipement.arme.ListeSort;
import equipement.consommable.Consommable;
import equipement.consommable.ListePotion;

public class Magicien extends Personnage {

    private String sort;
    private String potion;
    private int puissancePotion;
    private boolean potionEstDegat;

    public Magicien (int id, String nom, int hp, int hpMax, int degat, String image, String sort, String potion, boolean potionEstDegat, int puissancePotion)
    {
    	super(id, nom, hp, hpMax, degat, image);
    	this.sort = sort;
    	this.potion = potion;
    	this.puissancePotion = puissancePotion;
    	this.potionEstDegat = potionEstDegat;
    }
    
    public Magicien (String nom, int hp, int hpMax, int degat, String image, String sort, String potion, boolean potionEstDegat, int puissancePotion)
    {
    	super(nom, hp, hpMax, degat, image);
    	this.sort = sort;
    	this.potion = potion;
    	this.puissancePotion = puissancePotion;
    	this.potionEstDegat = potionEstDegat;
    }
    
	public static Magicien initialisation(String nom)
    {
		String image = "/img/Magicien.jpg";
		int hpMax = 35;
		int hpMin = 30;
	
        int range = (hpMax - hpMin) + 1;
        int hp = (int)(Math.random() * range) + hpMin;
        
        hpMax = hp;
        
		Arme sortObj = choixSortDepart();
		Consommable potionObj = choixPotionDepart();
		
		String sort = sortObj.getNom();
		int degat = sortObj.getDegat();
		
		String potion = potionObj.getNom();
		int puissancePotion = potionObj.getPuissance();
		boolean potionEstDegat = potionObj.getEstDegat();
        
        Magicien magicien = new Magicien(nom, hp, hpMax, degat, image, sort, potion, potionEstDegat, puissancePotion);
        return magicien;
    }

    public static Arme choixSortDepart()
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        return ListeSort.listeSort.get(nombre);
    }

    public static Consommable choixPotionDepart()
    {
        int max = 1;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        return ListePotion.listePotion.get(nombre);
    }
    
    public void montrerStat()
    {
        System.out.println("");
        System.out.println("Nom: " + getNom());
        System.out.println("Hp: " + getHp() + " (" + getHpMax() + ")");
        System.out.println("Sort: " + getSort() + " (" + getDegat() + ")");
        if (getPotion() != null)
        {
            if (getPotionEstDegat())
            {
                System.out.println("Potion: " + getPotion() + " (" + getPuissancePotion() + " de dégat)");
            }
            else
            {
                System.out.println("Potion: " + getPotion() + " (" + getPuissancePotion() + " de soin)");
            }
        }
    }

	public void useEquipement(Equipement e) 
	{
		if (e instanceof Arme)
		{
			setSort(e.getNom());
			setDegat(((Arme) e).getDegat());
		}
		else
		{
			setPotion(e.getNom());
			setPuissancePotion(((Consommable) e).getPuissance());
			setPotionEstDegat(((Consommable) e).getEstDegat());
		}
	}

	public boolean canUseEquipement(Equipement e) 
	{
		if (e.getCLasse() == "Arme" || e.getCLasse() == "Bouclier")
		{
			return false;
		}
		else 
		{
			return true;
		}
	}

	public boolean subirDegat(int degat) 
	{
        System.out.println("Vous subissez " + degat + " de dégat.");
		setHp(getHp() - degat);
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
		if (getPotionEstDegat())
		{
            System.out.println("Vous lancez votre potion sur l'ennemi et il subit " + getPuissancePotion() + " de dégat.");
            
			if (ennemi.subirDegat(getPuissancePotion()))
			{
				setPotion(null);
				setPuissancePotion(0);
				return true;
			}
			else 
			{
				setPotion(null);
				setPuissancePotion(0);
				return false;
			}
		}
		else
		{
			utiliserPotionSoin();
			return false;
		}
	}
	
	public void utiliserPotionSoin()
	{
        System.out.println("Vous buvez votre potion et vous vous soignez de " + getPuissancePotion() + " points de vie.");
        
		soin(getPuissancePotion());
		setPotion(null);
		setPuissancePotion(0);
	}
	
    public String getSort()
    {
        return this.sort;
    }

    public void setSort(String sort)
    {
        this.sort = sort;
    }

    public String getPotion()
    {
        return this.potion;
    }

    public void setPotion(String potion)
    {
        this.potion = potion;
    }

    public int getPuissancePotion()
    {
        return this.puissancePotion;
    }

    public void setPuissancePotion(int puissancePotion)
    {
        this.puissancePotion = puissancePotion;
    }

    public boolean getPotionEstDegat()
    {
        return this.potionEstDegat;
    }

    public void setPotionEstDegat(Boolean potionEstDegat)
    {
        this.potionEstDegat = potionEstDegat;
    }
}