package Util;

import java.util.Comparator;

public class SortByX implements Comparator<House> {

    @Override
    public int compare(House o1, House o2) {
        if (o1.getX()<o2.getX() || (o1.getX()==o2.getX() && o1.getY() < o2.getY())){
            return -1;
        }else
            return 1;
    }
}
