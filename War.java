import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class War
{
	public static final int numberCards=52;
	private static final int numberCardsWar=3;

	public static void getPlayerCards(Card[] playerOne,Card[] playerTwo,Card[] shuffledDeck)
	{
		for(int i=0;i<numberCards;i++) // split entire deck to two shuffled hands
		{
			if(i%2==0)
			{
				playerTwo[i/2]=shuffledDeck[i];
			}
			else
			{
				playerOne[i/2]=shuffledDeck[i];
			}
		}
	}
	
	
	public static int[] getRoundResults(Card[] playerOne,Card[] playerTwo)
	{
		int battleCount=0;	//	provide the results of 1 game and build array of game counters to pass through
		int warCount=0;
		int doubleWarCount=0;
		int[] counters=new int[3];
		while((hasCards(playerOne))&&(hasCards(playerTwo)))
		{
			counters=(playRound(playerOne, playerTwo));
			battleCount++;
			warCount=warCount+counters[1];
			doubleWarCount=doubleWarCount+counters[2];
			if(battleCount==1234)
			{
				//System.out.println("discard pile loop."); // could not figure out how to remove the sequence of cards for this bug.
				break;
			}
		}
		counters[0]=battleCount;
		counters[1]=warCount;
		counters[2]=doubleWarCount;
		return counters;
	}
	
	
	public static int getMin(int [] numbers)
	{
		int min=1000; //	verified with filewriter that zero WARs does happen quite often.
		for(int num:numbers)
		{
			if(min>num)
				min=num;
		}
		return min;
	}
	
	
	public static int getMax(int [] numbers)
	{
		int max=0;	//	max of array
		for(int num:numbers)
		{
			if(max<num)
				max=num;
		}
		return max;
	}

	
	public static int getAverage(int [] numbers)
	{
		int sum=0;
		int numbersLength=numbers.length;	//	average of array type int
		for(int num:numbers)
		{
			sum=sum+num;
		}
		int average=sum/numbersLength;
		return average;
	}
	
	
	public static float getAverage(float [] numbers)
	{
		float sum=0;
		int numbersLength=numbers.length;	//	average of array type float
		for(float num:numbers)
		{
			sum=sum+num;
		}
		float average=sum/numbersLength;
		return average;
	}
	
	
	public static void writeStats(int[]avgBattles,int[]avgWars,float[]avgDoubleWars) throws IOException
	{
		FileWriter fw = new FileWriter("stats.txt", false); // true for repeated || false for overwrite file
		try
		{
			// line seperator for windows OS is \r\n  .....grrr  ********* //	write to stats.txt output file
			fw.write("For 1000 games: \r\n");
			fw.write("Avg battles was: "+getAverage(avgBattles)+"\r\n");
			fw.write("Avg wars was: "+getAverage(avgWars)+"\r\n");
			fw.write("Avg double wars was: "+getAverage(avgDoubleWars)+"\r\n");
			fw.write("MAX number of battles was: "+getMax(avgBattles)+"\r\n");
			fw.write("MIN number of battles was: "+getMin(avgBattles)+"\r\n");
			fw.write("MAX number of wars was: "+getMax(avgWars)+"\r\n");
			fw.write("MIN number of wars was: "+getMin(avgWars)+"\r\n"+"\r\n");
			/*for(int a:avgWars)
			{
				fw.write("WAR in each round was: "+avgWars[a]+"\r\n");	// Yup, MIN is zero most of time according to printout.
			}*/
			fw.close();
		}
		catch (FileNotFoundException e)
		{
			//pass
		}
	}
	
	
	public static void printStats(int[]avgBattles,int[]avgWars,float[]avgDoubleWars)
	{
		System.out.println();
		System.out.println("Avg battles was: "+getAverage(avgBattles));	//	prints stats to std out.
		System.out.println("Avg wars was: "+getAverage(avgWars));
		System.out.println("Avg double wars was: "+getAverage(avgDoubleWars));
		System.out.println("MAX number of battles was: "+getMax(avgBattles));
		System.out.println("MIN number of battles was: "+getMin(avgBattles));
		System.out.println("MAX number of wars was: "+getMax(avgWars));
		System.out.println("MIN number of wars was: "+getMin(avgWars));
	}


	private static Card[] createDeck()
	{
		Card[] entireDeck=new Card[numberCards];	//	fill Cards array with numbers and suits
		int z=0;
		for(int i=0;i<13; i++) //	n+1 is number on cards with face cards being above 10, ie Jack, Queen, King, and ACE is high
		{
			for(int j=0;j<4;j++)
			{
				entireDeck[z]=new Card();
				entireDeck[z].setSuit(j);
				entireDeck[z].setNumber(i + 1);
				z++;
			}
		}
		return entireDeck;
	}


	private static Card[] shuffleDeck(Card[] deck)
	{
		final int swaps=1000;	// randomly swap elements of any deck of cards
		Random generator=new Random();
		for(int i=0;i<swaps;i++)
		{
			int index1=generator.nextInt(numberCards);
			int index2=generator.nextInt(numberCards);
			Card temporaryCard=deck[index1];
			deck[index1]=deck[index2];
			deck[index2]=temporaryCard;
		}		
		return deck;
	}


	private static int[] playRound(Card[] deck1,Card[] deck2)
	{
		int[] counters=new int[3];	// create our counters for this game round to track average, min, and max
		int battleCount=0;
		int warCount=0;
		int doubleWarCount=0;
		Card[] pile=new Card[numberCards];	// track cards from player to battle area to the winner.
		Card playerOneCard=deck1[0];
		Card playerTwoCard=deck2[0];		
		removeTopCard(deck1);
		addCardToBottom(pile, playerOneCard);
		removeTopCard(deck2);
		addCardToBottom(pile, playerTwoCard); 
		int comparison=compareCards(playerOneCard, playerTwoCard);
		//printRoundResults(playerOneCard, playerTwoCard);
		while(comparison==0) // comparison 0=WAR begins, positive=player 1 wins, negative= player 2 wins.
		{
			warCount++;
			for(int j=0;j<numberCardsWar;j++)
			{
				if(!hasCards(deck1)||!hasCards(deck2))
				{
					doubleWarCount++;
					counters[0]=battleCount;
					counters[1]=warCount;
					counters[2]=doubleWarCount;
					return counters;
				}
			addCardToBottom(pile,deck1[0]);
			addCardToBottom(pile,deck2[0]);
			removeTopCard(deck1);
			removeTopCard(deck2);
			}
			if(!hasCards(deck1)||!hasCards(deck2))
			{
				doubleWarCount++;
				counters[0]=battleCount;
				counters[1]=warCount;
				counters[2]=doubleWarCount;
				return counters;
			}
			playerOneCard=deck1[0];
			playerTwoCard=deck2[0];		
			removeTopCard(deck1);
			addCardToBottom(pile,playerOneCard);
			removeTopCard(deck2);
			addCardToBottom(pile,playerTwoCard);
			comparison=compareCards(playerOneCard,playerTwoCard);
			//printRoundResults(playerOneCard, playerTwoCard);
		}
		//pile=shuffleDeck(pile); // still breaks game. lame.
		Card tempcard=pile[0]; // found that a returned pile in a perfect order would cause a bad battle loop necessitating a break.
		pile[0]=pile[1];	// fewer errors. Not perfect.
		pile[1]=tempcard;
		if(comparison>0)
		{
			while(hasCards(pile))
			{	
				addCardToBottom(deck1,pile[0]);
				removeTopCard(pile);
			}
		}
		else if(comparison<0)
		{
			while(hasCards(pile))
			{
				addCardToBottom(deck2,pile[0]); // consolidate cards for winner and complete counters
				removeTopCard(pile);
			}
		}
		counters[0]=battleCount;
		counters[1]=warCount;
		counters[2]=doubleWarCount;
		return counters;
	}


	private static void printRoundResults(Card playerOneCard,Card playerTwoCard)
	{
		System.out.println("Player one: "+playerOneCard.getNumber());	//	for troubleshooting individual rounds
		System.out.println("Player two: "+playerTwoCard.getNumber());
		int comparison=compareCards(playerOneCard,playerTwoCard);
		if(comparison==0)
		{
			System.out.println("WAR!");
		}
		else if(comparison>0)
		{
			System.out.println("Player one wins that round!");
		}
		else
		{
			System.out.println("Player two wins that round!");
		}
	}


	private static void removeTopCard(Card[] deck)
	{
		for(int i=0;i<deck.length-1;i++)	//	Move card from player hands to winners pile
		{
			deck[i]=deck[i+1];
		}
	}


	private static void addCardToBottom(Card[] deck,Card newCard)
	{
		for(int i=0;i<deck.length;i++)	//	finish moving to winners pile
		{
			if(deck[i]==null)
			{
				deck[i]=newCard;
				return;
			}
		}
	}


	private static int compareCards(Card card1,Card card2)
	{
		int playerOneNumber=card1.getNumber();	// comparison 0=WAR begins, positive=player 1 wins, negative= player 2 wins.
		int playerTwoNumber=card2.getNumber();
		if(playerOneNumber==playerTwoNumber)
		{
			return 0;
		}	
		if(playerOneNumber==1)
		{
			return 1;
		}
		if(playerTwoNumber==1)
		{
			return 1;
		}
		if(playerTwoNumber>playerOneNumber)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

	private static boolean hasCards(Card[] deck)
	{
		for(int i=0;i<deck.length;i++)	//	iteration for winning and losing cards
		{
			if(deck[i]!=null)
			{
				return true;
			}
		}
		return false;
	}

	private static int countCards(Card[] deck)
	{
		int total=0;
		for(int i=0;i<deck.length;i++)	// count cards.
		{
			if(deck[i]!=null)
			{
				total++;
			}
		}
		return total;
	}
	

	public static void main(String[] args) throws IOException
	{
		int gameCount=0;
		int gamesToPlay=1000;
		int[]counters=new int[2];
		int[] avgBattles=new int[1000];
		int[] avgWars=new int[1000];
		float[] avgDoubleWars=new float[1000];
		while(gamesToPlay>0)
		{
			Card[] entireDeck=createDeck();
			Card[] shuffledDeck=shuffleDeck(entireDeck);
			Card[] playerOne=new Card[numberCards];
			Card[] playerTwo=new Card[numberCards];
			getPlayerCards(playerOne,playerTwo,shuffledDeck);
			counters=getRoundResults(playerOne,playerTwo);
			avgBattles[gameCount]=counters[0];
			avgWars[gameCount]=counters[1];
			avgDoubleWars[gameCount]=counters[2];
			/*System.out.println("Battles played: "+counters[0]+" in round: "+gameCount); 	// for troubleshooting
			System.out.println("WARs played: "+counters[1]+" in round: "+gameCount);
			System.out.println("DOUBLE WARs played: "+counters[2]+" in round: "+gameCount);*/
			gamesToPlay--;gameCount++;
		}
		printStats(avgBattles,avgWars,avgDoubleWars);
		writeStats(avgBattles,avgWars,avgDoubleWars);
	}
}