import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.Math;

/**
 * This class contains a majority of the methods used in the program.
 * The main calculator method is in a separate class for the
 * sake of tidyness of the overall program.
 *
 * @author Brian Kline
 */
public class calcOps {

  //publicly declared ArrayList so it can be accessed from multiple methods
  public static ArrayList<String> functionList = new ArrayList<String>(10);

  //input string that goes to the calcEngine to be solved
  public static String inputString() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter your binomial expression (not in terms of x): ");
    String calcString = scanner.next();
    return calcString;
  }

  //separate method to reduce lines b/c repeated after every option operation
  public static void optionPrint() {
    System.out.println("\nAvailable options: calculate, "
        + "solve, list, clear, add, quit, help");
  }

  //method for 'help' option. Just print statements
  public static void helpPrint() {
    System.out.println("Option calculate will solve a binomial expression "
    + "from a list. You first need to add the function to the list with "
    + "the add option.");
    System.out.println("Option solve will solve a simple binomial math "
    + "expression only containing numbers.");
    System.out.println("Option list will show a list of all expressions you've"
    + " added with the add option.");
    System.out.println("Option clear will clear the list of functions you've  "
    + "added with the add option.");
    System.out.println("Option add will add your binomial function to a list.");
    System.out.println("Option quit exits the program.");
    System.out.println("Remember! functions only work if they have one "
    + "operation. Plus(+) Minus(-) Divide(/) Multiply(*) Power(^)");
  }

  //method for 'add' option
  public static void addEquation() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter your function in terms of x: ");
    functionList.add(scanner.next()); //adds entered string to the array
    System.out.println("Would you like to add another function? y/n");

    //while loop to add multiple functions w/o exiting the add method
    while (scanner.hasNext()) {
      String reply = scanner.next();
      if (reply.equals("y")) {
        System.out.println("Enter your other function in terms of x: ");
        functionList.add(scanner.next()); //adds entered string to the array
        System.out.println("Would you like to add another function? y/n");
    }
      else if (reply.equals("n")) {
        break; //leaves the 'add' method back to the main program
      }
    }
  }

  public static void listEquation() {
    int i = 0;
    /**
    * as long as int i is less than the size of the largest element in the
    * array it will print out the string with its index +1 so the array
    * starts at 1. Because we all know arrays are supposed to start at 1.
    */
    while (i++ < functionList.size()) {
      System.out.println(i + ") " + functionList.get(i - 1));
    }
  }

  //method for the 'clear' option
  public static void clearEquation() {
    int i = 0;
    while (i != functionList.size()) { //loop repeats removing the first element until the size of the array =0
      functionList.remove(i);
    }
    System.out.println("Functions have been wiped.");
  }

  //method for the solveFunctionBreaker which is called by the calcEngine
  public static Integer solveInteger() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Choose which function you want to use by entering the "
        + "appropriate number.");
        int i = 0;
    while (i++ < functionList.size()) {
      System.out.println(i + ") " + functionList.get(i-1));
    }
    String numberChosen = scanner.next();
    try { //if the input is not a number than the output will be 0, to avoid crashing
    int numberChosen1 = Integer.parseInt(numberChosen); //converts string to int
    return numberChosen1;
    }
    catch (NumberFormatException e) {
      int numberChosen1 = 0;
      return numberChosen1;
    }
  }
  // method used by calcEngine to pull and alter the stored function
  public static String solveFunctionBreaker() {
    Scanner scanner = new Scanner(System.in);
    String function;
    try { //if a number chosen isn't listed, it will substitute an empty string instead of throwing an error
    int numberChosen1 = calcOps.solveInteger();
    function = functionList.get(numberChosen1 - 1);
    }
    catch (IndexOutOfBoundsException e) {
    System.out.println("Invalid option.");
    function = ""; //this empty string will run through the calcEngine and output as an invalid equation
    }
    while (function.indexOf('x') != -1) { //loop to detect that an x is present in the function

      //detects if a power sign is present, as this means breaking the function differently
      if (function.indexOf('^') != -1) {

        //another if statement to detect if the x is it's own value (no preceding #)
        if (function.indexOf('^') == 1) {
          System.out.println("What is the x-value?");
          String solver = function.replaceAll("x", scanner.next()); //replace x with the given value
          System.out.println("Your equation is now: " + solver + " which means");
          return solver; //this string is fine to be computed by calcEngine
        }
        else { //every string with the '^' symbol goes through this fork in the method
        System.out.println("What is the x-value?");
        String value = scanner.next(); //reads x-value as a string
        String solver = function.replaceAll("x", "(" + value + ")"); //inserts parenthesis around x-value
        System.out.println("Your equation is now: " + solver + " which means");
        float realValue = Float.parseFloat(value); //converts x-value from string to float
        int varBracket1 = solver.indexOf('(');
        int varBracket2 = solver.indexOf(')');
        String one = solver.substring(0, varBracket1); //makes a string from first character to first bracket
        String two = solver.substring(varBracket2 + 2, solver.length()); //makes a string from after power symbol bracket to end
        float oneone = Float.parseFloat(one); //convert string one to float
        float twotwo = Float.parseFloat(two); //convert string two to float
        double nut = Math.pow(realValue, twotwo); //perform power operation of x-value
        double nutter = oneone*nut; //multiply x-value by the first number
        String nutty = new Double(nutter).toString() + "+0"; //add +0 to the string so it goes through calcEngine w/o error
        return nutty;
      }
    }
    else { //every other function without '^' symbol goes through this fork of the method
      System.out.println("What is the x-value?");
      String value = scanner.next(); //reads x-value as string
      String solver = function.replaceAll("x", "(" + value + ")"); //inserts parenthesis around x-value
      System.out.println("Your equation is now: " + solver + " which means");
      float realValue = Float.parseFloat(value); //converts x-value from string to float
      int varBracket1 = solver.indexOf('(');
      int varBracket2 = solver.indexOf(')');
      String one = solver.substring(0, varBracket1); //makes a string from first character to first bracket
      String two = solver.substring(varBracket2 + 1, solver.length()); //makes a string from after 2nd bracket to end
      float oneone = Float.parseFloat(one); //converts first number to float
      float nut = oneone*realValue; //multiply x-value by first number
      String nutty = new Float(nut).toString() + two; //converts product of x-value back to string and adds 2nd string
      return nutty;
      }
    }
    return function; //returns empty string field as result of IndexOutOfBoundsException
  }
}
