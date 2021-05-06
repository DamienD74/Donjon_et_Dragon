package Equipement.Arme;

import java.util.ArrayList;
import java.util.List;

public class ListeArme extends Arme{

    public static List<Arme> listeArme = new ArrayList<>();

    public static void ajouterArme(String nom, int degat)
    {
        Arme arme = new Arme();
        arme.setNom(nom);
        arme.setDegat(degat);
        arme.setClasse("Arme");
        listeArme.add(arme);
    }   
}
