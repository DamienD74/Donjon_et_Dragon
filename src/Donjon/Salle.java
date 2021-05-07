package Donjon;

public class Salle {

	private String typeSalle;
	private String messageSalle;

	public Salle (String typeSalle, String messageSalle)
	{
		this.typeSalle = typeSalle;
		this.messageSalle = messageSalle;
	}
	
	public String getTypeSalle() 
	{
		return typeSalle;
	}

	public void setTypeSalle(String typeSalle) 
	{
		this.typeSalle = typeSalle;
	}
	
	public String getMessageSalle() 
	{
		return messageSalle;
	}

	public void setMessageSalle(String messageSalle)
	{
		this.messageSalle = messageSalle;
	}
}
