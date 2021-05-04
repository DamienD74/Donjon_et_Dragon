package Equipement.Arme;

import java.util.ArrayList;
import java.util.List;

public class ListeSort {

    public static List<Arme> listeSort = new ArrayList<>();

    public static void AjouterSort(String nom, int degat)
    {
        Arme sort = new Arme();
        sort.SetNom(nom);
        sort.SetDegat(degat);
        listeSort.add(sort);
    }   
}
