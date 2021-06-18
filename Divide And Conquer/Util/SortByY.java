package Util;

import java.util.Comparator;

public class SortByY implements Comparator<House> {

    @Override
    public int compare(House o1, House o2) {
        if (o1.getY()<o2.getY()){
            return -1;
        }else
            return 1;
    }
}