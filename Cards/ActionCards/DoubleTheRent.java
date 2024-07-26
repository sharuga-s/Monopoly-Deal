import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Double The Rent with the attributes String name, String type and int value. When a player plays this action card, the rent of a property is doubled. This card is also bankable for $1 
*/

class DoubleTheRent extends Card {

  /**
  * Constructs a DoubleTheRent object, inheriting from the Card class
  */
  public DoubleTheRent(){
    super("Double The Rent", "Action Card", 1);
  }

  /**
  * A player is able to double the rent of their property when played with a Rent card
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    Card c = new DoubleTheRent();
    /**
    * A Rent card must be played for a DoubleTheRent card to be viable. Checks if a Rent card is played, if not, they are UNABLE to use DoubleTheRent 
    */
    if (!bankOption(c, p)) {
      
      /**
      * Checks if a Rent card is played, if so, they are ABLE to use DoubleTheRent
      */
      for (int i = 0; i < p.getHand().size(); i++) {
        if (p.getHand().get(i).getName().equals("Rent")) { 
          System.out.println("You have one or more Rent Cards in your hand that you can play with this Double The Rent card. Please play the Rent card.");
          i = p.getHand().size();
      }
        /**
        * If the player has no rent cards, DoubleTheRent is unplayable. Instead, it is banked for $1
        */
      System.out.println("You have no Rent Cards. You cannot play a Double The Rent card without playing a Rent card first. This Double The Rent Card has been added to the bank.");
      p.addToBank(1);
      i = p.getHand().size();
    }
  }

}
}