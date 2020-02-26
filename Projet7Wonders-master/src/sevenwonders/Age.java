/**************************************************************************
* Source File	:  Age.java
* Author                   :  Quentin Pugeat  
* Project name         :  LO43 - 7 Wonders (UTBM Edition)* Created                 :  05/12/2019
* Modified   	:  11/12/2019
* Description	:  Definition of the class Age
**************************************************************************/
package sevenwonders;

import java.util.*;

public class Age  
{ 
	private int numero;
	ArrayList<CarteAge> paquetDeCartes;
	Jeu ageJeu;
	
	public Age(int _numero, ArrayList<CarteAge> _paquet, Jeu _ageJeu)
	{
		numero = _numero;
		paquetDeCartes = _paquet;
		ageJeu = _ageJeu;
	}
	
	public int getNumero()
	{
		return numero;
	}
}


