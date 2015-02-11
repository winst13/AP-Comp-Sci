import java.util.Random;

/**
 * This class was written for the homework due Monday, November 17, 2014
 * It contains three different sort methods that sort an array of any
 * object
 * @author Winston Wang
 *
 */
public class ThreeSorts
{
	/**
	 * This method uses a bubblesort to sort the array
	 * @param array
	 */
	public static double[] bubbleSort(double array[])
	{
		int swaps = 0;
		
		for (int i = 0; i < array.length-1; i++)
		{
			if (array[i]<array[i+1])
			{
				swaps++;
				double temp = array[i];
				array[i] = array[i+1];
				array[i+1] = temp;
			}
		}
		
		if (swaps == 0)
		{
			return array;
		}
		else
		{
			return bubbleSort(array);
		}
	}
	
	/**
	 * This method uses insertionsort to sort the array.  This is extremely hard
	 * because I do not know how to make an array that can only be populated
	 * by Comparable objects
	 * @param o
	 */
	public static double[] insertionSort(double array[])
	{
		int j;
		double key;
		int i;  

		for (j = 1; j < array.length; j++)
		{
			key = array[ j ];
			for(i = j - 1; (i >= 0) && (array[ i ] < key); i--)
			{
				array[ i+1 ] = array[ i ];	
			}
			array[ i+1 ] = key;
		}
		
		return array;
	}
	
	/**
	 * This method uses selectionsort to sort the array
	 * @param o
	 */
	public static double[] selectionSort(double array[])
	{
		int i, j, first;
		double temp;
		for (i = array.length-1; i > 0; i-- )  
		{
			first = 0;
			for(j = 1; j <= i; j ++)
			{
				if( array[j] < array[first] ) 
				{
					first = j;
				}
			}
			temp = array[first];
			array[first] = array[i];
			array[i] = temp; 
		}
		
		return array;
	}
	
	/**
	 * This method tests to see if the array is sorted
	 * @param array
	 * @return
	 */
	public static boolean isSorted(double array[])
	{
		double direction = 0;
		for (int i = 1; i < array.length; i++)
		{
			if (direction == 0)
			{
				direction = Math.signum(array[1]-array[0]);
				if (i==array.length-1)
				{
					return true;
				}
			}
		}
		boolean result = true;
		
		for (int i = 1; i < array.length; i++)
		{
			if ((array[i]-array[i-1])*direction<0)
			{
				result = false;
			}
		}
		
		return result;
	}
	
	/**
	 * This method tests all three sorts
	 * @param args
	 */
	public static void main(String args[])
	{
		double array1[] = new double[10];
		Random r = new Random(10);
		for(int i = 0; i < array1.length; i++)
		{
			array1[i] = r.nextDouble();
		}
		array1 = bubbleSort(array1);
		
		System.out.println(isSorted(array1));
		
		double array2[] = new double[10];
		for(int i = 0; i < array2.length; i++)
		{
			array2[i] = r.nextDouble();
		}
		array2 = insertionSort(array2);
		
		System.out.println(isSorted(array2));
		
		double array3[] = new double[10];
		for(int i = 0; i < array3.length; i++)
		{
			array3[i] = r.nextDouble();
		}
		array3 = selectionSort(array3);
		
		System.out.println(isSorted(array3));
	}
}
