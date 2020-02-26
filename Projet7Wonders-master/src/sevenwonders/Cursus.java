/**************************************************************************
 * Source File	:  Cursus.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Cursus
 **************************************************************************/
package sevenwonders;

import java.util.*;

public class Cursus  
{
	protected Ressource ressourceParDefaut;	
	Ressource ressourcesPossedees[];
	CarteAge cartesConstruites[];
	EtapeDeCursus etapesCursus[];
	Joueur proprietaire;

	public boolean AjouterCarteAge(CarteAge carte) throws AlreadyBuiltException, MissingResourceException
	{
		// Vérifier si elle n'est pas déjà construite
		if(CarteDejaConstruite(carte))
		{
			throw new AlreadyBuiltException("Carte déjà construite !");
		}
		
		// Vérifier chainage : si une carte déjà construite permet la construction gratuite d'un autre bâtiment.
		boolean skipResourceCheck = false;
		for (CarteAge c : cartesConstruites)
		{
			if(carte.nomCarte == c.nomCarteChainage)
			{
				skipResourceCheck = true;
			}
		}
		
		// Vérifier si le joueur a les ressources nécessaires pour la construction de la carte.
		if(skipResourceCheck == false)
		{
			for(Ressource r : carte.ressourcesRequises)
			{
				if(RessourceEstDisponible(r) == false)
				{
					throw new MissingResourceException("Ressource manquante : " + r.nom);
				}
			}
		}
		
		// Carte pas construite : l'ajouter dans cartesConstruites et ajouter ses ressources dans ressourcesPossedees.
		cartesConstruites[cartesConstruites.length] = carte;
		return true;
	}

	public boolean CarteDejaConstruite(CarteAge carte)
	{
		for(CarteAge c : cartesConstruites)
		{
			// Si une carte construite est égale à la carte passée en paramètre, c'est qu'elle est déjà construite.
			if(c.equals(carte))
			{
				return true;
			}
		}
		
		// Si aucune carte ne correspond, alors la carte n'est pas encore construite.
		return false;
	}

	public boolean RessourceEstDisponible(Ressource ressource)
	{
		// Vérifier dans les ressources du cursus (les CartesAges déjà construites)
		for(Ressource r : ressourcesPossedees)
		{
			if(r.equals(ressource))
			{
				return true;
			}
		}
		
		// Vérifier si le joueur a acheté la ressource
		for(Ressource rr : proprietaire.ressourcesAchetees)
		{
			if(rr.equals(ressource))
			{
				return true;
			}
		}

		return false;
	}

	public boolean AjouterConnaissance(CarteAge carte) throws MissingResourceException, AllStepsCrossedException
	{
		// Une connaissance == Étape de la merveille
		try
		{
			int etapeAFranchir = getEtapeAFranchir();
			
			// Vérifier que les ressources sont disponibles
			if(RessourceEstDisponible(etapesCursus[etapeAFranchir].coutEtape) == false)
			{
				throw new MissingResourceException("Ressource manquante : " + etapesCursus[etapeAFranchir].coutEtape.nom);
			}
			
			etapesCursus[etapeAFranchir].estFranchie = true;
			
			proprietaire.quantiteBiere += etapesCursus[etapeAFranchir].nbBiere;
			
			for(Ressource r : etapesCursus[etapeAFranchir].donneRessource)
			{
				ressourcesPossedees[ressourcesPossedees.length] = r;
			}
			
			return true;
		}
		catch(Exception ex)
		{
			throw ex;
		}
	}
	
	public int getEtapeAFranchir() throws AllStepsCrossedException
	{
		for(EtapeDeCursus e : etapesCursus)
		{
			if(e.estFranchie = false)
			{
				return e.numeroEtape;
			}
		}
		
		throw new AllStepsCrossedException("Étapes toutes franchies !");
	}

	public int NbCarte(java.lang.Class typeCarte)
	{
		int i = 0;	
		for(CarteAge c : cartesConstruites)
		{
			if(c.getClass().equals(typeCarte)) i++;
		}

		return i;
	}
}


