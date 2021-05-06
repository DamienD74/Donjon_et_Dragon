package Classe;

import Ennemi.Ennemi;
import Equipement.Equipement;
import Equipement.Arme.Arme;
import Equipement.Consommable.Consommable;
import Equipement.Arme.ListeSort;
import Equipement.Consommable.ListePotion;

public class Magicien extends Personnage {

    private String sort;
    private String potion;
    private int puissancePotion;
    private boolean potionEstDegat;

    public void initialisation(Magicien magicien)
    {
        magicien.setImage("/img/Guerrier.jpg");
        magicien.setHpMax(25);
        magicien.setHpMin(20);

        magicien.setHpDepart(magicien.getHpMin(), magicien.getHpMax());
        
        magicien.setHpMax(magicien.getHp());

        magicien.choixSortDepart(magicien);
        magicien.choixPotionDepart(magicien);
    }

    public Magicien choixSortDepart(Magicien magicien)
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        magicien.setSort(ListeSort.listeSort.get(nombre).getNom());
        magicien.setDegat(ListeSort.listeSort.get(nombre).getDegat());
        return magicien;
    }

    public Magicien choixPotionDepart(Magicien magicien)
    {
        int max = 1;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        magicien.setPotion(ListePotion.listePotion.get(nombre).getNom());
        magicien.setPuissancePotion(ListePotion.listePotion.get(nombre).getPuissance());
        magicien.setPotionEstDegat(ListePotion.listePotion.get(nombre).getEstDegat());
        return magicien;
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