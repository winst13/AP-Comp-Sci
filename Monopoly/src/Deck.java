import java.util.*;


/**
 * This is the class that handles all of the chance and 
 * community chest cards
 * @author winst13
 *
 */
public class Deck 
{
	/**
	 * This is the list of cards in each deck
	 * 
	 * Community Chest list (type 1): 
	 * 0. Holiday Fund matures - Receive $100 
	 * 1. Advance to Go (Collect $200) 
	 * 2. Bank error in your favor – collect $75 
	 * 3. Doctor's fees – Pay $50 
	 * 4. Get out of jail free – this card may be kept until needed, or sold 
	 * 5. Go to jail – go directly to jail – Do not pass Go, do not collect $200 
	 * 6. It is your birthday Collect $10 from each player 
	 * 7. Grand Opera Night – collect $50 from every player for opening night seats 
	 * 8. Income Tax refund – collect $20 
	 * 9. Life Insurance Matures – collect $100 
	 * 10. Pay Hospital Fees of $100 
	 * 11. Pay School Fees of $50 
	 * 12. Receive $25 Consultancy Fee 
	 * 13. You are assessed for street repairs – $40 per house, $115 per hotel 
	 * 14. You have won second prize in a beauty contest– collect $10 
	 * 15. You inherit $100 
	 * 16. From sale of stock you get $50 
	 * 
	 * Chance list (Type 2): 
	 * 0. See number 4
	 * 1. Advance to Go (Collect $200) 
	 * 2. Advance to Illinois Ave. 
	 * 3. Advance token to nearest Utility. If unowned, you may buy it from the Bank. 
	 *    If owned, throw dice and pay owner a total ten times the amount thrown. 
	 * 4. Advance token to the nearest Railroad and pay owner twice the rental to 
	 *    which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank. 
	 *    (There are two of these.) 
	 * 5. Advance to St. Charles Place – if you pass Go, collect $200 
	 * 6. Bank pays you dividend of $50 
	 * 7. Get out of Jail free – this card may be kept until needed, or traded/sold 
	 * 8. Go back 3 spaces 
	 * 9. Go directly to Jail – do not pass Go, do not collect $200 
	 * 10. Make general repairs on all your property – for each house pay $25 – for each hotel $100 
	 * 11. Pay poor tax of $15 
	 * 12. Take a trip to Reading Railroad – if you pass Go collect $200 
	 * 13. Take a walk on the Boardwalk – advance token to Boardwalk 
	 * 14. You have been elected chairman of the board – pay each player $50 
	 * 15. Your building loan matures – collect $150 
	 * 16. You have won a crossword competition - collect $100
	 */
	
	/**
	 * This is the list of card numbers left in each deck.  Once the deck is emptied
	 * it is shuffled and refilled
	 */
	private static ArrayList<Integer> comChest = new ArrayList<Integer>();
	private static ArrayList<Integer> chance = new ArrayList<Integer>();
	
	/**
	 * The number of cards is 17 (0-16)
	 */
	public static final int NO_OF_CARDS = 17;
	
	/**
	 * This is the constructor for a deck.  It should not be used, since
	 * the methods are all static
	 */
	public Deck()
	{
		//Nothing
	}
	
	/**
	 * This method draws a random Community Chest card and 
	 * performs the action on it.  It also prints the card
	 * name
	 * @param landed
	 * @param b
	 */
	public static void randComChest(Player landed, Board b)
	{
		if (comChest.size()==0)
		{
			comChest = new ArrayList<Integer>(NO_OF_CARDS);
			for (int i = 0; i < comChest.size(); i++)
			{
				comChest.add(i);
			}
		}
		Random r = new Random();
		int index = r.nextInt(comChest.size());
		int num = comChest.get(index);
		comChest.remove(index);
		
		if (num == 0)
		{
			landed.addCash(100);
			System.out.println("Holiday Fund matures - Receive $100 ");
		}
		else if (num == 1)
		{
			landed.location = 0;
			landed.addCash(200);
			System.out.println("Advance to Go (Collect $200)");
		}
		else if (num == 2)
		{
			landed.addCash(75);
			System.out.println("Bank error in your favor – collect $75");
		}
		else if (num == 3)
		{
			landed.addCash(-50);
			System.out.println("Doctor's fees – Pay $50");
		}
		else if (num == 4)
		{
			landed.freeOutCard = true;
			System.out.println("Get out of jail free – this card may be kept until needed, or sold");
		}
		else if (num == 5)
		{
			landed.changeJail();
			System.out.println("Go to jail – go directly to jail – Do not pass Go, do not collect $200");
		}
		else if (num == 6)
		{
			for (int i = 0; i < b.getPlayers().size(); i++)
			{
				b.getPlayers().get(i).addCash(-10);
				landed.addCash(10);
			}
			System.out.println("It is your birthday Collect $10 from each player");
		}
		else if (num == 7)
		{
			for (int i = 0; i < b.getPlayers().size(); i++)
			{
				b.getPlayers().get(i).addCash(-50);
				landed.addCash(50);
			}
			System.out.println("Grand Opera Night – collect $50 from every "
					+ "player for opening night seats");
		}
		else if (num == 8)
		{
			landed.addCash(20);
			System.out.println("Income Tax refund – collect $20");
		}
		else if (num == 9)
		{
			landed.addCash(100);
			System.out.println("Life Insurance Matures – collect $100");
		}
		else if (num == 10)
		{
			landed.addCash(-100);
			System.out.println("Pay Hospital Fees of $100");
		}
		else if (num == 11)
		{
			landed.addCash(-50);
			System.out.println("Pay School Fees of $50");
		}
		else if (num == 12)
		{
			landed.addCash(25);
			System.out.println("Receive $25 Consultancy Fee");
		}
		else if (num == 13)
		{
			int houses = 0;
			int hotels = 0;
			for (int i = 0; i < landed.getProperties().size(); i++)
			{
				if (landed.getProperties().get(i).getHouses() < 5)
				{
					houses = houses + landed.getProperties().get(i).getHouses();
				}
				else if (landed.getProperties().get(i).getHouses() == 5)
				{
					hotels++;
				}
				else
				{
					System.out.println("Invalid number of houses index " + i);
				}
			}
			landed.addCash(-(40*houses+115*hotels));
			System.out.println("You are assessed for street repairs – $40 per house, $115 per hotel");
		}
		else if (num == 14)
		{
			landed.addCash(10);
			System.out.println("You have won second prize in a beauty contest– collect $10");
		}
		else if (num == 15)
		{
			landed.addCash(100);
			System.out.println("You inherit $100");
		}
		else if (num == 16)
		{
			landed.addCash(50);
			System.out.println("From sale of stock you get $50");
		}
	}
	
	/**
	 * This method is the same as the above method except with
	 * a different deck
	 * @param landed
	 * @param b
	 */
	public static void randChance(Player landed, Board b)
	{
		if (chance.size()==0)
		{
			chance = new ArrayList<Integer>(NO_OF_CARDS);
			for (int i = 0; i < chance.size(); i++)
			{
				chance.add(i);
			}
		}
		Random r = new Random();
		int index = r.nextInt(chance.size());
		int num = chance.get(index);
		chance.remove(index);
		
		if (num == 0||num == 4)
		{
			while (!b.getLocations().get(landed.location).getType().equalsIgnoreCase("Railroad"))
			{
				landed.location++;
			}
			b.getLocations().get(landed.location).action(landed, b);
			b.getLocations().get(landed.location).action(landed, b);
			
			System.out.println("Advance token to the nearest Railroad and pay owner twice the "
					+ "rental to which he/she is otherwise entitled. If Railroad is unowned, "
					+ "you may buy it from the Bank. ");
		}
		else if (num == 1)
		{
			landed.location = 0;
			landed.addCash(200);
			System.out.println("Advance to Go (Collect $200)");
		}
		else if (num == 2)
		{
			if (landed.location > 24)
			{
				landed.addCash(200);
				System.out.println("Passed Go");
			}
			landed.location = 24;
			b.getLocation(landed.location).action(landed, b);
			System.out.println("Advance to Illinois Ave.");
		}
		else if (num == 3)
		{
			while (!b.getLocations().get(landed.location).getType().equalsIgnoreCase("Utility"))
			{
				landed.location++;
			}
			b.getLocations().get(landed.location).action(landed, b);
			
			System.out.println("Advance token to nearest Utility. If unowned, you "
					+ "may buy it from the Bank. If owned, pay rent");
		}
		else if (num == 5)
		{
			if (landed.location > 11)
			{
				landed.addCash(200);
				System.out.println("Passed Go");
			}
			landed.location = 11;
			b.getLocation(landed.location).action(landed, b);
			System.out.println("Advance to St. Charles Place");
		}
		else if (num == 6)
		{
			landed.addCash(50);
			System.out.println("Bank pays you dividend of $50");
		}
		else if (num == 7)
		{
			landed.freeOutCard = true;
			System.out.println("Get out of jail free – this card may be kept until needed, or sold");
		}
		else if (num == 8)
		{
			landed.location = landed.location-3;
			b.getLocation(landed.location).action(landed, b);
			System.out.println("Go back 3 spaces");
		}
		else if (num == 9)
		{
			landed.changeJail();
			System.out.println("Go to jail – go directly to jail – Do not pass Go, do not collect $200");
		}
		else if (num == 10)
		{
			int houses = 0;
			int hotels = 0;
			for (int i = 0; i < landed.getProperties().size(); i++)
			{
				if (landed.getProperties().get(i).getHouses() < 5)
				{
					houses = houses + landed.getProperties().get(i).getHouses();
				}
				else if (landed.getProperties().get(i).getHouses() == 5)
				{
					hotels++;
				}
				else
				{
					System.out.println("Invalid number of houses index " + i);
				}
			}
			landed.addCash(-(25*houses+100*hotels));
			System.out.println("Make general repairs on all your property – for "
					+ "each house pay $25 – for each hotel $100");
		}
		else if (num == 11)
		{
			landed.addCash(-15);
			System.out.println("Pay poor tax of $15");
		}
		else if (num == 12)
		{
			if (landed.location > 5)
			{
				landed.addCash(200);
				System.out.println("Passed Go");
			}
			landed.location = 5;
			b.getLocation(landed.location).action(landed, b);
			System.out.println("Take a trip to Reading Railroad – if you pass Go collect $200");
		}
		else if (num == 13)
		{
			landed.location = 39;
			b.getLocation(landed.location).action(landed, b);
			System.out.println("Take a walk on the Boardwalk – advance token to Boardwalk");
		}
		else if (num == 14)
		{
			for (int i = 0; i < b.getPlayers().size(); i++)
			{
				b.getPlayers().get(i).addCash(50);
				landed.addCash(-50);
			}
			System.out.println("You have been elected chairman of the board – pay each player $50");
		}
		else if (num == 15)
		{
			landed.addCash(150);
			System.out.println("Your building loan matures – collect $150");
		}
		else if (num == 16)
		{
			landed.addCash(100);
			System.out.println("You have won a crossword competition - collect $100");
		}
	}
}
