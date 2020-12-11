import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;

public class VarValue implements Tab_Sudo
{
	
	private final IntVar var; 

	public VarValue(int ligne, int col, Model model) 
	{
		var = model.intVar("" + ligne + "" + col, 1, 9);
	}


	@Override
	public IntVar getVar()
	{
		return var;
	}


	@Override
	public String toString(boolean resolu) 
	{
		if (resolu) 
		{
			return var.getValue() + "";
		}
		else 
		{
			return " ";
		}
	}

}
