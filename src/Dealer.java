/*
 * Meiyouka Yao
 * sy2688
 * Blackjack Program- Dealer class
 * 
 * The Dealer class is a class that simulates the dealer in blackjack
 * and only has a hand instance variable, an arraylist of cards.
 * The dealer constructor initializes hand, and has methods to accept a
 * card, to reset the hand to 0 cards, and the turn method to evaluate
 * the dealer's hand for blackjack. It also contains get methods for getting
 * the top card of the dealer that is unrevealed and the total value of
 * the dealer's hand.
 */

import java.util.ArrayList;
public class Dealer {

	private ArrayList<Card> hand;

	public Dealer()
	{
		hand = new ArrayList<Card>();	//initializes arraylist
	}

	//method for dealer to accept cards
	public void accept(Card card)
	{
		hand.add(card);
	}

	public void resetHand()
	{
		hand.clear();
	}
	
	public boolean turn()
	{
		boolean dealerWins = false;

		if (getTotal() == 21)	//winning condition
		{
			System.out.println("Dealer has 21.");
			dealerWins = true;
			return dealerWins;
		}
		else if (getTotal() > 21)	//losing condition
		{
			System.out.println("Dealer has busted.");
			return dealerWins;
		}
		
		return dealerWins;	//game continues
	}

	//method gets dealer's top card
	public Card getTopCard()
	{
		return hand.get(hand.size() - 1);
	}

	//method gets total value of hand
	public int getTotal()
	{
		int total = 0;
		boolean containsAce = false;

		for (int i = 0; i < hand.size(); i++)
		{
			Card c = hand.get(i);
			String rank = c.getRank();
			int value = c.getVal();

			if (rank.equals("Ace"))	//checks for ace
			{
				containsAce = true;
			}
			total += value;	
		}

		if (total < 12 && containsAce)	
		{
			total = total + 10;	//changes ace value to 11;
		}

		return total;
	}
}
