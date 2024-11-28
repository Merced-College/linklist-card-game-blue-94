
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
		// CHANGED: use cardLink to accurately compare the card to the Link
		// Compares the value of the link's associated card with the card that is being searched for 
		while(!current.cardLink.equals(cardToFind))        // while no match,
		{
			if(current.next == null)        // if end of list,
				return null;                 // didn't find it
			else                            // not end of list,
				current = current.next;      // go to next link
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
	public void swap(Card card1, Card card2) {
		Link currLink = first;
		boolean card1Found = false;
		boolean card2Found = false;

		while (!card1Found || !card2Found) {
			
			if (currLink.cardLink.equals(card1)) {
				currLink.setCardLink(card2);
				card1Found = true;
			}

			else if (currLink.cardLink.equals(card2)) {
				currLink.setCardLink(card1); // puts card one in card two position
				card2Found = true;
			}
		
			if (card2Found && card1Found) {
				return;
			}
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
	
	public static void main(String[] args) {
		LinkList theList = new LinkList();
		Card card1 = new Card("heart", "ace", 11,"ah.gif");
		Card card2 = new Card("spade", "queen", 11,"ah.gif");
		Card card3 = new Card("diamond", "two", 2,"ah.gif");

		theList.insertFirst(card1);      // insert 4 items
		theList.insertFirst(card2);
		theList.add(card3);


		theList.displayList();              // display list
		theList.swap(card2, card3);
		theList.displayList();

	} 
}
