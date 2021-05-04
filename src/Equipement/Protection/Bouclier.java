package Equipement.Protection;

import Equipement.Equipement;

public class Bouclier extends Equipement{
    
    private int armure;

    public int GetArmure()
    {
        return this.armure;
    }

    public void SetArmure(int armure)
    {
        this.armure = armure;
    }
}
