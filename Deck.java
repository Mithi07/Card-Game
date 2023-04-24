import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.Queue; 

public class Deck {
	public static Queue<Card> deck = new LinkedList<Card>();
	ArrayList<Character> list = new ArrayList<Character>();
    ArrayList<String> list2 = new ArrayList<String>();
    
	public Deck () {
	    // Always zero the deck vector and initialize it
		deck.clear();
	    for (int i=2; i<=9; i++) {
	    	list2.add(String.valueOf(i));
	    }
	    list.add(Card.HEARTS);
	    list.add(Card.DIAMONDS);
	    list.add(Card.CLUB);
	    list.add(Card.SPADES);
	    list2.add(Character.toString(Card.ACE));
	    list2.add(Character.toString(Card.TEN));
	    list2.add(Character.toString(Card.JACK));
	    list2.add(Character.toString(Card.KING));
	    list2.add(Character.toString(Card.QUEEN));
	      
	    // Then insert the 52 cards. One color at a time
	    for (int j = 0; j<4; j++) {
	    	for(int k=0; k<13; k++) {
				Card aCard = new Card();
				aCard.suit = list.get(j);
	    	  	aCard.point = list2.get(k);
	    	  	deck.add(aCard);
	      	}
	    }
	} 
}