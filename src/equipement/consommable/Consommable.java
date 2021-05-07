package equipement.consommable;

import equipement.Equipement;

public class Consommable extends Equipement{

	private boolean estDegat;
    private int puissance;
    
    public Consommable(String nom, String classe, boolean estDegat, int puissance) 
    {
		super(nom, classe);
		this.estDegat = estDegat;
		this.puissance = puissance;
	}

    public boolean getEstDegat()
    {
        return this.estDegat;
    }

    public void setEstDegat(boolean estDegat)
    {
        this.estDegat = estDegat;
    }

    public int getPuissance()
    {
        return this.puissance;
    }

    public void setPuissance(int puissance)
    {
        this.puissance = puissance;
    }
}
