
/*EECS 1510 Spring 2021
 * Exam 1 Programming Problem
 * Ethan Keegan
 *  
 * The area of a pentagon can be computed using the following
 * formula:
 * 
 * 			area = (5 * s^2)/(4 * tan(PI/5))
 * 
 * in your code use: 
 * 		area = 5 * side * side / Math.tan(Math.PI / 5) / 4
 * 
 * Write a method that returns the area of a pentagon using the
 * following header:
 * 
 * public static double area (double side)
 * 
 * Write a main method that prompts the user to enter the side
 * of a pentagon and display its area. Here is a sample run.
 * 
 * Enter the side: 5.5
 * The area of the pentagon is 52.04444136781625
 * 
 * Your messages MUST MATCH THE ABOVE EXACTLY! In order to 
 * avoid an issue, I am providing the text for the print
 * statements:
 * 
 * System.out.print("Enter the side: ");
 *  
 *   
 * System.out.println("The area of the pentagon is " + 
 *          YOU NEED TO FINISH THIS ONE);
 * 
 */


//import your Scanner
import java.util.Scanner;

public class Exam1 					
{
  public static void main(String[] args)  
  {
	  //create Scanner object
	  Scanner input = new Scanner(System.in);
	  
	  //Enter the side of the pentagon
	  System.out.print("Enter the side: ");
	  
	  //read the input
	  double side = input.nextDouble();
	  
      //call the method and print the output
	  System.out.println("The area of the pentagon is " + area(side));
  }
  
  public static double area(double side)
  {
	  double area = 5 * side * side / Math.tan(Math.PI / 5) / 4;
	  return area;
  }
}