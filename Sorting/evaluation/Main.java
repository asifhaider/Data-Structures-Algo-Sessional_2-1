package evaluation;

import algorithm.MergeSorting;
import algorithm.QuickSorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void report() throws IOException {
        FileWriter writer = new FileWriter("report2.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        MergeSorting m = new MergeSorting();
        QuickSorting q = new QuickSorting();

        int size = 10;
        while(size<=1000000){
            bufferedWriter.write(size + ": ");
            int[] array1Merge, array1Quick, array2Merge, array2Quick, array3Merge, array3Quick;
            double instance1Merge = 0.0, instance1Quick = 0.0, instance2Merge = 0.0, instance2Quick = 0.0, instance3Merge = 0.0, instance3Quick = 0.0;
            for(int i=1; i<=5; i++){
                array1Merge = randomArrayGenerator(size, "a");
                array1Quick = array1Merge;
                q.sort(array1Quick);
                instance1Quick += (q.getTotalTime()/1000000);
                m.sort(array1Merge);
                instance1Merge += (m.getTotalTime()/1000000);
                // bufferedWriter.write("\t" + instance1Merge/1000000 + "\t" + instance1Quick/1000000 + ", ");

                array2Merge = randomArrayGenerator(size, "d");
                array2Quick = array2Merge;
                q.sort(array2Quick);
                instance2Quick += (q.getTotalTime()/1000000);
                m.sort(array2Merge);
                instance2Merge += (m.getTotalTime()/1000000);
                // bufferedWriter.write("\t" + instance2Merge/1000000 + "\t" + instance2Quick/1000000 + ", ");

                array3Merge = randomArrayGenerator(size, "r");
                array3Quick = array3Merge;
                q.sort(array3Quick);
                instance3Quick += (q.getTotalTime()/1000000);
                m.sort(array3Merge);
                instance3Merge += (m.getTotalTime()/1000000);
                // bufferedWriter.write("\t" + instance3Merge/1000000 + "\t" + instance3Quick/1000000 + ", ");
            }
            bufferedWriter.write(String.format("%.3f, %.3f, %.3f, %.3f, %.3f, %.3f", instance1Merge/5, instance1Quick/5, instance2Merge/5, instance2Quick/5, instance3Merge/5, instance3Quick/5));
            size = size * 10;
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
    }

    // menu dialog box for each iteration of inputs
    public static void menuPrompt(){
        System.out.println("================ Welcome! Let's play with Mergesort and Quicksort! =============");
        System.out.println("Provide your input in two separate lines. Press Q to exit.");
        System.out.println("1. Enter your input size!");
        System.out.println("2. Press any of the following options: ");
        System.out.println("A: Ascending    D: Descending   R: Random");
    }

    // program ends
    public static void quitProgram(){
        System.out.println("Successfully exited the program!");
    }

    // single random array generator
    public static int[] randomArrayGenerator(int size, String order){
        int[] randomIntegers = new int[size];
        Random random = new Random(size);
        if (order.equalsIgnoreCase("A")){
            // int temp = random.nextInt(3000);
            for (int i=0; i<randomIntegers.length; i++){
                randomIntegers[i] = (int)((i+random.nextDouble())*10);
                // randomIntegers[i] = temp + i;
                System.out.print(randomIntegers[i] + "\t");
            }
        } else if (order.equalsIgnoreCase("D")){
            // randomIntegers[0] = Integer.MAX_VALUE;
            for (int i=0; i<randomIntegers.length; i++){
                randomIntegers[i] = (int)((i+random.nextDouble())*10);
                // randomIntegers[i] = temp+randomIntegers.length-i;
                System.out.print(randomIntegers[i] + "\t");
            }
        } else{
            for (int i=0; i<randomIntegers.length; i++){
                randomIntegers[i] = random.nextInt(size*10);
                System.out.print(randomIntegers[i] + "\t");
            }
        }
        System.out.println();
        return randomIntegers;
    }

    // printing sorted output in two side by side columns
    public static void showSortedArray(int[] sortedArray1, int[] sortedArray2) {
        for (int i=0; i<sortedArray1.length; i++){
            System.out.print("\t" + sortedArray1[i] + "\t" + "\t" + "\t" + sortedArray2[i]);
            System.out.println();
        }
    }

    // main driver function for evaluation code
    public static void main(String[] args) throws IOException {
        report();
        menuPrompt();
        int inputSize;
        Scanner scn = new Scanner(System.in);
        while(scn.hasNextLine()){
            String sizeOrQuit = scn.nextLine();

            // checking for quitting program command
            if(sizeOrQuit.equalsIgnoreCase("q")){
                quitProgram();
                break;
            }else{
                // check for non-digit input string
                try{
                    inputSize = Integer.parseInt(sizeOrQuit);
                } catch (NumberFormatException ne){
                    System.out.println("Please provide integer value!");
                    continue;
                }
            }

            String orderChoice = scn.nextLine().toUpperCase();
            if(orderChoice.equalsIgnoreCase("a") || orderChoice.equalsIgnoreCase("d") || orderChoice.equalsIgnoreCase("r")){

                // two sorting class instance
                MergeSorting m = new MergeSorting();
                QuickSorting q = new QuickSorting();

                int[] numbersToBeSortedByMerge = randomArrayGenerator(inputSize, orderChoice);
                // identical copy of the same array above
                int[] numbersToBeSortedByQuick = numbersToBeSortedByMerge;

                if(orderChoice.equalsIgnoreCase("a"))
                    orderChoice = "ascending";
                else if(orderChoice.equalsIgnoreCase("d"))
                    orderChoice = "descending";
                else
                    orderChoice = "random";

                // sorting and storing back the resulting arrays
                q.sort(numbersToBeSortedByQuick);
                numbersToBeSortedByQuick = q.getSortedArray();

                m.sort(numbersToBeSortedByMerge);
                numbersToBeSortedByMerge = m.getSortedArray();

                // printing sorted arrays
                System.out.println("Mergesort" + "\t" + "Quicksort");
                showSortedArray(numbersToBeSortedByMerge, numbersToBeSortedByQuick);

                // printing time, order and input size info
                System.out.println("Merge sort for " + inputSize + " inputs in " + orderChoice +
                        " order took: " + m.getTotalTime()/1000000 + " milliseconds");

                System.out.println("Quick sort for " + inputSize + " inputs in " + orderChoice +
                        " order took: " + q.getTotalTime()/1000000 + " milliseconds");

            }else{
                System.out.println("Please provide correct input!");
                continue;
            }
            menuPrompt();
        }
    }
}
