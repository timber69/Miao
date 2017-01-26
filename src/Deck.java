/*
 * Meiyouka Yao
 * sy2688
 * Blackjack Program- Deck class
 * 
 * The Deck class simulates a 52 card deck with 4 different suits,
 * 13 different ranks, and 11 different values. The 11 different values
 * apply blackjack, going from 1 to 11 (ace) rather than going by the normal
 * ranks. The Deck constructor constructs a deck of Cards and initializes
 * the instance variables. This class includes a shuffle, toString, and 
 * a deal method.
 */

public class Deck {

	private Card[] deck;
	private int top;

	//deck constructor, initializes deck and top
	public Deck()
	{
		deck = new Card[52];
		int[] values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
		String[] suits = new String[] {"spades", "clubs", "diamonds", 
			"hearts"};
		String[] ranks = new String[] {"Ace", "Two", "Three", "Four", "Five",
			"Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

		int k = 0;
		while (k < deck.length)	//loops through entire array
		{
			Card c;

			for (int j = 0; j < suits.length; j++)
			{
				for (int i = 0; i < values.length; i++)
				{
					//new card created and entered in array
					//according to its suit, rank and value
					c = new Card(suits[j], ranks[i], values[i]);
					deck[k++] = c;
				}
			}
		}

		top = 51;	//the last element in the array
	}

	
	//method returns card that is dealt, tracks new top of the deck
	public Card deal()
	{	
		Card c = deck[top];
		top = top - 1;
		
		if (top == 11)	//shuffles deck when there are 12 cards
		{
			top = 51;
			this.shuffle();
		}
		
		return c;	//returns card to be dealt
	}

	//shuffle method
	public void shuffle() 
	{
		//loops for shuffling cards
		for (int k = top; k >= 0; k--)
		{
			int y = (int) (Math.floor (Math.random() * (top - 1)));
			Card temp = deck[k];
			deck[k] = deck[y];
			deck[y] = temp;
		}
	}

	//toString method for deck, not used in Game, but to test if deck
	//is working correctly
	public String toString()
	{
		String rtn = "size = " + deck.length + "\nUndealt cards: \n";

		for (int k = deck.length - 1; k >= 0; k--) 
		{
			rtn = rtn + deck[k];
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((deck.length - k) % 2 == 0) 
			{
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = deck.length - 1; k >= deck.length; k--) 
		{
			rtn = rtn + deck[k];
			if (k != deck.length) 
			{
				rtn = rtn + ", ";
			}
			if ((k - deck.length) % 2 == 0) 
			{
				//Insert carriage returns-> entire deck is visible on console
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
