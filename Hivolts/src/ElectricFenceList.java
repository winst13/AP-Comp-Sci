import java.util.ArrayList;
import java.util.Random;


public class ElectricFenceList
{
	public ArrayList<GridObject> listOfFences;
	
	/**
	 * Constructs an instance of this class
	 */
	public ElectricFenceList()
	{
		listOfFences = new ArrayList<GridObject>();
	}
	
	/**
	 * This method adds the fences in the perimeter to any
	 * instance of ElectricFenceList
	 * @return
	 */
	public ArrayList<GridObject> makePerimeterList()
	{
		for (int i = 0; i < Hivolt.COLUMNS_IN_GRID; i++)
		{
			if (i==0 || i == (Hivolt.COLUMNS_IN_GRID-1))
			{
				for (int j = 0; j < Hivolt.ROWS_IN_GRID; j++)
				{
					GridObject e = new ElectricFence(i, j);
					listOfFences.add(e);
				}
			}
			else
			{
				GridObject e = new ElectricFence(i, 0);
				listOfFences.add(e);
				e = new ElectricFence(i, Hivolt.ROWS_IN_GRID-1);
				listOfFences.add(e);
			}
		}
		return listOfFences;
	}
	
	/**
	 * Adds the fences in the interior to any ElectricFenceList
	 * @return
	 */
	public void makeInteriorList()
	{
		for (int i = 0; i < ElectricFence.NO_OF_FENCES; i++)
		{
			newRandomFence(listOfFences);
		}
	}
	
	/**
	 * Adds a random fence to an ArrayList of ElectricFences
	 * @param listoffences
	 * @return
	 */
	public static void newRandomFence(ArrayList<GridObject> listoffences)
	{
		Random r = new Random();
		int x = r.nextInt(Hivolt.COLUMNS_IN_GRID-2)+1;
		int y = r.nextInt(Hivolt.ROWS_IN_GRID-2)+1;
		GridObject fence = new ElectricFence(x, y);
		boolean equal = false;
		for (int j = 0; j < listoffences.size(); j++)
		{
			if (fence.equals(listoffences.get(j)))
			{
				equal = true;
				j = listoffences.size()-1;
			}
		}
		if (equal == false)
		{
			listoffences.add(fence);
		}
		else
		{
			newRandomFence(listoffences);
		}
	}
}
