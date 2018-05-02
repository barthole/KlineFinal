import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * This class manages a simple calculator that is operated via
 * command-line and offers several options such as storing equations,
 * solving simple single variable problems, and other basic math operations.
 *
 * @author Brian Kline
 */
public class calcMain {

  /**
   * This is the main method that holds the (basic) user interface and allows
   * users to navigate through the program.
   *
   * To run the program type: "java calcMain" after compiling.
   *
   */
  public static void main(String[] args) {
    System.out.println("Welcome to my simple calculator!");
    System.out.println("This calculator can solve binomial functions as " +
    "well as calculate simple math problems.");
    System.out.println("Be aware, this calculator can only solve problems "
    + "with one operation (+,-,*,/,^). It will take decimal values though!");
    System.out.println("What would you like to do? Choose from the options."
    + " Option 'help' will read a list of what the options accomplish.");
    calcOps.optionPrint();
    Scanner scanner = new Scanner(System.in);
    while (scanner.hasNext()) {
      String chosenOption = scanner.nextLine();
      if (chosenOption.equals("list")) {
        calcOps.listEquation();
        calcOps.optionPrint();
      }
      else if (chosenOption.equals("calculate")) {
        /**
        * In order for the calculator to differentiate reading a string from
        * the user or from the array, I created a .txt file that a string gets
        * written to. Because the user chose to calculate, the string
        * "calculate" gets written to the .txt file and calcEngine will read
        * that string from the file and run the corresponding method.
        *
        */
          try {
            PrintWriter writer = new PrintWriter("final.txt", "UTF-8");
            writer.println("calculate");
            writer.close();
          }
          catch (IOException e) { //in case string cannot be written to file
            System.out.println("Error writing to file.");
          }

          calculatorEngine.calcEngine();
          calcOps.optionPrint();
      }
      else if (chosenOption.equals("solve")) {
        try { //writes string "solve" instead of "calculate"
          PrintWriter writer = new PrintWriter("final.txt", "UTF-8");
          writer.println("solve");
          writer.close();
        }
        catch (IOException e) { //in case string cannot be written to file
          System.out.println("Error writing to file.");
        }
        calculatorEngine.calcEngine();
        calcOps.optionPrint();
      }
      else if (chosenOption.equals("clear")) {
        calcOps.clearEquation();
        calcOps.optionPrint();
      }
      else if (chosenOption.equals("quit")) {
        System.out.println("Thanks for using my calculator!");
        break;
      }
      else if (chosenOption.equals("add")) {
        calcOps.addEquation();
        calcOps.optionPrint();
      }
      else if (chosenOption.equals("help")) {
        calcOps.helpPrint();
        calcOps.optionPrint();
      }
      /**
      * This else-if block of code is for when the user tries to enter an option
      * not on the list. Keeps the program from throwing an exception and
      * automatically exiting.
      *
      */
      else if (!chosenOption.equals("add") && !chosenOption.equals("list")
      && !chosenOption.equals("quit") && !chosenOption.equals("clear")
      && !chosenOption.equals("calculate") && !chosenOption.equals("solve")) {
        System.out.println("That is not a option from the list.");
        calcOps.optionPrint();
      }
    }
  }
}
