package Ennemi;

public class Ennemi {
	
	private String nom;
	private int hp;
	private int hpMax;
	private int degat;
	
	private String image;
	
	public void montrerStat()
	{
        System.out.println("");
        System.out.println("Nom: " + getNom());
        System.out.println("Hp: " + getHp() + " (" + getHpMax() + ")");
        System.out.println("Degat: " + getDegat());
	}
	
	public boolean subirDegat(int degat)
	{
		setHp(getHp() - degat);
		
		if (getHp() <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String getNom() 
	{
		return nom;
	}
	public void setNom(String nom) 
	{
		this.nom = nom;
	}
	
	public int getHp() 
	{
		return hp;
	}	
	
	public void setHp(int hp) 
	{
		this.hp = hp;
	}
	
	public int getHpMax()
	{
		return this.hpMax;
	}
	
	public void setHpMax(int hp)
	{
		this.hpMax = hp;
	}
	
	public int getDegat() 
	{
		return degat;
	}
	public void setDegat(int degat) 
	{
		this.degat = degat;
	}
	
	public String getImage() 
	{
		return image;
	}
	public void setImage(String image) {
		
		this.image = image;
	}
}
