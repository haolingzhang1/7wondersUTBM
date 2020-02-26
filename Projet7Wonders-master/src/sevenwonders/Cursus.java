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
		// V�rifier si elle n'est pas d�j� construite
		if(CarteDejaConstruite(carte))
		{
			throw new AlreadyBuiltException("Carte d�j� construite !");
		}
		
		// V�rifier chainage : si une carte d�j� construite permet la construction gratuite d'un autre b�timent.
		boolean skipResourceCheck = false;
		for (CarteAge c : cartesConstruites)
		{
			if(carte.nomCarte == c.nomCarteChainage)
			{
				skipResourceCheck = true;
			}
		}
		
		// V�rifier si le joueur a les ressources n�cessaires pour la construction de la carte.
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
			// Si une carte construite est �gale � la carte pass�e en param�tre, c'est qu'elle est d�j� construite.
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
		// V�rifier dans les ressources du cursus (les CartesAges d�j� construites)
		for(Ressource r : ressourcesPossedees)
		{
			if(r.equals(ressource))
			{
				return true;
			}
		}
		
		// V�rifier si le joueur a achet� la ressource
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
		// Une connaissance == �tape de la merveille
		try
		{
			int etapeAFranchir = getEtapeAFranchir();
			
			// V�rifier que les ressources sont disponibles
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
		
		throw new AllStepsCrossedException("�tapes toutes franchies !");
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


