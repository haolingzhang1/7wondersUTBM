/**************************************************************************
 * Source File	:  Service.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Service
 **************************************************************************/

package sevenwonders;

import java.util.*;

public class Service  extends CarteAge 
{
	Ressource produitRessources[];
	
	public Service()
	{
		super();
		produitRessources = new Ressource[1];
	}
	
	public Service(Ressource[] _produitRessources)
	{
		super();
		produitRessources = _produitRessources;
	}
}