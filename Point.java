import java.util.Enumeration;
import java.util.Vector;

public class Point {
    public static void Points(Player p, Vector<Card> card) {
		Enumeration<Card> c = card.elements();
		int c_point = 0, s_point = 0, d_point = 0, h_point = 0;
		int card_point = 0;
		int point = 0;
		
		while (c.hasMoreElements()) {
			Card next = c.nextElement();
			if (next.point.equals("J") || next.point.equals("Q") || next.point.equals("K") || next.point.equals("X")) 
				card_point = 10;
			else if (next.point.equals("A")) 
				card_point = 1;
			else 
				card_point = Integer.parseInt(next.point);

			if (next.suit == 'c') 
				c_point += card_point;
			else if (next.suit == 's') 
				s_point += card_point;
			else if (next.suit == 'd') 
				d_point +=  card_point;
			else if (next.suit == 'h') 
				h_point += card_point;
		}
		
		if (s_point >= c_point && s_point >= d_point && s_point >= h_point) 
			point = s_point;
		else if (c_point >= s_point && c_point>= d_point && c_point >=h_point) 
			point = c_point;
		else if (d_point >= s_point && d_point>= c_point && d_point >=h_point) 
			point = d_point;
		else 
			point= h_point;
		p.point = point;
	}

	public static void score(Vector<Player> players) {
		System.out.println("Score:");
		Enumeration<Player> p = players.elements();
		while(p.hasMoreElements()) {
			Player player = p.nextElement();
			System.out.printf("%-10s =  %2d \n", player.name, player.point);
		}
		System.out.println();
	}

	public static void phase1_score(Vector<Player> players) {
		int index = 0;
		Enumeration<Player> player = players.elements();
		Player p1 = player.nextElement();
		Player p2 = player.nextElement();
		Player p3 = player.nextElement();
		if (p1.point < p2.point && p1.point < p3.point) 
			index = 0;
		if (p2.point < p1.point && p2.point < p3.point) 
			index = 1;
		if (p3.point < p1.point && p3.point < p2.point) 
			index = 2;
		players.remove(index);
	}

	public static void phase2_score(Vector<Player> players) {
		int lowest = 0;
		int index = 0;
		Enumeration<Player> player = players.elements();
		for (int i=0; player.hasMoreElements(); i++) {
			Player p = player.nextElement();
			if (lowest == 0 || lowest > p.point) {
				lowest = p.point;
				index = i;
			}
		}
		players.remove(index);
	}
}
