import java.util.ArrayList;


public class MhoList 
{
	public ArrayList<GridObject> mholist;
	
	/**
	 * This creates a new MhoList.  It automatically
	 * creates an ArrayList with all the Mhos
	 */
	public MhoList()
	{
		mholist = new ArrayList<GridObject>();
		for (int i = 0; i < Hivolt.NUMBER_OF_MHOS; i++)
		{
			Mho.addRandomMho(mholist);
		}
	}
}
