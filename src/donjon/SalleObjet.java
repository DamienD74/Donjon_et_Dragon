package donjon;

import java.util.ArrayList;
import java.util.List;

import equipement.Equipement;

public class SalleObjet extends Salle{

	public static List<SalleObjet> listeSalleObjet = new ArrayList<>();
    
	private Equipement equipement;

    public SalleObjet(String typeSalle, String messageSalle, Equipement equipement) 
    {
		super(typeSalle, messageSalle);
		this.equipement = equipement;
	}

	public static void ajouterSalleObjet(String message, Equipement equipement)
	{
		SalleObjet salleObjet = new SalleObjet("SalleObjet", message, equipement);
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
