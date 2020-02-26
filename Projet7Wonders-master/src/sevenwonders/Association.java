/**************************************************************************
* Source File	:  Association.java
* Author                   :  Quentin Pugeat  
* Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
* Modified   	:  11/12/2019
* Description	:  Definition of the class Association
**************************************************************************/

package sevenwonders;

import java.util.*;

public class Association  extends CarteAge 
{ 
	protected int nbCreditsDonnes;
	protected Class typeCarte;
	protected boolean cartesADisposition;//"F"
	protected java.lang.String apportPoints;
	
	public Association()
	{
		super();
	}
	
	public Association(int _nbCredits, Class _typeCarte)
	{
		super();
		nbCreditsDonnes = _nbCredits;
		typeCarte = _typeCarte;
	}
}


