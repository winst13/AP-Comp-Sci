
public class Matrix 
{
	public static double[][] multiply(double mat1[][], double mat2[][])
	{
		double[][] result = new double[mat1.length][mat2[0].length];
		
		for (int i = 0; i < result.length; i++)
		{
			for (int j = 0; j < result[i].length; j++)
			{
				double value = 0;
				for (int k = 0; k < mat2.length; k++)
				{
					value = value + mat1[i][k]*mat2[k][j];
				}
				result[i][j] = value;
			}
		}
		
		return result;
	}
	
	public static void printMat(double mat[][])
	{
		for (int i = 0; i < mat.length; i++)
		{
			for (int j = 0; j < mat[i].length; j++)
			{
				System.out.print(mat[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Returns the row echelon form of given matrix.  This only
	 * works for square matrices
	 * @param mat
	 * @return
	 */
	public static double[][] ref(double[][] mat)
	{
		//Guarantees square matrix
		if (mat.length != mat[0].length)
		{
			return null;
		}
		
		//If the matrix is the 0 matrix
		double abstotal = 0;
		for (int i = 0; i < mat.length; i++)
		{
			for (int j = 0; j < mat[i].length; j++)
			{
				abstotal = abstotal + Math.abs(mat[i][j]);
			}
		}
		if (abstotal == 0)
		{
			return mat;
		}
		
		//Base case for recursion
		if (mat.length == 1)
		{
			double[][] result = {{1}};
			return result;
		}
		
		//What will get returned
	    double[][] ref = new double[mat.length][mat[0].length];

	    //Copy matrix
	    for (int r = 0; r < ref.length; r++)
	    {
	        for (int c = 0; c < ref[r].length; c++)
	        {
	            ref[r][c] = mat[r][c];
	        }
	        
	    }
	    
	    //Find pivot
	    double start = -1;
	    int index = 1;
	    for (index = 0; index < ref.length; index++)
	    {
	    	if (ref[index][0] != 0)
	    	{
	    		start = index;
	    		break;
	    	}
	    }
	    
	    //Move the pivot row to the top row
	    if (start != -1 && index != 0)
	    {
	    	for (int i = 0; i < ref[index].length; i++)
	    	{
	    		ref[index][i] = mat[0][i];
	    		ref[0][i] = mat[index][i];
	    	}
	    }
	    
	    //Make pivot point 1
	    double pivotval = ref[0][0];
	    for (int i = 0; i < ref[0].length; i++)
	    {
	    	ref[0][i] = ref[0][i]/pivotval;
	    }
	    
	    //Makes points below pivot in column = 0
	    for (int i = 1; i < ref.length; i++)
	    {
	    	double frontval = ref[i][0];
	    	for (int j = 0; j < ref[i].length; j++)
	    	{
	    		ref[i][j] = ref[i][j] - frontval*ref[0][j];
	    	}
	    }
	    
	    //Create a small matrix for recursion
	    double[][] smallmat = new double[ref.length-1][ref.length-1];
	    for (int i = 0; i < smallmat.length; i++)
	    {
	    	for (int j = 0; j < smallmat[i].length; j++)
	    	{
	    		smallmat[i][j] = ref[i+1][j+1];
	    	}
	    }
	    
	    //Recursion, gets the ref of the smaller matrix
	    double[][] refsmallmat = ref(smallmat);
	    
	    for (int i = 0; i < refsmallmat.length; i++)
	    {
	    	for (int j = 0; j < refsmallmat[i].length; j++)
	    	{
	    		ref[i+1][j+1] = refsmallmat[i][j];
	    	}
	    }
	    
	    return ref;
	}
	
	/**
	 * This method returns the triangular form of a square matrix
	 * @param mat
	 * @return
	 */
	public static double[][] triangular(double mat[][])
	{
		//Guarantees square matrix
		if (mat.length != mat[0].length)
		{
			return null;
		}
				
		//If the matrix is the 0 matrix
		double abstotal = 0;
				for (int i = 0; i < mat.length; i++)
		{
			for (int j = 0; j < mat[i].length; j++)
			{
				abstotal = abstotal + Math.abs(mat[i][j]);
			}
		}
		if (abstotal == 0)
		{
			return mat;
		}
				
		//Base case for recursion
		if (mat.length == 1)
		{
			return mat;
		}
				
		//What will get returned
	    double[][] ref = new double[mat.length][mat[0].length];

	    //Copy matrix
	    for (int r = 0; r < ref.length; r++)
	    {
	    	for (int c = 0; c < ref[r].length; c++)
	        {
	    		ref[r][c] = mat[r][c];
	        }
	    }
			    
	    //Find pivot
	    double start = -1;
		int index = 1;
		for (index = 0; index < ref.length; index++)
		{
			if (ref[index][0] != 0)
			{
				start = index;
				break;
			}
		}
			    
		//Move the pivot row to the top row
		if (start != -1 && index != 0)
		{
			for (int i = 0; i < ref[index].length; i++)
			{
				ref[index][i] = mat[0][i];
				ref[0][i] = mat[index][i];
			}
		}
			    
		//Makes points below pivot in column = 0
		for (int i = 1; i < ref.length; i++)
		{
			double frontval = ref[i][0];
			double topval = ref[0][0];
			for (int j = 0; j < ref[i].length; j++)
			{
				ref[i][j] = ref[i][j] - frontval*ref[0][j]/topval;
			}
		}
			    
		//Create a small matrix for recursion
		double[][] smallmat = new double[ref.length-1][ref.length-1];
		for (int i = 0; i < smallmat.length; i++)
		{
			for (int j = 0; j < smallmat[i].length; j++)
			{
				smallmat[i][j] = ref[i+1][j+1];
			}
		}
			    
		//Recursion, gets the ref of the smaller matrix
		double[][] refsmallmat = triangular(smallmat);
			    
		for (int i = 0; i < refsmallmat.length; i++)
		{
			for (int j = 0; j < refsmallmat[i].length; j++)
			{
				ref[i+1][j+1] = refsmallmat[i][j];
			}
		}
			    
		return ref;
	}
	
	public static double[][] exp(double[][] mybase, int myexp) 
	{
		double[][] myresult = new double[mybase.length][mybase.length];//will be final answer after the loop
		//Make the start matrix the identity matrix
		for (int i = 0; i < myresult.length; i++)
		{
			myresult[i][i] = 1;
		}
		while (myexp != 0)
		{
			if ((myexp & 1) != 0)
			{
				myresult = Matrix.multiply(myresult,mybase);
			}
			
		myexp >>= 1;//shifts binary representation over by 1
        mybase = Matrix.multiply(mybase, mybase);//squares mybase
		}
		return myresult;
	}
	
	
}
