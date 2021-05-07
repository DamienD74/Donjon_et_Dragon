package equipement.protection;

import equipement.Equipement;

public class Bouclier extends Equipement{

	private int armure;
	
    public Bouclier(String nom, String classe, int armure) {
		super(nom, classe);
		this.armure = armure;
	}

    public int getArmure()
    {
        return this.armure;
    }

    public void setArmure(int armure)
    {
        this.armure = armure;
    }
}
