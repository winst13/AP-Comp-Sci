import java.util.*;

public class GenericMax<E extends Comparable<E>>
{
	private Collection<E> collection;
	
	public GenericMax(Collection<E> c)
	{
		collection = c;
	}
	
	/**
	 * This is the method that finds the max of a generic collection
	 * @return
	 */
	public E genericMax()
	{
		//Uses the iterator that all collections have
		Iterator<E> iter = collection.iterator();
		E max = iter.next();
		for (int i = 1; i < collection.size(); i++)
		{
			E element = iter.next();
			if (element.compareTo(max)>0)
			{
				max = element;
			}
		}
		return max;
	}
	
	public static void main(String args[])
	{
		//Tests for Integers
		ArrayList<Integer> ints = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < 100; i++)
		{
			ints.add(r.nextInt(100));
		}
		System.out.println(ints);
		
		GenericMax<Integer> maxfinder = new GenericMax<Integer>(ints);
		Integer result = maxfinder.genericMax();
		System.out.println("Max:  "+result);
		
		//Tests for Doubles
		ArrayList<Double> doubles = new ArrayList<Double>();
		Random dr = new Random();
		for (int i = 0; i < 100; i++)
		{
			doubles.add(100*dr.nextDouble());
		}
		System.out.println(doubles);
		
		GenericMax<Double> doublemaxfinder = new GenericMax<Double>(doubles);
		Double doubleresult = doublemaxfinder.genericMax();
		System.out.println("Max:  "+doubleresult);
	}
}
