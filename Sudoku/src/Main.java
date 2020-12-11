import java.util.*;

public class Main 
{
	
	public static void main(String[] args ) 
	{
		System.out.println("\n				Résolution du Sudoku \n");

		// Voici une grille qui peut être résolu
		String[] sudoku = new String[] 
		{
				"3#65#84##",
				"52#######",
				"#87####31",
				"##3#1##8#",
				"9##863##5",
				"#5##9#6##",
				"13####25#",
				"#######74",
				"##52#63##"
		};

		// Voici une grille qui ne peut pas être résolu
		/*String[] sudoku = new String[]
		{
				"3#65#84##",
				"52###1###",
				"#87####31",
				"##3#1##8#",
				"9##863##5",
				"#5##9#6##",
				"13####25#",
				"#######74",
				"##52#63##"
		};*/

		Sudoku grille = new Sudoku(sudoku);

		System.out.println("\n				Voici la grille de départ :\n");
		grille.dessine();
		grille.resoudre();

	}
}
