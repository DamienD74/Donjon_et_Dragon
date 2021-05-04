package Classe;

public abstract class Personnage {
    
    private String nom;

    private int hp;
    private int hpMax;
    private int hpMin;

    private int degat;

    private String classe;
    private String image;

    public String GetNom()
    {
        return this.nom;
    }

    public void SetNom(String nom)
    {
        this.nom = nom;
    }

    public int GetHp()
    {
        return this.hp;
    }

    public void SetHpDepart(int min, int max)
    {
        int range = (max - min) + 1;
        this.hp = (int)(Math.random() * range) + min;
    }

    public int GetHpMax()
    {
        return this.hpMax;
    }

    public void SetHpMax(int hpMax)
    {
        this.hpMax = hpMax;
    }

    public int GetHpMin()
    {
        return this.hpMin;
    }

    public void SetHpMin(int hpMin)
    {
        this.hpMin = hpMin;
    }

    public int GetDegat()
    {
        return this.degat;
    }

    public void SetDegat(int degat)
    {
        this.degat = degat;
    }
    
    public String GetClasse()
    {
        return this.classe;
    }

    public String SetClasse(String classe)
    {
        return this.classe = classe;
    }


    public String GetImage()
    {
        return this.image;
    }

    public void SetImage(String image)
    {
        this.image = image;
    }
}