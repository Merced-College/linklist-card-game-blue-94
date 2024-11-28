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

    public static void shuffle() {
        String[] suits = {"heart", "space", "club", "diamond"};
        String[] values = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
        Random randNum = new Random();

        Card card1 = new Card();
        Card card2 = new Card();
        String randSuit1 = "";
        String randSuit2 = "";
        String randValue1 = "";
        String randValue2 = "";
        while (randSuit1 == randSuit2 && randValue1 == randValue2 || cardList.find(card1) == null || cardList.find(card2) == null) {
            randSuit1 = suits[randNum.nextInt(4)];
            randSuit2 = suits[randNum.nextInt(4)];
            randValue1 = values[randNum.nextInt(14)];
            randValue2 = values[randNum.nextInt(14)];
            card1.setCardSuit(randSuit1);
            card1.setCardName(randValue1);
            card2.setCardSuit(randSuit2);
            card2.setCardSuit(randValue2);
        }
        
        // Need to swap the two cards
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
		
		Card[] playerHand = new Card[5];
        // Pulls five cards from the deck for the player's hand
		for(int i = 0; i < playerHand.length; i++)
			playerHand[i] = cardList.getFirst();
		
        // Prints out the cards in the player's hand
		System.out.println("players hand");
		for(int i = 0; i < playerHand.length; i++)
			System.out.println(playerHand[i]);
		
		System.out.println();
        // Prints out the contents of the deck
		System.out.println("the deck");
		cardList.displayList();

	}//end main

}//end class
