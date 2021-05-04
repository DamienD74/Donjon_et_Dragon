package GameManager;

import java.util.Scanner; 
import Classe.*;
import Equipement.Arme.ListeArme;
import Equipement.Arme.ListeSort;
import Equipement.Protection.ListeBouclier;
import Equipement.Consommable.ListePotion;

import java.util.ArrayList;
import java.util.List;

public class GameManager{

    public List<Personnage> listePersonnage = new ArrayList<>();
    private static boolean hasReqExit = false;
    private static boolean estChoixClasse = false;
    private static boolean estAvantPartie = false;

    public Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
    	
    	GameManager gm = new GameManager();
    	
    	gm.InitialisationArme();
    	gm.InitialisationBouclier();
    	gm.InitialisationSort();
    	gm.InitialisationPotion();   
    	
        do
        {
            try {
    			gm.Menu();
    		} catch (Exception e) {
    			 System.out.println(e);
    		}
        }
        while(!hasReqExit);
    }

    void InitialisationArme()
    {
        ListeArme.AjouterArme("Epée Rouillé", 10);
        ListeArme.AjouterArme("Dague Rouillé", 7);
        ListeArme.AjouterArme("Bâton", 5);
    }

    void InitialisationBouclier()
    {
        ListeBouclier.AjouterBouclier("Bouclier Rouillé", 3);
        ListeBouclier.AjouterBouclier("Bouclier en bois", 2);
        ListeBouclier.AjouterBouclier("Bouclier Tonneau", 1);
    }

    void InitialisationSort()
    {
        ListeSort.AjouterSort("Boule De Feu", 15);
        ListeSort.AjouterSort("Stalactite", 12);
        ListeSort.AjouterSort("Prout", 8);
    }

    void InitialisationPotion()
    {
        ListePotion.AjouterPotion("Potion de soin", 10, false);
        ListePotion.AjouterPotion("Potion de degat", 20, true);
    }

    void Menu () throws Exception
    {
    	do
    	{        	
    		System.out.println("Choisissez NewGame ou Quittez (vous pouvez quittez a tout moment), vous pourrez utiliser Menu a tout moment pour revenir au menu");

            String value = sc.nextLine();

            if (value.startsWith("NewGame"))
            {
                do
                {
                    try {
                        ChoixClasse();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estChoixClasse);
            }
            else if (value.startsWith("Quittez"))
            {
                Quittez();
            }
            else
            {
            	throw new Exception("Entrée invalide");
            }
    	}while(!hasReqExit);     
    }

    void AvantPartie() throws Exception
    {
    	do
    	{
    		estAvantPartie = true;
 	        System.out.println("");
	        System.out.println("Voulez vous ajouter un autre personnage ou commencer la partie ? Ajouter/Commencer");
	        System.out.println("Pour montrer la liste de vos personnage taper Liste");
	        String value = sc.nextLine();
	        
	        if (value.startsWith("Ajouter"))
	        {
                do
                {
                    try {
                        ChoixClasse();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estChoixClasse);
	        }
	        else if (value.startsWith("Commencer"))
	        {
	            System.out.println("");
	            System.out.println("Plus Tard");
	        }
	        else if (value.startsWith("Liste"))
	        {
	        	for (Personnage perso : listePersonnage)
	        	{
	        		if (perso.GetClasse() == "Guerrier")
	        		{
	        			MontrerStatGuerrier((Guerrier)perso);
	        		}
	        		else if (perso.GetClasse() == "Magicien")
	        		{
	        			MontrerStatMagicien((Magicien)perso);
	        		}     		
	        	}
	        	AvantPartie();
	        }
	        else if (value.startsWith("Quittez"))
	        {
	            Quittez();
	        }
	        else
	        {
            	throw new Exception("Entrée invalide");
	        }
    	}while(!hasReqExit);     
    }

    void ChoixClasse () throws Exception
    {
    	do
    	{
        	estChoixClasse = true;
            System.out.println("");
            System.out.println("Choisissez votre classe Guerrier ou Magicien ?");

            String value = sc.nextLine();

            if (value.startsWith("Guerrier"))
            {
                CreationGuerrier();
            }
            else if (value.startsWith("Magicien"))
            {
                CreationMagicien();
            }
            else if (value.startsWith("Menu"))
            {
            	estChoixClasse = false;
                return;
            }
            else if (value.startsWith("Quittez"))
            {
                Quittez();
            }
            else
            {
            	throw new Exception("Entrée invalide");
            }
    	}while(!hasReqExit);    
    }

    void CreationGuerrier()
    {   	
    	do
    	{
            Guerrier guerrier = new Guerrier();
            guerrier.Initialisation(guerrier);

            System.out.println("");
            System.out.println("Vous avez choisi guerrier. Quelles et votre nom ? ");
            String value = sc.nextLine();

            if (value.startsWith("Quittez"))
            {
                Quittez();
            }
            else
            {
                guerrier.SetNom(value);
                System.out.println("");
                System.out.println("Initialisation de vos statistique et objet de départ :");
                
                MontrerStatGuerrier(guerrier);

                listePersonnage.add(guerrier);

                do
                {
                    try {
                        AvantPartie();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
    	}while(!hasReqExit);    
    }

    void CreationMagicien()
    {
    	do
    	{
            Magicien magicien = new Magicien();
            magicien.Initialisation(magicien);

            System.out.println("");
            System.out.println("Vous avez choisi un magicien. Quelles et votre nom ? ");
            String value = sc.nextLine();

            if (value.startsWith("Quittez"))
            {
                Quittez();
            }
            else
            {
                magicien.SetNom(value);

                System.out.println("");
                System.out.println("Initialisation de vos statistique et objet de départ :");
                
                MontrerStatMagicien(magicien);

                listePersonnage.add(magicien);

                do
                {
                    try {
                        AvantPartie();
            		} catch (Exception e) {
            			 System.out.println(e);
            		}
                }
                while(estAvantPartie);
            }
    	}while(!hasReqExit);    
    }
    
    void MontrerStatGuerrier(Guerrier guerrier)
    {
        System.out.println("");
        System.out.println("Nom: " + guerrier.GetNom());
        System.out.println("Hp: " + guerrier.GetHp());
        System.out.println("Arme: " + guerrier.GetArme() + " (" + guerrier.GetDegat() + ")");
        System.out.println("Bouclier: " + guerrier.GetBouclier() + " (" + guerrier.GetArmure() + ")");
    }
    
    void MontrerStatMagicien(Magicien magicien)
    {
        System.out.println("");
        System.out.println("Nom: " + magicien.GetNom());
        System.out.println("Hp: " + magicien.GetHp());
        System.out.println("Sort: " + magicien.GetSort() + " (" + magicien.GetDegat() + ")");
        if (magicien.GetPotionEstDegat())
        {
            System.out.println("Potion: " + magicien.GetPotion() + " (" + magicien.GetPuissancePotion() + " de dégat)");
        }
        else
        {
            System.out.println("Potion: " + magicien.GetPotion() + " (" + magicien.GetPuissancePotion() + " de soin)");
        }
    }

    void Quittez ()
    {
        System.out.println("");
        System.out.println("Vous avez quitter le jeu !");
        sc.close();
        hasReqExit = true;
        estChoixClasse = false;
        estAvantPartie = false;
    }
}
