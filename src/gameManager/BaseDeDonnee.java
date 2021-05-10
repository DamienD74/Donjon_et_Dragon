package gameManager;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

import classe.Guerrier;
import classe.Magicien;
import classe.Personnage;

public class BaseDeDonnee {
	
    public static void creerHero (Personnage perso) throws SQLException, ClassNotFoundException
    {
    	String url = "jdbc:mysql://localhost/bdd_Donjon_et_Dragon?characterEncoding=latin1&useConfigs=maxPerformance";
    	String login = "Damien";
    	String password = "Nazarick74200";
    	Connection cn = null;
    	Statement st = null;
    	
		Class.forName("com.mysql.jdbc.Driver");
		cn = (Connection) DriverManager.getConnection(url, login, password);
		st = (Statement) cn.createStatement();
		String ecriture = "INSERT INTO Personnage (nom, image, hp, hpMax, degat) VALUES ('" + perso.getNom() + "', '" + perso.getImage() + "', '" + perso.getHp() + "', '" + perso.getHpMax() + "', '" + perso.getDegat() + "');";
			
		st.executeUpdate(ecriture);
		int id = (int)st.getLastInsertID();
		perso.setId(id);
	
		if (perso instanceof Guerrier)
        {
			Guerrier guerrier = (Guerrier)perso;
			ecriture = "INSERT INTO Guerrier (arme, bouclier, armure, id_Personnage) VALUES ('" + guerrier.getArme() + "', '" + guerrier.getBouclier() + "', '" + guerrier.getArmure() + "', '" + id + "');";
			st.executeUpdate(ecriture);
        }
		else if (perso instanceof Magicien)
        {
			Magicien magicien = (Magicien)perso;
			int estDegatPotion;
			if (magicien.getPotionEstDegat())
			{
				estDegatPotion = 0;
			}
			else
			{
				estDegatPotion = 1;
			}
			ecriture = "INSERT INTO Magicien (sort, potion, potionEstDegat, puissancePotion, id_Personnage) VALUES ('" + magicien.getSort() + "', '" + magicien.getPotion() + "', '" + estDegatPotion + "', '" + magicien.getPuissancePotion() + "', '" + id + "');";
			st.executeUpdate(ecriture);
        }
    }
    
    public static void recupererHeros(GameManager gm) throws SQLException, ClassNotFoundException
    {
    	String url = "jdbc:mysql://localhost/bdd_Donjon_et_Dragon?characterEncoding=latin1&useConfigs=maxPerformance";
    	String login = "Damien";
    	String password = "Nazarick74200";
    	Connection cn = null;
    	Statement st = null;
    	ResultSet res = null;
    	
    	Class.forName("com.mysql.jdbc.Driver");
		cn = (Connection) DriverManager.getConnection(url, login, password);
		st = (Statement) cn.createStatement();
		
		String lectureClasseGuerrier = "SELECT * FROM Guerrier LEFT JOIN Personnage ON Personnage.id = Guerrier.id_Personnage";
		
		res = (ResultSet) st.executeQuery(lectureClasseGuerrier);
		
		while(res.next())
		{			
			int id = res.getInt("id_Personnage");
			String arme = res.getString("arme");
			String bouclier =res.getString("bouclier");
			int armure = res.getInt("armure");		
			String nom = res.getString("nom");
			int hp = res.getInt("hp");
			int hpMax = res.getInt("hpMax");
			int degat = res.getInt("degat");
			String image = res.getString("image");
			
			Guerrier guerrier = new Guerrier(id, nom, hp, hpMax, degat, image, arme, bouclier, armure);
			
			gm.listePersonnage.add(guerrier);		
		}
		
		String lectureClasseMagicien = "SELECT * FROM Magicien LEFT JOIN Personnage ON Personnage.id = Magicien.id_Personnage";
		
		res = (ResultSet) st.executeQuery(lectureClasseMagicien);
		
		while(res.next())
		{			
			String sort = res.getString("sort");
			String potion = res.getString("potion");
			int puissancePotion = res.getInt("puissancePotion");		
			boolean potionEstDegat;
			
			if (res.getInt("potionEstDegat") == 1)
			{
				potionEstDegat = false;
			}
			else
			{
				potionEstDegat = true;
			}
			
			int id = res.getInt("id");
			String nom = res.getString("nom");
			int hp = res.getInt("hp");
			int hpMax = res.getInt("hpMax");
			int degat = res.getInt("degat");
			String image = res.getString("image");
			
			Magicien magicien = new Magicien(id, nom, hp, hpMax, degat, image, sort, potion, potionEstDegat, puissancePotion);
			
			gm.listePersonnage.add(magicien);
		}
    }
    
    public static void updateHero(Personnage perso) throws SQLException, ClassNotFoundException
    {
    	String url = "jdbc:mysql://localhost/bdd_Donjon_et_Dragon?characterEncoding=latin1&useConfigs=maxPerformance";
    	String login = "Damien";
    	String password = "Nazarick74200";
    	Connection cn = null;
    	Statement st = null;
    	
    	Class.forName("com.mysql.jdbc.Driver");
		cn = (Connection) DriverManager.getConnection(url, login, password);
		st = (Statement) cn.createStatement();
		
		String ecriture;
		
		ecriture = "UPDATE Personnage SET nom = '" + perso.getNom() + "', image = '" + perso.getImage() + "', hp = '" + perso.getHp() + "', hpMax = '" + perso.getHpMax() + "', degat = '" + perso.getDegat() + "' WHERE id = " + perso.getId();
		st.executeUpdate(ecriture);
		
		if (perso instanceof Guerrier)
		{
			Guerrier guerrier = (Guerrier)perso;
			ecriture = "UPDATE Guerrier SET arme = '" + guerrier.getArme() + "', bouclier = '" + guerrier.getBouclier() + "', armure = '" + guerrier.getArmure() + "' WHERE id_Personnage = " + guerrier.getId();
			st.executeUpdate(ecriture);
		}
		else if (perso instanceof Magicien)
		{
			Magicien magicien = (Magicien)perso;
			
			int potionEstDegat;
			
			if (magicien.getPotionEstDegat())
			{
				potionEstDegat = 0;
			}
			else
			{
				potionEstDegat = 1;
			}
			
			ecriture = "UPDATE Magicien SET sort = '" + magicien.getSort() + "', potion = '" + magicien.getPotion() + "', potionEstDegat = '" + potionEstDegat + "', puissancePotion = '" + magicien.getPuissancePotion() + "' WHERE id_Personnage = " + magicien.getId();
			st.executeUpdate(ecriture);
		}
    }
    
    public static void deleteHero(Personnage perso) throws SQLException, ClassNotFoundException
    {
    	String url = "jdbc:mysql://localhost/bdd_Donjon_et_Dragon?characterEncoding=latin1&useConfigs=maxPerformance";
    	String login = "Damien";
    	String password = "Nazarick74200";
    	Connection cn = null;
    	Statement st = null;
    	
    	Class.forName("com.mysql.jdbc.Driver");
		cn = (Connection) DriverManager.getConnection(url, login, password);
		st = (Statement) cn.createStatement();
		
		String ecriture;
		
		if (perso instanceof Guerrier)
		{
			ecriture = "DELETE FROM Guerrier WHERE id_Personnage = " + perso.getId();
			st.executeUpdate(ecriture);
		}
		else if (perso instanceof Magicien)
		{	
			ecriture = "DELETE FROM Magicien WHERE id_Personnage = " + perso.getId();
			st.executeUpdate(ecriture);
		}
		
		ecriture = "DELETE FROM Personnage WHERE id = " + perso.getId();
		st.executeUpdate(ecriture);
    }
    
    public static void deleteAllHeros() throws SQLException, ClassNotFoundException
    {
    	String url = "jdbc:mysql://localhost/bdd_Donjon_et_Dragon?characterEncoding=latin1&useConfigs=maxPerformance";
    	String login = "Damien";
    	String password = "Nazarick74200";
    	Connection cn = null;
    	Statement st = null;
    	
    	Class.forName("com.mysql.jdbc.Driver");
		cn = (Connection) DriverManager.getConnection(url, login, password);
		st = (Statement) cn.createStatement();
		
		String ecriture;
				
		ecriture = "DELETE FROM Guerrier";
		st.executeUpdate(ecriture);
		
		ecriture = "DELETE FROM Magicien";
		st.executeUpdate(ecriture);
		
		ecriture = "DELETE FROM Personnage";
		st.executeUpdate(ecriture);
    }
}
