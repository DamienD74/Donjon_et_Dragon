package ennemi;

import java.util.ArrayList;
import java.util.List;

public class ListeEnnemi extends Ennemi{

    public static List<Ennemi> listeEnnemi = new ArrayList<>();
    public static List<Ennemi> listeBoss = new ArrayList<>();
    
    public static void ajouterEnnemi(String nom, int hp, int degat, String image)
    {
    	Ennemi ennemi = new Ennemi();
    	ennemi.setNom(nom);
    	ennemi.setHp(hp);
    	ennemi.setHpMax(hp);
    	ennemi.setDegat(degat);
    	ennemi.setImage(image);
    	listeEnnemi.add(ennemi);
    }
    
    public static void ajouterBoss(String nom, int hp, int degat, String image)
    {
    	Ennemi ennemi = new Ennemi();
    	ennemi.setNom(nom);
    	ennemi.setHp(hp);
    	ennemi.setHpMax(hp);
    	ennemi.setDegat(degat);
    	ennemi.setImage(image);
    	listeBoss.add(ennemi);
    }
}
