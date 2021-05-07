package Donjon;

import java.util.ArrayList;
import java.util.List;

import Ennemi.Ennemi;

public class SalleEnnemi extends Salle{

	public static List<SalleEnnemi> listeSalleEnnemi = new ArrayList<>();
    public static List<SalleEnnemi> listeSalleBoss = new ArrayList<>();
    
	private Ennemi ennemi;
	
    public SalleEnnemi(String typeSalle, String messageSalle, Ennemi ennemi)
    {
		super(typeSalle, messageSalle);
		this.ennemi = ennemi;
	}

	public static void ajouterSalleEnnemi(String message, Ennemi ennemi)
	{
		SalleEnnemi salleEnnemi = new SalleEnnemi("SalleEnnemi", message, ennemi);
		listeSalleEnnemi.add(salleEnnemi);
	}
	
	public static void ajouterSalleBoss(String message, Ennemi ennemi)
	{
		SalleEnnemi salleEnnemi = new SalleEnnemi("SalleEnnemi", message, ennemi);
		listeSalleBoss.add(salleEnnemi);
	}
	
	public Ennemi getEnnemi() 
	{
		return ennemi;
	}

	public void setEnnemi(Ennemi ennemi) 
	{
		this.ennemi = ennemi;
	}
}
