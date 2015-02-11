/**
 * Exercise 1.11.  A function f is defined by the rule that 
 * f(n) = n if n<3 and f(n) = f(n - 1) + 2f(n - 2) + 3f(n - 3) 
 * if n> 3. Write a procedure that computes f by means of a 
 * recursive process. Write a procedure that computes f by 
 * means of an iterative process. 
 * 
 * The following code tests the values from 1 to 10 for both procedures
 * with initial values of 1.
 * @author winst13
 *
 */
public class FibbonacciLike 
{
	int init1;
	int init2;
	int init3;
	
	/**
	 * Creates a FibbonacciLike
	 */
	public FibbonacciLike()
	{
		init1 = 1; 
		init2 = 1; 
		init3 = 1;
	}
	
	/**
	 * Creates a FibbonacciLike with specific initial values
	 * @param a
	 * @param b
	 * @param c
	 */
	public FibbonacciLike(int a, int b, int c)
	{
		init1 = a; 
		init2 = b; 
		init3 = c;
	}
	
	/**
	 * This method computes the nth element of the sequence
	 * defined in the problem.  It does this recursively
	 * @param elementnum
	 * @return
	 */
	public int computeElementRecursive(int elementnum)
	{
		if (elementnum <= 0)
		{
			return 0;
		}
		else if (elementnum == 1||elementnum == 2||elementnum == 3)
		{
			return 1;
		}
		int result = computeElementRecursive(elementnum-1)+
				2*computeElementRecursive(elementnum-2)+
				3*computeElementRecursive(elementnum-3);
		return result;
	}
	
	/**
	 * This method also computes the nth element of the sequence
	 * defined in the problem.  It does this iteratively
	 * @param elementnum
	 * @return
	 */
	public int computeElementIterative(int elementnum)
	{
		int a = init1;
		int b = init2;
		int c = init3;
		
		for (int i = 1; i <= elementnum-3; i++)
		{
			int tempa = a + 2*b + 3*c;
			int tempb = a;
			int tempc = b;
			a = tempa;
			b = tempb;
			c = tempc;
		}
		
		return a;
	}
	
	/**
	 * This tests the recursive and iterative methods
	 * for generating elements of the sequence
	 * @param args
	 */
	public static void main(String[] args)
	{
		FibbonacciLike myfib = new FibbonacciLike();
		for (int i = 1; i <= 10; i++)
		{
			int result = myfib.computeElementRecursive(i);
			System.out.println(result);
		}
		
		System.out.println();
		
		for (int i = 1; i <= 10; i++)
		{
			int result = myfib.computeElementIterative(i);
			System.out.println(result);
		}
	}
}
