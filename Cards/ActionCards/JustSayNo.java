import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Just Say No with the attributes String name, String type and int value. When played, the player can cancel the effect of any action card on them. This card is also bankable for $4
*/

public class JustSayNo extends Card {
  
  /**
  * Constructs a JustSayNo object, inheriting from the Card class
  */
  public JustSayNo() {
    super("Just Say No", "Action Card", 4);
    }

  /**
  * If a player chooses to play JustSayNo, they can cancel the action card played on them. If it is played and no action cards have been activated against them, it is banked for $4.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    System.out.println("You have played a Just Say No card when an action card is not being used against you. This card has been banked.");
    p.addToBank(4);
  }

  /**
  * Checks if player has a JustSayNo card. If they do, provide them the option of using it. Re-ask if the answer provided is invalid.
  */
  public boolean returnBool(Player p, Player[] players){
    Card c = new JustSayNo();
      if (p.handToString().contains("Just Say No")){
        System.out.println(p.getName() + ", you have a Just Say No card. Would you like to use it? (Y/N)");
        String ans = s.nextLine();
          while (!(ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("No"))){
            System.out.println("Invalid input. Please enter Yes/Y or No/N.");
            ans = s.nextLine();
          }
        if (ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("Y")){
          for (int i = 0; i < p.getHand().size(); i++){
              System.out.println(p.getName() + " has used a Just Say No card.");
              return true;
            
          }
        }
      }
    return false;
  }
}