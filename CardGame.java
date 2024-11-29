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

    public static void game(Card[] playerHand) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your cards:");
        for (int i = 0; i < 10; ++i) {
            playerHand[i] = cardList.getFirst();
            System.out.println(playerHand[i]);
        }
        System.out.println("Do you want instructions? yes/no");
        String answer = "";
        int points = 0;
        while(!answer.equals("yes") || !answer.equals("no")) {
            answer = scanner.nextLine();
            if (answer.equals("yes")) {
                System.out.println("\n10 cards will be drawn: using the drawn ten cards, guess the suit or name for new card!");
                System.out.println("Correct answers for name will gain 5 points and correct answers for suit will gain 1");
                System.out.println("Please enter answers in lowercase, with numbers written as words (e.g. one, two , three)");
                break;
            }
            else if (answer.equals("no")) {
                break;
            }
            else {
                System.out.println("\nPlease enter a valid answer (yes/no)");
            }            
        }

        for (int i = 0; i < 10; ++i) {
            System.out.println("\nGuess the suit or name:");
            Card drawnCard = cardList.getFirst();
            System.out.println(drawnCard);
            answer = scanner.nextLine();

            if (answer.equals(drawnCard.getCardSuit())) {
                System.out.println("Correct suit guessed! Card added to hard. You gained 1 point");
                points += 1;
            }
            else if (answer.equals(drawnCard.getCardName())) {
                System.out.println("Correct name guessed! You gained 5 points");
                points += 5;
            }
            System.out.println("Card was " + drawnCard.getCardName() + " of " + drawnCard.getCardSuit() + "s");
            //playerHand[playerHand.length] = drawnCard;
        }
        System.out.println("GAME ENDED THANK YOU");
        System.out.println(points);
        scanner.close();

    }

    public static void shuffle() {

        // Initialize random object for shuffling
        Random randNum = new Random();

        // Swaps a pair of cards 52 times; emulates the shuffling of the deck
        for (int i = 0; i < 52; ++i) {  // There are 52 cards in the deck so this value is used
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
                // Generates a random number between 0 and the length of the arrays; sets the corresponding string to the Card objects
                randSuit1 = suits[randNum.nextInt(4)];
                randSuit2 = suits[randNum.nextInt(4)];
                randName1 = values[randNum.nextInt(14)];
                randName2 = values[randNum.nextInt(14)];
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

        // Print the loaded cards
        System.out.println("Cards loaded:");
        cardList.displayList();
        shuffle();
		
        
		Card[] playerHand = new Card[10];
 
        game(playerHand);
	}//end main

}//end class
