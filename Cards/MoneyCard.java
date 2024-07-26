import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the MoneyCard. You are able to bank this card to pay othe players
*/

class MoneyCard extends Card {

  /**
  * Constructs a MoneyCard object, inheriting from the Card class. The parameter int value is the value of the money card
  */
  public MoneyCard(int value){
    super("$" + value, "Money Card", value);
  }

  /**
  * Gets the description of the card from the file CardDescriptions.txt
  * @return returns a String explaining the money card function
  */
  @Override
  public String getDescription() {
    return "Description: Put money cards into your Bank pile and use them to pay other players.";
  }

  /**
  * If a player chooses to play a Money Card, they are able put money into their bank
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    
    /**
    * Adds the played money card value, into the bank of the player
    */
    Card c = new MoneyCard(this.value);
    p.addToBank(value);
    System.out.println(p.getName() + ", $" + this.value + " has been added to your bank.");
  }
  
}