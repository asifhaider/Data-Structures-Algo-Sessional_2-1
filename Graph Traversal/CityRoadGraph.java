import utils.LinkedQueue;
import utils.LinkedStack;

import java.util.ArrayList;

// the core graph data structure
public class CityRoadGraph {
    private final int totalCities;    // number of vertices
    private int piecesRetrieved;    // how many items rescued
    private int totalPieces;    // total target items

    // a list of cities inside the graph
    private final ArrayList<City> graphElement = new ArrayList<>();
    // a queue for bfs
    private final LinkedQueue<City> bfsQueue = new LinkedQueue<>();
    // a stack for dfs
    private final LinkedStack<City> dfsStack = new LinkedStack<>();
    // for checking unvisited nodes during graph traversal
    private final boolean[] isVisited;


    public CityRoadGraph(int totalCities) {
        this.totalCities = totalCities;
        this.totalPieces = 0;
        isVisited = new boolean[totalCities];

        // initializing vertices and adjacency list pairs
        for (int i=0; i<totalCities; i++){
            graphElement.add(new City(i));
        }
    }

    public int getTotalPieces() {
        return totalPieces;
    }

    public int getPiecesRetrieved() {
        return piecesRetrieved;
    }

    // adding new edges into adjacency list
    public void addEdges(int city1, int city2){
        graphElement.set(city1, graphElement.get(city1).addRoads(graphElement.get(city2)));
        graphElement.set(city2, graphElement.get(city2).addRoads(graphElement.get(city1)));

    }

    // adding new values into nodes
    public void addPieces(int city, int value) {
        graphElement.set(city, graphElement.get(city).addValue(value));
        this.totalPieces += value;
    }

    // method to implement Breadth First Search Algorithm
    public int BFS(int start){
        int collected = 0;  // collection for individual friend
        this.isVisited[start] = true;
        City c = graphElement.get(start);
        bfsQueue.enqueue(c);
        collected += c.getPieces();
        c.setPieces(0); // updating the pieces to zero after a successful retrieval
        graphElement.set(start, c);

        // using queue for implementing BFS
        while(this.bfsQueue.queueSize()!=0){
            City city = this.bfsQueue.dequeue();
            ArrayList<City> roads = city.getAdjacencyList();
            // iterating the adjacency list and apply same functions on the incident nodes
            for (City nextCity : roads) {
                if (!this.isVisited[nextCity.getId()]) {
                    this.isVisited[nextCity.getId()] = true;
                    collected += nextCity.getPieces();
                    nextCity.setPieces(0);
                    graphElement.set(nextCity.getId(), nextCity);
                    this.bfsQueue.enqueue(nextCity);
                }
            }
        }
        this.piecesRetrieved += collected;  // total number of items collected
        return collected;   // individual items collected
    }

    // method to implement Depth First Search Algorithm
    public int DFS(int start){
        int collected = 0;
        this.isVisited[start] = true;
        City c = graphElement.get(start);
        dfsStack.stackPush(c);
        collected += c.getPieces();
        c.setPieces(0);
        graphElement.set(start, c);

        // using stack for implementing DFS
        while(!this.dfsStack.isEmpty()){
            City city = this.dfsStack.stackPop();
            ArrayList<City> roads = city.getAdjacencyList();
            for (City nextCity : roads) {
                if (!this.isVisited[nextCity.getId()]) {
                    this.isVisited[nextCity.getId()] = true;
                    collected += nextCity.getPieces();
                    nextCity.setPieces(0);
                    graphElement.set(nextCity.getId(), nextCity);
                    this.dfsStack.stackPush(nextCity);
                }
            }
        }
        this.piecesRetrieved += collected;
        return collected;
    }
}