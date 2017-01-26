/*
 * Meiyouka Yao
 * sy2688
 * Blackjack Program- Game class
 * 
 * The Game class is the class with most of the work implementing the play
 * of blackjack. The game consists of a deck, dealer and player, initialized
 * in the Game constructor with the constructor setting up the initial hands
 * of the player and dealer. The play method plays the game of Blackjack and
 * when one round has finished, the playAgain method is used to determine if
 * the game should be continued. The draw method is for the players to draw
 * a card.
 */
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Game {

	private Deck deck;
	private Dealer dealer;
	private Player player;	

	//Game constructor initializes instance variables
	public Game()
	{
		Scanner console = new Scanner(System.in);
		double b = 0;

		deck = new Deck();
		deck.shuffle();
		dealer = new Dealer();

		do	//asks for buy-in amount
		{
			System.out.println("Buy in for how much? At least $100");
			b = (int) console.nextDouble();
		}
		while (b < 100.0);

		player = new Player(b);

		//sets up player/dealer hand at the start of game
		player.accept(draw());
		player.accept(draw());
		dealer.accept(draw());
		dealer.accept(draw());

	}

	//method, call it every time someone busts
	public void playAgain()
	{
		Scanner console = new Scanner(System.in);
		int i = 0;

		do
		{
			System.out.println("Play again? Enter 1 (Yes) or 0 (No)?");
			i = console.nextInt();
		}
		while (!(i == 1 || i == 0));
		
		if (i == 0)
		{
			System.exit(0);
		}
		else
		{
			player.resetHand();
			dealer.resetHand();
			
			//sets up new player/dealer hand
			player.accept(draw());
			player.accept(draw());
			dealer.accept(draw());
			dealer.accept(draw());
		}
		
		play();
	}

	//play method
	public void play()
	{	
		boolean winner = false;
		boolean dealerBlackjack = false;
		boolean playerBlackjack = false;

		Scanner console = new Scanner(System.in);
		int choice = 0;	//to stay or to hit for player
		double bet = 0;	//player's bet

		do	//loop checks for valid bet, no choice when cards dealt
		{
			System.out.println("Bet how much? Between 10 & 1000");
			bet = (int) console.nextDouble();
		}
		while (bet > player.getBalance() || bet < 10.0 
				|| bet > 1000.0);
		player.changeBalance(0, bet);	//subtracts bet

		//shows cards
		Card c = dealer.getTopCard();
		System.out.println("Dealer's top card: " + c.toString());

		while (!winner)	//while there is no winner
		{
			//prints info needed for player
			System.out.println(player.getHand());
			System.out.println("Player's total value of cards: " 
					+ player.getTotal());
			System.out.println("Balance after bet: " + NumberFormat.
					getCurrencyInstance(new Locale("en", "US")).
					format(player.getBalance()));

			//if you get blackjack when cards are first dealt
			int i = player.turn(bet);
			if (i == 0)
			{
				playerBlackjack = true;
			}
			else if (i == 1)	//player busted
			{
				playerBlackjack = false;
				playAgain();
			}
			else if (i == 2)
			{
				playerBlackjack = false;
			}
			winner = playerBlackjack;

			dealerBlackjack = dealer.turn();
			winner = dealerBlackjack;
			System.out.println("Dealer total value initial: " 	
					+ dealer.getTotal());	//for error checking/grading

			//compares dealer and player scores, pays player as needed
			if (player.getTotal() > dealer.getTotal())	//player bet wins
			{
				player.changeBalance(1, bet);
			}
			else if (player.getTotal() == dealer.getTotal())	//tie
			{
				player.changeBalance(3, bet);	//resets balance
			}
			else if (playerBlackjack)	//player gets blackjack
			{
				player.changeBalance(2, bet);
				System.out.println("Final Balance: " + player.getBalance());
				playAgain();	//ends game
			}
			else if (dealerBlackjack)
			{
				System.out.println("Dealer has 21.");
				System.out.println("Final Balance: " + player.getBalance());
				playAgain();	//ends game
			}
			System.out.println("Balance after initial round (2 cards):" 
					+ NumberFormat.
					getCurrencyInstance(new Locale("en", "US")).
					format(player.getBalance()));

			while (player.getTotal() < 21 && winner == false)
			{
				if (player.getBalance() == 0)	//can't bet
				{
					System.out.println("No more money left. Game ends.");
					System.exit(0);
				}

				do	//hit or stay
				{
					System.out.println("Stay or hit? (Input 0 or 1)");
					choice = console.nextInt();
				}
				while (!(choice == 1 || choice == 0));

				if (choice == 1)	//player hits
				{
					player.accept(draw());	//gives another card to player
					
					player.turn(bet);	//evaluates score

					int j = player.turn(bet);	//evaluates score

					if (j == 0)	//player has blackjack
					{
						playerBlackjack = true;
					}
					else if (j == 1)	//player busted
					{
						playerBlackjack = false;
						playAgain();
					}
					else if (j == 2)	//game continues
					{
						playerBlackjack = false;
					}
					winner = playerBlackjack;
					
					if (playerBlackjack == true)
					{
						winner = true;
					}
				}

				//if player didn't bust, dealer's turn
				while (dealer.getTotal() <= 16)
				{
					Card x = dealer.getTopCard();
					System.out.println("Dealer's next card: " + x);
					dealer.accept(draw());	//draws another card
					dealerBlackjack = dealer.turn(); //score evaluated
					System.out.println("I'm only printing the dealer's"
							+ " value for cards for ease of checking,"
							+ " not because the player should know it");
					System.out.println("Dealer total value: " + 
							dealer.getTotal());
				}

				if (dealerBlackjack == true)	//if dealer has blackjack
				{
					winner = true;
				}
				else if (dealerBlackjack == false && dealer.getTotal() > 21)	
				{
					//if dealer has busted, game ends
					player.changeBalance(1, bet); //player wins bet
					System.out.println("Final Balance: " + NumberFormat.
							getCurrencyInstance(new Locale("en", "US")).
							format(player.getBalance()));
					playAgain();
				}

				//in the event that both players get blackjack
				if (dealerBlackjack == true && playerBlackjack == true)
				{
					winner = false;
					System.out.println("Player and dealer have blackjack,"
							+ " game continues.");
					player.changeBalance(3, bet);	//resets balance
					System.out.println("Balance reset: " + 
							player.getBalance());
				}

				//compares scores, game continues
				if (player.getTotal() > dealer.getTotal())
				{
					//player wins bet
					player.changeBalance(1, bet);
				}
				else if (player.getTotal() == dealer.getTotal())	//tie
				{
					//resets balance, gives back original bet
					player.changeBalance(3, bet);	//resets balance
				}

				//outputs information after player and dealer turns
				System.out.println(player.getHand());
				System.out.println("Total value: " + player.getTotal());
				System.out.println("Balance after dealer turn: " + 
						NumberFormat.getCurrencyInstance(new Locale
						("en", "US")).format(player.getBalance()));
			}
			playAgain();
		}

		console.close();
	}

	//draws a card
	public Card draw()
	{
		return deck.deal();
	}
}
