import java.util.*; 
import java.io.*;

/**
  * This class extends from Card and represents the action card House. When a player plays this action card, they add a house onto their full property set, increasing the value. This card is also bankable for $3
*/

class House extends Card {
  Scanner s = new Scanner(System.in);
  
  /**
  * Constructs a House object, inheriting from the Card class
  */
  public House(){
    super("House", "Action Card", 3); 
  }

  /**
  * If a player chooses to play House, they are able to increase the value of a full property set.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override 
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp) {
    /**
    * House is only playable if the player has full property sets. If not, the card is banked for $3
    */
    Card c = new House();
    
    if (!bankOption(c, p)) {
      if (p.getNoOfSets() == 0) { 
        System.out.println("You cannot play this house card because you have no full sets. This house card has been added to the bank.");
        p.addToBank(this.value);
        /**
        * The selected full property set gains a Hotel and increases the value. If they input an invalid answer, the system re-asks.
        */
      }
      else{
        System.out.println(p.playedCardsToString() + "\n\nWhich full set would you like to add this house to? Specify the colour. Please ensure the color is capitalized and spelled correctly.");
        String colourInput = s.nextLine();
        while (!p.getSetColors().contains(colourInput)) {
          System.out.println("Invalid colour. Which full set would you like to add this house to? Specify the colour. Please ensure the color is capitalized and spelled correctly.");
          colourInput = s.nextLine();
        }
        if (colourInput.equals("Black") || colourInput.equals("Utility")){
          System.out.println("You cannot put a house on a utility or black property. This House card has been added to the bank.");
          p.addToBank(this.value);
          
        }
        else{
          p.addHouse(colourInput);
        }
      }
    }
    
  }
  
  
}