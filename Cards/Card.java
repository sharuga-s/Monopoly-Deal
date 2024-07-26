import java.util.*;
import java.io.*;

/**
  * This abstract class that can be inherited from has attributes String name, String type, and int value.
*/
abstract class Card {
  protected String name;
  protected String type;
  protected int value;
  Scanner s = new Scanner(System.in);

  /**
  * Constructs a card object consisting of a name, type and value
  * @param String name is the name of the card
  * @param String type is the type of the card
  * @param int value is the value the card holds
  */
  public Card(String name, String type, int value) {
    this.name = name;
    this.type = type;
    this.value = value;
  }

  /**
  * An abstract method that is overriden in the subclasses representing the action of playing a card
  * @param Player p is the player who plays the card
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  abstract void play(Player p, Player[] players, Deck deck, DiscardPile dp);

  /**
  * Gets the name of the card
  * @return returns the name of the card
  */
  public String getName() {
    return this.name;
  }

  /**
  * Gets the type of the card
  * @return returns the type of the card
  */
  public String getType() {
    return this.type;
  }

  /**
  * Gets the value of the card
  * @return returns the value of the card
  */
  public int getValue() {
    return this.value;
  }

  /**
  * Gets the description of the card from the file CardDescriptions.txt
  * @return returns the description of the card
  */
  public String getDescription() throws IOException {
    BufferedReader inputStream = null;
    try {
      inputStream = new BufferedReader(new FileReader("Cards/CardDescriptions.txt"));
      String line;
      while ((line = inputStream.readLine()) != null) {
        if (line.contains("\"" + this.name + "\"")) {
          String[] desc = line.split(":");
          return "Description: " + desc[1];
        }
      }
    }
    catch (IOException e) {
      System.out.println("There was an IOException thrown");
    }
    finally {
      if (inputStream != null) {
        inputStream.close();
      }
    }
    return "";
  }
  
  /**
  * Checks the name of the player
  * @return returns the name of the player if it is correct. If it is inaccurate, the system displays "Invalid input."
  */
  public Player nameChecker(Player p, Player[] players){
    String pName = s.nextLine();
    boolean flag = false;
    Player playa = new Player("", 0);
    if (!(pName.equals(p.getName()))){
      for (int i = 0; i < players.length; i++){
        if (players[i].getName().equals(pName)){
          return players[i];
        }
      }
    }
    while (!flag){
      System.out.println("Invalid input.");
      pName = s.nextLine();
      if (!(pName.equals(p.getName()))){
      for (int i = 0; i < players.length; i++){
        if (players[i].getName().equals(pName)){
          return players[i];
        }
      }
      }
    }
    return playa;
  }
  
  /**
  * Provides the player the option to bank or utilize the played card
  * @param Card c is the card being banked or played
  * @param Player p is the player banking or playing the card
  * @return returns true if the card was banked, false otherwise
  */
  public boolean bankOption(Card c, Player p){
    System.out.println(p.getName() + ", you have played the " + c.getName() + " card. Would you like to bank this card (B) or use it? (U)");
    String ans = s.nextLine();
    while (!(ans.equalsIgnoreCase("U") || ans.equalsIgnoreCase("B"))){
      System.out.println("Invalid input. Would you like to bank this card (B) or use it? (U)");
      ans = s.nextLine();
    }
    if (ans.equalsIgnoreCase("B")){
      p.addToBank(c.getValue());
      System.out.println("You have banked " + c.getName() + ".");
      return true;
    }
    return false;
  }

  /**
  * If the player plays a justSayNo card, it cancels the effect of the action card played on them. After the justSayNo card is played, it is discarded into the discard pile.
  * @param Player p is the player playing the card
  * @param Player[] players is an array containing all of the players
  * @param DiscardPile dp is the discard pile cards are discarded to
  * @return true if the Just Say No card was played, false otherwise
  */
  public boolean justSayNo(Player p, Player[] players, DiscardPile dp){
    JustSayNo jsn = new JustSayNo();
    boolean b = jsn.returnBool(p, players);
    if (b){
      for (int i = 0; i < p.getHand().size(); i++){
      if (p.getHand().get(i).getName().equals("Just Say No")){
        p.addToDP(dp, p.getHand().get(i));
      }
    }
    }
    return b;

  }

  /**
  * Checks if the specified string is an integer
  * @param String str is the string being validated
  * @return true if it is a valid integer, false otherwise
  */
  public static boolean isInt(String str) {
    boolean flag = true;
    //This if statement makes sure that the else-if will not run when the length of the string is 0 and checks if it's an empty string or "-".
    if (str.length() == 0 || str.equals("-")) {
      flag = false;
    }
    //This else if statement checks if the first character is not between 0-9 and it's not a minus symbol.
    else if (!(str.charAt(0) >= 48 && str.charAt(0) <= 57) && str.charAt(0) != '-') {
      flag = false;
    }
      //This loop goes through each character (except the first) in the string to check if it's between 0-9.
    for (int i = 1; i < str.length(); i++) {
      if (!(str.charAt(i) >= 48 && str.charAt(i) <= 57)) {
        flag = false;
      }
    }
    return flag;  
  }

  /**
  * This method allows for players to pay their owed debt
  * @param Player p is the player collecting the debt
  * @param Player player is the player paying the debt
  * @param Player[] players is an array containing all of the players
  * @param int amount is the amount of debt being paid
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  */
  public void payOffDebt(Player p, Player player, Player[] players, int amount, Deck deck, DiscardPile dp){
    System.out.println("Click Enter to pay up!");
    s.nextLine();
    /**
    * If a player plays justSayNo, they do not need to pay their debt owed
    */
    boolean b = justSayNo(player, players, dp);
    boolean paid = false;
    String ans = "";
    if (!b){
    //* If the has enough in their bank to pay off their debt, provide the option to pay the debt with the money in their bank
    /**
    */
      if (player.getTotalBank() >= amount && player.getPlayedCards().size() > 0){
        System.out.println("Would you like pay your debt with your bank? (Y/N)");
        ans = s.nextLine();
      while (!(ans.equalsIgnoreCase("Y") || ans.equalsIgnoreCase("N") || ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("No"))){
        System.out.println("Invalid input. Please enter Yes or No.");
        ans = s.nextLine();
      }
        if (ans.equalsIgnoreCase("Yes") || ans.equalsIgnoreCase("Y")){
          int i = payWithBank(p, player, players, amount, dp);
          if (i <= 0){
            amount -= i;
            paid = true;
          }
      }
      }
      if (!paid){
      /**
      * If the player doesn't have any played cards, and their bank is empty, they can't pay off the debt
      */
        if (player.getPlayedCards().size() == 0){
          if (player.getTotalBank() == 0){
            System.out.println(player.getName() + " has no played cards nor any money in their bank. They cannot pay off debt.");
          }
            /**
            * If they have no played cards, they must pay from their bank
            */
          else {
            if (player.getTotalBank() >= amount){
              System.out.println("You do not have any played cards, so you must pay from your bank.");
              s.nextLine();
              amount -= payWithBank(p, player, players, amount, dp);

            }
            else{
              System.out.println("You have no played properties to mortgage. There was a total of $" + player.getTotalBank() + " in your bank. It has been emptied.");
              for (int i = 0; i < player.getBank().size(); i++){
                p.addToBank(player.getBank().get(i));
                player.removeFromBank(player.getBank().get(i));
              }
            }
          }

        }

        else if (player.getPlayedCards().size() == 1){ 
          /**
          * If the only played card is a rainbow card, they cannot pay off the debt.
          */
          if (player.getPlayedCards().get(0).getType().equals("Rainbow Card")){
            System.out.println("You cannot pay off your debt, as you only have a rainbow card in your played cards.");
          }
            /**
            * If their bank is empty, their card must be handed over to cover the debt
            */
          else{
          if (player.getTotalBank() == 0){
            System.out.println(player.getName() + ", your bank does not have enough money to pay off $" + amount + ". Your only played card, " + player.getPlayedCards().get(0).getName() + ", has been handed over to " + p.getName() + ".");    
          Card c = player.getPlayedCards().get(0);
          player.removePlayedCard(c);
          p.addPlayedCard(c);
          
          }
            /**
            * The card/value is given to the player who the player owed debt to
            */
          else{
            Card c = player.getPlayedCards().get(0);
            System.out.println("Your one played card, " + c.getName() + ", was worth $" + c.getValue() + ". It has been mortgaged, and handed over to " + p.getName() + ".");
            player.removePlayedCard(c);
            p.addPlayedCard(c);
            amount = amount - c.getValue();
            /**
            * If the player still has money left to pay, they must pay from their bank
            */
            if (amount > 0){
              System.out.println("\nYou still owe $" + (amount) + " to " + p.getName() + ". Please pay from your bank.\n");
              payWithBank(p, player, players, amount, dp);
          }
          }
        
          }
        }
    
        else if (player.getPlayedCards().size() > 1){
          int j = payWithProperties(p, player, players, amount, deck, dp);
          amount -= j;
          if (player.getTotalBank() > 0 && amount > 0){
            System.out.println("\nYou still owe $" + (amount ) + " to " + p.getName() + ". Please pay from your bank.\n");
            s.nextLine();
            payWithBank(p, player, players, j, dp);
            
          }
        }
      }
      
    System.out.println("\nYou are done responding to this action card.");
    }
  }

  /**
  * Provides the player the option to pay off their debt with properties
  * @param Player p is the player collecting the debt
  * @param Player player is the player paying the debt
  * @param Player[] players is an array containing all of the players
  * @param int amount is the amount of debt being paid
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  * @return an int representing the value of player's properties
  */
  public int payWithProperties(Player p, Player player, Player[] players, int amount, Deck deck, DiscardPile dp){
    boolean done = false;
      int num;
      int val = 0;
      ArrayList<Integer> usedNumbers = new ArrayList<Integer>();
      PropertyCard[] cards = new PropertyCard[1];
      String ans = "";
    /**
    * Checks for new cards every loop and clear the scanner if the loop runs more than once
    */
      while (!done){
        usedNumbers.clear();
        System.out.println(player.playedCardsToString() + "\n\n" + player.getName() + ", how many cards would you like to use to pay off your debt?");
        if (val != 0){
          s.nextLine();
          val = 0;
        }
        /**
        * Input check to make sure that the user enters a number that is less than the number of cards in their playedCards
        */
        ans = s.nextLine();
        while (!isInt(ans) || Integer.parseInt(ans) > player.getPlayedCards().size()){
          System.out.println("Invalid input. How many cards would you like to use to pay off your debt?");
          ans = s.nextLine();
        }
        num = Integer.parseInt(ans);
        //create a Card[] to keep all of the cards that the player chooses to get rid of
        cards = new PropertyCard[num];
        for (int k = 0; k < num; k++){
          System.out.println("Please enter the number of the card(s) you would like to use.");
          int cardNum = s.nextInt();
          while (usedNumbers.contains(cardNum) || cardNum > player.getPlayedCards().size()){
            //reprompt if the card number was already chosen (e.g. you can't choose the same card to mortgage twice) OR if the card number is greater than the number of cards in the player's playedCards
            System.out.println("Invalid input. Please enter the number of a different card you would like to use.");
            cardNum = s.nextInt();
          }
          //add the card to the Card[], increase value
            PropertyCard c = (PropertyCard)player.getPlayedCards().get(cardNum - 1);
          usedNumbers.add(cardNum);
          cards[k] = c;
          val += c.getValue();
        }
        //if the card(s) sum of values exceeds/equals the amount owed, break out of the loop
          if (val >= amount || num == player.getPlayedCards().size()){
            done = true;
          }
            //if the player still owes some money, run the wheel again
        else{
          System.out.println("Invalid amount.\n");

        }
        }
        for (int m = 0; m < cards.length; m++){
            for (int i = 0; i < AllCards.propertyColors.length; i++){
              if (AllCards.propertyColors[i].equals(cards[m].getColor())){
                //Increases the rent of the property based on how many rent cards are played
                if (player.getNoOfColor(cards[m].getColor()) == AllCards.propertyNames[i].length){
                  if (player.getNoOfHouses(cards[m].getColor()) == 1){
                    player.removeHouse(cards[m].getColor());
                    p.addHouse(cards[m].getColor());
                  }
                  else if (player.getNoOfHotels(cards[m].getColor()) == 1){
                    player.removeHotel(cards[m].getColor());
                    p.addHotel(cards[m].getColor());
                  }
                }
              }
            }
          player.removePlayedCard(cards[m]);
          p.addPlayedCard(cards[m]);
          Card c = cards[m];
          changeRainbow(c, p, players, deck, dp);
      }
    return val;
    }

  /**
  * Provides the player to pay their owed debt with the bank
  * @param Player p is the player collecting the debt
  * @param Player player is the player paying the debt
  * @param Player[] players is an array containing all of the players
  * @param int amount is the amount of debt being paid
  * @param Deck deck is the deck cards are drawn from
  * @param DiscardPile dp is the discard pile cards are discarded to
  * @return an int representing the value of player's paid money cards
  */
    public int payWithBank(Player p, Player player, Player[] players, int amount, DiscardPile dp){
      boolean done = false;
      int num;
      int val = 0;
      ArrayList<Integer> usedNumbers = new ArrayList<Integer>();
      MoneyCard[] cards = new MoneyCard[1];
      String ans = "";
     
      while (!done){
        //check for new cards every loop
        usedNumbers.clear();
        
        System.out.println(player.bankToString() + "\n\n" + player.getName() + ", how many cards from your bank would you like to use to pay off your debt?");
        //clear the scanner if the loop runs more than once
        if (val != 0){
          s.nextLine();
          val = 0;
        }
        ans = s.nextLine();
        while (!isInt(ans) || Integer.parseInt(ans) > player.getBank().size() || Integer.parseInt(ans) <= 0){
          //input check to make sure that the user enters a number that is less than the number of cards in their playedCards
          System.out.println("Invalid input. How many cards would you like to use to pay off your debt?");
          ans = s.nextLine();
        }
        num = Integer.parseInt(ans);
        //create a Card[] to keep all of the cards that the player chooses to get rid of
        cards = new MoneyCard[num];
        for (int k = 0; k < num; k++){
          System.out.println("Please enter the number of the card(s) you would like to use.");
          int cardNum = s.nextInt();
          while (usedNumbers.contains(cardNum) || cardNum > player.getBank().size()){
            //reprompt if the card number was already chosen (e.g. you can't choose the same card to mortgage twice) OR if the card number is greater than the number of cards in the player's playedCards
            System.out.println("Invalid input. Please enter the number of a different card you would like to use.");
            cardNum = s.nextInt();
          }
          //add the card to the Card[], increase value
          MoneyCard c = player.getBank().get(cardNum - 1);
          usedNumbers.add(cardNum);
          cards[k] = c;
          val += c.getValue();
        }
        //if the card(s) sum of values exceeds/equals the amount owed, break out of the loop
          if (val >= amount || num == player.getBank().size()){
            // System.out.println
            amount -= val;
            done = true;
          }
            //if the player still owes some money, run the wheel again
        else{
          System.out.println("Invalid amount.\n");

        }
        }
      for (int i = 0; i < cards.length; i++){
        
        player.removeFromBank(cards[i]);
        p.addToBank(cards[i]);
      }
      return amount;
    }

  /**
  * Allows the player to change the color of the Rainbow Card
  * @param Card c is the card being played 
  * @param Player p is the player collecting the debt
  * @param Players[] players is an array containing all of the players
  * @param Deck deck is the deck cards are drawn from
  */
  public void changeRainbow(Card c, Player p, Player[] players, Deck deck, DiscardPile dp){
    System.out.println(c.getType());
    //If the card is a rainbow card, its color can be changed
    if (c.getType().equals("Rainbow Card")){
      RainbowCard rc = (RainbowCard)c;
      System.out.println("The Rainbow Card color is currently " + rc.getColor() + ". Would you like to change it? (Y/N)");
      s.nextLine();
      String ans = s.nextLine();
      while (!(ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("n") || ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("no"))){
        System.out.println("Invalid input. The Rainbow Card color is currently " + rc.getColor() + ". Would you like to change it? (Y/N)");
        ans = s.nextLine();
      }
      //If the player chooses to change the color, playCard() for a rainbow card is run
      if (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes")){
        p.playCard(rc, players, deck, dp);
        for (int i = 0; i < p.getPlayedCards().size(); i++){
          if (p.getPlayedCards().get(i).getName().equals(c.getName())){
            p.removePlayedCard(p.getPlayedCards().get(i));
              i = p.getPlayedCards().size();
          }
        }
      }
    }
  }

}