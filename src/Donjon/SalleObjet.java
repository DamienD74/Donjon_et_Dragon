package Donjon;

import java.util.ArrayList;
import java.util.List;

import Equipement.Equipement;

public class SalleObjet extends Salle{

    public static List<SalleObjet> listeSalleObjet = new ArrayList<>();
    
	private Equipement equipement;

	public static void ajouterSalleObjet(String message, Equipement equipement)
	{
		SalleObjet salleObjet = new SalleObjet();
		salleObjet.setTypeSalle("SalleObjet");
		salleObjet.setMessageSalle(message);
		salleObjet.setEquipement(equipement);
		listeSalleObjet.add(salleObjet);
	}
	
	public Equipement getEquipement() 
	{
		return equipement;
	}

	public void setEquipement(Equipement equipement) 
	{
		this.equipement = equipement;
	}
}
