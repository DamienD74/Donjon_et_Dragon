package Equipement.Arme;

import Equipement.Equipement;

public class Arme extends Equipement{
   
    private int degat;

    public int GetDegat()
    {
        return this.degat;
    }

    public void SetDegat(int degat)
    {
        this.degat = degat;
    }
}
