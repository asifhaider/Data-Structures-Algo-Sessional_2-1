import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static CityRoadGraph instance;

    // choosing traversal method or exit
    public static int traversalChoice(){
        System.out.println("Press 1 to traverse using BFS, 2 to traverse using DFS, 0 to quit!");
        Scanner scn = new Scanner(System.in);
        return scn.nextInt();
    }

    // program ends
    public static void quitProgram(){
        System.out.println("==================== Successfully Ended Program ==================");
    }

    // program starts
    public static void enterProgram(){
        System.out.println("====================================");
        System.out.println("Enter the number of cities, roads, pieces and friends! ");
    }

    // writing output to a file, instantiates each time
    public static void writeOutput(HashMap<Integer, Integer> map, String result){
        try{
            FileWriter fileWriter = new FileWriter("output.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(result + "\n");
            writer.write(instance.getPiecesRetrieved() + " out of " +
                    instance.getTotalPieces() + " pieces are collected\n");
            for (int i=0; i<map.size(); i++){
                writer.write(i + " collected " + map.get(i) +
                        " pieces\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // welcome text
        enterProgram();
        // taking primary console input
        Scanner scn = new Scanner(System.in);
        while(scn.hasNextLine()){
            String input = scn.nextLine();
            String[] inputs = input.split(" ");

            // program end logic
            if (inputs.length == 1){
                if(inputs[0].equals("q") || inputs[0].equals("Q")){
                    quitProgram();
                    return;
                }
            } else if (inputs.length == 4) {
                // initial variables
                int cities = Integer.parseInt(inputs[0]);
                int roads = Integer.parseInt(inputs[1]);
                int pieces = Integer.parseInt(inputs[2]);
                int friends = Integer.parseInt(inputs[3]);

                // one and only graph instance to be used in whole program
                instance = new CityRoadGraph(cities);

                // input edges
                for(int i=0; i<roads; i++){
                    String[] line = scn.nextLine().split(" ");
                    instance.addEdges(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                }

                // input pieces and locations
                for(int i=0; i<pieces; i++){
                    String[] line = scn.nextLine().split(" ");
                    instance.addPieces(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                }

                // input starting points and friends
                HashMap<Integer, Integer> friendList = new HashMap<>(friends);
                for (int i=0; i<friends; i++){
                     String[] line = scn.nextLine().split(" ");
                     friendList.put(Integer.parseInt(line[1]), Integer.parseInt(line[0]));
                }

                String result;
                switch(traversalChoice()){
                    case 1:
                        // traversal using BFS
                        for (int i=0; i<friendList.size(); i++){
                            int collected = instance.BFS(friendList.get(i));
                            friendList.put(i, collected);
                        }
                        if(instance.getTotalPieces() == instance.getPiecesRetrieved()){
                            result = "Mission Accomplished";
                        } else
                            result = "Mission Impossible";
                        System.out.println(result);
                        System.out.println(instance.getPiecesRetrieved() + " out of " +
                                instance.getTotalPieces() + " pieces are collected");
                        for (int i=0; i<friendList.size(); i++){
                            System.out.println(i + " collected " + friendList.get(i) +
                                    " pieces");
                        }
                        writeOutput(friendList, result);
                        break;
                    case 2:
                        // traversal using DFS
                        for (int i=0; i<friendList.size(); i++){
                            int collected = instance.DFS(friendList.get(i));
                            friendList.put(i, collected);
                        }
                        if(instance.getTotalPieces() == instance.getPiecesRetrieved()){
                            result = "Mission Accomplished";
                        } else
                            result = "Mission Impossible";
                        System.out.println(result);
                        System.out.println(instance.getPiecesRetrieved() + " out of " +
                                instance.getTotalPieces() + " pieces are collected");
                        for (int i=0; i<friendList.size(); i++){
                            System.out.println(i + " collected " + friendList.get(i) +
                                    " pieces");
                        }
                        writeOutput(friendList, result);
                        break;
                    case 0:
                        quitProgram();
                        return;
                }
            }
            enterProgram();
        }
    }
}
