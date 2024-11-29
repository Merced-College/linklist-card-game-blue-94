// Jeremiah Tenn

// Added a swap method which swaps the associated Card objects for two specified Links.
// Changed find(), using !current.cardLink.equalSuitName(cardToFind) rather than !current.cardLink.equals(cardToFind)
// Change made because we are comparing the associated Card object (cardLink), not the Link itself.

public class LinkList
{
	private Link first;            // ref to first link on list

	//-------------------------------------------------------------
	public LinkList()              // constructor
	{
		first = null;               // no links on list yet
	}
	//-------------------------------------------------------------
	public void insertFirst(Card card)
	{                           // make new link
		Link newLink = new Link(card);
		newLink.next = first;       // it points to old first link
		first = newLink;            // now first points to this
		
	}

	// Actually the same function as insertFirst
	public void add(Card card)
	{                           // make new link
		Link newLink = new Link(card);
		newLink.next = first;       // it points to old first link
		first = newLink;            // now first points to this
	}
	//-------------------------------------------------------------
	public Link find(Card cardToFind)      // find link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // start at 'first'

		// CHANGED: uses current.cardLink.equalSuitName() rather than current.equal() to accurately compare the card to the Link
		// Compares the value of the Link's associated card with the card that is being searched for 
		// CHANGED: uses equalSuitName so picture name and value are not needed to search. Every card with a suit and name are unique.
		while(!current.cardLink.equalSuitName(cardToFind))        // while no match,
		{
			if (current.next == null) { // if end of list,
				return null;          	// didn't find it   
			}     
			else {                           // not end of list,
				current = current.next;      // go to next link
			}
		}
		return current;                    // found it
	}
	//-------------------------------------------------------------
	public Link delete(Card cardToFind)    // delete link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // search for link
		Link previous = first;
		while(!current.equals(cardToFind))
		{
			if(!current.equals(cardToFind))
				return null;                 // didn't find it
			else
			{
				previous = current;          // go to next link
				current = current.next;
			}
		}                               // found it
		if(current.equals(cardToFind))               // if first link,
			first = first.next;             //    change first
		else                               // otherwise,
			previous.next = current.next;   //    bypass it
		return current;
	}
	//--------------------------------------------------------

	//--------------------------------------------------------
	// Finds card1, puts card2 in its place. Finds card2 and puts card1 in its place.
	public void swap(Card card1, Card card2) 
	{
		Link currLink = first;		// Assigns currLink to the first object in the LinkList
		
		// booleans are false - will be true once the Link has been find
		boolean card1Found = false; 
		boolean card2Found = false;

		// while card1 and card2 have not been found continue in the loop
		while (!card1Found || !card2Found) 
		{
			
			// If the cardLink in the current Link is the same as card1, change the cardLink to card2 and make the boolean true (since found)
			if (currLink.cardLink.equalSuitName(card1)) 
			{
				currLink.setCardLink(card2);
				card1Found = true;
			}

			// If the cardLink is same as card2, change it to card1 and make the boolean true
			else if (currLink.cardLink.equalSuitName(card2)) 
			{
				currLink.setCardLink(card1); // puts card one in card two position
				card2Found = true;
			}
			
			// If both booleans are true (both have been found), return (exit method)
			if (card2Found && card1Found) 
			{
				return;
			}
			// Otherwise move on to the next Link
			currLink = currLink.next;
		}
	}

	//-------------------------------------------------------------
	public void displayList()      // display the list
	{
		System.out.print("List (first-->last): ");
		Link current = first;       // start at beginning of list
		while(current != null)      // until end of list,
		{
			current.displayLink();   // print data
			current = current.next;  // move to next link
		}
		System.out.println("");
	}
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	public Card getFirst()    // delete link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // search for link
		first = first.next;             //    change first
		return current.cardLink;
	}
	/* 
	public static void main(String[] args) {
		LinkList theList = new LinkList();
		Card card1 = new Card("heart", "ace", 11,"ah.gif");
		Card card2 = new Card("spade", "queen", 11,"ah.gif");
		Card card3 = new Card("diamond", "two", 2,"ah.gif");
		Card card4 = new Card();
		theList.add(card1);
		theList.add(card2);
		theList.add(card3);
		card4.setCardName("two");
		card4.setCardSuit("diamond");
		System.out.println(theList.find(card4));
		System.out.println(theList.find(new Card("spade", "two", 2,"ah.gif")));
	} */
}
