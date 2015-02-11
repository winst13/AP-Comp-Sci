import java.util.*;

/**
 * This class encodes for an object that can perform Dijkstra's algorithm.
 * The constructor for the object is the one that actually performs the
 * algorithm because it is more efficient to do Dijkstra's once for
 * every start node/graph pair, rather than do it every time the path length
 * is requested for different end nodes.  For example, if the program
 * requested the shortest path between nodes 0 and 5 and the shortest
 * path between nodes 0 and 7, performing Dijkstra's algorithm once will
 * get both paths.
 * 
 * This class contains a couple methods:  
 * The constructor, which takes arguments for the start node and the graph
 * The shortestPath method, which uses the vertex ArrayList
 * to reconstruct the shortest path
 * The shortestDistance method, which looks into the distance ArrayList
 * to find the shortest distance
 * The getStart method, which returns the starting node for the
 * specific Dijkstra object
 * The selectMinNode method, which is used in Dijkstra's algorithm
 * to find the next node.  This is essential, since if the algorithm
 * chooses the wrong node, the shortest path could come out wrong
 * The update method, which calculates new distances for all of the
 * nodes, with respect to the current node.  It then compares the
 * new distances to the old distances, and updates the distances
 * that need to be updated
 * 
 * There were a couple problems that I encountered on the way.  First,
 * it was hard to design a good selectMinNode method, since it requires
 * checking both the distance ArrayList and the visited ArrayList.
 * 
 * In addition, a problem came up using Integer.MAX_VALUE to represent
 * unconnected nodes.  As it turns out, Integer.MAX_VALUE + 1 returns
 * Integer.MIN_VALUE.  Because Dijkstra's algorithm deals with minimums,
 * this fact caused many problems in my code, that I eventually did solve
 * @author winst13
 *
 */
public class Dijkstra 
{
	/**
	 * This ArrayList records whether each node has been visited
	 * or not
	 */
	private ArrayList<Boolean> visited = new ArrayList<Boolean>();
	
	/**
	 * This ArrayList records the temporary shortest distance.  When
	 * the algorithm finishes running, it will contain the final shortest
	 * distances
	 */
	private ArrayList<Integer> distance = new ArrayList<Integer>();
	
	/**
	 * This method contains the index of the previous node in the
	 * shortest path.  From this information, it is possible to reconstruct
	 * the shortest path
	 */
	private ArrayList<Integer> vertex = new ArrayList<Integer>();
	
	/**
	 * This object is the graph that the constructor performs
	 * Dijkstra's on
	 */
	private Graph graph;
	
	/**
	 * This integer represents the start node
	 */
	private Integer start;
	
	/**
	 * This integer stores the current node while Dijkstras is running
	 */
	private Integer currentNode;
	
	/**
	 * The constructor, which takes arguments for the start node and the graph
	 * It makes sure that the start nodes and the graphs are valid.  It delegates
	 * most of the actual calculations the the selectMinNode and update methods
	 * @param start
	 * @param g
	 */
	public Dijkstra(Integer start, Graph g)
	{
		graph = g;
		this.start = start;
		
		//Checks to make sure the adjacency matrix is square
		for (int i = 0; i < graph.adjacency.size(); i++)
		{
			if (graph.adjacency.size() != graph.adjacency.get(i).size())
			{
				System.out.println("Adjacency matrix invalid");
			}
		}
		
		//Checks to make sure the adjacency matrix doesn't have any negative edge weights
		for (int i = 0; i < graph.adjacency.size(); i++)
		{
			for (int j = 0; j < graph.adjacency.get(i).size(); j++)
			{
				if (graph.adjacency.get(i).get(j) < 0)
				{
					System.out.println("Contains negative edge weights");
				}
			}
		}
		
		//Set values in the visited, distance, and vertex arrays
		for (int i = 0; i < graph.adjacency.size(); i++)
		{
			visited.add(false);
			vertex.add(null);
			if (i == start)
			{
				distance.add(0);
			}
			else
			{
				distance.add(Integer.MAX_VALUE);
			}
		}
		
		//Dijkstra's Algorithm
		boolean allVisited = false;
		while (!allVisited)
		{
			currentNode = selectMinNode();
			update();
			
			allVisited = true;
			for (int i = 0; i < visited.size(); i++)
			{
				if (!visited.get(i))
				{
					allVisited = false;
				}
			}
		}
	}
	
	
	/**
	 * This method uses Dijkstra's algorithm to find the shortest path from one node
	 * to another.  It returns the path.  If two nodes are not connected, then it should
	 * be represented in the adjacency matrix as infinite.  I took infinite to be 
	 * Integer.MAX_VALUE
	 * 
	 * This method uses the vertex ArrayList in order to reconstruct the path that
	 * corresponds to the shortest distance.  
	 * @param start
	 * @param adjacency
	 * @return
	 */
	public ArrayList<Integer> shortestPath(Integer end)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		Integer prev = end;
		while (prev != start)
		{
			result.add(vertex.get(prev));
			prev = vertex.get(prev);
		}
		
		return result;
	}
	
	/**
	 * This method simply returns the shortest distance corresponding
	 * to the inputted end node
	 * @param end
	 * @return
	 */
	public Integer shortestDistance(Integer end)
	{
		return distance.get(end);
	}
	
	/**
	 * This method is used in Dijkstra's algorithm (found in the constructor)
	 * It selects the node that is the closest to the source that
	 * has also not been visited yet.  It then changes currentNode
	 * to the selected node
	 * @return
	 */
	public Integer selectMinNode()
	{
		Integer mindex = null;
		for (int i = 0; i < distance.size(); i++)
		{
			if (!visited.get(i))
			{
				if (mindex == null||distance.get(i)<distance.get(mindex))
				{
					mindex = i;
				}
			}
		}
		
		//System.out.println("Select Dist"+distance);
		//System.out.println("Mindex"+mindex);
		
		return mindex;
	}
	
	/**
	 * This method updates all of the distances.  It takes
	 * the current node and calculates the distance every other node
	 * is from the current node.  It then adds that distance to the
	 * current node's distance from the source.  If the distance
	 * from the current node to another node is infinity (Integer.MAX_VALUE)
	 * then that distance is not added.
	 * 
	 * The added values are then compared with the temporary shortest
	 * distance list.  If the calculated value is less than the temporary
	 * shortest distance, then the calculated value becomes the new
	 * temporary shortest distance
	 * 
	 * It then marks the current node as visited so that it will not be
	 * selected again by selectMinNode
	 * @param adjacency
	 */
	public void update()
	{
		//Calculate new values.  As long as not connected means distance = infinity, this
		//should be fine
		ArrayList<Integer> newDist = new ArrayList<Integer>();
		for (int i = 0; i < graph.adjacency.get(currentNode).size(); i++)
		{
			if (distance.get(currentNode) != Integer.MAX_VALUE && 
					graph.adjacency.get(currentNode).get(i) != Integer.MAX_VALUE)
			{
				newDist.add(graph.adjacency.get(currentNode).get(i)+distance.get(currentNode));
			}
			else
			{
				newDist.add(Integer.MAX_VALUE);
			}
			//System.out.println(i);
		}
		
		//Compare values, replace if needed
		for (int i = 0; i < distance.size(); i++)
		{
			//System.out.println("Update distance"+distance);
			//System.out.println("New dist"+newDist);
			if (distance.get(i)>newDist.get(i))
			{
				distance.set(i, newDist.get(i));
				vertex.set(i, currentNode);
			}
		}
		
		//Mark currentNode as visited
		visited.set(currentNode, true);
	}
	
	/**
	 * This method gets the start node of the Dijkstra object
	 * @return
	 */
	public Integer getStart()
	{
		return start;
	}
	
	/**
	 * This is the main method, which can be used for testing
	 * @param args
	 */
	public static void main(String args[])
	{
		Graph g = new Graph(10);
		
		g.biLink(0, 1, 6);
		g.biLink(0, 2, 10);
		g.biLink(0, 3, 2);
		g.biLink(1, 5, 12);
		g.biLink(1, 9, 9);
		g.biLink(2, 4, 4);
		g.biLink(2, 8, 2);
		g.biLink(3, 4, 3);
		g.biLink(3, 7, 7);
		g.biLink(5, 6, 1);
		g.biLink(5, 8, 5);
		g.biLink(6, 9, 11);
		g.biLink(7, 9, 8);
		
		g.printGraph();
		
		Dijkstra d = new Dijkstra(0, g);
		for (int i = 0; i < 10; i++)
		{
			System.out.println(i+" Path: "+d.shortestPath(i));
			System.out.println(i+" Distance: "+d.shortestDistance(i));
		}
	}
}
