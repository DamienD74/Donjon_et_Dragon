package Equipement.Arme;

import java.util.ArrayList;
import java.util.List;

public class ListeSort{

    public static List<Arme> listeSort = new ArrayList<>();

    public static void ajouterSort(String nom, int degat)
    {
        Arme sort = new Arme(nom, "Sort", degat);
        listeSort.add(sort);
    }   
}
