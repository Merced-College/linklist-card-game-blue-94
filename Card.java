// Jeremiah Tenn

// Added equalSuitName() method, which is an alternate comparison method for objects that have only a suit and a name.
// Considering the nature of the cards, every card with a suit and name are unique.

//package cardGame;

public class Card {

    // Instance variables
    private String cardSuit;
    private String cardName;
    private int cardValue;
    private String cardPic;

    // Default constructor
    // Assigns default King card if no values are given
    public Card() {
        cardName = "King";
        cardValue = 10; // Corrected this from a string to an int
    }

    // Non-default constructor
    // Assigns the Card object with a given name and value
    public Card(String name, int value) {
        cardName = name;
        cardValue = value;
    }

    // Constructor with all attributes
    // Assigns the Card object with the given values for all variables 
    public Card(String suit, String name, int value, String pic) {
        cardSuit = suit;
        cardName = name;
        cardValue = value;
        cardPic = pic;
    }

    // Getter methods
    public String getCardName() {
        return cardName; // Returns card name 
    }

    public int getCardValue() {
        return cardValue; // Returns the value of the card
    }

    public String getCardSuit() {
        return cardSuit; // Returns the suit of the card
    }

    public String getCardPic() {
        return cardPic; // Returns the name of the card picture
    }

    // Setter methods
    public void setCardName(String newCardName) {
        cardName = newCardName; // Assigns cardName with the given string
    }

    public void setCardValue(int newCardValue) {
        cardValue = newCardValue; // Assigns cardValue with the given integer 
    }

    public void setCardSuit(String newCardSuit) {
        cardSuit = newCardSuit; // Assignts cardSuite with the given string for the suit
    }

    public void setCardPic(String newCardPic) {
        cardPic = newCardPic; // Assignts cardPic with the given string for the card name
    }

    // Method to get combined card values
    public String getCardValues() {
        return cardName + " " + cardValue; // Returns a string with the name and value of the card
    }

    // Equals method to compare two cards
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true; // Same object reference
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false; // Null or different class
        }
        Card otherCard = (Card) obj; // Converts obj into a card
        // Compares the field values with those of the obj Card object; if they are different then false is returned, otherwise true.
        return cardValue == otherCard.cardValue && 
               cardName.equals(otherCard.cardName) && 
               cardSuit.equals(otherCard.cardSuit) && 
               cardPic.equals(otherCard.cardPic);
    }

    // ADDED: alternate comparison method. Requires only suit and name to compare, since these are unique per card.
    // Used mainly for shuffling
    public boolean equalSuitName(Object obj) {
        if (this == obj) {  // Same as in equals(): returns true of same object
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;   // Same as in equals(): returns false if its null or diff class
        }
        Card otherCard = (Card) obj;    // Converts the object to a Card
        return cardSuit.equals(otherCard.cardSuit) && cardName.equals(otherCard.cardName);  // If the suit and name are the same return true if different return false
    }

    // toString method for printing the card
    @Override
    public String toString() {
        // The values of the fields are printed out upon printing a card. 
        return cardName + " of " + cardSuit + " (Value: " + cardValue + ", Picture: " + cardPic + ")";
    }
}
