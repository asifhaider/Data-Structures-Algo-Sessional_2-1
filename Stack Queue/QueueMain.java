/* Solution of Offline 3 on Data Structure: Queue
   Author: Md. Asif Haider, 1805112
   Date: 26 March 2021
*/

import java.util.Scanner;

public class QueueMain {
    public static void main(String[] args) {
        System.out.println("Welcome to The First Non-Repeating Character Finder!");
        System.out.println("======================================================");
        System.out.println("Provide your input stream!");

        // taking input string
        Scanner scn = new Scanner(System.in);
        while (scn.hasNextLine()) {
            String expression = scn.nextLine();
            if (expression.equalsIgnoreCase("Exit")) {
                System.out.print("The Program is shut down");
                break;
            }

            // finding the first non repeating character from the expression
            CharacterFinder c = new CharacterFinder();
            c.firstNonRepeatingCharacterFinder(expression);
        }
    }
}
