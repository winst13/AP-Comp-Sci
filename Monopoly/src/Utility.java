
public class Utility extends Property
{	
	/**
	 * This is the constructor, which takes index, name, owner, and value
	 * @param index
	 * @param name
	 * @param owner
	 * @param value
	 */
	public Utility(int index, String name, Player owner, int value)
	{
		super(index, name, owner, value);
	}
	
	/**
	 * This rent will be calculated using the last roll that the
	 * player had.  Also depends on if the other person 
	 */
	@Override
	public void rent(Player p1, Player p2)
	{
		int rent = 0;
		switch (this.numberInGroup())
		{
			case 1: rent = p1.getLastMove() * 4;
				break;
			case 2: rent = p1.getLastMove() * 10;
				break;
		}
		System.out.println("Rent for railroad: "+rent);
		p2.addCash(rent);
		p1.addCash(-rent);
	}

	@Override
	public int numberInGroup() 
	{
		int count = 0;
		for (int i = 0; i < owner.getProperties().size(); i++)
		{
			if (owner.getProperties().get(i).getType().equals("Utility"))
			{
				count++;
			}
		}
		System.out.println("Number in group is "+count);
		return count;
	}

	@Override
	public String getType() 
	{
		return "Utility";
	}

	@Override
	public int getGroup() 
	{
		return 0;
	}
}
