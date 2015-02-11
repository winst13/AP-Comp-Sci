/* 
 * Example code for Think Java (http://thinkapjava.com)
 *
 * Copyright(c) 2011 Allen B. Downey
 * GNU General Public License v3.0 (http://www.gnu.org/copyleft/gpl.html)
 *
 * @author Allen B. Downey
 * @author Winston Wang
 */


public class CardSoln3 {
    /*
     * Test code.
     */
    public static void main(String[] args) {
	Card card = new Card(1, 1);
	card.print();
	String s = card.toString();
	System.out.println(s);
	System.out.println(card);

	Card card2 = new Card(1, 1);
	System.out.println(card.equals(card2));

	Deck deck = new Deck();
	
	// check sortDeck
	deck.shuffle();
	deck.sort();
	checkSorted(deck);

	// check that findBisect finds each card
	int index;
	for (int i=0; i<52; i++) {
	    index = deck.findBisect(deck.cards[i], 0, 
				    deck.cards.length-1);
	    if (index != i) {
		System.out.println("Not found!");
	    }
	}
	
	// make a subdeck
	Deck hand = deck.subdeck(8, 12);
	hand.print();

	// check that findBisect doesn't find a card that's not there
	Card badCard = new Card(1, 1);
	index = hand.findBisect(badCard, 0, hand.cards.length-1);
	if (index != -1) {
	    System.out.println("Found?");
	}

	// check mergeSort
	deck.shuffle();
	deck = deck.mergeSort();
	checkSorted(deck);
	
	
	//This tests the deal() method, for part 4 of exercise 5
	deck.shuffle();
	PokerHand hand1 = deck.deal(5);
	PokerHand hand2 = deck.deal(5);
	PokerHand hand3 = deck.deal(5);
	PokerHand hand4 = deck.deal(5);
	System.out.println("");
	hand1.print();
	System.out.println("");
	hand2.print();
	System.out.println("");
	hand3.print();
	System.out.println("");
	hand4.print();
	System.out.println("");
	
	//This test the poker hand methods, for parts 7 and 8
	int n = 100000;
	int numflush = 0;
	int numtriple = 0;
	int numpair = 0;
	int numquad = 0;
	int numtwopair = 0;
	int numfullh = 0;
	int numstraight = 0;
	int numsflush = 0;
	for (int i = 0; i < n; i++)
	{
		Deck newdeck = new Deck();
		newdeck.shuffle();
		PokerHand newhand = newdeck.deal(5);
		if (newhand.hasFlush())
		{
			numflush++;
		}
		if (newhand.hasThreeKind())
		{
			numtriple++;
		}
		if (newhand.hasFourKind())
		{
			numquad++;
		}
		if (newhand.hasPair())
		{
			numpair++;
		}
		if (newhand.hasTwoPair())
		{
			numtwopair++;
		}
		if (newhand.hasFullHouse())
		{
			numfullh++;
		}
		if (newhand.hasStraight())
		{
			numstraight++;
		}
		if (newhand.hasStraightFlush())
		{
			numsflush++;
		}
	}
	System.out.println("\n5 cards:");
	System.out.println("Straight Flush:"+ ((double)numsflush)/((double)n)*100);
	System.out.println("Quadruple: "+ ((double)numquad)/((double)n)*100);
	System.out.println("Full Houlse: "+ ((double)numfullh)/((double)n)*100);
	System.out.println("Flush: "+ ((double)numflush)/((double)n)*100);
	System.out.println("Straight: "+ ((double)numstraight)/((double)n)*100);
	System.out.println("Triple: "+ ((double)numtriple)/((double)n)*100);
	System.out.println("Two Pair: "+ ((double)numtwopair)/((double)n)*100);
	System.out.println("Pair: "+ ((double)numpair)/((double)n)*100);
	
	//This test the poker hand methods, for parts 7 and 8
	n = 100000;
	numflush = 0;
	numtriple = 0;
	numpair = 0;
	numquad = 0;
	numtwopair = 0;
	numfullh = 0;
	numstraight = 0;
	numsflush = 0;
	for (int i = 0; i < n; i++)
	{
		Deck newdeck = new Deck();
		newdeck.shuffle();
		PokerHand newhand = newdeck.deal(7);
		if (newhand.hasFlush())
		{
			numflush++;
		}
		if (newhand.hasThreeKind())
		{
			numtriple++;
		}
		if (newhand.hasFourKind())
		{
			numquad++;
		}
		if (newhand.hasPair())
		{
			numpair++;
		}
		if (newhand.hasTwoPair())
		{
			numtwopair++;
		}
		if (newhand.hasFullHouse())
		{
			numfullh++;
		}
		if (newhand.hasStraight())
		{
			numstraight++;
		}
		if (newhand.hasStraightFlush())
		{
			numsflush++;
		}
	}
	System.out.println("\n7 cards:");
	System.out.println("Straight Flush:"+ ((double)numsflush)/((double)n)*100);
	System.out.println("Quadruple: "+ ((double)numquad)/((double)n)*100);
	System.out.println("Full Houlse: "+ ((double)numfullh)/((double)n)*100);
	System.out.println("Flush: "+ ((double)numflush)/((double)n)*100);
	System.out.println("Straight: "+ ((double)numstraight)/((double)n)*100);
	System.out.println("Triple: "+ ((double)numtriple)/((double)n)*100);
	System.out.println("Two Pair: "+ ((double)numtwopair)/((double)n)*100);
	System.out.println("Pair: "+ ((double)numpair)/((double)n)*100);
    }

    /*
     * Checks that the deck is sorted.
     */
    public static void checkSorted(Deck deck) {
	for (int i=0; i<51; i++) {
	    int flag = deck.cards[i].compareTo(deck.cards[i+1]);
	    if (flag != -1) {
		System.out.println("Not sorted!");
	    }
	}
    } 
}
