import java.util.*;
import java.io.*;
import java.util.Scanner;

/**
* File: Monopoly Deal
* Authors: Alyssa Baichulall, Sharuga Suthakaran, Vivian Shim, Amy Wang
* Date Created: December 27, 2023
* Date last modified: January 17, 2023
*/

public class Main {
  //COLOURS
  public static final String RESET = "\u001B[0m"; // to reset font/print
  public static final String BROWN = "\033[1;33m"; //brown
  public static final String DARKBLUE = "\033[1;34m"; //dark blue
  public static final String GREEN = "\033[38;5;82;1m"; //green
  public static final String LIGHTBLUE = "\033[1;94m"; //light blue
  public static final String ORANGE = "\033[38;2;255;165;1m"; //after years of experimenting and searching i made orange !
  public static final String PINK = "\033[38;5;206;1m"; //pink
  public static final String BLACK = "\033[1;30m"; //as black as its gonna get
  public static final String RED = "\033[38;5;196;1m"; //red
  public static final String GREY = "\033[38;5;252;1m"; //grey
  public static final String YELLOW = "\033[38;5;226;1m";; //yellowx
  
  public static void main(String[] args) throws IOException {

    Scanner s = new Scanner(System.in);
        
    System.out.println(BLACK+"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n" + BROWN + "W"+DARKBLUE+"E"+GREEN+"L"+LIGHTBLUE+"C"+ORANGE+"O"+PINK+"M"+RED+"E"+RESET+" TO " + RED+"M"+ORANGE+"O"+YELLOW+"N"+GREEN+"O"+LIGHTBLUE+"P"+DARKBLUE+"O"+PINK+"L"+BROWN+"Y"+GREY+" DEAL"+BLACK+"!!!" + "\n\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n" + RESET);
    
    // //instructions
    System.out.println(LIGHTBLUE+"The Goal of the Game"+RESET+"\n");
    System.out.println("The purpose of Monopoly DEAL is to collect 3 property sets before anyone else. Stop your opponents from stealing from you with "+RED+"bank"+RESET+", make people pay your "+YELLOW+"rent"+RESET+" and "+PINK+"birthday money"+RESET+", put down "+DARKBLUE+"properties"+RESET+" or even "+GREEN+"swap/steal"+RESET+" properties using different ACTION CARDS");
    System.out.println("\n\n"+BLACK+"Press "+DARKBLUE+"ENTER"+BLACK+" to see rest of the rules and the Action Card descriptions, then play the game!"+ORANGE+" Have fun!"+RESET);
    
    s.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println(LIGHTBLUE+"General Rules\n"+RESET+"- 2-5 players\n- Each player starts with 5 cards\n- Pick up 2 cards at the start of each turn\n- Can only play up to 3 cards max per turn, but can skip turn or only play1/2 if you do so desire\n- Cards can NEVER go back into a players hand after being played\n- If you have more than 7 cards at the end of your turn, you must discard enough to only have 7. They cannot be used during this time\n- If you have 0 cards in your hand at the start of your turn, 5 cards are picked up instead of 3\n- The game runs from youngest to oldest"+"\n");
    s.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println(LIGHTBLUE+"Bank & Pay Rules\n"+RESET+"- Each card has a monetary value (except the rainbow card), and can be played as money in your bank\n- Putting money in your bank counts as a turn\n- You cannot pay with cards in your hand\n- Change is NOT GIVEN (say you need to pay $2 and only have $3, you don't get money back\n- You can pay with properties or from your bank, your choice!\n- When paying with property cards, they go to the other players Property Collection (playedcards), not their hand or bank\n- If an action card was used as money and paid with, it must go into the bank and is redundant as an action card for the rest of the game\n- If you have no cards in your collection (or only the rainbow card), you don't have to pay at all!"+"\n");
    s.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.println(LIGHTBLUE+"Property Rules\n"+RESET+"Each property card tells you how many cards you need of that color to complete the set\n- Once a player achieves 3 full sets of properties, they are declared the"+GREEN+" WINNER!"+RESET+"\n- You can add one house onto a FULL SET property to increase rent, then you can add one hotel\n- Rainbow Card can be used at anytime, however to charge rent, it must be paired with a matched colour, since its rent has no value\n- Players are able to steal a Rainbow Card as long as it is not part of a FULL set\n- If you have played colour cards than the required amount to create a full set, you are able to make a new set with the same colour\n-A house is required to be played on a COMPLETED property set for hotels to be played"+"\n");
    
    //FILE-READING from CardDescriptions.txt to output the descriptions of the cards.
    s.nextLine();
    System.out.print("\033[H\033[2J");
    
    System.out.println(LIGHTBLUE+"Action Card Descriptions"+RESET+"\n");
    BufferedReader inputStream = null;
    String str = "";
    try {
      inputStream = new BufferedReader(new FileReader("Cards/CardDescriptions.txt"));
      String line;
      while ((line = inputStream.readLine()) != null) {
        String[] desc = line.split(":");
        str += "- " + desc[0].substring(1, desc[0].length() - 1) + ": " + desc[1] + "\n";
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
    System.out.println(str + "\n");

    //# OF PLAYERS    
    s.nextLine();
    System.out.println("\033[H\033[2J");
    System.out.print(BLACK+"*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n"+BROWN+"Enter the number of players "+BLACK+"(Remember, only 2-5 players permitted): "+RESET);
    String noOfPlayers = s.nextLine();
    while (!isInt(noOfPlayers) || Integer.parseInt(noOfPlayers) < 2 || Integer.parseInt(noOfPlayers) > 5) { //input checking to verify that noOfPlayers is an int >= 2
      System.out.print("Invalid input. Enter the number of players: ");
      noOfPlayers = s.nextLine();
    }


    Player[] players = new Player[Integer.parseInt(noOfPlayers)]; //array of all the players
    //Players are asked to input their name and age, and input is validated
    ArrayList<String> names = new ArrayList<String>();
    for (int i = 0; i < Integer.parseInt(noOfPlayers); i++) {
      System.out.print("Enter player " + (i + 1) + "'s name: ");
      String name = s.nextLine();
      while(names.contains(name) || name.equals("")){
        System.out.println("\nInvalid. Either that name is already taken, or it is an empty String. Please enter a different name.");
        name = s.nextLine();
      }
      names.add(name);
      System.out.print("Enter player " + (i + 1) + "'s age: ");
      String age = s.nextLine();
      while (!isInt(age) || Integer.parseInt(age) < 0) {
        System.out.print("Invalid input. Enter player " + (i + 1) + "'s age: ");
        age = s.nextLine();
      }
      players[i] = new Player(name, Integer.parseInt(age));
    }

    //Players are sorted by age using bubble sort
    for(int j = 0; j < players.length; j++) { 
      boolean sorted = true;
      for (int i = 0; i < players.length - 1 - j; i++) {
        if (players[i].getAge() > players[i+1].getAge()) {
          Player temp = players[i];
          players[i] = players[i+1];
          players[i+1] = temp;
          sorted = false;
        }
      }
      if (sorted) {
        j = players.length; //stop the loop
      }
    }

    Deck deck = new Deck();
    DiscardPile discardPile = new DiscardPile();
    for (int j = 0; j < players.length; j++){
      for (int i = 0; i < 5; i++){
        players[j].addToHand(deck.draw());
      }
    }

    //PRINTING THE GAME
    boolean flag = false;
  
    while (!flag){
      System.out.println("\nPress "+DARKBLUE+"ENTER"+RESET+" to start playing!");
      s.nextLine();
      System.out.println("\033[H\033[2J"); 
      String ans = "";
      for (int i = 0; i < players.length; i++){
        if (players[i].getNoOfSets() == 3){
          flag = true;
        }
        else{
          for (int j = 0; j < 3; j++){
            if (deck.size() == 0){
              deck.reshuffle(discardPile.empty());
            }
            System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n\n"+"It's " + players[i].getName() + "'s turn! Turn # " + (j + 1) + ".\n");
            if (players[i].getHand().size() == 0){
              for (int f = 0; f < 5; f++){
                players[i].addToHand(deck.draw());
              }
            }
            if (j == 0){
              players[i].addToHand(deck.draw());
              players[i].addToHand(deck.draw());
            }
            System.out.println("Would you like to play this turn, or would you like to skip? (P/S)");
            ans = s.nextLine();
            while (!(ans.equalsIgnoreCase("p") || ans.equalsIgnoreCase("play") | ans.equalsIgnoreCase("s") || ans.equalsIgnoreCase("skip"))){
              System.out.println("Invalid input. Would you like to play this turn, or would you like to skip? (P/S)");
              ans = s.nextLine();
            }
            if (ans.equalsIgnoreCase("p") || ans.equalsIgnoreCase("play")){
              System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
                for (int z = 0; z < players.length; z++){
                  if (!players[i].getName().equals(players[z].getName())){
                    
                    System.out.println(players[z].playedCardsToString() + "\n");
                  }
                }
              System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
                System.out.println(players[i].handToString() + "\n\n" + players[i].playedCardsToString());
                System.out.println("\nWhich card would you like to play from your hand? Please indicate its number.");
                ans = s.nextLine();
                  while (!isInt(ans) || Integer.parseInt(ans) > players[i].getHand().size() || Integer.parseInt(ans) <= 0){
                    System.out.println("Invalid input. Which card would you like to play from your hand? Please indicate its number.");
                    ans = s.nextLine();
                  }
                Card playCard = players[i].getHand().get(Integer.parseInt(ans)-1);
                System.out.println(playCard.getDescription());
                System.out.println();
                players[i].playCard(playCard, players, deck, discardPile);
              }
              else {
                
                System.out.println("You have skipped your turn. Click Enter to continue.");
                s.nextLine();
                System.out.println("\033[H\033[2J"); 
              }
          }
          
          System.out.println(players[i].playedCardsToString());
          if (players[i].getHand().size() > 7){
            System.out.println(players[i].getName() + ", your hand has too many cards. There can only be 7 cards in your hand at once. Your last three cards will be discarded. Click Enter to continue.");
            for (int m = 0; m < players[i].getHand().size(); m++){
              players[i].addToDP(discardPile, players[i].getHand().get(m));
              players[i].removeFromHand(players[i].getHand().get(m));
            }
          }


          System.out.println("\nClick Enter to end your three turns.");
          s.nextLine();
          System.out.println("\033[H\033[2J"); 
        }
      }
        
      }
    ArrayList<Player> arr = new ArrayList<Player>();
    for (int i = 0; i < players.length; i++){
      if (players[i].getNoOfSets() == 3){
        arr.add(players[i]);
      }
    }
    if (arr.size() == 1){
      System.out.println("Congratulations, " + arr.get(0).getName() + "! You have won the game!");
    }
    else{
      System.out.println("It's a tie between " + arr.size() + " players!");
      for (int i = 0; i < arr.size(); i++){
        System.out.println("Cogratulations, " + arr.get(i).getName() + "! You are one of the winners!");
      }
    }
  }

  
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
}
    

  //TESTING TESTINHG 123
  // TwoColourRent r = new TwoColourRent("Pink", "Brown");
  // Player c = new Player("a", 10);
  // DoubleTheRent g = new DoubleTheRent();
  
  // Scanner s = new Scanner(System.in);
  // Deck deck = new Deck();
  // DiscardPile discardPile = new DiscardPile();


  //   // System.out.println(e.getNoOfSets());
    
   //   // d.addToHand(va);
  //   // d.playCard(d, va, players);
  //   // e.addToHand(aa);
  //   // d.playCard(d, aa, players);
  //   // d.addToHand(p);
  //    c.playCard(bday, players, deck, discardPile);
  //   c.playCard(rent, players, deck, discardPile);
  //   // System.out.println(e.getPlayedCards().size());
  //   // System.out.println(d.getPlayedCards().size());
  //   // e.addToHand(debtcollecter);
  //   // c.addToHand(forcedDeal);
  //   // c.addToHand(slyDeal);
  //   // c.playCard(mc, players, deck, discardPile);
  //   System.out.println(c.bankToString());
  //   // c.playCard(dc, players);
  //   // c.playCard(c, forcedDeal, players);
  //   // System.out.println(d.getNoOfSets());
  //   // System.out.println(e.getNoOfSets());
  //   // for (int i = 0; i < deck.size(); i++){
  //   //   System.out.println(deck.draw().getName());
  //   // }
  //   // System.out.println(deck.size());
  //   // System.out.println(e.getPlayedCards().size());
  //   // System.out.println(e.getPlayedCards().get(0).getName());
  //   // System.out.println(e.playedCardsToString());
  //   // c.addToHand(deck);
  //   // System.out.println(c.getHandSize());
  //   // System.out.println(d.playedCardsToString());
  //   // System.out.println(e.handToString());
  //   // c.addToHand(ac);
  //   // System.out.println(c.getBank());
  //   // c.playCard(ac, players, deck, discardPile);
  //   // // // System.out.println();
  //   // c.playCard(slyDeal, players, deck, discardPile);
    // System.out.println(e.playedCardsToString());
    // System.out.println(e.handToString());
    // System.out.println(d.playedCardsToString());
    // System.out.println(d.handToString());
    // System.out.println(c.playedCardsToString());
    // System.out.println(c.handToString());
  //   // System.out.println(c.getBank());
  //   // d.playCard(d, debtcollecter, players);
  //   // c.playCard(c, house, players);
    // System.out.println(e.playedCardsToString());
    // System.out.println(e.handToString());
    // System.out.println(d.playedCardsToString());
    // System.out.println(d.handToString());
    // System.out.println(c.playedCardsToString());
    // System.out.println(c.handToString());
  //   // System.out.println(d.getBank());
  //   // System.out.println(c.getBank());
  //   // System.out.println(c.getCards());
  //   // c.playCard(c, ac, players);
  //   // System.out.println(c.getBank());
  //   // System.out.println(e.getBank());
  //   //   Scanner s = new Scanner(System.in);
  // //   // createAndShowGUI();
  // //   System.out.println("**Welcome to Monopoly Deal!**");
  // //   System.out.println("Rules: ____\n"); //put in the rules later
    
  // //   System.out.print("Enter the number of players: ");
  // //   String noOfPlayers = s.nextLine();
  // //   while (!isInt(noOfPlayers) || Integer.parseInt(noOfPlayers) < 2) { //input checking to verify that noOfPlayers is an int >= 2
  // //     System.out.print("Invalid input. Enter the number of players: ");
  // //     noOfPlayers = s.nextLine();
  // //   }

  // //   Player[] players = new Player[Integer.parseInt(noOfPlayers)]; //array of all the players
  // //   for (int i = 0; i < Integer.parseInt(noOfPlayers); i++) {
  // //     System.out.print("Enter player " + i + "'s name: ");
  // //     String name = s.nextLine();
      
  // //     System.out.print("Enter player " + i + "'s age: ");
  // //     String age = s.nextLine();
  // //     while (!isInt(age) || Integer.parseInt(age) < 0) {
  // //       System.out.print("Invalid input. Enter player " + i + "'s age: ");
  // //       age = s.nextLine();
  // //     }
  // //     players[i] = new Player(name, Integer.parseInt(age));
  // //   }

  // //   //insertion sort to sort by age
  // //   for (int i = 1; i < players.length; i++) {
  // //     int key = players[i].getAge();
  // //     int index = i - 1;
  // //     while (index >= 0 && key < players[index].getAge()) {
  // //       players[index + 1] = players[index];
  // //       index--;
  // //     }
  // //     key = players[index + 1].getAge();
  // //   }
  // //   Deck deck = new Deck();
  // //   for (int j = 0; j < players.length; j++){
  // //     for (int i = 0; i < 5; i++){
  // //       players[j].addCard(deck.draw());
  // //     }
  // //   }
  // //   play(players);
    
  // // }


  // // public static void play(Player[] players) {
    
    
    
  // //   for (int i = 0; i < players.length; i++){
  // //     players[i].playedCardsToString();
  // //   }
  // // }


  // // public static boolean isInt(String str) {  
  // //   boolean flag = true;
  // //   //This if statement makes sure that the else-if will not run when the length of the string is 0 and checks if it's an empty string or "-".
  // //   if (str.length() == 0 || str.equals("-")) {
  // //     flag = false;
  // //   }
  // //   //This else if statement checks if the first character is not between 0-9 and it's not a minus symbol.
  // //   else if (!(str.charAt(0) >= 48 && str.charAt(0) <= 57) && str.charAt(0) != '-') {
  // //     flag = false;
  // //   }
  // //     //This loop goes through each character (except the first) in the string to check if it's between 0-9.
  // //   for (int i = 1; i < str.length(); i++) {
  // //     if (!(str.charAt(i) >= 48 && str.charAt(i) <= 57)) {
  // //       flag = false;
  // //     }
  // //   }
  // //   return flag;  
  // // }

  // // //JAVA SWING STUFF THAT WAS ALREADY HERE WHEN THE REPL WAS CREATED
  // // // private static void createAndShowGUI() {
  // // //     JFrame jFrame = new JFrame("Hello World Swing Example");
  // // //     jFrame.setLayout(new FlowLayout());
  // // //     jFrame.setSize(500, 360);
  // // //     jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

  // // //     JLabel label = new JLabel("Hello World Swing");
  // // //     Border border = BorderFactory.createLineBorder(Color.BLACK);
  // // //     label.setBorder(border);
  // // //     label.setPreferredSize(new Dimension(150, 100));

  // // //     label.setText("Hello World Swing");
  // // //     label.setHorizontalAlignment(JLabel.CENTER);
  // // //     label.setVerticalAlignment(JLabel.CENTER);

  // // //     jFrame.add(label);
  // // //     jFrame.setVisible(true);
  // // // }
  

  // public String instructions() {
  //   System.out.println();
  // }

