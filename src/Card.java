/*
 * Meiyouka Yao
 * sy2688
 * Blackjack Program- Card class
 * 
 * The Card class is meant to simulate a normal card, with a suit,
 *  rank, and value. It includes a card constructor, taking parameters
 *  of suit, rank and value and has get methods for suit rank and value.
 *  The Card toString method gives the format for labeling the card.
 */
public class Card {

	private String suit;
	private String rank;
	private int value;

	//card constructor with suit, rank and value
	public Card(String suit, String rank, int value)	
	{
		this.suit = suit;
		this.rank = rank;
		this.value = value;
	}
	
	//method returns card value
	public int getVal()
	{
		return value;
	}
	
	//method returns rank of card
	public String getRank()
	{
		return rank;
	}
	
	//method returns card suit
	public String getSuit()
	{
		return suit;
	}
	
	//toString method of Card
	public String toString() 
	{
		return value + " " + rank + " of " + suit;
	}
}
