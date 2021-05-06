package Equipement.Protection;

import java.util.ArrayList;
import java.util.List;

public class ListeBouclier extends Bouclier{
    
    public static List<Bouclier> listeBouclier = new ArrayList<>();

    public static void ajouterBouclier(String nom, int armure)
    {
        Bouclier bouclier = new Bouclier();
        bouclier.setNom(nom);
        bouclier.setArmure(armure);
        bouclier.setClasse("Bouclier");
        listeBouclier.add(bouclier);
    }  
}
