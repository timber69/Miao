/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) 
	{        
        //prints deck
        Deck d = new Deck();
        System.out.println(d);
        d.shuffle();
        System.out.println(d);
	}
}
