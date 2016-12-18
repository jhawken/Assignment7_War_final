public class Card
{
	private int suit;
	private int number;

	public static final int SPADES = 0; // suit only used as tie-breaker as an option
	public static final int CLUBS = 1;
	public static final int DIAMONDS = 2;
	public static final int HEARTS = 3;


	public void setSuit(int newSuit)
	{
		if((newSuit>= 0)&&(newSuit<= 3))	// for creating deck
		{
			this.suit = newSuit;
		}
	}

	
	public int getSuit()
	{
		return this.suit;	//	accessing player decks or current battle pile...as an option
	}

	
	public void setNumber(int newNumber)
	{
		this.number = newNumber;	// for creating deck
	}

	
	public int getNumber()
	{
		return this.number;		//	accessing player decks or current battle pile
	}

	
	public void setSuitFromString(String suit)
	{
		if(suit.equals("SPADES"))	// create deck with this assignment
		{
			this.suit=Card.SPADES;
		}
		else if (suit.equals("CLUBS"))
		{
			this.suit=Card.CLUBS;
		}
		else if(suit.equals("DIAMONDS"))
		{
			this.suit=Card.DIAMONDS;
		}
		else if(suit.equals("HEARTS"))
		{
			this.suit=Card.HEARTS;
		}
		else {
			System.out.println("Invalid suit!");
		}
	}

	
	public String getSuitAsString()
	{
		if(this.suit==Card.SPADES)	// access deck suits
		{
			return "SPADES";
		}
		else if(this.suit==Card.CLUBS)
		{
			return "CLUBS";
		}
		else if(this.suit==Card.HEARTS)
		{
			return "DIAMONDS";
		}
		else
		{ 
			return "HEARTS";
		}
	}
}