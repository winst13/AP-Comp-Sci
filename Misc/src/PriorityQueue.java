import java.util.*;


/**
 * Documentation for AbstractQueue was found at 
 * http://docs.oracle.com/javase/7/docs/api/java/util/AbstractQueue.html
 * 
 * This class contains both the methods for a Priority Queue and the main
 * method that tests it.  I made PriorityQueue extend AbstractQueue since
 * a priority queue is obviously an example of a queue.  Moreover, I used
 * an ArrayList to store the queue values and priorities.  I was able to
 * delegate many of the methods that I needed to program to the ArrayList
 * class, rather than program them myself.
 * 
 * This program, however, does not meet all of the requirements.  I was
 * unable to design a Comparator.  Also, some of the time complexity
 * requirements are not met.  Although poll, peek, and remove operate
 * at O(1) time, the add function has a time complexity of O(n)
 * 
 * @author winst13
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> extends AbstractQueue<E>
{
	private ArrayList<E> queue;
	private ArrayList<Integer> priorities;
	private final Comparator<? super E> comparator;
	private boolean reverse;
	
	/**
	 * This is the default constructor for the Priority Queue.
	 * It sets the queue and priorities to a new, blank arraylist.
	 * It also sets the comparator to null
	 */
	public PriorityQueue()
	{
		queue = new ArrayList<E>();
		priorities = new ArrayList<Integer>();
		comparator = null;
	}
	
	/**
	 * This method adds an element to the priority queue.  It sets
	 * the priority to default 0 (lowest priority)
	 */
	public boolean add(E e)
	{
		return offer(e);
	}
	
	/**
	 * This method adds an element to the priority queue.  It also
	 * sets the priority to a specified integer
	 * @param e
	 * @param priority
	 * @return
	 */
	public boolean add(E e, Integer priority)
	{
		return offer(e, priority);
	}
	
	/**
	 * This method clears the priority queue by
	 * resetting the queue and priorities ArrayLists
	 */
	public void clear()
	{
		queue = new ArrayList<E>();
		priorities = new ArrayList<Integer>();
	}
	
	/**
	 * This method returns the comparator that is used.  However,
	 * right now it only returns null since there is no way to
	 * modify the default comparator
	 * @return
	 */
	public Comparator<? super E> comparator()
	{
		return comparator;
	}
	
	/**
	 * This method searches the priority queue values for a given value
	 * If the value is in the queue, then this returns true.  I delegated
	 * this function to the ArrayList class
	 * @param e
	 * @return
	 */
	public boolean contains(E e)
	{
		return queue.contains(e);
	}
	
	@Override
	/**
	 * This method returns an iterator
	 */
	public Iterator<E> iterator()
	{
		return new Iter();
	}
	
	/**
	 * This is the class describing an iterator for the priority queue
	 * It contains methods described in Iterator
	 * @author winst13
	 *
	 */
	private final class Iter implements Iterator<E>
	{
		private int place = 0;
		
		/**
		 * This method returns true if there is an next element
		 */
		public boolean hasNext()
		{
			return place < queue.size();
		}
		
		/**
		 * This method returns the next element
		 */
		public E next()
		{
			if (hasNext())
			{
				return queue.get(place + 1);
			}
			else
			{
				return null;
			}
		}
		
		/**
		 * This method removes the current node
		 */
		public void remove()
		{
			queue.remove(place);
			priorities.remove(place);
		}
	}

	@Override
	/**
	 * This method is called by the add method.  It adds an
	 * element to the queue
	 */
	public boolean offer(E e)
	{
		if (e == null)
		{
			return false;
		}
		else
		{
			queue.add(e);
			priorities.add(new Integer(0));
			return true;
		}
	}
	
	/**
	 * This method also adds an element to the queue, along with a
	 * specified priority
	 * @param e
	 * @param priority
	 * @return
	 */
	public boolean offer(E e, Integer priority)
	{
		if (e == null)
		{
			return false;
		}
		else
		{
			int place = 0;
			for (int i = 0; i < priorities.size(); i++)
			{
				if (priority > priorities.get(i))
				{
					place = i;
					break;
				}
			}
			queue.add(place, e);
			priorities.add(place, new Integer(priority));
			return true;
		}
	}
	
	/**
	 * This method looks at the first element in the queue and
	 * returns it, without removing it from the queue
	 */
	@Override
	public E peek()
	{
		if (queue == null)
		{
			return null;
		}
		else
		{
			if (reverse = false)
			{
				return queue.get(0);
			}
			else
			{
				return queue.get(queue.size()-1);
			}
		}
	}
	
	/**
	 * This method does the same thing as peek, except it removes
	 * that element from the queue
	 */
	@Override
	public E poll()
	{
		if (queue == null)
		{
			return null;
		}
		else
		{
			if (reverse = false)
			{
				E result = queue.get(0);
				queue.remove(0);
				priorities.remove(0);
				return result;
			}
			else
			{
				E result = queue.get(queue.size()-1);
				queue.remove(queue.size()-1);
				priorities.remove(queue.size()-1);
				return result;
			}
		}
	}
	
	/**
	 * This method removes a given element from
	 * the queue.  
	 * @param e
	 * @return
	 */
	public boolean remove(E e, Integer priority)
	{
		priorities.remove(priority);
		return queue.remove(e);
	}
	
	/**
	 * This method returns the size of the queue
	 */
	@Override
	public int size()
	{
		return queue.size();
	}
	
	/**
	 * This method prints the queue with each element's priorities
	 * for the purposes of testing
	 */
	public void print()
	{
		for (int i = 0; i < size(); i++)
		{
			System.out.println(queue.get(i)+"("+priorities.get(i)+")");
		}
	}
	
	/**
	 * This method reverses the comparator. When reverse is false,
	 * the poll and peek methods return the last element, rather than
	 * the first
	 */
	public void reverseComparator()
	{
		reverse = !reverse;
	}
	
	/**
	 * This is the main method that tests the priority queue
	 * @param args
	 */
	public static void main(String args[])
	{
		PriorityQueue<Integer> test = new PriorityQueue<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(5, 1);
		test.add(6, 5);
		test.add(7, 4);
		System.out.println("Start queue");
		test.print();
		System.out.println("Size: "+test.size());
		
		System.out.println("Peek: "+test.peek());
		
		System.out.println("Poll: "+test.poll());
		System.out.println("Poll: "+test.poll());
		test.print();
		
		System.out.println("");
		
		System.out.println("remove");
		System.out.println(test.contains(new Integer(1)));
		System.out.println(test.contains(new Integer(100)));
		
		System.out.println("");
		
		test.clear();
		test.print();
		
		test.add(1);
		System.out.println("Clear, add 1");
		test.print();
	}
}
