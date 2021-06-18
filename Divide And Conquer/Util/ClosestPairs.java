package Util;

public class ClosestPairs {
    private HousePair closest;
    private HousePair secondClosest;

    public ClosestPairs(HousePair closest, HousePair secondClosest) {
        this.closest = closest;
        this.secondClosest = secondClosest;
    }

    public HousePair getClosest() {
        return closest;
    }

    public HousePair getSecondClosest() {
        return secondClosest;
    }
}
