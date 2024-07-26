import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Forced Deal with the attributes String name, String type and int value. When a player (player1) plays this action card, they swap properties with a selected player (player2). This card is also bankable for $3
*/

class ForcedDeal extends Card {

  /**
  * Constructs a ForcedDeal object, inheriting from the Card class
  */
  public ForcedDeal(){
    super("Forced Deal", "Action Card", 3);
  }

  /**
  * If a player chooses to play Forced Deal, they are able to swap a property with a selected player
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    /**
    * ForcedDeal is only playable if other players have played cards. 
    */
    Card c = new ForcedDeal();
    if (!bankOption(c, p)){
    boolean flag = false;
    int num = 0; 
    Player pNamePlayer = new Player("", 0);
    for (int i = 0; i < players.length; i++){
      if ((!(players[i].getName().equals(p.getName())) && players[i].getPlayedCards().size() > 0) && p.getPlayedCards().size() > 0){
        flag = true;
      }
    }
      /**
      * System asks for a selected player. If the selected play does not have any played cards, re-ask for input. 
      */
    if (flag){
    System.out.println(p.getName() + " is going to force a property swap! " + p.getName() + ", enter the name of the player you want to swap properties with. (Ensure the player name is spelled correctly!)");
    pNamePlayer = nameChecker(p, players);
    while (pNamePlayer.getPlayedCards().size() == 0){
      System.out.println(pNamePlayer.getName() + " doesn't have any played cards. Try again.\n");
      pNamePlayer = nameChecker(p, players);
    }
      /**
      * If the selected player plays a Just Say No action card, cancel the ForcedDeal effect. If not, a property is selected to be swapped.
      */
    if (!justSayNo(pNamePlayer, players, dp)){
    System.out.println(p.getName() + ", which property of yours would you like to swap with?\n\n" + p.playedCardsToString());
    String ans = s.nextLine();
    while (!isInt(ans) || Integer.parseInt(ans) > p.getPlayedCards().size() || Integer.parseInt(ans) <= 0){
      System.out.println("Invalid input. Please enter the number of a different card you would like to use.");
        ans = s.nextLine();
    }
    num = Integer.parseInt(ans);
    Card swapWith = p.getPlayedCards().get(num-1);
    System.out.println(p.getName() + ", which property of " + pNamePlayer.getName() + "'s would you like to swap for?\n\n" + pNamePlayer.playedCardsToString());
    ans = s.nextLine();
    while (!isInt(ans) || Integer.parseInt(ans) > pNamePlayer.getPlayedCards().size() || Integer.parseInt(ans) <= 0){
      System.out.println("Invalid input. Please enter the number of a different card you would like to use.");
        ans = s.nextLine();
    }
    num = Integer.parseInt(ans);
      /**
      * The two properties are swapped.
      */
    Card swapFor = pNamePlayer.getPlayedCards().get(num - 1);
    p.removePlayedCard(swapWith);
    p.addPlayedCard(swapFor);
    pNamePlayer.addPlayedCard(swapWith);
    pNamePlayer.removePlayedCard(swapFor);
    }
    }
      /**
      * If players have no played cards, ForcedDeal is unplayable. Instead, it is banked for $3.
      */
    else {
      System.out.println("A Forced Deal cannot be performed at this time, as no other players have any played cards. This card has been banked.");
      p.addToBank(this.value);
      }
    }
  }
  
}