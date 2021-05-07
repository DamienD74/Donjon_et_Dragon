package Equipement.Protection;

import java.util.ArrayList;
import java.util.List;

public class ListeBouclier{
    
    public static List<Bouclier> listeBouclier = new ArrayList<>();

    public static void ajouterBouclier(String nom, int armure)
    {
        Bouclier bouclier = new Bouclier(nom , "Bouclier", armure);
        listeBouclier.add(bouclier);
    }  
}
