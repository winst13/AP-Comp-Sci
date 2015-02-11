import java.util.*;

/**
 * This class was created in order to answer the finding Medians in O(n)
 * time.  This sort, a modified quicksort, only sorts the parts of the 
 * array containing the median.  This allows the time complexity to be
 * O(n) rather than O(nlog(n))
 * 
 * Some limitations include the fact that this program does not return
 * doubles.  If the median is in between 9 and 10 (or even 9 and 11),
 * it will only select one and return it, rather than averaging the two.
 * @author winst13
 *
 */
public class Median 
{
	int arraysize = 23;
	int myArray[] = new int[arraysize];
	//int myArray[] = {13, 2, 20, 5, 9, 2, 7, 15, 13, 17, 10, 8, 5, 8, 19, 5, 5, 18, 3, 13, 10, 20};
	
	/**
	 * This method exchanges two numbers in the array
	 * @param i
	 * @param j
	 */
	public void exchangeNumbers(int i, int j) 
	{
		int temp = myArray[i];
		myArray[i] = myArray[j];
		myArray[j] = temp;
	}
     
	/**
	 * This is the main method that executes the median-finding
	 * method, as well as a method that sorts the rest of the 
	 * array
	 * @param a
	 */
	public static void main(String a[])
	{
		Median medianFinder = new Median();
		Random r = new Random();
		
		/*
		 *Populates array with random ints from 1 to 20 
		 */
		for(int i = 0; i<medianFinder.arraysize; i++)
		{
			medianFinder.myArray[i] = r.nextInt(20)+1;
		}
		for(int i:medianFinder.myArray)
		{
			System.out.print(i);
			System.out.print(" ");
		}

		int foundmedian = medianFinder.findMedian();
		System.out.println("\nFound median: " + foundmedian);

		/*The following is to verify that the median is correct, sort the array and see.  
		This part is not included in the O(n) time.
		You can see after finding median, the median is at the correct place, 
		but rest of array is not sorted.*/
		System.out.println("after found median, array is: ");
		for(int i:medianFinder.myArray)
		{
			System.out.print(i);
			System.out.print(" ");
		}

		Arrays.sort(medianFinder.myArray);
		System.out.println("\nSorted array is: ");
		for(int i:medianFinder.myArray)
		{
			System.out.print(i);
			System.out.print(" ");
		}
	}
	
	/**
	 * This method finds the median of the array
	 * @return
	 */
	int findMedian()
	{
		return useSelectionAlgorithm(0, arraysize-1, (arraysize+1)/2);
	}
	
	/**
	 * This method is basically half of a quicksort.  It takes
	 * the first element of the array and puts everything that is
	 * larger than it in towards the back end, with everything smaller
	 * in the front end, and then returns the index of the pivot.
	 * In the next iteration, only one side of the array (front or back)
	 * is sorted
	 * @param low
	 * @param high
	 * @return
	 */
	int partitions(int low,int high)
	{
	    int i=low+1;
	    int j = high;
	    int pivotidx = low;
	    int pivot=myArray[pivotidx];
	 
	    while (i<=j)
	    {
	    	while (myArray[i]<pivot && i<high)
	    	{
	    		i++;
	    	}
	    	while (myArray[j]>pivot && j>low)
	    	{
	    		j--;
	    	}
	    	if (i <=j)
	    	{
	    		exchangeNumbers(i,j);
	    		i++;
	    		j--;
	    	}
	    }
    	exchangeNumbers(pivotidx, j);
	    return j;
	}
	
	/**
	 * This method sorts the array around the median until the
	 * median is within its own partition, at which point it is returned
	 * This method leaves much of the array unsorted, but has the 
	 * median where it should be.
	 * @param left
	 * @param right
	 * @param kth
	 * @return
	 */
	int useSelectionAlgorithm(int left,int right,int kth)
	{
	    for(;;)
	    {
	        int pivotIndex=partitions(left,right);//Select the Pivot Between Left and Right
	        int len=pivotIndex-left+1;

	        if(kth==len)
	        {
	            return myArray[pivotIndex];
	        }
	        else if(kth<len)
	        {
	            right=pivotIndex;
	        }
	        else
	        {
	            kth=kth-len;
	            left=pivotIndex+1;
	        }
	    }
	}
}