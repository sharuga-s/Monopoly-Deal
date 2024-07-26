import java.util.*;

/**
  * This class contains a player's information, including their name, age, hand, played cards, number of sets, number of properties per color, color of any full sets, number of hotels and number of houses.
*/
class Player{
  private String name;
  private int age;
  private ArrayList<Card> hand = new ArrayList<Card>();
  private ArrayList<Card> playedCards = new ArrayList<Card>();
  public ArrayList<String> setColors = new ArrayList<String>(); //all colours with full sets
  
  //noOfEachColor, setHouses and setHotels are parallel arrays representing the number of cards of that color, and the number of houses and hotels associated with that color
  public int[] noOfEachColor = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};  
  public int[] setHouses = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public int[] setHotels = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
  public int sets = 0;
  private ArrayList<MoneyCard> bank = new ArrayList<MoneyCard>(); 
  Scanner s = new Scanner(System.in);

  /**
  * Constructs a Player object given its name and age
  * @param String name the player's name
  * @param int age the player's age
  */
  
  public Player(String name, int age) {
    this.name = name;
    this.age = age;
  }

  /**
  * Gets the player's age
  * @return returns the player's age
  */
  public int getAge() {
    return this.age;
  }

  /**
  * Gets the player's name
  * @return returns the player's name
  */
  public String getName(){
    return this.name;
  }

  /**
  * Gets the player's bank
  * @return returns the player's bank
  */
  public ArrayList<MoneyCard> getBank(){
    return this.bank;
  }

  /**
  * Gets the player's hand
  * @return returns the hand of the player
  */
  public ArrayList<Card> getHand(){
    return this.hand;
  }
  
  /**
  * Gets the number of cards in the player's hand
  * @return returns the size of the player's hand
  */
  public int getHandSize(){
    return this.hand.size();
  }

  /**
  * Gets the cards the player has played
  * @return returns the played cards of the player
  */
  public ArrayList<Card> getPlayedCards(){
    return this.playedCards;
  }


  /**
  * Gets the total value of the cards in the player's bank
  * @return returns the total bank of the player
  */
  
  public int getTotalBank(){
    int b = 0;
    for (int i = 0; i < this.bank.size(); i++){
      b += this.bank.get(i).getValue();
    }
    return b;
  }

  /**
  * Removes the specified Money card from the player's bank
  * @param MoneyCard mc is a MoneyCard that is to be removed from the player's bank
  */
  public void removeFromBank(MoneyCard mc){
    for (int i = 0; i < this.bank.size(); i++){
      if (this.bank.get(i) == mc){
        this.bank.remove(i);
      }
    }
  }

  /**
  * Removes the money card associated with that value from the player's bank
  * @param int amount is the value of the MoneyCard that is removed from the player's bank
  */
  public void removeFromBank(int amount){
    for (int i = 0; i < this.bank.size(); i++){
      if (this.bank.get(i).getValue() == amount){
        this.bank.remove(i);
      }
    }
  }

  /**
  * Adds the Money Card to the player's bank
  * @param mc the Money Card added to the player's bank
  */
  public void addToBank(MoneyCard mc){
    this.bank.add(mc);
  }
  
  /**
  * Adds the monetary value, as a MoneyCard, to the player's bank
  * @param int amount is the value of the MoneyCard that is added to the player's bank
  */
  public void addToBank(int amount){
    MoneyCard mc = new MoneyCard(amount);
    this.bank.add(mc);
  }


  /**
  * Format's the cards in the player's bank as a String
  @return returns the String of the player's bank cards
  */
  public String bankToString(){
    // If the player's bank is empty, creates a string in the format of name + ", your bank is empty." 
    if (this.bank.size() == 0){
      return this.name + ", your bank is empty.";
    }
    // If the player has bank cards, creates a string in the format of name + "'s Bank Cards are:\n"
    String str = this.name + "'s Bank Cards are:\n";
    for (int i = 0; i < this.bank.size(); i++){
      str += (i + 1) + ". $" + this.bank.get(i).getValue();
      if (i != this.bank.size() - 1){
        str += "\n";
      }
    }
    return str;
  }

  /**
  * Removes a card from the player's played cards
  * @param Card c is the card that is to be removed from the played cards
  */
  public void removePlayedCard(Card c){
    this.playedCards.remove(c);
  }

  /**
  * Adds a card to the played cards
  * @param Card c is the card that is added to the played cards
  */
  public void addPlayedCard(Card c){
    this.playedCards.add(c);
  }

  /**
  * Adds cards to the discard pile
  * @param DiscardPile dp is the discard pile the card is discarded to
  * @param Card c is the card that is discarded
  */
  public void addToDP(DiscardPile dp, Card c){
    dp.addCard(c);
    this.hand.remove(c);
  }
  
  /**
  * Plays the selected card. The card is removed from the hand and discarded to the discard pile. 
  * @param Card c is the card that is being played
  * @param Player[] players is an array of all of the players
  * @param Deck deck is the deck of cards that is drawn from
  * @param DiscardPile dp is the discard pile that cards are discarded to
  */
  public void playCard(Card c, Player[] players, Deck deck, DiscardPile dp){
    dp.addCard(c);
    for (int i = 0; i < players.length; i++){
      if (players[i].getName().equals(this.name)){
        //Plays each card's individual play() method
        c.play(players[i], players, deck, dp);
      }
    }
    this.hand.remove(c);
    System.out.println("\nClick Enter to continue.");
    s.nextLine();
    System.out.println("\033[H\033[2J"); 
  }

  /**
  * Adds a card to the hand
  * @param Card c is the card that is added to the player's hand
  */
  public void addToHand(Card c){
    this.hand.add(c);
  }

  /**
  * Removes a card from the hand
  * @param Card c is the card that is removed from the player's hand
  */
  public void removeFromHand(Card c){
    this.hand.remove(c);
  }

  /**
  * Draws a card and adds it to the player's hand
  * @param Deck deck is the deck that is drawn from
  */
  public void drawToHand(Deck deck){
    this.hand.add(deck.draw());
  }

  /**
  * Checks if the player has any full property sets. It updates noOfEachColor, sets and setColors accordingly.
  */
  
  public void checkForSets() {
    for (int i = 0; i < this.noOfEachColor.length; i++){
      this.noOfEachColor[i] = 0;
    }
    this.setColors.clear();
    this.sets = 0;
    //reset the set-associated variables, to re-check number of sets
    
    for (int i = 0; i < this.playedCards.size(); i++){
      if (this.playedCards.get(i) instanceof PropertyCard){
        PropertyCard pc = (PropertyCard)this.playedCards.get(i);
        String color = pc.getColor();
        for (int j = 0; j < AllCards.propertyColors.length; j++){
          if (color.equals(AllCards.propertyColors[j])){
            this.noOfEachColor[j] += 1;
            j = AllCards.propertyColors.length;
          }
        }
      }
      else if (this.playedCards.get(i) instanceof RainbowCard){
        RainbowCard pc = (RainbowCard)this.playedCards.get(i);
        String color = pc.getColor();
        for (int j = 0; j < AllCards.propertyColors.length; j++){
          if (color.equals(AllCards.propertyColors[j])){
            this.noOfEachColor[j] += 1;
            j = AllCards.propertyColors.length;
          }
        }
      }
    }

    for (int k = 0; k < this.noOfEachColor.length; k++){
      if (this.noOfEachColor[k] == AllCards.propertyNames[k].length){
        setColors.add(AllCards.propertyColors[k]);
        sets++;
      }
    }
    
  }

  /**
  * Gets the colours of the full sets the player has
  * @return the coloured sets
  */
  public ArrayList<String> getSetColors(){
    checkForSets();
    return this.setColors;
  }

  /**
  * Gets the number of full sets the player has in their played cards
  * @return the amount of sets the player has in their played cards
  */
  
  public int getNoOfSets(){
    checkForSets();
    return this.sets;
  }

  /**
  * Gets the number of property colours the player has
  * @param String c is the colour that is being checked to determine the number of those cards the player has
  * @return returns the number of property cards of that color in the player's played cards
  */
  public int getNoOfColor(String c){
    checkForSets();
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (c.equals(AllCards.propertyColors[i]))  {
        return this.noOfEachColor[i];
      }  
    }
    return 0;
  }

  /**
  * Gets the number of houses on a specified colour
  * @param String c is the colour being checked to see if it has a house
  * @return returns the number of houses the player owns,
  */
  public int getNoOfHouses(String c){
    checkForSets();
    for (int i = 0; i < setHouses.length; i++){
      if (c.equals(AllCards.propertyColors[i]))  {
        return this.setHouses[i];
      }  
    }
    return 0;
  }

  /**
  * Gets the number of hotels on a specified colour
  * @param String c is the colour being checked to see if it has a hotel
  * @return returns the number of hotels the player owns
  */
  public int getNoOfHotels(String c){
    checkForSets();
    for (int i = 0; i < setHotels.length; i++){
      if (c.equals(AllCards.propertyColors[i]))  {
        return this.setHotels[i];
      }  
   
    }
    return 0;
  }

  /**
  * Adds a house onto the a full property set. Only one house can be applied.
  * @param String colour is the color the house is added onto
  */
  public void addHouse(String color){
    checkForSets();
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (AllCards.propertyColors[i].equals(color)){
        if (this.setHouses[i] == 1){
          // the player cannot try to add more than one house onto a property set
          System.out.println("You can only put one house on a property set. This set already has a house. This House card has been added to your bank.");
          addToBank(3);
      }
          /**
            * If they are able to add houses, add them onto their set.
            */
        else{
          this.setHouses[i] = this.setHouses[i] + 1;
          System.out.println("A house has been added to your " + color + " set.");
        }
      }
    }
  }

  /**
  * Adds a hotel onto the a full property set. Only one hotel can be applied per full set.
  * @param String color is the color the hotel is added onto
  */
  public void addHotel(String color){
    checkForSets();
    boolean flag = false;
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (AllCards.propertyColors[i].equals(color)){
        if (this.setHouses[i] > 0 && this.setColors.contains(color)){
        flag = true;
        }
      }
    }
    /**
    * If they are able to add hotels, add them onto their set.
    */
    if (flag){
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (AllCards.propertyColors[i].equals(color)){
      this.setHotels[i] = this.setHotels[i] + 1;
      }
    }
      System.out.println("You have placed a hotel on " + color + ".");
    }
      /**
      * The value of the hotel (3) is added to the bank if the set does not have a house
      */
    else {
      System.out.println("You cannot put a hotel on a set that does not have a house. This hotel card has been added to the bank.");
      addToBank(4);
    }
  }

  /**
  * Removes the house from a full property set. This is required when full sets with houes are taken.
  * @param color the color of the full set the house is removed from
  */
  public void removeHouse(String color){
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (AllCards.propertyColors[i].equals(color)){
        this.setHouses[i] = 0;
      }
    }
  }
  
  /**
  * Removes the hotel from a full property set.This is required when full sets with hotels are taken.
  * @param color the color of the full set the hotel is removed from
  */
  public void removeHotel(String color){
    for (int i = 0; i < AllCards.propertyColors.length; i++){
      if (AllCards.propertyColors[i].equals(color)){
        this.setHotels[i] = 0;
      }
    }
  }
  
  /**
  * Formats the cards in the player's hand as a String
  * @return returns a String with the player's hand cards
  */
  public String handToString() { 
    String str = this.name + "'s Hand's Cards are:\n";
    return toString(this.hand, str);
  }

  /**
  * Formats the cards in the player's played cards as a String
  * @return returns a String with the player's played cards 
  */
  public String playedCardsToString() {
    // If the player has no played cards, creates a string with the name of the player in the format of name + " has no played cards." 
    if (this.playedCards.size() == 0){
      return this.name + " has no played cards.";
    }
    String str = this.name + "'s Played Cards are:\n";
    return toString(this.playedCards, str);
  }

  /**
  * Formats arraylist of cards as a String by numbering the cards
  * @param ArrayList<Card> arr is the arraylist of cards that is being formatted
  * @param String str is the string that the cards are being added to
  * @return returns a String with the cards in the arraylist
  */
  
  public String toString(ArrayList<Card> arr, String str){
    int value = 0;
    for (int i = 0; i < arr.size(); i++) {
      str += (i + 1) + ". ";
      str += arr.get(i).getName() + " (";
      if (arr.get(i) instanceof PropertyCard){
        PropertyCard pc = (PropertyCard)arr.get(i);
        str += pc.getColor() + " ";
      }
      else if (arr.get(i) instanceof TwoColourRent){
        TwoColourRent r = (TwoColourRent)arr.get(i);
        str += r.getColor()[0] + " and " + r.getColor()[1] + " ";
      }
      str += arr.get(i).getType() + "), Value: " + arr.get(i).getValue();
      if (i != arr.size() - 1){
        str += "\n";
      }
    }
    return str;
  }
  
}