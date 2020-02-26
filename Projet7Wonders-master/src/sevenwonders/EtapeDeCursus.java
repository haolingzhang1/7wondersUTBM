/**************************************************************************
 * Source File	:  EtapeDeCursus.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  11/12/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class EtapeDeCursus
 **************************************************************************/
package sevenwonders;

import java.util.*;

public  class EtapeDeCursus  
{ 	
	protected int numeroEtape;
	protected boolean estFranchie;
	protected int nbForceMilitaire;
	protected int nbDeCreditsECTS;
	protected int nbBiere;
	protected Ressource coutEtape;	
	Ressource donneRessource[];
	
	public EtapeDeCursus()
	{
		numeroEtape = 0;
		estFranchie = false;
		nbForceMilitaire = 0;
		nbDeCreditsECTS = 0;
		nbBiere = 0;
		coutEtape = null;
		donneRessource = new Ressource[1];
	}
}


