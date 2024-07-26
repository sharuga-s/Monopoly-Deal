import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Hotel. When a player plays this action card, they add a hotel onto their full property set, increasing the value. This card is also bankable for $3
*/

class Hotel extends Card {
  Scanner s = new Scanner(System.in);

  /**
  * Constructs a Hotel object, inheriting from the Card class
  */
  public Hotel(){
    super("Hotel", "Action Card", 3); 
  }

  /**
  * If a player chooses to play Hotel, they are able to increase the value of a full property set.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override 
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp) {
    /**
    * Hotel is only playable if the player has full property sets. If not, the card's vaue ($3) is banked.
    */
    Card c = new Hotel();
    if (!bankOption(c, p)) {
      if (p.getNoOfSets() == 0) { 
        System.out.println("You cannot play this hotel card because you have no full sets. This hotel card will be added to the bank.");
        p.addToBank(this.value);
      }
        /**
        * The selected full property set gains a Hotel and increases the value. If they input an invalid answer, the system re-asks.
        */
      else{
        System.out.println(p.playedCardsToString() + "\n\nWhich full set would you like to add this hotel to? Specify the colour. Please ensure the color is capitalized and spelled correctly.");
        String colourInput = s.nextLine();
        while (!p.getSetColors().contains(colourInput)) { 
          System.out.println("Invalid colour. Which full set would you like to add this hotel to? Specify the colour. Please ensure the color is capitalized and spelled correctly.");
          colourInput = s.nextLine();
        }
        p.addHotel(colourInput);
      }
    }
  }
  
}