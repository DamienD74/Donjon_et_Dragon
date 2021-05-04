package Equipement.Arme;

import java.util.ArrayList;
import java.util.List;

public class ListeArme extends Arme{

    public static List<Arme> listeArme = new ArrayList<>();

    public static void AjouterArme(String nom, int degat)
    {
        Arme arme = new Arme();
        arme.SetNom(nom);
        arme.SetDegat(degat);
        listeArme.add(arme);
    }   
}
