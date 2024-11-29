// Jeremiah Tenn

// Added shuffling method and a game method that allows the player to guess 10 drawn cards given a hand of 10 cards
// Deleted some of the in main for displaying the deck
// The shuffle method uses a LinkList method I implemented in the LinkList class called swap(), switches the positions of two cards.
// In order for some of the card comparisons to work, I implemented a compareSuitName method in Card, 
// which compares two the suit and name of two Card objects. This works because any are with a suit and name has no duplicate in a standard deck
// Changed find() in LinkList to compare the cardLink with cardToFind(), because we are comparing the Card objects, not the Link with the Card.
// Added a setCardLink() method to the Link class to change the associated Card object for the Link. This is used in swapping cards during shuffling.

//package linkedLists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
import java.util.Random; // ADDED: used for random shuffling


public class CardGame {
	
	private static LinkList cardList = new LinkList();  // make list
    // ADDED: arrays of strings for the suits and values. a random integer will be generated and will access one from each array for shuffling
    // Also used for checking player guesses
    public static String[] suits = {"heart", "spade", "club", "diamond"};        
    public static String[] values = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};

    // ADDED: game method which contains the code for the cardguessing game
    public static void game(Card[] playerHand) {    // Uses the hand array declared in main
        Scanner scanner = new Scanner(System.in);   // Sets up the scanner
        String answer = "";                         // Declares a blank answer string for the player's answer 
        int points = 0;                             // Player's points initialized to zero

        System.out.println("Your cards:");        // Draws 10 cards and adds them to the hand, also prints them out
        for (int i = 0; i < 10; ++i) {
            playerHand[i] = cardList.getFirst();
            System.out.println(playerHand[i].getCardName() + " of " + playerHand[i].getCardSuit());
        }
        System.out.println("\nDo you want instructions? yes/no");   // Asks the player if they want instructions
        
        // Keeps on prompting an answer as long as the answer is not yes or no
        while(!answer.equals("yes") && !answer.equals("no")) {
            answer = scanner.nextLine();    // Get input

            // If yes, output the instructions and then break out of the loop
            if (answer.equals("yes")) { 
                System.out.println("\n10 cards will be drawn: using the drawn ten cards and your hand of 10 cards, guess the suit or name for the next card!");
                System.out.println("Correct answers for name will gain 5 points and correct answers for suit will gain 1");
                System.out.println("Please enter answers in lowercase, with numbers written as words (e.g. one, two , three)");
                break;
            }
            // If no, immediately break out of the loop
            else if (answer.equals("no")) {
                break;
            }
            // If not yes or no, tell the user that the answer is invalid and the while loop gives them the prompt again
            else {
                System.out.println("\nPlease enter a valid answer (yes/no)");
            }            
        }

        // The guessing game, 10 cards will be guessed so the loop will run 10 times
        for (int i = 0; i < 10; ++i) {
            System.out.println("\nGuess the suit or name:");
            Card drawnCard = cardList.getFirst();   // Pull the next card from the deck
            System.out.println(drawnCard);
            answer = scanner.nextLine();            // Get the player's guess

            // If correct suit is guessed tell the player that and incremnt their points by 1
            if (answer.equals(drawnCard.getCardSuit())) {
                System.out.println("Correct suit guessed! Card added to hard. You gained 1 point");
                points += 1;
            }
            // If correct name is guessed tell the player and add five to points
            else if (answer.equals(drawnCard.getCardName())) {
                System.out.println("Correct name guessed! You gained 5 points");
                points += 5;
            }
            // If both are incorrect tell the player so
            else {
                System.out.println("Sorry, that was not the suit or the name of the card");
            }
            // Tell the player what the card was and their amount of points
            System.out.println("Card was " + drawnCard.getCardName() + " of " + drawnCard.getCardSuit() + "s");
            System.out.println("Points: " + points);
        }

        System.out.println("GAME ENDED - THANK YOU FOR PLAYING");   // Thank the player for playing
        System.out.println("Final points: " + points);  // Output the final points
        scanner.close();    // Close scanner since it will no longer be used

    }

    public static void shuffle() {

        // Initialize random object for shuffling
        Random randNum = new Random();

        // Swaps a pair of cards 52 times; emulates the shuffling of the deck
        for (int i = 0; i < 104; ++i) {  // There are 52 cards in the deck so twice the value is used for a thorough shuffle
            Card card1 = new Card();    // Creates two card objects that will represent the two cards that will be swapped
            Card card2 = new Card();
            String randSuit1 = "";      // Sets up default values for Suit and Name for the two Card Ovjwxra
            String randSuit2 = "";
            String randName1 = "";
            String randName2 = "";
            card1.setCardSuit(randSuit1);   // Assignts the default blank values, otherwise a null pointer exception error will occur
            card1.setCardName(randName1);
            card2.setCardSuit(randSuit2);
            card2.setCardName(randName2);

            // Keep on generating cards when suite and number is the same or if the cards are not in the deck.
            while (card1.equalSuitName(card2) || cardList.find(card1) == null || cardList.find(card2) == null) {
                // Generates a random number between 0 and 4 for a random suit string
                randSuit1 = suits[randNum.nextInt(4)];
                randSuit2 = suits[randNum.nextInt(4)];  

                // Generates a random number between 0 and 14 for a random name string
                randName1 = values[randNum.nextInt(14)];
                randName2 = values[randNum.nextInt(14)];

                // Sets the randon suits and names to their respective Card objects
                card1.setCardSuit(randSuit1);
                card1.setCardName(randName1);
                card2.setCardSuit(randSuit2);
                card2.setCardName(randName2);
            }

            // Performs the swap between the two cards; card 1 is in card 2's Link, and vice versa
            cardList.swap(card1, card2);
        }
    }

	public static void main(String[] args) {

		// File name to read from
        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line); // Outputs error message if there is a problem with the current line
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage()); // Outputs error message if reading file does succeed.
        }
       
        shuffle();                          // Shuffle the deck before playing the game
		Card[] playerHand = new Card[10];   // Initialize the player's hand
        game(playerHand);                   // Start the game using the declared playerHand array
	}//end main

}//end class
