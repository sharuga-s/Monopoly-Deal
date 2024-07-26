import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the Rainbow Card with the attributes String name, String type and int value (0). When a player plays this card, they are able to choose a colour that will be added to their owned coloured properties. 
*/
class RainbowCard extends Card {

  Scanner s = new Scanner(System.in);
  public String color;

  /**
  * Constructs a RainbowCard object, inheriting from the Card class
  * @param color is the colour of the property
  */
  public RainbowCard(String color){
    super(color + " Rainbow Card", "Rainbow Card", 0);
    this.color = color;
  }
  
  /**
  * If a player chooses to play RainbowCard, they are able to choose a colour property they'd like to add onto. 
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile discardPile){
    Card c = new RainbowCard("");
      System.out.println(p.getName() + ", what color would you like to apply to your Rainbow Card? Please spell the color correctly and ensure to capitalize.");
      String ans = s.nextLine();
      while (!(ans.equals("Brown") || ans.equals("Red") || ans.equals("Yellow") || ans.equals("Dark Blue") || ans.equals("Orange") || ans.equals("Black") || ans.equals("Utility") || ans.equals("Light Blue") || ans.equals("Pink") || ans.equals("Green"))){
        System.out.println("Invalid input. What color would you like to apply to your Rainbow Card? Please spell the color correctly and ensure to capitalize.");
        ans = s.nextLine();
      }
    /**
    * Adds the selected RainbowCard colour into the played cards
    */
    RainbowCard wc = new RainbowCard(ans);
    p.addPlayedCard(wc);
    System.out.println("A new " + ans + " card has been added to your played cards.");
  }

  /**
  * Gets the Rainbow Card's color
  * @return returns the color of the card
  */
  public String getColor(){
    return this.color;
  }
  
}