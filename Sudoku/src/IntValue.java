import org.chocosolver.solver.*;
import org.chocosolver.solver.variables.*;

public class IntValue implements Tab_Sudo
{
	
	private int num;
	private IntVar var;


	public IntValue(char val, Model model) 
	{
		num = Character.getNumericValue(val);
		var = model.intVar(num);
	}


	@Override
	public IntVar getVar() 
	{
		return var;
	}

	@Override
	public String toString(boolean resolu) 
	{
		return "" + num;
	}

	
}
