/**************************************************************************
 * Source File	:  Langue.java
 * Author                   :  Quentin Pugeat  
 * Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  27/11/2019
 * Modified   	:  11/12/2019
 * Description	:  Definition of the class Langue
 **************************************************************************/

package sevenwonders;

import java.util.*;

public class Langue  extends CarteAge 
{ 
	protected String nomLangue;	

	public Langue()
	{
		super();
		nomLangue = "";		
	}
	
	public Langue(String _nomLangue)
	{
		super();
		nomLangue = _nomLangue;
	}
}