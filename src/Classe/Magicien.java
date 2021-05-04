package Classe;

import Equipement.Arme.ListeSort;
import Equipement.Consommable.ListePotion;

public class Magicien extends Personnage {

    private String sort;
    private String potion;
    private int puissancePotion;
    private boolean potionEstDegat;

    public String GetSort()
    {
        return this.sort;
    }

    public void SetSort(String sort)
    {
        this.sort = sort;
    }

    public String GetPotion()
    {
        return this.potion;
    }

    public void SetPotion(String potion)
    {
        this.potion = potion;
    }

    public int GetPuissancePotion()
    {
        return this.puissancePotion;
    }

    public void SetPuissancePotion(int puissancePotion)
    {
        this.puissancePotion = puissancePotion;
    }

    public boolean GetPotionEstDegat()
    {
        return this.potionEstDegat;
    }

    public void SetPotionEstDegat(Boolean potionEstDegat)
    {
        this.potionEstDegat = potionEstDegat;
    }

    public void Initialisation(Magicien magicien)
    {
    	magicien.SetClasse("Magicien");
        magicien.SetImage("/img/Guerrier.jpg");
        magicien.SetHpMax(6);
        magicien.SetHpMin(3);

        magicien.SetHpDepart(magicien.GetHpMin(), magicien.GetHpMax());

        magicien.ChoixSortDepart(magicien);
        magicien.ChoixPotionDepart(magicien);
    }

    public Magicien ChoixSortDepart(Magicien magicien)
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        magicien.SetSort(ListeSort.listeSort.get(nombre).GetNom());
        magicien.SetDegat(ListeSort.listeSort.get(nombre).GetDegat());
        return magicien;
    }

    public Magicien ChoixPotionDepart(Magicien magicien)
    {
        int max = 1;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        magicien.SetPotion(ListePotion.listePotion.get(nombre).GetNom());
        magicien.SetPuissancePotion(ListePotion.listePotion.get(nombre).GetPuissance());
        magicien.SetPotionEstDegat(ListePotion.listePotion.get(nombre).GetEstDegat());
        return magicien;
    }
}