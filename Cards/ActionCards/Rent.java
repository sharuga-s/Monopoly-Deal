import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card Rent with the attributes String name, String type and int value. When a player plays this action card, all players must pay the rent value. This card is also bankable for $1
*/

class Rent extends Card {
  public String color; 
  public int rent = 0;

  /**
  * Constructs a Rent object, inheriting from the Card class
  * @param color is the colour of the property
  */
  public Rent(String color) {
    super(color + " Rent", "Action Card", 1);
    this.color = color;
  }

  /**
  * If a player chooses to play Rent, all players owe them the property value.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp) {
    /**
    * Checks if they have properties of the colour, if not, it is banked for $1.
    */
    Card c = new Rent(this.color);
    if (!bankOption(c, p)) {
      if (p.getNoOfColor(this.color) == 0){
        System.out.println("You cannot play the rent card if you do not have any properties of the colour. This rent card has been banked.");
        p.addToBank(1);
      }
        /**
        * If they have a DoubleTheRent action card in their hand AND a DoubleTheRent isn't in their played cards, they are able to use it. 
        */
      else {
        boolean doubleRentPlayed = false;
        for (int i = 0; i < p.getHand().size(); i++) {
          if (p.getHand().get(i).getName().equals("Double The Rent")) {
            i = p.getHand().size();
            System.out.println("You have a Double The Rent card in your hand. Would you like to play it? (Y/N)");
              String ans = s.nextLine();
              while (!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n"))) { 
                System.out.println("Invalid input. You have a Double The Rent card in your hand. Would you like to play it? (Y/N)");
                ans = s.nextLine();
              }
            /**
            * If player uses DoubleTheRent, the effect is applied to the colour property.
            */
              if (ans.equalsIgnoreCase("y")) {
                doubleRentPlayed = true;
              }
            }
          }
          this.rent = returnRent(p, doubleRentPlayed);
        /**
        * Players owe the rent value on their turn.
        */
          System.out.println(p.getName() + " has played the Rent card for colour " + this.color + ". All players must pay rent. The rent is $" + this.rent + ". Click Enter to Continue.");
        s.nextLine();
        System.out.println("\033[H\033[2J");
          for (int i = 0; i < players.length; i++){
            if (!(players[i].getName().equals(p.getName()))){
              System.out.print("It's " + players[i].getName() + "'s turn! You owe " + p.getName() + " $" + this.rent + ". ");
              payOffDebt(p, players[i], players, this.rent, deck, dp);
              if (players[players.length - 1].getName().equals(p.getName())){
                if (i != players.length - 2){
                  System.out.println("\nClick Enter to continue.");
                  s.nextLine();
                  System.out.println("\033[H\033[2J");
                }
              } 
                /**
                * If there is no players, rent resets back to 0??**IDK
                */
              else{
                if (i != players.length - 1){
                  System.out.println("\nClick Enter to continue.");
                  s.nextLine();
                  System.out.println("\033[H\033[2J");
                }
              }
            }
          }
        this.rent = 0; 
        }
      }
  }

  
  /**
  * Calculates the rent amount based on number of properties, the existence of houses or hotels on that colour, and if Double The Rent is played
  * @param Player p is the player who played the Rent card
  * @param boolean doubleRentPlayed represents whether a Double The Rent card has been played. true if so, false otherwise.
  * @return an int representing the amount of rent that would be owed by players
  */
  public int returnRent(Player p, boolean doubleRentPlayed) { 
    this.rent = 0; //Resets rent back to 0
    
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (AllCards.propertyColors[i].equals(this.color)){
        //Increases the rent of the property based on how many rent cards are played
        if (p.getNoOfColor(this.color) == AllCards.propertyNames[i].length){
          if (AllCards.propertyNames[i].length == 1){
            this.rent = AllCards.onePropertyRent[i];
          }
          else if (AllCards.propertyNames[i].length == 2){
            this.rent = AllCards.twoPropertyRent[i];
          }
          else if (AllCards.propertyNames[i].length == 3){
            this.rent = AllCards.threePropertyRent[i];
          }
          else if (AllCards.propertyNames[i].length == 4){
            this.rent = AllCards.fourPropertyRent[i];
          }

          //Increases the rent based on how many hotels/houses are played
          if (p.getNoOfHotels(this.color) == 1){
            this.rent += 4;
          }
          else if (p.getNoOfHouses(this.color) == 1){
            this.rent += 3;
          }
        }
        //Sets the rent based on how many property cards of the colour have been played
        else if (p.getNoOfColor(this.color) == 1){
          this.rent = AllCards.onePropertyRent[i];
        }
        else if (p.getNoOfColor(this.color) == 2){
          this.rent = AllCards.twoPropertyRent[i];
        }
        else if (p.getNoOfColor(this.color) == 3){
          this.rent = AllCards.threePropertyRent[i];
        }
      }
    }

    //If Double The Rent is played, rent is doubled
    if (doubleRentPlayed) { 
      return this.rent * 2;
    }
    return this.rent;
  }

  
  
}

