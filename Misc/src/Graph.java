import java.util.ArrayList;

/**
 * This class contains the information for a graph.  Its main
 * mechanism is an adjacency matrix which stores the relationships
 * among the nodes.  There are methods here that can change
 * the adjacency matrix
 * @author winst13
 *
 */
public class Graph 
{
	public ArrayList<ArrayList<Integer>> adjacency;
	
	/**
	 * This constructor takes the size and turns it into a 2D
	 * ArrayList (i.e. an ArrayList of ArrayLists)  It sets
	 * the default distances to infinity.  It then sets
	 * the diagonal to 0
	 * @param size
	 */
	public Graph(int size)
	{
		adjacency = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < size; i++)
		{
			adjacency.add(new ArrayList<Integer>());
			for (int j = 0; j < size; j++)
			{
				adjacency.get(i).add(Integer.MAX_VALUE);
			}
		}
		
		for (int i = 0; i < size; i++)
		{
			adjacency.get(i).set(i, 0);
		}
	}
	
	/**
	 * This alternative constructor takes a 2D ArrayList and sets it as
	 * the adjacency matrix.  
	 * @param adjacency
	 */
	public Graph(ArrayList<ArrayList<Integer>> adjacency)
	{
		this.adjacency = adjacency;
	}
	
	/**
	 * This method creates a bidirectional link between the
	 * two specified nodes of specified length.  It uses
	 * the method for establishing a unidirectional link
	 * @param node1
	 * @param node2
	 * @param length
	 */
	public void biLink(Integer node1, Integer node2, Integer length)
	{
		uniLink(node1, node2, length);
		uniLink(node2, node1, length);
	}
	
	/**
	 * This method creates a unidirectional link between the
	 * two specified nodes, from node 1 to node 2, of specified length.
	 * @param node1
	 * @param node2
	 * @param length
	 */
	public void uniLink(Integer node1, Integer node2, Integer length)
	{
		adjacency.get(node1).set(node2, length);
	}
	
	/**
	 * This method prints the graph.  It only prints the linkages,
	 * rather than having every node connected in a figure.  For
	 * example, if I had a graph without any linkages, nothing
	 * would be printed
	 */
	public void printGraph()
	{
		System.out.println("Graph:");
		for (int i = 0; i < adjacency.size(); i++)
		{
			for (int j = 0; j < adjacency.get(i).size(); j++)
			{
				if (adjacency.get(i).get(j) < Integer.MAX_VALUE)
				{
					System.out.println("Node "+i+", Node "+j+", Length "+adjacency.get(i).get(j));
				}
			}
		}
	}
}
