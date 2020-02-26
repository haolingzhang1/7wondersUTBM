package sevenwonders;
/**************************************************************************
 * Source File	:  Lieu_festif.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Lieu_festif
 **************************************************************************/

import java.util.*;

public  class LieuFestif  extends CarteAge 
{ 
	protected int apportInfluence;	

	public LieuFestif()
	{
		super();
		apportInfluence = 0;
	}
	
	public LieuFestif(int _apportInfluence)
	{
		super();
		apportInfluence = _apportInfluence;
	}
}