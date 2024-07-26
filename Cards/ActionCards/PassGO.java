import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Pass GO with the attributes String name, String type and int value. When played, the player can pick up two cards. This card is also bankable for $1.
*/

class PassGO extends Card {
  Scanner s = new Scanner(System.in);

  /**
  * Constructs a PassGO object, inheriting from the Card class
  */
  public PassGO() {
    super("Pass GO", "Action Card", 1);
  }

  /**
  * If a player chooses to play PassGO, they pick up 2 cards on their turn.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    Card c = new PassGO();
    if (!bankOption(c, p)){
      p.drawToHand(deck);
      p.drawToHand(deck);
      System.out.println("2 cards have been drawn to your hand.");
    }
  }
  
  
}
