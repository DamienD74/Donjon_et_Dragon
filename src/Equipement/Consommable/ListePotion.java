package Equipement.Consommable;

import java.util.ArrayList;
import java.util.List;

public class ListePotion extends Consommable{
    
    public static List<Consommable> listePotion = new ArrayList<>();

    public static void AjouterPotion(String nom, int puissance, boolean estDegat)
    {
        Consommable potion = new Consommable();
        potion.SetNom(nom);
        potion.SetPuissance(puissance);
        potion.SetEstDegat(estDegat);
        listePotion.add(potion);
    }   
}
