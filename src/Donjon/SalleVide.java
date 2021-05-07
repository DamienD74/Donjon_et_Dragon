package Donjon;

import java.util.ArrayList;
import java.util.List;

public class SalleVide extends Salle{

	public static List<SalleVide> listeSalleVide = new ArrayList<>();
	
    public SalleVide(String typeSalle, String messageSalle) 
    {
		super(typeSalle, messageSalle);
	}
    
	public static void ajouterSalleVide(String message)
	{
		SalleVide salleVide = new SalleVide("SalleVide", message);
		listeSalleVide.add(salleVide);
	}
}
