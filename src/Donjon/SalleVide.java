package Donjon;

import java.util.ArrayList;
import java.util.List;

public class SalleVide extends Salle{

    public static List<SalleVide> listeSalleVide = new ArrayList<>();
    
	public static void ajouterSalleVide(String message)
	{
		SalleVide salleVide = new SalleVide();
		salleVide.setTypeSalle("SalleVide");
		salleVide.setMessageSalle(message);
		listeSalleVide.add(salleVide);
	}
}
