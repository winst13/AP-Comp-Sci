import java.util.ArrayList;
import java.util.Random;

/**
 * This class was written for the O(n) median finder
 * I took the quicksort method from 
 * http://www.java2novice.com/java-sorting-algorithms/quick-sort/
 * 
 * I then changed it to find the median 
 * @author winst13
 *
 */
public class Median 
{
	public void sort(int[] inputArr) 
	{
		if (inputArr == null || inputArr.length == 0) 
		{
			return;
		}
		quickSort(0, inputArr.length - 1, inputArr);
	}
	
	public void sort(int[] inputArr, int pivot) 
	{
		if (inputArr == null || inputArr.length == 0) 
		{
			return;
		}
		quickSort(0, inputArr.length - 1, inputArr, pivot);
	}
	
	public int fivemedian(int[] thisarray)
	{
		sort(thisarray);
		return thisarray[thisarray.length/2];
	}
	
	public int recursemedian(int[] array)
	{
		if (array.length>5)
		{
			ArrayList<Integer> medians = new ArrayList<Integer>();
			for (int i = 0; i < array.length/5+1; i++)
			{
				if (i < array.length/5)
				{
					int[] subarray = {array[i*5], array[i*5+1], array[i*5+2], array[i*5+3], array[i*5+4]};
					int addition = fivemedian(subarray);
					medians.add(addition);
					System.out.println(addition);
				}
				else
				{
					int[] subarray = new int[array.length-5*i];
					for (int j = 0; j < array.length-5*i; j++)
					{
						subarray[j] = array[i*5+j];
					}
					
					if (subarray.length == 0)
					{
						continue;
					}
					else
					{
						//System.out.println(subarray.length);
						medians.add(fivemedian(subarray));
					}
				}
			}
			int[] result = new int[medians.size()];
			for(int j = 0; j < medians.size(); j++)
			{
				result[j] = medians.get(j);
			}
			return recursemedian(result);
		}
		else
		{
			return fivemedian(array);
		}
	}
	
	public int findmedian(int[] array)
	{
		int pivot = recursemedian(array);
		halfsort(0, array.length-1, array, pivot);
		return pivot;
	}
	
	public void halfsort(int lowerIndex, int higherIndex, int[] array, int pivot)
	{
		if (array == null || array.length == 0) 
		{
			return;
		}
		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, I am taking pivot as middle index number
		//int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
		// Divide into two arrays
		while (i <= j) 
		{
			while (array[i] < pivot) 
			{
				i++;
			}
			while (array[j] > pivot) 
			{
				j--;
			}
			if (i <= j) 
			{
				exchangeNumbers(i, j, array);
				//move index to next position on both sides
				i++;
				j--;
			}
		}
	}
 
	/**
	 * This is the quicksort method where the first pivot is an argument
	 * @param lowerIndex
	 * @param higherIndex
	 * @param array
	 * @param pivot
	 */
	public void quickSort(int lowerIndex, int higherIndex, int[] array, int pivot) 
	{
		
		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, I am taking pivot as middle index number
		//int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
		// Divide into two arrays
		while (i <= j) 
		{
			/**
			 * In each iteration, we will identify a number from left side which 
			 * is greater then the pivot value, and also we will identify a number 
			 * from right side which is less then the pivot value. Once the search 
			 * is done, then we exchange both numbers.
			 */
			while (array[i] < pivot) 
			{
				i++;
			}
			while (array[j] > pivot) 
			{
				j--;
			}
			if (i <= j) 
			{
				exchangeNumbers(i, j, array);
				//move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j)
		{
			quickSort(lowerIndex, j, array);
		}
		if (i < higherIndex)
		{
			quickSort(i, higherIndex, array);
		}
	}
	
	/**
	 * This is the default quicksort method
	 * @param lowerIndex
	 * @param higherIndex
	 * @param array
	 */
	public void quickSort(int lowerIndex, int higherIndex, int[] array) 
	{
		
		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, I am taking pivot as middle index number
		int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2];
		// Divide into two arrays
		while (i <= j) 
		{
			/**
			 * In each iteration, we will identify a number from left side which 
			 * is greater then the pivot value, and also we will identify a number 
			 * from right side which is less then the pivot value. Once the search 
			 * is done, then we exchange both numbers.
			 */
			while (array[i] < pivot) 
			{
				i++;
			}
			while (array[j] > pivot) 
			{
				j--;
			}
			if (i <= j) 
			{
				exchangeNumbers(i, j, array);
				//move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j)
		{
			quickSort(lowerIndex, j, array);
		}
		if (i < higherIndex)
		{
			quickSort(i, higherIndex, array);
		}
	}
	
	public void exchangeNumbers(int i, int j, int[] array) 
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
     
	public static void main(String a[])
	{
		Median medianFinder = new Median();
		Random r = new Random();
		int input[] = new int[22];
		for(int i = 0; i<input.length; i++)
		{
			input[i] = r.nextInt(20)+1;
		}
		for(int i:input)
		{
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println("");
		System.out.println("Found median: " + medianFinder.recursemedian(input));
//Following is to verify that the median is correct, sort the array and see.  Not part of the O(n) time.
		medianFinder.sort(input);
		for(int i:input)
		{
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println("");
		
	}
}
