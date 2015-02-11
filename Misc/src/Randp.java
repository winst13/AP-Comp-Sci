import java.util.*;

/**
 * This is the Randp class
 * It contains methods to generate any number
 * of random numbers without repeats.  After the
 * numbers are exhausted, it returns 0
 * @author winst13
 *
 */
public class Randp 
{
	private ArrayList<Integer> array;
	private int numleft;
	
	/**
	 * This is the constructor for Randp.  It has an argument
	 * that specifies the number of numbers it will return
	 * @param n
	 */
	public Randp(int n)
	{
		array = new ArrayList<Integer>(n);
		numleft = n;
		for (int i = 1; i <= n; i++)
		{
			array.add(i);
		}
	}
	
	/**
	 * This returns the number of numbers that have not been returned
	 * yet
	 * @return
	 */
	public int getnumleft()
	{
		return numleft;
	}
	
	/**
	 * This returns the next random number
	 * @return
	 */
	public int nextInt()
	{
		if (array.size()==0)
		{
			return 0;
		}
		double randindex = Math.random()*array.size();
		int index = (int)(randindex-randindex%1);
		int result = array.get(index);
		array.remove(index);
		numleft = array.size();
		return result;
	}
	
	/**
	 * This tests the Randp class
	 * @param args
	 */
	public static void main(String args[])
	{
		int n = 10;
		Randp r = new Randp(n);
		for (int i = 0; i <= n; i++)
		{
			System.out.println(r.nextInt());
		}
	}
}
