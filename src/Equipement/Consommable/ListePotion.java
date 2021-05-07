package Equipement.Consommable;

import java.util.ArrayList;
import java.util.List;

public class ListePotion{
    
    public static List<Consommable> listePotion = new ArrayList<>();

    public static void ajouterPotion(String nom, int puissance, boolean estDegat)
    {
        Consommable potion = new Consommable(nom, "Potion", estDegat, puissance);
        listePotion.add(potion);
    }   
}
