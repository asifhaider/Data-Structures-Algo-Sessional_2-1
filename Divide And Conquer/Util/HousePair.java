package Util;

public class HousePair {
    private House first;
    private House second;
    private double distance;

    public HousePair(House first, House second, double distance) {
        this.first = first;
        this.second = second;
        this.distance = distance;
    }

    public House getFirst() {
        return first;
    }

    public House getSecond() {
        return second;
    }

    public double getDistance() {
        return distance;
    }
}
