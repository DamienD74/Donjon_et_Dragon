package equipement.arme;

import equipement.Equipement;

public class Arme extends Equipement{
   
	private int degat;
	
    public Arme(String nom, String classe, int degat) {
		super(nom, classe);
		this.degat = degat;
	}

    public int getDegat()
    {
        return this.degat;
    }

    public void setDegat(int degat)
    {
        this.degat = degat;
    }
}
