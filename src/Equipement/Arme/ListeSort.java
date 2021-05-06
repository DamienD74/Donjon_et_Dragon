package Equipement.Arme;

import java.util.ArrayList;
import java.util.List;

public class ListeSort {

    public static List<Arme> listeSort = new ArrayList<>();

    public static void ajouterSort(String nom, int degat)
    {
        Arme sort = new Arme();
        sort.setNom(nom);
        sort.setDegat(degat);
        sort.setClasse("Sort");
        listeSort.add(sort);
    }   
}
