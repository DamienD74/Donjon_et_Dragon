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
		
		System.out.println(id);
	
		if (perso instanceof Guerrier)
        {
			Guerrier guerrier = (Guerrier)perso;
			ecriture = "INSERT INTO Guerrier (nomArme, nomBouclier, armure, id_Personnage) VALUES ('" + guerrier.getArme() + "', '" + guerrier.getBouclier() + "', '" + guerrier.getArmure() + "', '" + id + "');";
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
			ecriture = "INSERT INTO Magicien (nomSort, nomPotion, potionEstDegat, puissancePotion, id_Personnage) VALUES ('" + magicien.getSort() + "', '" + magicien.getPotion() + "', '" + estDegatPotion + "', '" + magicien.getPuissancePotion() + "', '" + id + "');";
			st.executeUpdate(ecriture);
        }
    }
}
