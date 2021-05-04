package Equipement.Protection;

import java.util.ArrayList;
import java.util.List;

public class ListeBouclier extends Bouclier{
    
    public static List<Bouclier> listeBouclier = new ArrayList<>();

    public static void AjouterBouclier(String nom, int armure)
    {
        Bouclier bouclier = new Bouclier();
        bouclier.SetNom(nom);
        bouclier.SetArmure(armure);
        listeBouclier.add(bouclier);
    }  
}
