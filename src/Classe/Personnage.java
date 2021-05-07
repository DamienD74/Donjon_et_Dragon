package Classe;

import Ennemi.Ennemi;
import Equipement.Equipement;

public abstract class Personnage {
    
    private String nom;

    private int hp;
    private int hpMax;
    private int hpMin;

    private int degat;

    private String image;
    
    public Personnage(String nom, int hp, int hpMax, int hpMin, int degat, String image)
    {
    	this.nom = nom;
    	this.hp = hp;
    	this.hpMax = hpMax;
    	this.hpMin = hpMin;
    	this.degat = degat;
    	this.image = image;
    }
	public void subirDegatBrut(int degatSubit)
    {
    	setHp(getHp() - degatSubit);
    	
    	if (getHp() <= 0)
    	{
            System.out.println("");
            System.out.println("Vous êtes Mort");
    	}

    }
    
    public void soin(int soin)
    {
    	setHp(getHp() + soin);
    	if (getHp() > getHpMax())
    	{
    		setHp(getHpMax());
    	}
    }
    
    public void augmentationHpMax(int hpAugmenter)
    {
    	setHpMax(getHpMax() + hpAugmenter);
    	setHp(getHp() + hpAugmenter);
    }
    
    public abstract void montrerStat();
    
    public abstract void useEquipement(Equipement e);
    
    public abstract boolean canUseEquipement(Equipement e);
    
    public abstract boolean subirDegat(int degat);
    
    public abstract boolean utiliserSecondaire(Personnage perso, Ennemi ennemi);
    
    public String getNom()
    {
        return this.nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public int getHp()
    {
        return this.hp;
    }

    public void setHp(int hp)
    {
    	this.hp = hp;
    }
    
    public void setHpDepart(int min, int max)
    {
        int range = (max - min) + 1;
        this.hp = (int)(Math.random() * range) + min;
    }
   

    public int getHpMax()
    {
        return this.hpMax;
    }

    public void setHpMax(int hpMax)
    {
        this.hpMax = hpMax;
    }

    public int getHpMin()
    {
        return this.hpMin;
    }

    public void setHpMin(int hpMin)
    {
        this.hpMin = hpMin;
    }

    public int getDegat()
    {
        return this.degat;
    }

    public void setDegat(int degat)
    {
        this.degat = degat;
    }

    public String getImage()
    {
        return this.image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
}