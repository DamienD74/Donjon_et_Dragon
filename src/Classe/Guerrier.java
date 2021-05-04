package Classe;

import Equipement.Arme.ListeArme;
import Equipement.Protection.ListeBouclier;

public class Guerrier extends Personnage {

    private String arme;
    private String bouclier;
    private int armure;

    public String GetArme()
    {
        return this.arme;
    }

    public void SetArme(String arme)
    {
        this.arme = arme;
    }

    public String GetBouclier()
    {
        return this.bouclier;
    }

    public void SetBouclier(String bouclier)
    {
        this.bouclier = bouclier;
    }

    public int GetArmure()
    {
        return this.armure;
    }

    public void SetArmure(int armure)
    {
        this.armure = armure;
    }

    public void Initialisation(Guerrier guerrier)
    {
    	guerrier.SetClasse("Guerrier");
        guerrier.SetImage("/img/Guerrier.jpg");
        guerrier.SetHpMax(10);
        guerrier.SetHpMin(5);

        guerrier.SetHpDepart(guerrier.GetHpMin(), guerrier.GetHpMax());

        guerrier.ChoixArmeDepart(guerrier);
        guerrier.ChoixBouclierDepart(guerrier);
    }

    public Guerrier ChoixArmeDepart(Guerrier guerrier)
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        guerrier.SetArme(ListeArme.listeArme.get(nombre).GetNom());
        guerrier.SetDegat(ListeArme.listeArme.get(nombre).GetDegat());
        return guerrier;
    }

    public Guerrier ChoixBouclierDepart(Guerrier guerrier)
    {
        int max = 2;
        int min = 0;
        int range = (max - min) + 1;
        int nombre = (int)(Math.random() * range) + min;

        guerrier.SetBouclier(ListeBouclier.listeBouclier.get(nombre).GetNom());
        guerrier.SetArmure(ListeBouclier.listeBouclier.get(nombre).GetArmure());
        return guerrier;
    }
}
