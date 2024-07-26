import java.util.*;
import java.io.*;

/**
  * This class extends from Card and represents the action card It's My Birthday with the attributes String name, String type and int value. When played, all players owe $2 to the player who played the card. This card is also bankable for $2
*/

class ItsMyBirthday extends Card {
  Scanner s = new Scanner(System.in);

  /**
  * Constructs a ItsMyBirthday object, inheriting from the Card class
  */
  public ItsMyBirthday() {
    super("It's My Birthday", "Action Card", 2);
  }

  /**
  * If a player chooses to play ItsMyBirthday, all players owe them $2.
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  @Override
  public void play(Player p, Player[] players, Deck deck, DiscardPile dp){
    /**
    * Players pay their $2 when it is their turn.
    */
    Card c = new ItsMyBirthday();
    if (!bankOption(c, p)){
    System.out.println("It's " + p.getName() + "'s birthday! All players owe " + p.getName() + " $2. Click Enter to Continue!");
    s.nextLine();
    System.out.println("\033[H\033[2J");
    for (int i = 0; i < players.length; i++){
      if (!(players[i].getName().equals(p.getName()))){
      System.out.print("It's " + players[i].getName() + "'s turn! You owe " + p.getName() + " $2. ");
      payOffDebt(p, players[i], players, 2, deck, dp);
        if (players[players.length - 1].getName().equals(p.getName())){
          if (i != players.length - 2){
            System.out.println("\nClick Enter to continue.");
            s.nextLine();
            System.out.println("\033[H\033[2J");
          }
        } 
        else{
          if (i != players.length - 1){
            System.out.println("\nClick Enter to continue.");
            s.nextLine();
            System.out.println("\033[H\033[2J");
          }
        }
      }
    }
    }
  }  

  
}