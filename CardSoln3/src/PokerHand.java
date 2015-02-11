import java.util.Arrays;


/**
 * For part 2 of exercise 5
 * This is the PokerHand class
 * @author winst13
 *
 */
class PokerHand extends Deck
{
	public PokerHand()
	{
		super(5);
	}
	
	public PokerHand(int num)
	{
		super(num);
	}
	
	/**
	 * This method returns whether a hand has a flush or not
	 * @return
	 */
	public boolean hasFlush()
	{	
		String suit = cards[0].getsuit();
		this.mergeSort();
		int lengthofstreak = 0;
		for (int i = 0; i < cards.length; i++)
		{
			if (suit == cards[i].getsuit())
			{
				lengthofstreak++;
			}
			else
			{
				suit = cards[i].getsuit();
				lengthofstreak = 1;
			}
			
			if (lengthofstreak == 5)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method returns whether a hand has a three of a kind
	 * @return
	 */
	public boolean hasThreeKind()
	{
		if (hasStreak(4))
		{
			return false;
		}
		return hasStreak(3);
	}
	
	/**
	 * This method returns whether a hand has a pair or not
	 * It excludes the instances of two pairs
	 * @return
	 */
	public boolean hasPair()
	{
		if (hasStreak(2))
		{
			int rank = rankStreak(2);
			if (hasStreak(2,rank))
			{
				return false;
			}
		}
		if (hasStreak(4)||hasStreak(3))
		{
			return false;
		}
		
		return hasStreak(2);
	}
	
	/**
	 * This method returns whether a hand has a four of a kind
	 * @return
	 */
	public boolean hasFourKind()
	{
		return hasStreak(4);
	}
	
	/**
	 * This useful method is used when finding quads, trips, and pairs
	 * @param n
	 * @return
	 */
	public boolean hasStreak(int n)
	{
		int[] ranks = new int[this.cards.length];
		for (int i = 0; i < this.cards.length; i++)
		{
			ranks[i] = this.cards[i].rank;
		}
		Arrays.sort(ranks);
		
		int lengthofstreak = 1;
		int rank = -1;
		for (int i = 0; i < this.cards.length; i++)
		{
			if (rank == ranks[i])
			{
				lengthofstreak++;
			}
			else
			{
				lengthofstreak = 1;
				rank = ranks[i];
			}
			
			if (lengthofstreak == n)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This useful method is used when finding quads, trips, and pairs
	 * It also allows the exception of one rank
	 * @param n
	 * @return
	 */
	public boolean hasStreak(int n, int excluded)
	{
		int[] ranks = new int[this.cards.length];
		for (int i = 0; i < this.cards.length; i++)
		{
			ranks[i] = this.cards[i].rank;
		}
		Arrays.sort(ranks);
		
		int lengthofstreak = 1;
		int rank = -1;
		for (int i = 0; i < this.cards.length; i++)
		{
			if (rank == ranks[i]&&rank != excluded)
			{
				lengthofstreak++;
			}
			else
			{
				lengthofstreak = 1;
				rank = ranks[i];
			}
			
			if (lengthofstreak == n)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the rank of the streak of cards
	 * @return
	 */
	public int rankStreak(int n)
	{
		int[] ranks = new int[this.cards.length];
		for (int i = 0; i < this.cards.length; i++)
		{
			ranks[i] = this.cards[i].rank;
		}
		Arrays.sort(ranks);
		
		int lengthofstreak = 1;
		int rank = -1;
		for (int i = 0; i < this.cards.length; i++)
		{
			if (rank == ranks[i])
			{
				lengthofstreak++;
			}
			else
			{
				lengthofstreak = 1;
				rank = ranks[i];
			}
			
			if (lengthofstreak == n)
			{
				return rank;
			}
		}
		
		return -1;
	}
	
	/**
	 * Returns true if the hand has a two pair
	 * @return
	 */
	public boolean hasTwoPair()
	{
		if (hasStreak(2))
		{
			int rank = rankStreak(2);
			if (hasStreak(2,rank))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method returns whether a hand has a full house or not
	 * @return
	 */
	public boolean hasFullHouse()
	{
		if (hasStreak(3))
		{
			int rank = rankStreak(3);
			if (hasStreak(2,rank))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method returns whether the hand has a straight or not
	 * @return
	 */
	public boolean hasStraight()
	{	
		int[] ranks = new int[this.cards.length];
		for (int i = 0; i < this.cards.length; i++)
		{
			ranks[i] = this.cards[i].rank;
		}
		Arrays.sort(ranks);
		
		int lengthofstreak = 1;
		int rank = -1;
		for (int i = 0; i < this.cards.length; i++)
		{
			if (rank == ranks[i]-1)
			{
				lengthofstreak++;
				rank = ranks[i];
			}
			else
			{
				lengthofstreak = 1;
				rank = ranks[i];
			}
			
			if (lengthofstreak == 5)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * This method returns the highest rank in a 5-card straight
	 * @return
	 */
	public int rankStraight()
	{
		int[] ranks = new int[this.cards.length];
		for (int i = 0; i < this.cards.length; i++)
		{
			ranks[i] = this.cards[i].rank;
		}
		Arrays.sort(ranks);
		
		int lengthofstreak = 1;
		int rank = -1;
		for (int i = 0; i < this.cards.length; i++)
		{
			if (rank == ranks[i]-1)
			{
				lengthofstreak++;
				rank = ranks[i];
			}
			else
			{
				lengthofstreak = 1;
				rank = ranks[i];
			}
			
			if (lengthofstreak == 5)
			{
				return rank;
			}
		}
		
		return -1;
	}
	
	/**
	 * This method returns the highest rank of a flush
	 * @return
	 */
	public int rankFlush()
	{
		String suit = cards[0].getsuit();
		this.mergeSort();
		int lengthofstreak = 0;
		for (int i = 0; i < cards.length; i++)
		{
			if (suit == cards[i].getsuit())
			{
				lengthofstreak++;
			}
			else
			{
				suit = cards[i].getsuit();
				lengthofstreak = 1;
			}
			
			if (lengthofstreak == 5)
			{
				return cards[i].rank;
			}
		}
		
		return -1;
	}
	
	/**
	 * This method implements many helper functions.  It can detect
	 * whether a hand has a straight flush or not.
	 * @return
	 */
	public boolean hasStraightFlush()
	{
		if (hasFlush())
		{
			int endrank = rankFlush();
			if (endrank==rankStraight())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else 
		{
			return false;
		}
	}
	
}