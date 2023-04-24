interface CardConstants {
	char DIAMONDS = 'd';
	char CLUB = 'c';
	char HEARTS = 'h';
	char SPADES = 's';
	char ACE = 'A';
	char TEN ='X';
	char JACK ='J';
	char QUEEN = 'Q';
	char KING = 'K';
	int ACE_LOW = 1;
	int ACE_HIGH = 52;
	int PLAYER_2 = 2;
	int PLAYER_3 = 3;
}

class Card implements CardConstants {
	  public char suit;
	  public String point;
}

