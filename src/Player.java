/*
 * Meiyouka Yao
 * sy2688
 * Blackjack Program- Player class
 * 
 * The Player class simulates a player in blackjack. The player class
 * has its own "hand" and its own balance, like that of a real player.
 * The class has a resetHand method, which removes all cards from the
 * player's hand, the player can also accept cards, using the accept
 * method. The turn method evaluates the player's hand for blackjack and
 * the changeBalance method changes the balance of the player depending on
 * their win/loss of the bet. There are various get methods to return the 
 * hand and the balance, as well as the total value of the hand.
 * 
 */
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Player {

	private ArrayList<Card> hand;
	private double balance;	
	
	//player constructor, initializes instance variables
	public Player(double b)
	{
		hand = new ArrayList<Card>();
		balance = b;	
	}
	
	public void resetHand()
	{
		hand.clear();
	}

	//accept cards
	public void accept(Card card)
	{
		hand.add(card);
	}
	
	//method gets hand
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	
	
	//method returns balance
	public double getBalance()
	{
		return balance;
	}
	
	//method changes balance of player
	public void changeBalance(int i, double bet)
	{
		if (i == 0)	//subtract when betting
		{
			balance = balance - bet;
		}
		else if (i == 1)	//adds if you win bet
		{
			balance = balance + (bet * 1.0 + bet);
			//if you bet 10, you get 20
		}
		else if (i == 2)	//if you win blackjack
		{
			balance = balance + (bet * 1.5 + bet);
			//if you bet 10, you get 25
		}
		else //resets balance when both sides get blackjack
		{
			balance = balance + bet;
		}
	}

	//method evaluates player's hand for blackjack
	public int turn(double bet)
	{
		if (getTotal() == 21)	//winning condition
		{
			System.out.println("Player has 21!");
			changeBalance(2, bet);	//adds to balance using blackjack ratio
			System.out.println("Balance: " + NumberFormat.getCurrencyInstance
					(new Locale("en", "US")).format(balance));
			return 0;
		}
		else if (getTotal() > 21)	//losing condition
		{
			System.out.println(hand);
			System.out.println("You lose.");
			System.out.println("Final Balance: " + NumberFormat.
					getCurrencyInstance(new Locale("en", "US")).
					format(balance));
			return 1;
		}
		
		return 2;	//game continues

	}

	//method gets player's total value for hand
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
