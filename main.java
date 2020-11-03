import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import java.io.*;

// The Golden Coin Casino
// Jerry Zhu
// ICS3U1 Period 1
// This program manages a complete casino.
// The casino keeps track of the user's coin balance and has a variety of games to choose from.


public class Casino {
	// Initialize file readers and writers
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader for file input
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out)); // Printwriter for file output
	static StringTokenizer st; // String tokenizer for parsing input
	// Run method of program -> this is where the program starts
	public static void main(String[] args) throws IOException, InterruptedException{
		// Greeting message
		System.out.println("Welcome to the Golden Coin Casino!");
		System.out.println("Is this your first time here? (y/n): ");
		while (true) 
		{
			// Get input from user
			char ch = readChar();
			if (ch == 'y') 
			{
				// If user has been here before
				System.out.println("Thank you for choosing the Golden Coin Casino!");
				System.out.println("As gratitude for playing with us, we will give you 100 coins free of charge.");
				// Make a file with bank and coins amount
				try {
					// Try to create new file called balance.txt
					File myObj = new File("balance.txt");
					if (myObj.createNewFile()) {
						System.err.println("File created. ");
					} 
					else {
						System.err.println("File already exists. ");
					}
				} 
				catch (IOException e) {
					// Catch any errors that occur and print stack trace
					System.out.println("An error occurred. Java packages/setup are not properly installed. ");
					System.err.println("Resolving issues... ");
					e.printStackTrace();
					System.out.println("Aborting... ");
					System.exit(0);
			    }
				// Flush any unneeded text in the file
				FileWriter writer = new FileWriter("balance.txt", false); 
		        PrintWriter pw = new PrintWriter(writer, false);
		        pw.flush();
		        // Write in the starting balance amounts
				writer.write("1000");
				writer.write(System.getProperty("line.separator"));
				writer.write("0");
				writer.write(System.getProperty("line.separator"));
				writer.close();
				System.out.println("We hope you enjoy your time here. ");
				Thread.sleep(1000);
				// Give balance amount to user
				printBalance();
				// Start main game loop
				gameLoop();
			}
			else if (ch == 'n')
			{
				// Greeting message
				System.out.println("Thank you for returning!");
				System.out.println("We hope you enjoy your time here. ");
				Thread.sleep(1000);
				// Give balance amount to user
				printBalance();
				// Start main game loop
				gameLoop();
			}
			else {
				// Catch any exceptions or errors
				System.out.println("Invalid character. Please type y for yes or n for no. ");
			}
		}
	}
	
	// This is the main game loop of the program, where the user decides where to go
	public static void gameLoop() 
	{
		while (true)
		{
			// Give the user options
			System.out.println("To play a game, type 1. ");
			System.out.println("To go to the coin bank, type 2. ");
			System.out.println("To go to the lounge, type 3. ");
			System.out.println("To stop playing, type 4. ");
			System.out.println("What would you like to do?");
			while (true)
			{
				try {
					// Get input from the user
					int option = readInt();
					switch(option) {
						// Use switch statement to decide where to go and catch any exceptions that occur
						case 1:
							game();
							break;
						case 2:
							coins();
							break;
						case 3:
							lounge();
							break;
						case 4:
							end();
						default:
							System.out.println("Invalid option. Please try again. ");
					}
				}
				catch (Exception e)
				{
					System.out.println("Invalid option. Please try again. ");
				}
			}
		}
	}
	
	public static void game()
	{
		// Give user a list of games they can play
		System.out.println("Welcome to the Golden Coin Arcade! ");
		System.out.println("What game do you want to play? ");
		System.out.println("To play blackjack, type 1. ");
		System.out.println("To play slots, type 2. ");
		System.out.println("To play roulette, type 3. ");
		System.out.println("To play lottery, type 4. ");
		System.out.println("To play wheel of fortune, type 5. "); 
		System.out.println("To play high or low, type 6. ");
		System.out.println("To play baccarat, type 7. ");
		System.out.println("To play scratch cards, type 8. ");
		System.out.println("To play split or steal, type 9. ");
		System.out.println("To play deal or no deal, type 10. "); 
		System.out.println("To go back, print 11. ");
		while (true)
		{
			try {
				// Get input from user
				int option = readInt();
				switch(option) {
					// Use switch statement to go to whichever game the user wants to play, and catch any exceptions that occur
					case 1: 
						blackjack();
						break;
					case 2: 
						slots();
						break;
					case 3: 
						roulette();
						break;
					case 4: 
						lottery();
						break;
					case 5: 
						wheelOfFortune();
						break;
					case 6: 
						highOrLow();
						break;
					case 7: 
						bacarrat();
						break;
					case 8: 
						scratchCards();
						break;
					case 9:
						splitOrSteal();
						break;
					case 10:
						dealOrNoDeal();
						break;
					case 11:
						gameLoop();
					default:
						System.out.println("Invalid option. Please try again. ");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
	}
	
	// This method will run one game of blackjack with the user
	public static void blackjack() throws IOException, InterruptedException
	{
		// Get bet amount from user
		int bet = getBet();
		// Get random cards of dealer and player
		int dealer = 0, player = 0;
		int card = getRand(1, 13);
		dealer += card;
		System.out.println("Dealer's card: " + displayCard(card) + "\n");
		System.out.println("Dealer's total is " + dealer + ".");
		int card1 = getRand(1, 13), card2 = getRand(1, 13);
		player += (card1 + card2);
		System.out.println("Player's cards: " + displayCard(card1) + " " + displayCard(card2) + "\n");
		System.out.println("Player's total is " + player + ".");
		boolean win = true, done = false;
		// Start main hit/stand game loop
		while (true)
		{
			if (done)
			{
				break;
			}
			// Ask the user to either hit or stand every turn
			System.out.println("Do you want to hit or stand? ");
			String next = readLine();
			if (next.equals("hit"))
			{
				// If they hit, give them another card and check for game end
				card = getRand(1, 13);
				System.out.println("The card you got is " + displayCard(card));
				player += card;
				System.out.println("Your new total is " + player + ".");
				if (player > 21)
				{
					System.out.println("You busted! ");
					win = false;
					done = true;
					break;
				}
			}
			else if (next.equals("stand"))
			{
				// If they stand, hit the dealer with cards until they bust or have higher than the player
				while (true)
				{
					card = getRand(1, 13);
					dealer += card;
					System.out.println("The card the dealer got is " + displayCard(card));
					if ((dealer > player) && (dealer <= 21))
					{
						System.out.println("The dealer has a highter total!");
						win = false;
						done = true;
						break;
					}
					else if (dealer > 21)
					{
						System.out.println("The dealer busted!");
						win = true;
						done = true;
						break;
					}
				}
			}
			else 
			{
				System.out.println("You must either hit or stand. Please try again. ");
			}
		}
		// Display winning or losing message and update user's coin balance
		if (win)
		{
			System.out.println("You win! You won " + bet + " coins!");
			updateCoins(bet, 0);
		}
		else 
		{
			System.out.println("You lost! You lost " + bet + " coins!");
			updateCoins(-bet, 0);
		}
		Thread.sleep(1000);
		// Ask the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				blackjack();
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of slots with the user
	public static void slots() throws IOException, InterruptedException
	{
		// Verify that the user wants to play
		while (true)
		{
			System.out.println("This will cost 10 coins per spin. Would you like to spin? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				break;
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
		// Get 3 random fruits
		int t1 = getRand(1, 5), t2 = getRand(1, 5), t3 = getRand(1, 5);
		// Fruits are cherry, apple, banana, orange, kiwi
		if (t1 == t2 && t1 == t3 && t1 == 1)
		{
			// If all 3 fruits are cherries, the user wins the jackpot
			System.out.println("cherry cherry cherry");
			System.out.println("You win 500 coins!");
			updateCoins(500, 0);
		}
		else if (t1 == t2 && t1 == t3)
		{
			// If all 3 fruits are the same, the user wins
			if (t1 == 2)
			{
				System.out.println("apple apple apple");
				System.out.println("You win 100 coins!");
				updateCoins(100, 0);
			}
			else if (t1 == 3)
			{
				System.out.println("banana banana banana");
				System.out.println("You win 100 coins!");
				updateCoins(100, 0);
			}
			else if (t1 == 4)
			{
				System.out.println("orange orange orange");
				System.out.println("You win 100 coins!");
				updateCoins(100, 0);
			}
			else 
			{
				System.out.println("kiwi kiwi kiwi");
				System.out.println("You win 100 coins!");
				updateCoins(100, 0);
			}
		}
		else 
		{
			// Otherwise, the user loses
			// Print out the 3 fruits that were spun and update user's coin balance
			System.out.print(slotValue(t1) + " ");
			System.out.print(slotValue(t2) + " ");
			System.out.print(slotValue(t3) + "\n");
			System.out.println("You lose!");
			updateCoins(-10, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				slots();
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will return the corresponding fruit based on the random value that is spun
	public static String slotValue(int x) 
	{
		if (x == 1)
		{
			return "cherry";
		}
		else if (x == 2)
		{
			return "apple";
		}
		else if (x == 3)
		{
			return "banana";
		}
		else if (x == 4)
		{
			return "orange";
		}
		else
		{
			return "kiwi";
		}
	}
	
	// This method will play one game of roulette with the user
	public static void roulette() throws IOException, InterruptedException
	{
		// Get the bet value from the user
		int bet = getBet();
		// Prompt user to pick a color and a number and catch any errors that occur
		System.out.println("Pick a color(red/black): ");
		String option;
		while (true)
		{
			option = readLine();
			if (option.equals("red") || option.equals("black"))
			{
				break;
			}
			else 
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
		int val = option.equals("red") ? 1 : 2;
		// Prompting number from 1 to 36
		System.out.println("Pick a number(1-36): ");
		int op;
		while (true)
		{
			try 
			{
				op = readInt();
				if (op > 0 && op <= 36)
				{
					break;
				}
				else 
				{
					System.out.println("Invalid option. Please try again.");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
		// Spin a random color and number
		int rand1 = getRand(1, 2), rand2 = getRand(0, 37);
		// Decide if the user won and update coins accordingly
		if (rand2 == 0 || rand2 == 37)
		{
			System.out.println("You lose " + bet + " coins! ");
			updateCoins(-bet, 0);
		}
		else if (rand2 == op)
		{
			System.out.println("You win " + bet + "coins! ");
			updateCoins(bet, 0);
		}
		else if (rand1 == val)
		{
			System.out.println("You break even! ");
		}
		else 
		{
			System.out.println("You lose " + bet + " coins! ");
			updateCoins(-bet, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char opt = readChar();
			if (opt == 'y')
			{
				roulette();
			}
			else if (opt == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of lottery with the user
	public static void lottery() throws IOException, InterruptedException
	{
		// Verify that the user wants to play
		while (true)
		{
			System.out.println("It costs 10 coins to buy one ticket. Would you like to? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				break;
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
		// Get a random number that represents the winning ticket
		int num = getRand(1, getRand(100, 200));
		// Determine if the user got the winning ticket or not, and update the values accordingly
		if (num == 1)
		{
			System.out.println("You won the jackpot of 1000 coins!");
			updateCoins(1000, 0);
		}
		else 
		{
			System.out.println("You lose! ");
			updateCoins(-10, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				lottery();
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of wheel of fortune with the user
	public static void wheelOfFortune() throws IOException, InterruptedException
	{
		// Get a word bank of 20 words, and choose a random "secret" word
		String[] words = {"push","lift","dish","tire","rock","hill","tent","lion","exit","kick","moon","lazy","axis","half","coin","soft","loud","cold","read","idea","cart","roof","seat"};
		int idx = getRand(0, 20);
		String secret = words[idx];
		int[] secretChars = new int[26];
		// Verify bet value from the user
		int bet = getBet();
		boolean win = false;
		// Initialize array that will keep track of which letters were guessed
		for (int i = 0; i < 26; i++)
		{
			secretChars[i] = 0;
		}
		for (int i = 0; i < 4; i++)
		{
			secretChars[secret.charAt(i) - 'a']++;
		}
		// Prompt the user to guess a letter 10 times
		System.out.println("You have 10 chances to guess the word.");
		for (int i = 0; i < 10; i++)
		{
			// Get a letter from the user and determine if the user guessed the letter right. 
			// Catch any exceptions that occur
			System.out.println("Guess a letter: ");
			char letter = readChar();
			if (!Character.isLetter(letter))
			{
				System.out.println("Invalid letter. ");
				i--;
				continue;
			}
			if (secretChars[letter - 'a'] == 0)
			{
				System.out.println("You guessed the wrong letter. ");
			}
			else 
			{
				secretChars[letter - 'a']--;
				System.out.println("You guessed one of the letters right!");
			}
		}
		// Determine if the user won and update coin balance accordingly
		if (win)
		{
			System.out.println("You guessed the word! You win! ");
			updateCoins(bet, 0);
		}
		else 
		{
			System.out.println("You failed to guess the word! You lose! ");
			updateCoins(-bet, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				wheelOfFortune();
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of high or low with the user
	public static void highOrLow() throws IOException, InterruptedException
	{
		// Verify bet value from the user
		int bet = getBet();
		// Get a random "secret" value for the user to bet
		int val = getRand(1, 100);
		boolean win = false;
		// Prompt the user to guess a number 5 times
		for (int i = 0; i < 5; i++)
		{
			// Get a number from the user and determine if its too high, too low, or just right
			System.out.println("Guess a number from 1 to 100: ");
			int a = readInt();
			if (a == val)
			{
				System.out.println("You guessed right! ");	
				win = true;
				break;
			}
			else if (a < val)
			{
				System.out.println("Too low! ");
			}
			else 
			{
				System.out.println("Too high! ");
			}
		}
		// Determine if the user guessed the number right and update coin balance accordingly
		if (win)
		{
			System.out.println("You guessed the number! You won! ");
			updateCoins(bet, 0);
		}
		else 
		{
			System.out.println("You failed to guess the number! You lose! ");
			updateCoins(-bet, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				highOrLow();
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of bacarrat with the user
	public static void bacarrat() throws IOException, InterruptedException
	{
		// Verify the bet value from the user
		int bet = getBet();
		String option;
		// Bet on either the dealer or the player winning
		while (true)
		{
			System.out.println("Choose the dealer or the player: ");
			option = readLine();
			if (option.equals("dealer") || option.equals("player"))
			{
				break;
			}
			System.out.println("Invalid option. Please try again. ");
		}
		// Get 2 random cards from the dealer or the player
		int dcard1 = getRand(1, 13), dcard2 = getRand(1, 13);
		int pcard1 = getRand(1, 13), pcard2 = getRand(1, 13);
		int sumd = (dcard1 + dcard2) % 10, sump = (pcard1 + pcard2) % 10;
		System.out.println("Dealer's cards: " + displayCard(dcard1) + " " + displayCard(dcard2));
		System.out.println("Player's cards: " + displayCard(pcard1) + " " + displayCard(pcard2));
		// Determine if the player or dealer won and update coin values accordingly
		if ((sumd > sump && option.equals("dealer")) || (sump > sumd && option.equals("player")))
		{
			System.out.println("You win " + bet + " coins! ");
			updateCoins(bet, 0);
		}
		else 
		{
			System.out.println("You lose " + bet + " coins! ");
			updateCoins(-bet, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char op = readChar();
			if (op == 'y')
			{
				bacarrat();
			}
			else if (op == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of scratch cards with the user
	public static void scratchCards() throws IOException, InterruptedException
	{
		// Verify that the user wants to play
		while (true)
		{
			System.out.println("This will cost 10 coins to play. Would you like to play? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				break;
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
		// Get the scratch card array configuration
		// Initialize arrays and values
		int[][] arr = {{1, 2, 2}, {3, 4, 5}, {4, 3, 1}};
		boolean[][] found = {{false, false, false}, {false, false, false}, {false, false, false}};
		int[] cnt = {0, 0, 0, 0, 0, 0};
		int bet = 10;
		// Print the starting scratch card board
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				System.out.print("X ");
			}
			System.out.println();
		}
		// Get a (x, y) position from the user 3 times
		for (int i = 0; i < 3; i++)
		{
			while (true)
			{
				System.out.println("Give 0 indexed x y position. ");
				int a = readInt();
				int b = readInt();
				if (a > 2 || b > 2)
				{
					System.out.println("Invalid option. Please try again. ");
					continue;
				}
				// Update array values if position is unique
				if (!found[a][b])
				{
					found[a][b] = true;
					cnt[arr[a][b]]++;
					break;
				}
				else 
				{
					System.out.println("You already chose this! ");
				}
			}
		}
		// Determine if the player won
		boolean win = false;
		for (int i = 1; i <= 5; i++)
		{
			if (cnt[i] == 2)
			{
				win = true;
				break;
			}
		}
		// Update values accordingly
		if (win)
		{
			System.out.println("You win! ");
			updateCoins(bet, 0);
		}
		else {
			System.out.println("You lose! ");
			updateCoins(-bet, 0);
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				scratchCards();
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of split or steal with the user
	public static void splitOrSteal() throws IOException, InterruptedException
	{
		// Verify that the user wants to play
		while (true)
		{
			System.out.println("This will cost 50 coins to play. Would you like to play? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				break;
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
		System.out.println("The prize pool is 100 coins. ");
		String option; 
		// Ask the user if htey want to split or steal the money
		while (true)
		{
			System.out.println("Would you like to split or steal? ");
			option = readLine();
			if (option.equals("split"))
			{
				System.out.println("You split! ");
				break;
			}
			else if (option.equals("steal"))
			{
				System.out.println("You steal! ");
				break;
			}
			else 
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
		// Find out if opponent wants to split or steal
		int val = getRand(1, 2);
		// Determine final outcome and update coin balances accordingly
		if (option.equals("split"))
		{
			if (val == 1)
			{
				System.out.println("The opponent splits. You win 25 coins! ");
				updateCoins(25, 0);
			}
			else 
			{
				System.out.println("The opponent steals. You lose 50 coins. ");
				updateCoins(-50, 0);
			}
		}
		if (option.equals("steal"))
		{
			if (val == 1)
			{
				System.out.println("The opponent splits. You win 50 coins. ");
				updateCoins(50, 0);
			}
			else 
			{
				System.out.println("The opponent steals. You lose 25 coins. ");
				updateCoins(-25, 0);
			}
		}
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char op = readChar();
			if (op == 'y')
			{
				splitOrSteal();
			}
			else if (op == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will play one game of deal or no deal with the user
	public static void dealOrNoDeal() throws IOException, InterruptedException
	{
		// Verify that the user wants to play
		while (true)
		{
			System.out.println("It will cost 50 coins to play. Would you like to play? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				break;
			}
			else if (option == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
		// Initialize all arrays, including one with case money values
		int money[] = {1, 10, 50, 100, 500, 1000, 10000};
		int offset[] = {50, 25, 10, 0, -10, -25, -100};
		boolean chosen[] = {false, false, false, false, false, false, false};
		boolean taken[] = {false, false, false, false, false, false, false};
		// Initialize starting offer
		int offer = 50;
		String op;
		int option;
		// Ask the user 7 times to choose a case to remove
		for (int i = 1; i <= 7; i++)
		{
			if (i >= 5)
			{
				// Prompt the user to choose deal or no deal given a bank offer
				System.out.println("The bank can offer you " + Math.max(offer, 0) + " coins. ");
				System.out.println("deal or no deal? ");
				while (true)
				{
					op = readLine();
					if (op.equals("deal"))
					{
						// If user chooses deal, return final offer and update bank values
						System.out.println("Your final offer was " + Math.max(offer, 0) + " coins. ");
						updateCoins(Math.max(offer, 0) - 50, 0);
						game();
					}
					else if (op.equals("no deal"))
					{
						break;
					}
					else 
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}
			}
			// Prompt the user to choose a case to remove
			System.out.println("Choose a case to eliminate (0-6): ");
			while (true)
			{
				try
				{
					option = readInt();
					if (option > 6 || option < 0)
					{
						System.out.println("Invalid option. Please try again. ");
					}
					else 
					{
						if (chosen[option] == false)
						{
							break;
						}
						else 
						{
							System.out.println("You already chose this case. ");
						}
					}
				}
				catch (Exception e)
				{
					System.out.println("Invalid option. Please try again. ");
				}
			}
			// Remove case and update bank offer accordingly
			int idx;
			while (true)
			{
				idx = getRand(0, 6);
				if (taken[idx] == false)
				{
					taken[idx] = true;
					break;
				}
			}
			System.out.println("You chose case " + option + " with " + money[idx] + " coins! ");
			chosen[option] = true;
			offer += offset[idx];
		}
		// Return final offer and update coin balance 
		System.out.println("Your final offer was " + Math.max(offer, 0) + " coins. ");
		updateCoins(Math.max(offer, 0) - 50, 0);
		Thread.sleep(1000);
		// Prompt the user to play again
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char opt = readChar();
			if (opt == 'y')
			{
				dealOrNoDeal();
			}
			else if (opt == 'n')
			{
				game();
			}
			else 
			{
				System.out.println("Invalid option. Please try again.");
			}
		}
	}
	
	// This method will let the user manage their coin balance
	public static void coins() throws IOException
	{
		// Give the user coin management options
		System.out.println("Welcome to the coin bank! ");
		System.out.println("What do you want to do? ");
		System.out.println("To purchase more coins, type 1. ");
		System.out.println("To view your balance, type 2. ");
		System.out.println("To go back, type 3.");
		while (true)
		{
			// Use switch statement option from the user and go to the corresponding method
			// Catch any exceptions that occur
			try 
			{
				int option = readInt();
				switch(option) {
					case 1: 
						purchase();
						break;
					case 2: 
						balance();
						break;
					case 3:
						gameLoop();
						break;
					default:
						System.out.println("Invalid option. Please try again. ");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
	}
	
	// This program lets the user purchase coins
	public static void purchase() throws IOException, InterruptedException
	{
		while (true)
		{
			// Prompt the user to purchase coins, and update coin balances accordingly
			System.out.println("You can purchase 1000 coins for $10. Would you like to do so? (y/n): ");
			char ch = readChar();
			if (ch == 'y')
			{
				updateCoins(1000, -10);
				Thread.sleep(500);
			}
			else if (ch == 'n')
			{
				coins();
			}
			else 
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
	}
	
	// This method will give the user the current coin balance
	public static void balance() throws IOException, InterruptedException
	{
		BufferedReader reader = new BufferedReader(new FileReader("balance.txt"));
		String coins = reader.readLine();
		String bank = reader.readLine();
		System.out.println("Your coins balance is " + coins + ". \nYour bank balance is " + bank + ".");
		reader.close();
		Thread.sleep(1000);
		coins();
	}
	
	// This program will let the user visit the lounge
	public static void lounge()
	{
		// Display a list of options to the user
		System.out.println("Welcome to the lounge! ");
		System.out.println("To speak to a supervisor, type 1. ");
		System.out.println("To buy something, type 2. ");
		System.out.println("To hang out and listen to music, type 3. ");
		System.out.println("To go back, type 4. ");
		while (true)
		{
			try 
			{
				// Get the option from the user and catch any exceptions that occur
				int option = readInt();
				switch(option) {
					case 1: 
						supervisor();
						break;
					case 2: 
						buy();
						break;
					case 3:
						music();
						break;
					case 4:
						gameLoop();
					default:
						System.out.println("Invalid option. Please try again. ");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
	}
	
	// This method gives a description of each game in the casino
	public static void supervisor() throws InterruptedException
	{
		System.out.println("We have 10 wonderful games to choose from! ");
		System.out.println("In blackjack, the goal is to beat the dealer. \n"
		+ "You do this by either hitting or standing until you get over 21 and lose or more than the dealer and win. ");
		Thread.sleep(1000);
		System.out.println("In slots, the goal is to spin all three of the same fruit to win. ");
		Thread.sleep(1000);
		System.out.println("In roulette, the goal is to guess the correct color and/or number value. ");
		Thread.sleep(1000);
		System.out.println("For lottery, the goal is to win a jackpot against many other users. ");
		Thread.sleep(1000);
		System.out.println("For wheel of fortune, the goal is to guess the correct word. . ");
		Thread.sleep(1000);
		System.out.println("For high or low, the goal is to guess the correct number in the least number of tries. ");
		Thread.sleep(1000);
		System.out.println("For bacarrat, the goal is to guess correctly who will win: the dealer or hte player. ");
		Thread.sleep(1000);
		System.out.println("For scratch cards, the goal is to get a matching pair. ");
		Thread.sleep(1000);
		System.out.println("For split or steal, the goal is to split a pot with the opponent or steal the entire pot. ");
		Thread.sleep(1000);
		System.out.println("For deal or no deal, the goal is to choose briefcases that have the smallest amounts of money. ");
		Thread.sleep(1000);
		lounge();
	}
	
	// This program lets you buy food from the shop
	public static void buy()
	{
		// Display a list of items to the user
		System.out.println("What would you like to buy? ");
		System.out.println("To buy a $10 meal, type 1. ");
		System.out.println("To buy a $20 meal, type 2. ");
		System.out.println("To buy a $5 drink, type 3. ");
		System.out.println("To go back, type 4. ");
		while (true)
		{
			try 
			{
				// Get which item the user wants and update bank balances
				// Catch any exceptions that occur
				int option = readInt();
				switch(option) {
					case 1: 
						updateCoins(0, -10);
						System.out.println("Here is your meal to go.");
						System.out.println("We hope you enjoy your time here. ");
						lounge();
						break;
					case 2: 
						updateCoins(0, -20);
						System.out.println("Here is your meal to go.");
						System.out.println("We hope you enjoy your time here. ");
						lounge();
						break;
					case 3:
						updateCoins(0, -5);
						System.out.println("Here is your drink. ");
						System.out.println("We hope you enjoy your time here. ");
						lounge();
						break;
					case 4:
						lounge();
					default:
						System.out.println("Invalid option. Please try again. ");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid option. Please try again. ");
			}
		}
	}
	
	// This program will play some relaxing casino music
	public static void music() throws InterruptedException
	{
		// Get filepath of casino music
		System.out.println("Playing Casion music... ");
		Thread.sleep(1000);
		File musicPath = new File("Casino Music.wav");
		try
		{
			// Show dialog box and play music
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			System.out.println("You just got rickrolled! ");
			JOptionPane.showMessageDialog(null, "You just got rickrolled! ");
			clip.close();
			lounge();
		}
		catch (Exception e)
		{
			// Catch any exceptions that occur and print stack trace
			System.out.println("An error occurred. Java packages/setup are not properly installed. ");
			System.out.println("Resolving... ");
			e.printStackTrace();
			System.out.println("File not found or file type not supported. ");
			System.out.println("Please go do something else while we fix the issue. ");
		}
	}
	
	// This method will end off the casino program if the user wants to stop playing
	public static void end() throws IOException, InterruptedException
	{
		System.out.println("Thank you so much for coming to the Golden Coin Casino. ");
		System.out.println("We hope you enjoyed your time here. ");
		// Get the bank balance
		printBalance();
		System.out.println("Please come again soon. Goodbye! ");
		// System exit the program
		System.exit(0);
	}
	
	// These are some commonly used methods in this program
	
	// THis program will return a random number in the range of [min, max]
	public static int getRand(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	// This program will update the user's coin balance and the balance.txt file 
	public static void updateCoins(int addCoin, int addBank) throws IOException
	{
		// Get original values from coin balance
		BufferedReader reader = new BufferedReader(new FileReader("balance.txt"));
		String coins = reader.readLine();
		String bank = reader.readLine();
		int coinsInt = Integer.parseInt(coins), bankInt = Integer.parseInt(bank);
		coinsInt += addCoin; 
		bankInt += addBank;
		coins = Integer.toString(coinsInt); 
		bank = Integer.toString(bankInt);
		// Update values in the balance.txt file 
		FileWriter writer = new FileWriter("balance.txt", false); 
        PrintWriter pw = new PrintWriter(writer, false);
        pw.flush();
        writer.write(coins);
		writer.write(System.getProperty("line.separator"));
		writer.write(bank);
		writer.write(System.getProperty("line.separator"));
		System.err.println("Values updated. ");
		// Make sure to close reader and writer to prevent resource leaks
		reader.close();
		writer.close();
		pw.close();
	}
	
	// This method will return the character of the card given the card's numerical value
	public static String displayCard(int card)
	{
		if (card == 1)
		{
			return "A";
		}
		else if (card == 11)
		{
			return "J";
		}
		else if (card == 12)
		{
			return "Q";
		}
		else if (card == 13)
		{
			return "K";
		}
		return Integer.toString(card);
	}
	
	// This method will get the bet from the user and return it
	public static int getBet()
	{
		while (true)
		{
			System.out.println("How much money would you like to bet?");
			try 
			{
				int bet = readInt();
				if (betValid(bet))
				{
					return bet;
				}
				else 
				{
					System.out.println("Invalid bet value. Please try again. ");
				}
			}
			catch (Exception e)
			{
				System.out.println("Invalid bet value. Please try again. ");
			}
		}
	}
	
	// This method will check if a given bet is valid 
	public static boolean betValid(int betValue)
	{
		if (betValue > 0 && betValue <= 100)
		{
			return true;
		}
		return false;
	}
	
	// This method will print the coin balance of the user
	public static void printBalance() throws IOException, InterruptedException
	{
		BufferedReader reader = new BufferedReader(new FileReader("balance.txt"));
		String coins = reader.readLine();
		String bank = reader.readLine();
		System.out.println("Your coins balance is " + coins + ". \nYour bank balance is " + bank + ".");
		reader.close();
	}
	
	// These are some fast IO methods for fast simple input using BufferedReader and StringTokenizer
	
	// This method will read a String
	static String next() throws IOException 
	{
		while (st == null || !st.hasMoreTokens())
		{
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}
	
	// This method will read a long integer
	static long readLong() throws IOException 
	{
		return Long.parseLong(next());
	}
	
	// This method will read an integer
	static int readInt() throws IOException 
	{
		return Integer.parseInt(next());
	}
	
	// This method will read a double 
	static double readDouble() throws IOException 
	{
		return Double.parseDouble(next());
	}
	
	// This method will read a character
	static char readChar() throws IOException 
	{
		return next().charAt(0);
	}
	
	// This method will read a line
	static String readLine() throws IOException 
	{
		return br.readLine().trim();
	}
}
