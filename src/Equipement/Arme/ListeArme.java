package Equipement.Arme;

import java.util.ArrayList;
import java.util.List;

public class ListeArme{

    public static List<Arme> listeArme = new ArrayList<>();

    public static void ajouterArme(String nom, int degat)
    {
        Arme arme = new Arme(nom, "Arme", degat);
        listeArme.add(arme);
    }   
}
