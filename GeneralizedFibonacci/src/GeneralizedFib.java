
public class GeneralizedFib 
{
	private double coeff[];
	private double init[][];
	
	public GeneralizedFib()
	{
		coeff = new double[2];
		init = new double[2][1];
		coeff[0] = 1;
		coeff[1] = 1;
		init[0][0] = 1;
		init[1][0] = 1;
	}
	
	public GeneralizedFib(double mycoeff[], double myinit[])
	{
		coeff = mycoeff;
		init = new double[myinit.length][1];
		
		for (int i = 0; i < myinit.length; i++)
		{
			init[i][0] = myinit[i];
		}
	}
	
	public double[][] coeffMatrix()
	{
		if (coeff.length != init.length)
		{
			return null;
		}
		
		int n = coeff.length;
		
		double[][] result = new double[n][n];
		for (int i = 0; i < n; i++)
		{
			if (i==0)
			{
				for (int j = 0; j < n; j++)
				{
					result[i][j] = coeff[j];
				}
				continue;
			}
			for (int j = 0; j < n; j++)
			{
				if (j == i-1)
				{
					result[i][j] = 1;
				}
				else
				{
					result[i][j] = 0;
				}
			}
		}
		
		return result;
	}
	
	public double findTerm(int index)
	{
		if (index < coeff.length && index >= 0)
		{
			return init[index][0];
		}
		else if (index <= 0)
		{
			return -1;
		}
		else
		{
			double[][] coeffmat = this.coeffMatrix();
			double[][] multiplier = Matrix.exp(coeffmat,index-coeff.length+1);
			double[][] result = Matrix.multiply(multiplier, init);
			return result[0][0];
		}
	}
	
	public double[][] getInit()
	{
		return init;
	}
}
