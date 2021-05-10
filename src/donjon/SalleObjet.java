package donjon;

import java.util.ArrayList;
import java.util.List;

import equipement.Equipement;

public class SalleObjet extends Salle{

	public static List<SalleObjet> listeSalleArme = new ArrayList<>();
	public static List<SalleObjet> listeSalleBouclier = new ArrayList<>();
	public static List<SalleObjet> listeSalleSort = new ArrayList<>();
	public static List<SalleObjet> listeSallePotion = new ArrayList<>();
    
	private Equipement equipement;

    public SalleObjet(String typeSalle, String messageSalle, Equipement equipement) 
    {
		super(typeSalle, messageSalle);
		this.equipement = equipement;
	}

	public static void ajouterSalleArme(String message, Equipement equipement)
	{
		SalleObjet salleObjet = new SalleObjet("SalleObjet", message, equipement);
		listeSalleArme.add(salleObjet);
	}
	
	public static void ajouterSalleBouclier(String message, Equipement equipement)
	{
		SalleObjet salleObjet = new SalleObjet("SalleObjet", message, equipement);
		listeSalleBouclier.add(salleObjet);
	}
	
	public static void ajouterSalleSort(String message, Equipement equipement)
	{
		SalleObjet salleObjet = new SalleObjet("SalleObjet", message, equipement);
		listeSalleSort.add(salleObjet);
	}
	
	public static void ajouterSallePotion(String message, Equipement equipement)
	{
		SalleObjet salleObjet = new SalleObjet("SalleObjet", message, equipement);
		listeSallePotion.add(salleObjet);
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
