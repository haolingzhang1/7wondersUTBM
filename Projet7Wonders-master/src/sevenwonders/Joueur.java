/**************************************************************************
 * Source File	:  Joueur.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Joueur
 **************************************************************************/

package sevenwonders;

import java.util.*;
import javax.swing.JOptionPane;

public class Joueur  
{
	protected int quantiteBiere;
	protected int ptsInfluence;
	protected String nomJoueur;
	Cursus cursusJoueur;
	ArrayList<CarteAge> main;
	Ressource ressourcesAchetees[];
	
	public Joueur()
	{
		this.quantiteBiere = 3;
		this.ptsInfluence = 0;
		this.cursusJoueur = new Cursus();
		this.main = new ArrayList<CarteAge>();
		this.nomJoueur = "Joueur sans nom";
	}
	
	public Joueur(String _nom)
	{
		this.quantiteBiere = 3;
		this.ptsInfluence = 0;
		this.cursusJoueur = new Cursus();
		this.main = new ArrayList<CarteAge>();
		this.nomJoueur = _nom;
	}

	public void ExecuterTour()
	{
		System.out.println("Quelle carte voulez-vous utiliser ? ");
		Scanner sc = new Scanner(System.in);
		int numeroCarte = sc.nextInt();
		System.out.println("Construire ou defausser ? ");
		System.out.println("1 : construire");
		System.out.println("2 : defausser");
		int choix = sc.nextInt();
		sc.close();
		switch(choix) {
		    case 1 : {
			    ConstruireCarte(numeroCarte);
		    }
		    case 2 : {
			    DefausserCarte(numeroCarte);
		    }
	    }
	}

	public boolean RessourcePossedee(Ressource ressource)
	{
		return cursusJoueur.RessourceEstDisponible(ressource);
	}

	public int CalculerPuissanceMilitaire()
	{
		int pm = 0;
		
		// Pour chaque carte construite par le joueur
		for(CarteAge c : cursusJoueur.cartesConstruites) {
			pm += c.puissanceMilitaire; // Compter la puissance militaire qu'elle octroie
		}
		return pm;
	}
	
	public void ConstruireCarte(int emplacementDansMain)
	{
		try 
		{
			// Boîte de dialogue pour demander si le joueur veut construire simplement la carte ou en faire une connaissance
			int res = JOptionPane.showConfirmDialog(null, "S'agit-il d'une connaissance ?", "Construction", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
			if(res==1)
			    cursusJoueur.AjouterConnaissance(main.get(emplacementDansMain));
			else 
				cursusJoueur.AjouterCarteAge(main.get(emplacementDansMain));
		}
		catch(AlreadyBuiltException e)
		{
			//Si deja construite: defausser la carte
			DefausserCarte(emplacementDansMain);
		}
		catch(MissingResourceException f) {
			int achatResDialog = JOptionPane.showConfirmDialog(null, f.getMessage() + " Voulez-vous l'acheter ?", "Construction", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null);
			if(achatResDialog == 1) {
				// TODO: Prendre en compte une éventuelle réduction...
				quantiteBiere -= 2;  // L'achat coute deux bieres  	
				ConstruireCarte(emplacementDansMain); // Relancer la construction.
			}
		} catch (AllStepsCrossedException e) {
			JOptionPane.showMessageDialog(null, "Vous avez deja passe toutes les etapes de votre cursus");
		}	
		
		// Retirer la carte de la main
		main.remove(emplacementDansMain);
	}
	
	public void DefausserCarte(int n)
	{
		// Donner 3 bières au joueur
		quantiteBiere+=3;
		
		// Retirer la carte de la main du joueur
		main.remove(n);
	}
}


