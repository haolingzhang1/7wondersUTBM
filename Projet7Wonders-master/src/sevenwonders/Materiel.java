/**************************************************************************
 * Source File	:  Materiel.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Materiel
 **************************************************************************/

package sevenwonders;

import java.util.*;

public  class Materiel  extends CarteAge 
{ 
	Ressource produitRessource[];
	protected boolean ressourcesADisposition;//"F"
	
	public Materiel()
	{
		super();
	}
	
	public Materiel(Ressource[] _produitRessource, boolean _ressourcesADisposition)
	{
		super();
		produitRessource = _produitRessource;
		ressourcesADisposition = _ressourcesADisposition;
	}
}
