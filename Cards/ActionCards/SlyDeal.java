import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Sly Deal with the attributes String name, String type and int value. When played, a player (player1) can steal a property from another player (player 2) that is NOT from a full set.
*/

class SlyDeal extends Card {

  /**
  * Constructs a SlyDeal object, inheriting from the Card class
  */
  public SlyDeal(){
    super("Sly Deal", "Action Card", 3);
  }

  /**
  * If a player chooses to play SlyDeal, they are able to steal a property from another player.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    /**
    * SlyDeal is only playable if other players have played cards. 
    */
    Card c = new SlyDeal();
    if (!bankOption(c, p)){
    boolean flag = false;
    int num = 0; 
    Player pNamePlayer = new Player("", 0);
      for (int i = 0; i < players.length; i++){
        if (!players[i].getName().equals(p.getName())){
          if (players[i].getPlayedCards().size() > 0){ 
            flag = true;
          }
        }
      }
      /**
      * The player can select another player to steal from. Ensures name is properly spelt 
      */
      if (flag){
      System.out.println(p.getName() + " is going to force a sly deal! " + p.getName() + ", enter the name of the player you want to take a property from. (Ensure the player name is spelled correctly!)");
      pNamePlayer = nameChecker(p, players); 
        /**
        * Ensures the selected player has played cards, else, another player must be selected
        */
      while (pNamePlayer.getPlayedCards().size() == 0){
        System.out.println(pNamePlayer.getName() + " doesn't have any played cards. Try again.\n");
        pNamePlayer = nameChecker(p, players);
      }
        /**
        * If player2 plays a Just Say No action card, cancel the ForcedDeal effect. If not, a property is selected to be stolen.
        */
        if (!justSayNo(pNamePlayer, players, dp)){
      System.out.println(pNamePlayer.playedCardsToString() + "\n\nWhich property would you like to take from " + pNamePlayer.getName() + "?");
        num = s.nextInt();
        while (num > pNamePlayer.getPlayedCards().size() || num <= 0){
          System.out.println("Invalid input. Please enter the number of a different card you would like to use.");
            num = s.nextInt();
        }
        boolean validInput = false;
        while (!validInput){
          while (num > pNamePlayer.getPlayedCards().size() || num <= 0){
            System.out.println("Invalid input. Please enter the number of a different card you would like to use.");
              num = s.nextInt();
          }
          if (pNamePlayer.getPlayedCards().get(num-1) instanceof PropertyCard){
            String color = "";
            for (int i = 0; i < AllCards.propertyColors.length; i++){
              color = AllCards.propertyColors[i];
            }
            /**
            * SlyDeal cannot be played on a property part of a set. If player1 selects a property that is part of a set, re-ask for another selection
            */
            PropertyCard pc = new PropertyCard(pNamePlayer.getPlayedCards().get(num-1).getName(), color, pNamePlayer.getPlayedCards().get(num-1).getValue());
          if (pNamePlayer.getSetColors().contains(pc.getColor())){
            System.out.println("This property is part of a set. You cannot swap for it. Please enter a different card.");
            validInput = false;
            num = s.nextInt();
          }
            else{
              validInput = true;
            }
          }
          else{
            validInput = true;
          }
        }
          /**
          * Adds the stolen card to player 1's played cards. Removes the stolen card from player 2's played cards.
          */
          Card steal = pNamePlayer.getPlayedCards().get(num-1);
          pNamePlayer.removePlayedCard(steal);
          p.addPlayedCard(steal);
          changeRainbow(steal, p, players, deck, dp);
    }
      /**
      * If SlyDeal is unable to be played, it is banked for $3.
      */
    }
      else{
        System.out.println("A sly deal cannot be performed at this time, as there are not enough played cards on the table. " + p.getName() + ", your Sly Deal card has been added to your bank.");
        p.addToBank(c.getValue());
      }
  }
  }
  
}