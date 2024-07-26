import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Deal Breaker with the attributes String name, String type and int value. When a player plays this action card, a player (player1) can steal another player's (player2) full property set. This card is also bankable for $5.
*/

class DealBreaker extends Card {

  /**
  * Constructs a DealBreaker object, inheriting from the Card class
  */
  public DealBreaker(){
    super("Deal Breaker", "Action Card", 5);
  }

  /**
  * A player (player1) who plays their DealBreaker action card is able to select a player (player2) to steal a full property set. 
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp) {
    Card c = new DealBreaker();
      /**
      * Checks if player2 has a full property set that can be stolen from.
      */
    boolean flag = false;
      for (int i = 0; i < players.length; i++){
       if (players[i].getNoOfSets() > 0){
         flag = true;
       } 
      }
    /**
    * Asks player1 to enter the name of the player they want to steal from
    */
    if (!bankOption(c, p) && flag){
      System.out.println(p.getName() + " is looking to steal a set! " + p.getName() + ", enter the name of the player you want to steal a property set from. (Ensure the player name is spelled correctly!)");
      
      /**
      * Ensures that player2 has a full set to give
      */
      Player pNamePlayer = nameChecker(p, players);
      while (pNamePlayer.getNoOfSets() == 0){
        System.out.println(pNamePlayer.getName() + " has no sets to steal! Please enter a different player.");
        pNamePlayer = nameChecker(p, players);
      }
      /**
      * If  player2 uses a JustSayNo action card, disregard the DealBreaker action card
      */
      if (!justSayNo(pNamePlayer, players, dp)){
      String sets = "";
      for (int i = 0; i < pNamePlayer.getSetColors().size(); i++){
        sets += pNamePlayer.getSetColors().get(i);
        if (i != pNamePlayer.getSetColors().size() - 1){
          sets += ", ";
        }
      }

        /**
        * Asks player1 to enter the colour set they want to steal 
        */
      System.out.println(pNamePlayer.getName() + " has the following sets: " + sets.trim() + ". Enter the color of the set you wish to steal.");
      String ans = s.nextLine();
      while (!pNamePlayer.getSetColors().contains(ans)){
        System.out.println("Invalid input. " + pNamePlayer.getName() + " has the following sets: " + sets + ". Enter the color of the set you wish to steal.");
        ans = s.nextLine();
      }
      ArrayList<Card> arr = new ArrayList<Card>();
      for (int i = 0; i < pNamePlayer.getPlayedCards().size(); i++){
        if (pNamePlayer.getPlayedCards().get(i).getType().equals("Property Card")){
      
          PropertyCard pCard = (PropertyCard) pNamePlayer.getPlayedCards().get(i);
          /**
          * Adds the colour set into player 1's PlayedCards set (array)
          */
          if (pCard.getColor().equals(ans)){
            arr.add(pCard);
          }
          }
      }
        /**
        * Removes the colour set that is stolen from player 2's PlayedCards set (array)
        */
      for (int i = 0; i < arr.size(); i++){
        pNamePlayer.removePlayedCard(arr.get(i));
        p.addPlayedCard(arr.get(i));
      }

        /**
        * If the stolen set includes a house/hotel, it is included in player1's property set
        */
      System.out.println("The " + ans + " set has been stolen, and added to your played cards!");
      if (pNamePlayer.getNoOfHouses(ans) > 0){
        p.addHouse(ans);
        pNamePlayer.removeHouse(ans); 
      if (pNamePlayer.getNoOfHotels(ans) > 0){
        p.addHotel(ans);
        pNamePlayer.removeHotel(ans);
      }
      }
    }
  }
    /**
    * If the other players have no property sets, the Deal Breaker card is banked.
    */
    else{
      System.out.println("The other players have no property sets. This card has been banked.");
      p.addToBank(5);
    }
  }
}