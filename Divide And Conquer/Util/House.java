package Util;

public class House {
    private int x;
    private int y;
    private int id;

    public House() {
        this.id = -1;
    }

    public House(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}

