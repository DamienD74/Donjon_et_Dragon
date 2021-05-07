package equipement;

public class Equipement {
	
    private String nom;
    private String classe;

    public Equipement (String nom, String classe)
    {
    	this.nom = nom;
    	this.classe = classe;
    }
    
    public String getNom()
    {
        return this.nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

	public String getCLasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
}
