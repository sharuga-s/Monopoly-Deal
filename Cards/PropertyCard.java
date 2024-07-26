import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the Property Card with the attributes String name, String type and int value.
*/

class PropertyCard extends Card {

  public String color;
  public String name;
  public int value;

  /**
  * Constructs a PropertyCard object, inheriting from the Card class
  * @param String color is the colour of the property
  * @param String name is the name of the property
  * @param int value is the value of the property
  */
  public PropertyCard(String color, String name, int value) {
    super(name, "Property Card", value);
    this.color = color;
    this.name = name;
    this.value = value;
  }

  /**
  * @return returns the colour of the property
  */
  public String getColor(){
    return this.color;
  }

  /**
  * Gets the description of the card from the file CardDescriptions.txt
  * @return returns a String explaining the property card function
  */
  @Override
  public String getDescription() {
    return "Description: Each property card requires a number of the same colour properties to complete a full set. This applies to Railroads and Utilities, too.";
  }

  /**
  * Adds the played property card into the player's played cards.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile discardPile){
    Card c = new PropertyCard(this.color, this.name, this.value);
      p.addPlayedCard(c);
    System.out.println(p.getName() + ", you have played " + this.name + ", a " + this.color + " property card. It has been added to your Played Cards.");
  }

  

  
}