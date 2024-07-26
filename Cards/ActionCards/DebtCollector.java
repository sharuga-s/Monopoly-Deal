import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Debt Collector with the attributes String name, String type and int value. When a player (player1) plays this action card, they select another player (player2) who must pay them $5. This card is also bankable for $3
*/

class DebtCollector extends Card {
  
  /**
  * Constructs a DebtCollector object, inheriting from the Card class
  */
  public DebtCollector() {
    super("Debt Collector", "Action Card", 3);
  }

  /**
  * Player1 selects player2. Player 2 owes player1 $5 player
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    Card c = new DebtCollector();
    /**
    * If player1 chooses to play the action card, system asks for input (player2)
    */
    if (!bankOption(c, p)){
      System.out.println(p.getName() + " is collecting Debt! " + p.getName() + ", enter the name of the player you want to pay you $5. (Ensure the player name is spelled correctly!)");
      Player pNamePlayer = nameChecker(p, players);
      System.out.print(pNamePlayer.getName() + ", looks like you're unlucky! ");
      /**
      * Player2 pays off their debt to player 1
      */
      for (int i = 0; i < players.length; i++){
        if (pNamePlayer.getName().equals(players[i].getName())){
          payOffDebt(p, players[i], players, 5, deck, dp);
          i = players.length;
        }
      }
    }
  }
  
  
}