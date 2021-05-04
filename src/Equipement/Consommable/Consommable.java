package Equipement.Consommable;

import Equipement.Equipement;

public class Consommable extends Equipement{

    private boolean estDegat;
    private int puissance;

    public boolean GetEstDegat()
    {
        return this.estDegat;
    }

    public void SetEstDegat(boolean estDegat)
    {
        this.estDegat = estDegat;
    }

    public int GetPuissance()
    {
        return this.puissance;
    }

    public void SetPuissance(int puissance)
    {
        this.puissance = puissance;
    }
}
