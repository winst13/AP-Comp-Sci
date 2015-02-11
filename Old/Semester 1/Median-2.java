import java.util.*;


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
	int arraysize = 23;
	int myArray[] = new int[arraysize];
//	int myArray[] = {19, 11, 10, 6, 7, 12, 18, 6, 16, 14, 18, 17, 16, 17, 14, 5, 2, 9, 17, 5, 3, 10, 16};
	
	public void exchangeNumbers(int i, int j) 
	{
		int temp = myArray[i];
		myArray[i] = myArray[j];
		myArray[j] = temp;
	}
     
	public static void main(String a[])
	{
		Median medianFinder = new Median();
		Random r = new Random();
		
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

		//Following is to verify that the median is correct, sort the array and see.  Not part of the O(n) time.
		// You can see after finding median, the median is at the correct place, but rest of array is not sorted.
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
		System.out.println("\nVerify median is:" + medianFinder.myArray[medianFinder.arraysize/2]);
	}
	
	int findMedian(){
		return useSelectionAlgorithm(0, arraysize-1, (arraysize+1)/2);
	}
	
	int partitions(int low,int high)
	{
	    int i=low+1;
	    int j = high;
	    int pivotidx = low;
	    int pivot=myArray[pivotidx];
	 
	    while (i<=j){
	    	while (myArray[i]<pivot && i<high){
	    		i++;
	    	}
	    	while (myArray[j]>pivot && j>low){
	    		j--;
	    	}
	    	if (i <=j){
	    		exchangeNumbers(i,j);
	    		i++;
	    		j--;
	    	}
	    }
    	exchangeNumbers(pivotidx, j);
	    return j;
	}
	
	int useSelectionAlgorithm(int left,int right,int kth)
	{
	    for(;;)
	    {
	        int pivotIndex=partitions(left,right);          //Select the Pivot Between Left and Right
	        int len=pivotIndex-left+1;

	        if(kth==len){
	            return myArray[pivotIndex];
	        }
	        else if(kth<len){
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
