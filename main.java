import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import java.io.*;

public class Casino {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	public static void main(String[] args) throws IOException, InterruptedException{
		System.out.println("Welcome to the Golden Coin Casino!");
		System.out.println("Is this your first time here? (y/n): ");
		while (true) 
		{
			char ch = readChar();
			if (ch == 'y') 
			{
				System.out.println("Thank you for choosing the Golden Coin Casino!");
				System.out.println("As gratitude for playing with us, we will give you 100 coins free of charge.");
				// Make a file with bank and coins amount
				try {
					File myObj = new File("balance.txt");
					if (myObj.createNewFile()) {
						System.err.println("File created. ");
					} 
					else {
						System.err.println("File already exists. ");
					}
				} 
				catch (IOException e) {
					System.out.println("An error occurred. Java packages/setup are not properly installed. ");
					System.err.println("Resolving issues... ");
					e.printStackTrace();
					System.out.println("Aborting... ");
					System.exit(0);
			    }
				FileWriter writer = new FileWriter("balance.txt", false); 
		        PrintWriter pw = new PrintWriter(writer, false);
		        pw.flush();
				writer.write("1000");
				writer.write(System.getProperty("line.separator"));
				writer.write("0");
				writer.write(System.getProperty("line.separator"));
				writer.close();
				System.out.println("We hope you enjoy your time here. ");
				Thread.sleep(1000);
				printBalance();
				gameLoop();
			}
			else if (ch == 'n')
			{
				System.out.println("Thank you for returning!");
				System.out.println("We hope you enjoy your time here. ");
				Thread.sleep(1000);
				printBalance();
				gameLoop();
			}
			else {
				System.out.println("Invalid character. Please type y for yes or n for no. ");
			}
		}
	}
	
	public static void gameLoop() 
	{
		while (true)
		{
			// Print out the user's current balance
			System.out.println("To play a game, type 1. ");
			System.out.println("To go to the coin bank, type 2. ");
			System.out.println("To go to the lounge, type 3. ");
			System.out.println("To stop playing, type 4. ");
			System.out.println("What would you like to do?");
			while (true)
			{
				try {
					int option = readInt();
					switch(option) {
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
		// TODO: Fix numbering system
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
				int option = readInt();
				switch(option) {
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
	
	public static void blackjack() throws IOException, InterruptedException
	{
		int bet = getBet();
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
		while (true)
		{
			if (done)
			{
				break;
			}
			System.out.println("Do you want to hit or stand? ");
			String next = readLine();
			System.out.print(next);
			if (next.equals("hit"))
			{
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
	
	public static void slots() throws IOException
	{
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
		int t1 = getRand(1, 5), t2 = getRand(1, 5), t3 = getRand(1, 5);
		// cherry, apple, banana, orange, kiwi
		if (t1 == t2 && t1 == t3 && t1 == 1)
		{
			System.out.println("cherry cherry cherry");
			System.out.println("You win 500 coins!");
			updateCoins(500, 0);
		}
		else if (t1 == t2 && t1 == t3)
		{
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
			// print out the 3 coins
			System.out.println("You lose!");
			updateCoins(-10, 0);
		}
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
	
	public static void roulette() throws IOException
	{
		int bet = getBet();
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
		System.out.println("Pick a number(1-36)");
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
		int rand1 = getRand(1, 2), rand2 = getRand(0, 37);
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
	
	public static void lottery() throws IOException
	{
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
		
		int num = getRand(1, getRand(100, 200));
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
	
	public static void wheelOfFortune() throws IOException
	{
		// 23 words
		String[] words = {"push","lift","dish","tire","rock","hill","tent","lion","exit","kick","moon","lazy","axis","half","coin","soft","loud","cold","read","idea","cart","roof","seat"};
		int idx = getRand(0, 22);
		String secret = words[idx];
		int bet = getBet();
		int win = 50;
		String used = "";
		char[] right = new char[4];
		idx = 0;
		System.out.println("You have 10 chances to guess the word.");
		for (int i = 0; i < 10; i++)
		{
			System.out.println("Guess a letter: ");
			char letter = readChar();
			if (secret.indexOf(letter) != -1 && used.indexOf(letter) == -1)
			{
				System.out.println("You guessed the letter right! ");
				right[idx] = letter; idx++;
				char[] secretSort = secret.toCharArray();
				Arrays.sort(right); Arrays.sort(secretSort);
				boolean same = Arrays.equals(right, secretSort);
				if (same)
				{
					System.out.println("You guessed the word! You win! ");
					updateCoins(bet, 0);
					game();
				}
			}
			else 
			{
				System.out.println("You guessed the letter wrong! ");
			}
		}
		System.out.println("You failed to guess the word! You lose! ");
		updateCoins(-bet, 0);
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
	
	public static void highOrLow() throws IOException
	{
		int bet = getBet();
		int val = getRand(1, 100);
		boolean win = false;
		for (int i = 0; i < 5; i++)
		{
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
		if (win)
		{
			updateCoins(bet, 0);
		}
		else 
		{
			updateCoins(-bet, 0);
		}
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
	
	public static void bacarrat() throws IOException
	{
		int bet = getBet();
		String option;
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
		int dcard1 = getRand(1, 13), dcard2 = getRand(1, 13);
		int pcard1 = getRand(1, 13), pcard2 = getRand(1, 13);
		int sumd = (dcard1 + dcard2) % 10, sump = (pcard1 + pcard2) % 10;
		System.out.println("Dealer's cards: " + displayCard(dcard1) + " " + displayCard(dcard2));
		System.out.println("Player's cards: " + displayCard(pcard1) + " " + displayCard(pcard2));
		if ((sumd > sump && option.equals("dealer")) || (sump > sumd && option.equals("player")))
		{
			System.out.println("You win " + bet + " coins! ");
		}
		else 
		{
			System.out.println("You lose " + bet + " coins! ");
		}
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
	
	public static void scratchCards() throws IOException
	{
		int[][] arr = {{1, 2, 2}, {3, 4, 5}, {4, 3, 1}};
		boolean[][] found = {{false, false, false}, {false, false, false}, {false, false, false}};
		int[] cnt = {0, 0, 0, 0, 0, 0};
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
		int bet = 50;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				System.out.print("X ");
			}
			System.out.println();
		}
		for (int i = 0; i < 3; i++)
		{
			while (true)
			{
				System.out.println("Give 0 indexed x y position. ");
				int a = readInt();
				int b = readInt();
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
		boolean win = false;
		for (int i = 1; i <= 5; i++)
		{
			if (cnt[i] == 2)
			{
				System.out.println("You win! ");
				win = true;
				break;
			}
		}
		if (win)
		{
			updateCoins(bet, 0);
		}
		else {
			System.out.println("You lose! ");
			updateCoins(-bet, 0);
		}
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
	
	public static void splitOrSteal() throws IOException
	{
		System.out.println("The prize pool is 100 coins. ");
		String option; 
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
		int val = getRand(1, 2);
		if (option.equals("split"))
		{
			if (val == 1)
			{
				System.out.println("The opponent splits. You win 20 coins! ");
				updateCoins(20, 0);
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
				System.out.println("The opponent steals. You lose 20 coins. ");
				updateCoins(-20, 0);
			}
		}
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char op = readChar();
			if (op == 'y')
			{
				blackjack();
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
	
	public static void dealOrNoDeal() throws IOException
	{
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
		int money[] = {1, 10, 50, 100, 500, 1000, 10000};
		int offset[] = {50, 25, 10, 0, -10, -25, -100};
		boolean chosen[] = {false, false, false, false, false, false, false};
		int offer = 50;
		for (int i = 1; i <= 7; i++)
		{
			if (i >= 5)
			{
				System.out.println("The bank can offer you " + Math.max(offer, 0) + " coins. ");
				System.out.println("deal or no deal? ");
				while (true)
				{
					String option = readLine();
					if (option.equals("deal"))
					{
						updateCoins(Math.max(offer, 0) - 50, 0);
						game();
					}
					else if (option.equals("no deal"))
					{
						break;
					}
					else 
					{
						System.out.println("Invalid option. Please try again. ");
					}
				}
			}
			System.out.println("Choose a case to eliminate (0-6): ");
			while (true)
			{
				try
				{
					int option = readInt();
					if (option > 6 || option < 0)
					{
						System.out.println("Invalid option. Please try again. ");
					}
					else 
					{
						break;
					}
				}
				catch (Exception e)
				{
					System.out.println("Invalid option. Please try again. ");
				}
			}
			int idx = getRand(0, 6);
			if (chosen[idx] == false)
			{
				System.out.println("You chose case " + idx + " with " + money[idx] + " coins! ");
				chosen[idx] = true;
				offer += offset[idx];
			}
		}
		System.out.println("Your final offer was " + Math.max(offer, 0) + " coins. ");
		int a = Math.max(offer, 0) - 50;
		updateCoins(a, 0);
		while (true)
		{
			System.out.println("Would you like to play again? (y/n): ");
			char option = readChar();
			if (option == 'y')
			{
				dealOrNoDeal();
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
	
	public static void coins() throws IOException
	{
		System.out.println("Welcome to the coin bank! ");
		System.out.println("What do you want to do? ");
		System.out.println("To purchase more coins, type 1. ");
		System.out.println("To view your balance, type 2. ");
		System.out.println("To go back, type 3.");
		while (true)
		{
			try {
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
	
	public static void purchase() throws IOException, InterruptedException
	{
		while (true)
		{
			System.out.println("You can purchase 1000 coins for $10. Would you like to do so? (y/n): ");
			char ch = readChar();
			if (ch == 'y')
			{
				updateCoins(100, -10);
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

	public static void printBalance() throws IOException, InterruptedException
	{
		BufferedReader reader = new BufferedReader(new FileReader("balance.txt"));
		String coins = reader.readLine();
		String bank = reader.readLine();
		System.out.println("Your coins balance is " + coins + ". \nYour bank balance is " + bank + ".");
		reader.close();
	}
	
	public static void lounge()
	{
		System.out.println("Welcome to the lounge! ");
		System.out.println("To speak to a supervisor, type 1. ");
		System.out.println("To buy something, type 2. ");
		System.out.println("To hang out and listen to music, type 3. ");
		System.out.println("To go back, type 4. ");
		while (true)
		{
			try {
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
	
	public static void supervisor() throws InterruptedException
	{
		System.out.println("We have 10 wonderful games to choose from! ");
		System.out.println("In blackjack, the goal is to beat the dealer. \n"
		+ "You do this by either hitting or standing until you get over 21 and lose or more than the dealer and win. ");
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		System.out.println();
		Thread.sleep(1000);
		lounge();
	}
	
	public static void buy()
	{
		System.out.println("What would you like to buy? ");
		System.out.println("To buy a $10 meal, type 1. ");
		System.out.println("To buy a $20 meal, type 2. ");
		System.out.println("To buy a $5 drink, type 3. ");
		System.out.println("To go back, type 4. ");
		while (true)
		{
			try {
				int option = readInt();
				switch(option) {
					case 1: 
						updateCoins(0, -10);
						System.out.println("Here is your meal to go.");
						System.out.println("We hope you enjoy your time here. ");
						break;
					case 2: 
						updateCoins(0, -20);
						System.out.println("Here is your meal to go.");
						System.out.println("We hope you enjoy your time here. ");
						break;
					case 3:
						updateCoins(0, -5);
						System.out.println("Here is your drink. ");
						System.out.println("We hope you enjoy your time here. ");
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
	
	public static void music()
	{
		File musicPath = new File("Casino Music.wav");
		try
		{
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
			Clip clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			JOptionPane.showMessageDialog(null, "You just got rickrolled! ");
			lounge();
		}
		catch (Exception e)
		{
			System.out.println("An error occurred. Java packages/setup are not properly installed. ");
			System.out.println("Resolving... ");
			e.printStackTrace();
			System.out.println("File not found or file type not supported. ");
			System.out.println("Please go do something else while we fix the issue. ");
		}
	}
	
	public static void end()
	{
		System.out.println("Thank you so much for coming to the Golden Coin Casino. ");
		System.out.println("We hope you enjoyed your time here. ");
		// Get the bank balance
		balance();
		System.out.println("Please come again soon. Goodbye! ");
	}
	
	// Useful methods
	
	public static int getRand(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public static void updateCoins(int addCoin, int addBank) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("balance.txt"));
		String coins = reader.readLine();
		String bank = reader.readLine();
		int coinsInt = Integer.parseInt(coins), bankInt = Integer.parseInt(bank);
		coinsInt += addCoin; 
		bankInt += addBank;
		coins = Integer.toString(coinsInt); 
		bank = Integer.toString(bankInt);
		FileWriter writer = new FileWriter("balance.txt", false); 
        PrintWriter pw = new PrintWriter(writer, false);
        pw.flush();
        writer.write(coins);
		writer.write(System.getProperty("line.separator"));
		writer.write(bank);
		writer.write(System.getProperty("line.separator"));
		System.err.println("Values updated. ");
		reader.close();
		writer.close();
		pw.close();
	}
	
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
	
	public static int getBet()
	{
		while (true)
		{
			System.out.println("How much money would you like to bet?");
			try {
				int bet = readInt();
				if (betValid(bet))
				{
					return bet;
				}
				System.out.println("Invalid bet value. Please try again. ");
			}
			catch (Exception e)
			{
				System.out.println("Invalid bet value. Please try again. ");
			}
		}
	}
	
	public static boolean betValid(int betValue) throws IOException
	{
		if (betValue > 0 && betValue < 100)
		{
			return true;
		}
		return false;
	}
	
	// Fast IO Methods
	static String next() throws IOException 
	{
		while (st == null || !st.hasMoreTokens())
		{
			st = new StringTokenizer(br.readLine().trim());
		}
		return st.nextToken();
	}
	static long readLong() throws IOException 
	{
		return Long.parseLong(next());
	}
	static int readInt() throws IOException 
	{
		return Integer.parseInt(next());
	}
	static double readDouble() throws IOException 
	{
		return Double.parseDouble(next());
	}
	static char readChar() throws IOException 
	{
		return next().charAt(0);
	}
	static String readLine() throws IOException 
	{
		return br.readLine().trim();
	}
}
