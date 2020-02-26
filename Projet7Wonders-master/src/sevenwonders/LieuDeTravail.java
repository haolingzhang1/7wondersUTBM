/**************************************************************************
 * Source File	:  Lieu_de_travail.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Lieu_de_travail
 **************************************************************************/

package sevenwonders;

import java.util.*;

public class LieuDeTravail  extends CarteAge 
{ 
	Ressource ReduireGauche[];
	Ressource ReduireDroite[];
	Ressource gainRessources[];
	protected java.lang.String apportBiere;//biere(regle)
	protected java.lang.String apportPoints;//points
	protected int biereGagne;//(quantite)
	protected int pointsGagnes;
	protected boolean ressourcesADisposition;//"F"

	public LieuDeTravail()
	{
		super();
	}
}
