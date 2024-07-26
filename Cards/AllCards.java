import java.util.*;

/**
  * This class stores all of the colours, as well as parallel arrays of values and names for the cards.
*/

class AllCards {

  //COLOURS
  public static final String RESET = "\u001B[0m"; // to reset font/print
  public static final String BROWN = "\033[1;33m";
  public static final String DARKBLUE = "\033[1;34m";
  public static final String GREEN = "\033[38;5;82;1m";
  public static final String LIGHTBLUE = "\033[1;94m";
  public static final String ORANGE = "\033[38;2;255;165;1m";
  public static final String PINK = "\033[38;5;206;1m";
  public static final String BLACK = "\033[1;30m";
  public static final String RED = "\033[38;5;196;1m";
  public static final String GREY = "\033[38;5;252;1m";
  public static final String YELLOW = "\033[38;5;226;1m";
  

  public static String[] actionCardNames = {"Just Say No", "Deal Breaker", "Debt Collector", "Double The Rent", "Forced Deal", "House", "Hotel", "It's My Birthday", "Pass GO", "Sly Deal", "Rent", "Wild Card"};
  public static int[] actionCardValues = {4, 5, 3, 1, 3, 3, 4, 2, 1, 3, 1, 0};
  public static int[] numOfActionCards = {3, 2, 3, 2, 3, 3, 2, 3, 10, 3, 10, 2};
  public static String[] propertyColors = {"Brown", "Dark Blue", "Green", "Light Blue", "Orange", "Pink", "Black", "Red", "Utility", "Yellow"};
  public static String[][] propertyNames = {{"Baltic Avenue", "Mediterranean Avenue"}, {"Boardwalk", "Park Place"}, {"North Carolina Avenue", "Pacific Avenue", "Pennsylvania Avenue"}, {"Connecticut Avenue", "Oriental Avenue", "Vermont Avenue"}, {"New York Avenue", "St. James Place", "Tennesse Avenue"}, {"St. Charles Place", "Virginia Avenue", "States Avenue"}, {"Short Line", "B. & O. Railroad", "Reading Railroad", "Pennsylvania Railroad"}, {"Kentucky Avenue", "Indiana Avenue", "Illinois Avenue"}, {"Water Works", "Electric Company"}, {"Ventnot Avenue", "Marvin Gardens", "Atlantic Avenue"}};
  //-1 = null, there's no value
  public static int[] onePropertyRent = {1, 3, 2, 1, 1, 1, 1, 2, 1, 2}; 
  public static int[] twoPropertyRent = {2, 8, 4, 2, 3, 2, 2, 3, 2, 4};
  public static int[] threePropertyRent = {-1, -1, 7, 3, 5, 4, 3, 6, -1, 6};
  public static int[] fourPropertyRent = {-1, -1, -1, -1, -1, -1, 4, -1, -1, -1};
  public static int[] propertyValues = {1, 4, 4, 1, 2, 2, 2, 3, 2, 3};
  public static int[] moneyCards = {10, 5, 5, 4, 4, 4, 3, 3, 3, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1};
}