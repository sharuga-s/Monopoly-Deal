import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Two Coloured Rent. When a player plays this action card, they select one of the two colours to charge rent from all other players for.
*/
class TwoColourRent extends Card {
  String colour1;
  String colour2;
  Scanner s = new Scanner(System.in);

  /**
  * Constructs a TwoColourRent object, inheriting from the Card class
  * @param String colour1 one of the colours of the card
  * @param String colour 2 the other colour of the card
  */
  public TwoColourRent(String colour1, String colour2) {
    super("TwoColourRent", "Action Card", 1); 
    this.colour1 = colour1;
    this.colour2 = colour2;
  }

  /**
  * Since Rent.getDescription() is sufficient, this method is not necessary
  * @return an empty String
  */
  @Override
  public String getDescription() {
    return "This card has two rent colours. You can choose which colour you would like to use as a rent card.";
  }

  /**
  * gets the chosen color of the rent card
  * @return an empty String
  */

  public String[] getColor(){
    String[] arr = new String[2];
    arr[0] = this.colour1;
    arr[1] = this.colour2;
    return arr;
  }

  /**
  * Allows the user to choose which colour, of the card's two colours, they want to use for a rent card
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    Card b = new TwoColourRent(this.colour1, this.colour2);
    if (!bankOption(b, p)){
    System.out.println(p.getName() + ", would you like to play this Rent card as a " + this.colour1 + " Rent Card (1) or as a " + this.colour2 + " Rent Card (2)? Please enter 1 or 2 to signify the colour number.");
    String ans = s.nextLine();
    while (!(ans.equals("1") || ans.equals("2"))){
      System.out.println("Invalid input. Would you like to play this Rent card as a " + this.colour1 + "Card or as a " + this.colour2 + " Card? Please enter 1 or 2 to signify the colour number.");
      ans = s.nextLine();
    }
    Rent c = new Rent(this.colour1);
    if (ans.equals("1")){
       c = new Rent(this.colour1);
     }
  else{
    c = new Rent(this.colour2);
  }
  c.play(p, players, deck, dp);
  }
  }
}