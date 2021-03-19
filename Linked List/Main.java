/* Solution of Offline 2 on Data Structure: Linked List
   Author: Md. Asif Haider, 1805112
   Date: 19 March 2021
*/

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // console menu intro
        System.out.println("=============================================");
        System.out.println("Welcome to the interactive game admin panel!");

        // start the game and add nodes to the list
        System.out.println("Start the game with initial number of players and their reflex times!");

        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        PillowPassList newGame = new PillowPassList(); // the game time started

        for (int i=0; i<n; i++){
            int a = scn.nextInt();
            int b = newGame.getPlayerCount();
            newGame.addPlayer(new PlayerNode(a, b+1)); // assigning player names based on their entry serial
        }

        boolean gameFlag = true; // indicates the game is running

        // checking out all operations and commands
        System.out.println("=================== Menu ====================\n");
        System.out.println("Input a non-decreasing timestamp before each command");
        System.out.println("Press M: Stop Music and Eliminate Player");
        System.out.println("Press R: Reverse the Passing Direction");
        System.out.println("Press I: Enter new Player and Reflex Time");
        System.out.println("Press P: Check Current Pillow Holder");
        System.out.println("Press F: End Game");

        // taking inputs until the game ends by pressing F or everyone else gets eliminated
        while (scn.hasNextLine()){
            String input = scn.nextLine();
            if (input.equals("")){
                input = scn.nextLine();
            }
            String[] command = input.split(" "); // extracting out the two input parameters from string line
            int time = Integer.parseInt(command[0]);
            switch (command[1]) {
                case "P":
                    newGame.checkPillowHolder(time); // checks current pillow holder
                    break;
                case "M":
                    if (newGame.eliminatePillowHolder(time)){
                        break;
                    }
                    gameFlag = false; // ends the game when only one player is remaining
                    break;
                case "R":
                    newGame.reverseDirection(time); // reverses the direction
                    break;
                case "I":
                    int reflexTime = Integer.parseInt(command[2]); // handles additional input parameter
                    PlayerNode p = new PlayerNode(reflexTime, newGame.getPlayerSerial() + 1);
                    newGame.newPlayerEntry(p, time);
                    break;
                case "F":
                    newGame.gameExit(time); //closes the game
                    gameFlag = false;
                    break;
            }
            if (!gameFlag)
                break; // closes the program
        }
        System.out.println("\n============================================");
    }
}
