import java.util.*;

/**
 * This is the class for a Monopoly board.  It contains
 * two ArrayLists:  one of Players and one of Locations
 * Players move and interact with Locations on the board
 * @author winst13
 *
 */
public class Board 
{
	/**
	 * This is the number of locations on a board.  The classic Monopoly
	 * board has 40 locations
	 */
	public static final int NUMBER_OF_LOCATIONS = 40;
	
	/**
	 * This stores all the locations on the board
	 */
	private ArrayList<Location> locations;
	
	/**
	 * This ArrayList contains all of the players
	 */
	private ArrayList<Player> players;
	
	/**
	 * This is the constructor for a monopoly board
	 */
	public Board()
	{
		//createLocations();
		for (int i = 0; i < NUMBER_OF_LOCATIONS; i++)
		{
			setLocation(i);
		}
		players = new ArrayList<Player>();
	}
	
	/**
	 * This method should prompt the user for the location
	 * @param index
	 */
	public void setLocation(int index)
	{
		//Need to implement
	}
	
	/**
	 * This method returns the location at the given index
	 * @param index
	 * @return
	 */
	public Location getLocation(int index)
	{
		return locations.get(index);
	}
	
	/**
	 * This method returns a random number between 1 and 6
	 * @return
	 */
	public static int dice()
	{
		Random r = new Random();
		return r.nextInt(5)+1;
	}
	
	/**
	 * This method returns the list of players
	 * @return
	 */
	public ArrayList<Player> getPlayers()
	{
		return players;
	}
	
	/**
	 * This method returns the list of locations
	 * @return
	 */
	public ArrayList<Location> getLocations()
	{
		return locations;
	}
}
