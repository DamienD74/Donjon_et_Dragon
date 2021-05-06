package Donjon;

import java.util.ArrayList;
import java.util.List;

public class SalleEvenement extends Salle{
	
    public static List<SalleEvenement> listeSalleEvenement = new ArrayList<>();
    
	private boolean estSalleSoin;
	private boolean estSalleDegat;
	private boolean estSalleBonusVie;
	private int puissance;

	public static void ajouterSalleEvenement(String message, boolean estSalleSoin, boolean estSalleDegat, boolean estSalleBonusVie, int puissance)
	{
		SalleEvenement salleEvenement = new SalleEvenement();
		salleEvenement.setTypeSalle("SalleEvenement");
		salleEvenement.setMessageSalle(message);
		salleEvenement.setEstSalleSoin(estSalleSoin);
		salleEvenement.setEstSalleDegat(estSalleDegat);
		salleEvenement.setEstSalleBonusVie(estSalleBonusVie);
		salleEvenement.setPuissance(puissance);
		listeSalleEvenement.add(salleEvenement);
	}
	
	public boolean getSalleSoin() 
	{
		return estSalleSoin;
	}

	public void setEstSalleSoin(boolean estSalleSoin) 
	{
		this.estSalleSoin = estSalleSoin;
	}
	
	public boolean getSalleDegat() 
	{
		return estSalleDegat;
	}

	public void setEstSalleDegat(boolean estSalleDegat) 
	{
		this.estSalleDegat = estSalleDegat;
	}
	
	public boolean getSalleBonusVie() 
	{
		return estSalleBonusVie;
	}

	public void setEstSalleBonusVie(boolean estSalleBonusVie) 
	{
		this.estSalleBonusVie = estSalleBonusVie;
	}
	
	public int getPuissance() 
	{
		return puissance;
	}

	public void setPuissance(int puissance) 
	{
		this.puissance = puissance;
	}
}