import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Game {
	private static int round = 1;
	private static int phase = 3; 
	static Vector<Player> players = new Vector<Player>();
	static Queue<Card> DeckCards = new LinkedList<Card>();
	
	static void assignCards(int j) {
		Player p = players.get(j);
		int k = 5;
		if (DeckCards.size() > 7) 
			k = 5;
		else if (phase == 2 && DeckCards.size() == 2 || DeckCards.size() == 1) 
			k = 1;
		else if (DeckCards.size() == 0) 
			return;
		else if (phase == 3 && DeckCards.size() == 7 || DeckCards.size() == 4 || DeckCards.size() == 2) {
			if (j == 0) 
				k = 3;
			else 
				k = 2;
		}
		for (int i = 0; i < k; i++) {
			if (DeckCards.peek() != null) {
				Card aCard = DeckCards.poll();
				p.card.add(aCard);
			}
		}
	}

	static void available_cards() {
		if (round == 0) {
			return;
		}
		Enumeration<Player> e = players.elements();
		System.out.println("Available cards: ");
		for (;e.hasMoreElements();) {
			Player Next = e.nextElement();
			System.out.printf("%-8s : ", Next.name);
			Iterator<Card> c = Next.card.iterator();
			for (int j = 1; c.hasNext(); j++) {
				Card element = c.next();
				System.out.print(element.suit + element.point);
				if (j % 5 == 0)
					System.out.print(", ");
				else 
					System.out.print(" ");
			}
			System.out.println();
		}
	}

	static void RemoveCards() {
		Enumeration<Player> e = players.elements();
		Vector<Card> card = new Vector<Card>();
		Vector<Player> player = new Vector<Player>();
		while (e.hasMoreElements()) {
			Player p = e.nextElement();
			Player q = new Player();
			for (int i = 0; i < 27 && !p.card.isEmpty() ; i++) {
				Card c = p.card.poll();
			}
			card.removeAllElements();
		}
		player.removeAllElements();
	}

	static void Hand() {
		Enumeration<Player> e = players.elements();
		Vector<Card> card = new Vector<Card>();
		Vector<Player> player = new Vector<Player>();
		while (e.hasMoreElements()) {
			Player p = e.nextElement();
			Player q = new Player();
			for (int i = 0; i < 5 && !p.card.isEmpty() ; i++) {
				Card c = p.card.poll();
				q.card.add(c);
				card.add(c);
			}
			q.name = p.name;
			Point.Points(q, card);
			player.add(q);
			card.removeAllElements();
		}
		Enumeration<Player> play = player.elements();
		int h_point = 0;
		for (Player pl : player) {
			if (pl.point > h_point) 
				h_point = pl.point;
		}

		while(play.hasMoreElements()) {
			Player pl = play.nextElement();
			Iterator<Card> it = pl.card.iterator();
			Enumeration<Player> p = players.elements();
			System.out.printf("%-8s : ", pl.name);
			while (it.hasNext()) {
				Card next = it.next();
				System.out.print(next.suit + next.point + " ");
			}
			System.out.print("| Point = " + pl.point);
			if (pl.point == h_point) {
				System.out.println(" | Win");
				while (p.hasMoreElements()) {
					Player pla = p.nextElement();
					if (pla.name.equals(pl.name)) 
						pla.point += pl.point;
					else 
						continue;
				}
			}
			else {
				System.out.println();
				pl.point = 0;
			}
		}
		System.out.println();
		player.removeAllElements();
	}

	public static void playerNames() {
		Scanner myObj = new Scanner(System.in);
		System.out.println();
		System.out.println("********************");
		System.out.println("*  " + phase + "-Player Phase  *");
		System.out.println("********************");
		if (phase == 3) {
			for (int i = 1; i <= phase; i++) {
				Player player = new Player();
				System.out.print("Enter Player " + i + " name: ");
				player.name = myObj.nextLine();
				player.card = new LinkedList<Card>();
				players.add(player);
			}
			System.out.println();
		}
	}

	public static void phase1 () {
		Point.phase1_score(players);
		round = 0;
		phase--;
		Enumeration<Player> p = players.elements();
		System.out.print("***** ");
		for (int i = 0; p.hasMoreElements(); i++) {
		Player player = p.nextElement();
		player.card.remove();
		System.out.print(player.name);
		if (i == 0) 
			System.out.print(" and ");
		}
		System.out.print(" proceed to " + phase + "-Player phase *****");
		System.out.println();
	}

	public static void phase2 () {
		Point.phase2_score(players);
		phase = 0;
		round = 0;
		Enumeration<Player> p = players.elements();
		while (p.hasMoreElements()) {
			Player player = p.nextElement();
			System.out.print("***** "+ player.name + " is the WINNER! *****");
		}
	}

	static void Shuffle() {
		new Deck();
		DeckCards = Deck.deck;
		Collections.shuffle((List<?>) DeckCards);
			while (DeckCards.size() != 0) {				
				for (int i = 0; i < players.size(); i++) 
					assignCards(i);
			}
	}
	public static void main(String[] args) {
		while(phase != 0) {
			playerNames();
			round = 1;
			Scanner myObj = new Scanner(System.in);
			Shuffle();
			while (round != 0) {
				available_cards();
				System.out.println();
				String sh = "";
				if (round == 1) {
					System.out.println("Press S to shuffle or ENTER to start.");
					sh = myObj.nextLine();
					while (sh.equals("s") || sh.equals("S")) {
						System.out.println("<Shuffle is pressed>");
						System.out.println();
						RemoveCards();
						Shuffle();
						available_cards();
						System.out.println("Press S to shuffle or ENTER to start.");
						sh = myObj.nextLine();
						if (!sh.equals("s") && !sh.equals("S"))
							System.out.println("<Enter is pressed>");
					} 
				}
				else {
					System.out.println("Press ENTER to next round.");
					String s = myObj.nextLine();
				}
				System.out.println();
				System.out.println("*** Round "+ round +" ***");
				System.out.println("Cards at Hand:");
				Hand();
				Point.score(players);
				if (phase == 3 && round == 3) 
					phase1();
				else if (phase == 2 && round == 4 ) 
					phase2();
				else 
					round ++;
			}
		}
		available_cards();
	}
}