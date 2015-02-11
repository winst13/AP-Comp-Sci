import java.util.Random;


public class Test 
{
	public static void main(String[] args) 
	{
		//testMult();
		//testRef();
		//testTri();
		//testEigenval();
		//testExp();
		//testTermFind();
	}
	
	public static void testMult()
	{
		double mat1[][] = new double[3][5];
		double mat2[][] = new double[5][2];
		int counter = 0;
		for (int i = 0; i < mat1.length; i++)
		{
			for (int j = 0; j < mat1[i].length; j++)
			{
				mat1[i][j] = counter;
				counter++;
			}
		}
		
		Matrix.printMat(mat1);
		
		for (int i = 0; i < mat2.length; i++)
		{
			for (int j = 0; j < mat2[i].length; j++)
			{
				mat2[i][j] = counter;
				counter++;
			}
		}
		
		Matrix.printMat(mat2);
		Matrix.printMat(Matrix.multiply(mat1, mat2));
	}
	
	public static void testRef()
	{
		double mat1[][] = new double[4][4];
		
		Random rand = new Random();
		for (int i = 0; i < mat1.length-1; i++)
		{
			for (int j = 0; j < mat1[i].length; j++)
			{
				mat1[i][j] = (double)rand.nextInt(10);
			}
		}
		for (int j = 0; j < mat1[mat1.length-1].length; j++)
		{
			mat1[mat1.length-1][j] = 2*mat1[0][j];
		}
		
		Matrix.printMat(mat1);
		
		Matrix.printMat(Matrix.ref(mat1));
	}
	
	public static void testTri()
	{
		double mat1[][] = new double[3][3];
		
		Random rand = new Random();
		for (int i = 0; i < mat1.length; i++)
		{
			for (int j = 0; j < mat1[i].length; j++)
			{
				mat1[i][j] = (double)rand.nextInt(10);
			}
		}
		
		Matrix.printMat(mat1);
		
		Matrix.printMat(Matrix.ref(mat1));
		Matrix.printMat(Matrix.triangular(mat1));
	}
	
	public static void testExp()
	{
		double mat1[][] = new double[2][2];
		
		Random rand = new Random();
		for (int i = 0; i < mat1.length; i++)
		{
			for (int j = 0; j < mat1[i].length; j++)
			{
				mat1[i][j] = (double)rand.nextInt(10);
			}
		}
		
		Matrix.printMat(mat1);
		Matrix.printMat(Matrix.exp(mat1,2));
		Matrix.printMat(Matrix.exp(mat1,3));
		Matrix.printMat(Matrix.exp(mat1,5));
	}
	
	public static void testTermFind()
	{
		Random rand = new Random();
		int n = rand.nextInt(3)+1;
		double[] coeff = new double[n];
		double[] init = new double[n];
		for (int i = 0; i < n; i++)
		{
			coeff[i] = (double)rand.nextInt(9) + 1;
			init[i] = (double)rand.nextInt(9) + 1;
		}
		
		for (int i = 0; i < init.length; i++)
		{
			System.out.println(init[i]);
		}
		
		GeneralizedFib fib = new GeneralizedFib(coeff, init);
		
		System.out.println("Initial:");
		Matrix.printMat(fib.getInit());
		System.out.println("Coefficients:");
		Matrix.printMat(fib.coeffMatrix());
		
		for (int i = 0; i < 2*n; i++)
		{
			System.out.println(fib.findTerm(i)+" ");
		}
	}
}
