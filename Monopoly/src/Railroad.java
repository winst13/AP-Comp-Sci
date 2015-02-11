
public class Railroad extends Property
{
	/**
	 * This is the constructor
	 * @param index
	 * @param name
	 * @param owner
	 * @param value
	 */
	public Railroad(int index, String name, Player owner, int value)
	{
		super(index, name, owner, value);
	}
	
	/**
	 * This method takes into account the number of other
	 * Railroads that this owner owns
	 */
	@Override
	public void rent(Player p1, Player p2)
	{
		int rent = 0;
		switch (this.numberInGroup())
		{
			case 1: rent = 50;
				break;
			case 2: rent = 100;
				break;
			case 3: rent = 150;
				break;
			case 4: rent = 200;
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
			if (owner.getProperties().get(i).getType().equals("Railroad"))
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
		return "Railroad";
	}

	@Override
	public int getGroup() 
	{
		return 0;
	}
}
