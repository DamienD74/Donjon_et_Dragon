package Equipement.Consommable;

import Equipement.Equipement;

public class Consommable extends Equipement{

    private boolean estDegat;
    private int puissance;

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
