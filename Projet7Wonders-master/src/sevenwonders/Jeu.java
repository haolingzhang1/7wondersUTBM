/**************************************************************************
 * Source File	:  Jeu.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Jeu
 **************************************************************************/

package sevenwonders;

import java.util.*;
import javax.swing.JOptionPane;
//import org.json;

public class Jeu  
{ 
	private Age ageEnCours;
	private int tourEnCours;
	Age agesJeu[];
	Joueur joueurs[];
	
	public Jeu()
	{
		agesJeu = new Age[3];
		tourEnCours = 0;
	}

	public void PreparerJeu()
	{
		//TODO: lire les cartes à partir du fichier JSON et constituer les paquets en les mélangeant
		ArrayList<CarteAge> paquetAge1 = new ArrayList<CarteAge>();
		ArrayList<CarteAge> paquetAge2 = new ArrayList<CarteAge>();
		ArrayList<CarteAge> paquetAge3 = new ArrayList<CarteAge>();
				
		// Creation des ages
		agesJeu[0] = new Age(1, paquetAge1, this);
		agesJeu[1] = new Age(2, paquetAge2, this);
		agesJeu[2] = new Age(3, paquetAge3, this);
		
		ageEnCours = agesJeu[0];
				
		int nbJoueurs = Integer.parseInt(JOptionPane.showInputDialog("Veuillez saisir le nombre de joueurs"));
		joueurs = new Joueur[nbJoueurs];
		for(int i = 0; i < nbJoueurs; i++)
		{
			// Demander au joueur son nom
			String nomJoueur = JOptionPane.showInputDialog("Nom du joueur " + i+1);
			Joueur leJoueur = new Joueur(nomJoueur);
			
			// TODO: Demander au joueur son Cursus
			
			// Donner 7 cartes au joueur
			for(int c = 0; c < 7; c++)
			{
				leJoueur.main.add(DonnerCarte());
			}
			
			this.joueurs[i] = leJoueur;
		}
	}

	public void ChangerAge()
	{
		if(tourEnCours == 6)
		{
			for(int j = 0; j < joueurs.length; j++)
			{
				int puissanceMilitaire = joueurs[j].CalculerPuissanceMilitaire();
				int puissanceMilitaireGauche, puissanceMilitaireDroite, idJoueurGauche, idJoueurDroit;
				if(j - 1 <= - 1)
					idJoueurGauche = joueurs.length - 1;
				else
					idJoueurGauche = j-1;		
				
				if(j + 1 >= joueurs.length)
					idJoueurDroit = 0;
				else
					idJoueurDroit = j + 1;
				
				puissanceMilitaireGauche = joueurs[idJoueurGauche].CalculerPuissanceMilitaire();
				if(puissanceMilitaireGauche < puissanceMilitaire)
				{
					joueurs[j].ptsInfluence += 2;
					joueurs[idJoueurGauche].ptsInfluence -= 2;
				}
				if(puissanceMilitaireGauche > puissanceMilitaire)
				{
					joueurs[j].ptsInfluence -= 2;
					joueurs[idJoueurGauche].ptsInfluence += 2;
				}
				
				puissanceMilitaireDroite = joueurs[idJoueurDroit].CalculerPuissanceMilitaire();
				if(puissanceMilitaireDroite < puissanceMilitaire)
				{
					joueurs[j].ptsInfluence += 2;
					joueurs[idJoueurDroit].ptsInfluence -= 2;
				}
				if(puissanceMilitaireDroite > puissanceMilitaire)
				{
					joueurs[j].ptsInfluence -= 2;
					joueurs[idJoueurDroit].ptsInfluence += 2;
				}
			}
			
			switch(ageEnCours.getNumero())
			{
			case 1:
				ageEnCours = agesJeu[1];
			case 2:
				ageEnCours = agesJeu[2];
			case 3:
				TerminerJeu();
			}
		}
		
		tourEnCours = 0;
	}

	public void TerminerJeu()
	{
		int[] credits = new int[joueurs.length];
		for(int j = 0; j < joueurs.length; j++)
		{
			credits[j] = 0;
			
			// Un crédit par pt d'influence
			credits[j] += joueurs[j].ptsInfluence;
			
			// Un credit toutes les 3 bieres
			credits[j] += joueurs[j].quantiteBiere / 3;
			
			// Attribuer les credits conferes par les etapes du cursus du joueur
			for(EtapeDeCursus e : joueurs[j].cursusJoueur.etapesCursus)
			{
				if(e.estFranchie) credits[j] += e.nbDeCreditsECTS;
			}
			
			// Idem avec les salles de cours
			// Pour chaque association, attribuer des points pour chaque carte valorisee par l'association
			// Attribuer des credits en fonction du nombre de cartes d'une meme langue possedee et des groupes de langue
			Map<String, Integer> langues = new HashMap<String, Integer>();
			for(CarteAge s : joueurs[j].cursusJoueur.cartesConstruites)
			{
				if(s.getClass() == SalleDeCours.class)
				{
					credits[j] += ((SalleDeCours) s).apportPoints;
				}
				else if(s.getClass() == Association.class)
				{
					int nbCarte = joueurs[j].cursusJoueur.NbCarte(((Association) s).typeCarte);
					credits[j] += nbCarte * ((Association) s).nbCreditsDonnes;
				}
				else if(s.getClass() == Langue.class) {
					String nomLangue = ((Langue)s).nomLangue;
					int currentValue = langues.get(nomLangue);
					if(langues.containsKey(nomLangue) == false)
						langues.put(nomLangue, 1);
					else
						langues.put(nomLangue, currentValue + 1);
				}
				
				int minValue = 999;
				for(Map.Entry<String, Integer> i : langues.entrySet())
				{
					if(i.getValue() < minValue)
						minValue = i.getValue();
					
					credits[j] += i.getValue() * i.getValue();
				}
				credits[j] += 7 * minValue;
			}
		}
		
		// Comparer les nombres de points et déclarer un vainqueur.
		int maxScore = 0, joueurGagnant = 0;
		for(int i = 0; i < credits.length; i++)
		{
			if(credits[i] > maxScore) 
			{
				maxScore = credits[i];
				joueurGagnant = i;
			}
		}
		
		JOptionPane.showMessageDialog(null, "Le Joueur " + joueurGagnant + " remporte la partie avec " + maxScore + " credits !");
	}
	
	public CarteAge DonnerCarte()
	{
		if(ageEnCours.paquetDeCartes.size() == 0)
		{
			return null;
		}
		else
		{
			// On récupère la dernière carte du paquet, et cette carte n'est plus dans le paquet après exécution.
			CarteAge laCarte = ageEnCours.paquetDeCartes.get(ageEnCours.paquetDeCartes.size());
			ageEnCours.paquetDeCartes.remove(ageEnCours.paquetDeCartes.size());

			return laCarte;
		}
	}
}
