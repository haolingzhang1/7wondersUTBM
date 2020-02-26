/**************************************************************************
 * Source File	:  Salle_de_cours.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Salle_de_cours
 **************************************************************************/

package sevenwonders;

import java.util.*;

public class SalleDeCours  extends CarteAge 
{ 
    protected int apportPoints;	

	public SalleDeCours()
	{
		super();
		apportPoints = 0;
	}
	
	public SalleDeCours(int _apportPoints)
	{
		super();
		apportPoints = _apportPoints;
	}
}
