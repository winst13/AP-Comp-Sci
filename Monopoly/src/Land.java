
public class Land extends Property
{
	/**
	 * This is the number of the group the Land is in
	 */
	private int group;
	
	/**
	 * This is the number of houses on the property.  5 houses
	 * represents a hotel.  This should only end up as a number 
	 * between 0 and 5
	 */
	private int houses;
	
	private int rent0;
	private int rent1;
	private int rent2;
	private int rent3;
	private int rent4;
	private int rent5;
	
	/**
	 * This is the constructor for a Land.  It takes the 
	 * location of the land, the name, owner (null at first), value,
	 * and group.
	 * @param index
	 * @param name
	 * @param owner
	 * @param value
	 * @param group
	 */
	public Land(int index, String name, Player owner, int value, int group)
	{
		super(index, name, owner, value);
		this.group = group;
		houses = 0;
		System.out.println("Created a Land");
	}
	
	/**
	 * This method exchanges rent from player 1 to player 2
	 * Doubles when owner has all the lands in the group
	 * Takes into account houses/hotel
	 */
	@Override
	public void rent(Player p1, Player p2)
	{
		if (houses == 0)
		{
			p1.addCash(-rent0);
			p2.addCash(rent0);
		}
		else if (houses == 1)
		{
			p1.addCash(-rent1);
			p2.addCash(rent1);
		}
		else if (houses == 2)
		{
			p1.addCash(-rent2);
			p2.addCash(rent2);
		}
		else if (houses == 3)
		{
			p1.addCash(-rent3);
			p2.addCash(rent3);
		}
		else if (houses == 4)
		{
			p1.addCash(-rent4);
			p2.addCash(rent4);
		}
		else if (houses == 5)
		{
			p1.addCash(-rent5);
			p2.addCash(rent5);
		}
		else
		{
			System.out.println("Not a valid number of houses");
		}
	}
	
	/**
	 * This method returns the group number that this property
	 * is in
	 * @return
	 */
	public int getGroup()
	{
		return group;
	}
	
	/**
	 * This method adds a house to the land.  Makes sure that the
	 * number of houses on each property are the same (give or take
	 * 1, optional).  Subtracts appropriate amount of money from
	 * the owner
	 */
	public void addHouse()
	{
		int cost = 0;
		switch (group)
		{
			case 1: cost = 50;
			break;
			case 2: cost = 50;
			break;
			case 3: cost = 100;
			break;
			case 4: cost = 100;
			break;
			case 5: cost = 150;
			break;
			case 6: cost = 150;
			break;
			case 7: cost = 200;
			break;
			case 8: cost = 200;
			break;
		}
		
		if (houses == 5)
		{
			System.out.println("Already have a hotel");
		}
		else
		{
			houses++;
			owner.addCash(-cost);
			System.out.println("House bought");
		}
	}
	
	public int getHouses()
	{
		return houses;
	}
	
	public String getType()
	{
		return "Land";
	}
	
	public int numberInGroup()
	{
		int count = 0;
		for (int i = 0; i < owner.getProperties().size(); i++)
		{
			if (owner.getProperties().get(i).getType().equals("Land"))
			{
				if (owner.getProperties().get(i).getGroup() == 1)
				{
					count++;
				}
			}
		}
		System.out.println("Number in group is "+count);
		return count;
	}
}
