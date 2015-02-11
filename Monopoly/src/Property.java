
public abstract class Property extends Location
{
	/**
	 * This is the owner of the property
	 */
	protected Player owner;
	
	/**
	 * This is the value of the property
	 */
	protected int value;
	
	/**
	 * This tells the program whether the property is mortgaged or not
	 * If it is mortgaged, then the property will not act
	 */
	private boolean mortgaged;
	
	/**
	 * This takes index, name, owner, and value all at once
	 * @param index
	 */
	public Property(int index, String name, Player owner, int value)
	{
		super(index, name);
		this.owner = owner;
		this.value = value;
	}
	
	/**
	 * This method takes the player that lands on the property and performs
	 * actions on it.  For example, it should collect rent
	 */
	@Override
	public void action(Player landed, Board b) 
	{
		if (owner == null)
		{
			if(landed.decideBuy(this))
			{
				landed.properties.add(this);
				owner.addCash(-value);
				System.out.println("Land bought");
			}
		}
		else if(landed.equals(owner))
		{
			System.out.println("You are the owner!");
		}
		else
		{
			rent(landed, owner);
			System.out.println("Rent paid");
		}
	}
	
	/**
	 * This method takes a given amount of money from
	 * p1 and gives it to p2
	 * @param p1
	 * @param p2
	 */
	public abstract void rent(Player p1, Player p2);
	
	/**
	 * This method sets the owner to Player p
	 * @param p
	 */
	public void setOwner(Player p)
	{
		owner = p;
		System.out.println("Owner set");
	}
	
	/**
	 * This method returns the owner of the property
	 * @return
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * This method returns the value of the property
	 * @return
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * This method checks if the property is mortgaged
	 * @return
	 */
	public boolean getMortgaged()
	{
		return mortgaged;
	}
	
	/**
	 * This method changes the mortgaged status of the
	 * property
	 */
	public void changeMortgaged()
	{
		if (mortgaged)
		{
			System.out.println("Property unmortgaged");
			owner.addCash(-(int)(value/2*1.1));
		}
		else
		{
			System.out.println("Property mortgaged");
			owner.addCash(value/2);
		}
		mortgaged = !mortgaged;
	}
	
	public abstract int numberInGroup();
	
	public abstract String getType();
	
	public abstract int getGroup();
	
	public int getHouses()
	{
		return 0;
	}
}
