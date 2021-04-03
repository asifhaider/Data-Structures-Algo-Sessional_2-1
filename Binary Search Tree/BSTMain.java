/*
    Solution of Offline 4 on Data Structure: Binary Search Tree
    Author: Md. Asif Haider, 1805112
    Date: 3 April 2021
*/

import java.util.Scanner;

public class BSTMain {
    private static boolean programOn = true; // automatically flags program running period while being invoked

    static void printMenu(){
        System.out.println("============= MENU =============");
        System.out.println("1. Insert Item");
        System.out.println("2. Search Item");
        System.out.println("3. Get In Order Successor");
        System.out.println("4. Get In Order Predecessor");
        System.out.println("5. Delete Item");
        System.out.println("6. Get Item Depth");
        System.out.println("7. Get Maximum Item");
        System.out.println("8. Get Minimum Item");
        System.out.println("9. Get Height");
        System.out.println("10. Print In Order Traversal");
        System.out.println("11. Print Pre Order Traversal");
        System.out.println("12. Print Post Order Traversal");
        System.out.println("13. Get Size");
        System.out.println("14. Quit Program!");
    }

    static int inputPrompt(){
        // takes subsequent input and returns the integer element of interest
        System.out.println("Provide your value!");
        Scanner scn = new Scanner(System.in);
        String input = scn.nextLine();
        return Integer.parseInt(input);
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Welcome to the Binary Search Tree Operations Menu!");
        System.out.println("Press associated key option to perform desired operation");

        Scanner scn = new Scanner(System.in);
        BinarySearchTree bst = new BinarySearchTree();

        while (true){
            if (!programOn){
                System.out.println("You Have Successfully Exited The Program!");
                break;
            }
            printMenu();
            String inputString = scn.nextLine();
            int option, input;
            try{
                option = Integer.parseInt(inputString);
            }catch (Exception e){
                continue;
                // ignores if there's something other than integer
            }
            //?? handle any integer that comes as input
            switch (option){
                case 1:
                    // duplicate and non-positive cases handled while insertion
                    input = inputPrompt();
                    if (input<=0){
                        System.out.println("Only Positive Numbers Allowed!");
                        break;
                    }
                    int previousNodeCount = bst.getSize(bst.getRootNode());
                    TreeNode newNode = bst.insertItem(bst.getRootNode(),input);
                    if(previousNodeCount==bst.getSize(bst.getRootNode())){
                        // the node count of tree before and after calling insert method- means no new entry allowed
                        System.out.println("Similar Item Can't Be Inserted! Please Try Again.");
                    } else if (newNode!=null){
                        System.out.println("Item Inserted Successfully!");
                    }
                    break;
                case 2:
                    input = inputPrompt();
                    if(bst.searchItem(bst.getRootNode(), input) == null){
                        System.out.println(input + " has not been found");
                    } else{
                        System.out.println(input + " has been found");
                    }
                    break;
                case 3:
                    input = inputPrompt();
                    TreeNode tempNode = bst.searchItem(bst.getRootNode(), input);
                    if(tempNode == null){
                        System.out.println("Number Not Found in the Tree! Try Again");
                    } else {
                        int temp = bst.getInOrderSuccessor(bst.getRootNode(), tempNode);
                        if (temp!=-1)
                            System.out.println("The In Order Successor is: " + temp);
                    }
                    break;
                case 4:
                    input = inputPrompt();
                    tempNode = bst.searchItem(bst.getRootNode(), input);
                    if(tempNode == null){
                        System.out.println("Number Not Found in the Tree! Try Again");
                    } else {
                        int temp = bst.getInOrderPredecessor(bst.getRootNode(), tempNode);
                        if (temp!=-1)
                            System.out.println("The In Order Predecessor is: " + temp);
                    }
                    break;
                case 5:
                    input = inputPrompt();
                    if(bst.getSize(bst.getRootNode())==1 && bst.getRootNode().getNodeValue()==input) {
                        bst = new BinarySearchTree();
                        System.out.println("Only Item Left Removed Successfully");
                    }else if((bst.deleteItem(bst.getRootNode(),input))!=null){
                        System.out.println("Item Deleted Successfully (If Available)!");
                    } else{
                        System.out.println("Item Deletion Failed! Please Try Again");
                    }
                    break;
                case 6:
                    input = inputPrompt();
                    int temp = bst.getItemDepth(bst.getRootNode(), input);
                    if (temp == -1)
                        System.out.println("Item Not Found! Please Try Again");
                    else
                        System.out.println("The Item Depth is: " + temp);
                    break;
                case 7:
                    if (bst.getRootNode() != null){
                        System.out.println("The Maximum Number is: " + bst.getMaxItem(bst.getRootNode()));
                    }
                    else
                        System.out.println("Can't Find Maximum From Empty Tree");
                    break;
                case 8:
                    if (bst.getRootNode() != null){
                        System.out.println("The Minimum Number is: " + bst.getMinItem(bst.getRootNode()));
                    }
                    else
                        System.out.println("Can't Find Minimum From Empty Tree");
                    break;
                case 9:
                    temp = bst.getHeight(bst.getRootNode());
                    if (temp == -1)
                        System.out.println("Can't Calculate Height! Please Try Again");
                    else
                        System.out.println("The Tree Height is: " + temp);
                    break;
                case 10:
                    bst.printInOrder(bst.getRootNode());
                    break;
                case 11:
                    bst.printPreOrder(bst.getRootNode());
                    break;
                case 12:
                    bst.printPostOrder(bst.getRootNode());
                    break;
                case 13:
                    System.out.println("Total Nodes: "
                            + bst.getSize(bst.getRootNode()));
                    break;
                case 14:
                    programOn = false;
                    break;
            }
        }
    }
}
