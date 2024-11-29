// Jeremiah Tenn

// Added setCardLink method to change the Card associated with the Link object. 
// Used for swapping in shuffling.

//package linkedLists;
//linkList2.java
//demonstrates linked list
//to run this program: C>java LinkList2App
////////////////////////////////////////////////////////////////
public class Link
{
	public Card cardLink;             // next link in list
	public Link next;
	//-------------------------------------------------------------
	public Link(Card card) // constructor
	{
		cardLink = card;
	}
	//-------------------------------------------------------------
	public void displayLink()      // display ourself
	{
		System.out.println(cardLink);
	}
	//-------------------------------------------------------------
	public void setCardLink(Card card) // ADDED: to change the card associated with the link, used for shuffling (swapping)
	{	
		cardLink = card;
	}
}  // end class Link
////////////////////////////////////////////////////////////////
