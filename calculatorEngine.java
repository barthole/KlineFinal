import java.util.Scanner;
import java.lang.StringBuilder;
import java.lang.Math;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main method that actually does the calculation.
 *
 * @author Brian Kline
 */

public class calculatorEngine extends calcOps {

  public static void calcEngine() {

    File calcFile = null;
    Scanner scanner = null;
    try { //creates the scanner to read from the .txt file
      calcFile = new File("final.txt");
      scanner = new Scanner(calcFile);
    }
    catch (FileNotFoundException noFile) {
      System.out.println("Error. File not found.");
    }
    String str = scanner.next();
    String sequence;
    if (str.equals("solve")) { //if command was 'solve' calls inputString method
      sequence = calcOps.inputString();
    }
    else if (str.equals("calculate")) { //if command was 'calculate' calls solveFunctionBreaker method
      sequence = calcOps.solveFunctionBreaker();
    }
    else { //if the string in the .txt file is neither 'calculate' or 'solve' outputs error and empty string
      System.out.println("Well, this isn't supposed to happen.");
      sequence = "";
    }
    int plus = sequence.indexOf('+');
    int minus = sequence.indexOf('-');
    int multi = sequence.indexOf('*');
    int divis = sequence.indexOf('/');
    int power = sequence.indexOf('^');

    if (plus != -1) { //detects if plus symbol present
      String first = sequence.substring(0, plus); //takes string of number before addition symbol
      String second = sequence.substring(plus + 1, sequence.length()); //takes string of number after addition symbol
      double num1 = Double.parseDouble(first); //converts first string to double
      double num2 = Double.parseDouble(second); // converts second string to double
      double solution = (num1 + num2); //adds two numbers together
      System.out.println("Your answer is: " + solution); //displays solution
    }
    else if (minus != -1) { //detects if minus symbol present
      String first = sequence.substring(0, minus); //takes string of number before subtraction symbol
      String second = sequence.substring(minus + 1, sequence.length()); //takes string of number after subtraction symbol
      double num1 = Double.parseDouble(first); //converts first string to double
      double num2 = Double.parseDouble(second); // converts second string to double
      double solution = (num1 - num2); //subtracts second number from the first
      System.out.println("Your answer is: " + solution); //displays solution
    }
    else if (multi != -1) { //detects if multiplication symbol present
      String first = sequence.substring(0, multi); //takes string of number before multiplication symbol
      String second = sequence.substring(multi + 1, sequence.length()); //takes string of number after multiplication symbol
      double num1 = Double.parseDouble(first); //converts first string to double
      double num2 = Double.parseDouble(second); // converts second string to double
      double solution = (num1 * num2); //multiplies numbers together
      System.out.println("Your answer is: " + solution); //displays solution
    }
    else if (divis != -1) { //detects if division present
      String first = sequence.substring(0, divis); //takes string of number before division symbol
      String second = sequence.substring(divis + 1, sequence.length()); //takes string of number after division symbol
      double num1 = Double.parseDouble(first); //converts first string to double
      double num2 = Double.parseDouble(second); // converts second string to double
      double solution = (num1 / num2); //divides first number by second number
      System.out.println("Your answer is: " + solution); //displays solution
    }
    else if (power != -1) { //detects if power symbol present
      String first = sequence.substring(0, power); //takes string of number before power symbol
      String second = sequence.substring(power + 1, sequence.length()); //takes string of number after power symbol
      double num1 = Double.parseDouble(first); //converts first string to double
      double num2 = Double.parseDouble(second); // converts second string to double
      double solution = Math.pow(num1, num2); //raises first number to the second
      System.out.println("Your answer is: " + solution); //displays solution
    }

    //if no operation symbols are present outputs error message asking to clear list and retry
    else if (plus == -1 && minus == -1 &&
    multi == -1 && divis == -1 && power == -1) {
        System.out.println("You didn't input a valid equation! "
        + "Please clear and reenter your function.");
    }
  }
}
