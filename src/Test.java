/*
 * Meiyouka Yao
 * sy2688
 * Blackjack Program- Test class
 * 
 * The Test class is just a class that creates an instance
 * of Game, it creates a new game of Blackjack. Through the game
 * object, the test class calls the play method to play blackjack.
 */
public class Test {

	public static void main(String[] args) 
	{
		//creates a Game object and plays it
		Game g = new Game();
		g.play();
	}

}
