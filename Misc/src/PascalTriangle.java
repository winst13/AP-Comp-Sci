/**
 * Exercise 1.12.  The following pattern of numbers 
 * is called Pascal's triangle. The numbers at the 
 * edge of the triangle are all 1, and each number 
 * inside the triangle is the sum of the two numbers 
 * above it. Write a procedure that computes elements 
 * of Pascal's triangle by means of a recursive process.
 * 
 * I created a recursive method that returns a given row
 * of Pascal's triangle.  By using a for loop, as I did in
 * the main method, it is possible to generate Pascal's
 * triangle, up to a certain number.  Here, I tested the
 * first 10 rows of Pascal's triangle, which do come out
 * correctly.  
 * 
 * I encountered several problems here.  The main one was
 * trying to get the loop in the recursive method to not
 * go out of bounds.  At first, I was trying to sum element
 * i and element i+1 of the previous row to get element i of
 * the current row; however, I realized that it is necessary 
 * to sum elements i-1 and i instead.  
 * 
 * @author winst13
 *
 */
public class PascalTriangle 
{
	/**
	 * This method finds the nth row of the Pascal Triangle
	 * @param num
	 * @return
	 */
	public static int[] pascalRow(int num)
	{
		if (num == 1)
		{
			int result[] = {1};
			return result;
		}

		int result[] = new int[num];
		int previous[] = pascalRow(num-1);
		
		for (int i = 0; i < num; i++)
		{
			if (i == 0)
			{
				result[i] = 1;
				continue;
			}
			else if (i == num-1)
			{
				result[i] = 1;
				continue;
			}
			result[i] = previous[i-1]+previous[i];
		}
		
		return result;
	}
	
	/**
	 * This method prints an array of integers in a 
	 * presentable manner
	 * @param array
	 */
	public static void printArray(int array[])
	{
		for (int i = 0; i < array.length; i++)
		{
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}
	
	/**
	 * This method tests the above two methods for integers
	 * 1 through 10 and demonstrates their accuracy.
	 * @param args
	 */
	public static void main(String[] args)
	{
		for (int i = 1; i <=10; i++)
		{
			PascalTriangle.printArray(pascalRow(i));
		}
	}
}
