import java.util.ArrayList;

// vertices of the graph
public class City {
    private final int id;
    private int pieces;
    private final ArrayList<City> adjacencyList = new ArrayList<>();  // the adjacency list for storing edges

    public City(int id) {
        this.id = id;
        this.pieces = 0;

    }

    public int getId() {
        return id;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public ArrayList<City> getAdjacencyList(){
        return this.adjacencyList;
    }


    // adding elements to adjacency list
    public City addRoads (City c){
        adjacencyList.add(c);
        return this;
    }

    // storing values into nodes
    public City addValue(int value) {
        this.pieces = value;
        return this;
    }
}