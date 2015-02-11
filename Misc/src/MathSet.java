import java.util.*;

/**
 * This class is the MathSet class
 * In addition to the union and intersection methods, I 
 * had to implement some other methods to help
 * 
 * @author winst13
 *
 */
public class MathSet<Double> implements Set<Double>
{
	private ArrayList<Double> elements;
	
	/**
	 * This is the constructor for a MathSet
	 */
	public MathSet()
	{
		elements = new ArrayList<Double>();
	}
	
	/**
	 * This method returns the intersection of the two sets
	 * @param b
	 * @return
	 */
	public MathSet<Double> intersection(MathSet<Double> b)
	{
		MathSet<Double> result = new MathSet<Double>();
		for (int i = 0; i < this.size(); i++)
		{
			for (int j = 0; j < b.size(); j++)
			{
				if (this.elements.get(i)==b.elements.get(j))
				{
					result.add(this.elements.get(i));
				}
			}
		}
		return result;
	}
	
	/**
	 * This method returns the union of the two sets, making sure that
	 * there are no duplicates
	 * @param b
	 * @return
	 */
	public MathSet<Double> union(MathSet<Double> b)
	{
		MathSet<Double> result = new MathSet<Double>();
		for (int i = 0; i < b.size(); i++)
		{
			if (this.contains(b.elements.get(i)))
			{
				continue;
			}
			else
			{
				result.add(b.elements.get(i));
			}
		}
		return result;
	}

	/**
	 * This returns the size of the MathSet
	 * @return
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return elements.size();
	}

	/**
	 * This returns wheter the set is empty or not
	 * @return
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		boolean result = false;
		if (size()==0)
		{
			result = true;
		}
		
		return result;
	}

	/**
	 * This method returns whether an object is contained in the MathSet
	 * @param o
	 * @return
	 */
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return elements.contains(o);
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @return
	 */
	@Override
	public Iterator<Double> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @return
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @param a
	 * @return
	 */
	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @param o
	 * @return
	 */
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @param c
	 * @return
	 */
	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @param c
	 * @return
	 */
	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @param c
	 * @return
	 */
	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 * @param c
	 * @return
	 */
	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Did not implement this because it does not relate
	 * to the project at hand
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method was implemented to add an element to the MathSet
	 * It returns false if the element is already in the MathSet
	 * @param e
	 * @return
	 */
	@Override
	public boolean add(Double e) 
	{
		// TODO Auto-generated method stub
		if (elements.contains(e))
			return false;
		else
		{
			elements.add(e);
			return true;
		}
	}

}
