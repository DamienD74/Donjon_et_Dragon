package Equipement.Consommable;

import java.util.ArrayList;
import java.util.List;

public class ListePotion extends Consommable{
    
    public static List<Consommable> listePotion = new ArrayList<>();

    public static void ajouterPotion(String nom, int puissance, boolean estDegat)
    {
        Consommable potion = new Consommable();
        potion.setNom(nom);
        potion.setPuissance(puissance);
        potion.setEstDegat(estDegat);
        potion.setClasse("Potion");
        listePotion.add(potion);
    }   
}
