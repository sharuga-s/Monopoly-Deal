import java.util.*;
import java.io.*;

/**
* DiscardPile class makes a discard/trash pile of cards using the Card class. It may be added to (addCard method), removed from (removeCard method), returned (returnCards method), & return & empty (empty method). 
* @author ___
* @version 1.0
*/

class DiscardPile{

  private ArrayList<Card> discardPile = new ArrayList<Card>();

  /**
   * Constructs a DiscardPile object using the cards in a provided array by converting the array of cards into an ArrayList then assigning it to the discardPile instance variable.
   * @param ArrayList<Card> discardPile an arraylist of Cards that represents the cards in the discard pile.
   */
  public DiscardPile(ArrayList<Card> discardPile){
    for (int i = 0; i < discardPile.size(); i++){
      if (discardPile.get(i)!= null){
        this.discardPile.add(discardPile.get(i));
      }
    }
  }

  /**
   * Constructs a default DiscardPile object with no parameters and generates an empty discard pile Card ArrayList that's assigned to the instance variable discardPile.
   */
  public DiscardPile(){
  }
  
  /**
   * Adds a specified card to the end of the discard pile.
   * @param Card card a Card that represents the new card being added to the discard pile.
   */
  public void addCard(Card card){
    if (card != null){
      this.discardPile.add(card);
    }
  }
  
  /** 
  * Outputs all the cards in the discard pile and empties the discard pile
  * @param Card card is a the card that should be removed from the discardPile
  * @return returns an ArrayList with all the Cards from the discardPile
  */
  public ArrayList<Card> empty(){
    ArrayList<Card> arr = new ArrayList<Card>();
    for (int i = 0; i < this.discardPile.size(); i++){
      arr.add(this.discardPile.get(i));
    }
    this.discardPile.clear();
    return arr;
  }

  /**
   * Gets the size of the card. 
   * @return returns the size of the discard pile.
   */
  public int size() {
    return this.discardPile.size();
  }
}