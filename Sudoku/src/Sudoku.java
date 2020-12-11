import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;

public class Sudoku 
{
	private Model modele = new Model("Sudoku");

	private int taille = 9;
		
	private Tab_Sudo[][] sudoku = new Tab_Sudo[taille][taille];

	private boolean resolu = false;
	
	public Sudoku(String[] grille) 
	{
		analyse(grille);
	}
	
	
	private void analyse(String[] grille) 
	{
		int cpt_ligne = 0;

		for ( String ligne : grille) 
		{
			int cpt_col = 0;

			for (char val : ligne.toCharArray() ) 
			{
				
				if (val != '#') 
				{
					sudoku[cpt_ligne][cpt_col] = new IntValue(val, modele); 
				}
				else 
				{
					sudoku[cpt_ligne][cpt_col] = new VarValue(cpt_ligne, cpt_col, modele);
				}
				
				cpt_col ++;
			}
			
			cpt_ligne ++;
		}
	}
	
	
	public void resoudre()
	{
		
		// On va vérifier que les contraintes du sudoku sont bien respecté avec allDifferent() et on "sauvegarde" l'info avec la post()
		verifRegles();
		
		// On vérifie que notre modèle pour le sudoku est résolu ou non
		resolu = modele.getSolver().solve();
		
		if (resolu == true)
		{
			System.out.println("				Voici la grille résolu : \n");
			dessine();
		}
		else
		{
			System.out.println("			Cette grille ne possède pas de solution !\n");
		}
	}
	
	private void verifRegles()
	{
		for ( int i = 0; i < taille; i++) 
		{
			modele.allDifferent(getCol(i)).post();
			modele.allDifferent(getLigne(i)).post();
			modele.allDifferent(getCarre(i)).post();
		}
	}

	private IntVar[] getCol(int col) 
	{
		IntVar[] resultat = new IntVar[taille];

		for ( int i = 0; i < taille; i++) 
		{
			resultat[i] = sudoku[i][col].getVar();
		}
		
		return resultat;
	}


	private IntVar[] getLigne(int ligne) 
	{
		IntVar[] resultat = new IntVar[taille];
		
		for ( int i = 0; i < taille; i++) 
		{
			resultat[i] = sudoku[ligne][i].getVar(); 
		}
		
		return resultat;
	}
	
	
	private IntVar[] getCarre(int i) 
	{
		IntVar[] resultat = new IntVar[taille];
		
		int limite_ligne = (i / 3) * 3;
		int limite_col = (i % 3) * 3;
		
		int parcoureur = 0;
		
		for ( int col = 0; col < 3; col++ ) 
		{
			for ( int ligne = 0; ligne < 3; ligne++ )
			{
				resultat[parcoureur++] = sudoku[ligne + limite_ligne][col + limite_col].getVar(); 
				
			}
		}
		
		return resultat;
	}
	
	
	public void dessine() 
	{
		int cpt_ligne = 0;

		for (Tab_Sudo[] ligne : sudoku) 
		{
			if ( cpt_ligne % 3 == 0 ) 
			{
				System.out.println("			-----------------------------------------");
				System.out.println("			-----------------------------------------");
			}

			System.out.print("			");
			int cpt_col = 0;
			
			for( Tab_Sudo Tab_Sudo : ligne) 
			{
				
				if ( cpt_col % 3 == 0 ) 
				{
					System.out.print("||");
				}
				else
				{
					System.out.print("|");
				}
				
				System.out.print(" " + Tab_Sudo.toString(resolu) + " ");
				
				cpt_col ++;
				
			}

			System.out.print("||\n");
			
			cpt_ligne ++;
		}

		System.out.println("			-----------------------------------------");
		System.out.println("			-----------------------------------------\n");
		
	}

}
