/* Solution of Offline 8 on Divide and Conquer Approach
   Author: Md. Asif Haider, 1805112
   Date: 18 June 2021
*/

import Util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    // comparing the distance between two houses
    public static HousePair min(HousePair x, HousePair y){
        return x.getDistance()<y.getDistance() ? x:y;
    }

    public static HousePair max(HousePair x, HousePair y){
        return x.getDistance()>y.getDistance()?x:y;
    }

    public static double distance(House h1, House h2){
        return Math.sqrt(((h1.getX()-h2.getX())*(h1.getX()-h2.getX())) +
                ((h1.getY()-h2.getY())*(h1.getY()-h2.getY())));
    }

    // method to solve the base cases consisting two or three houses
    public static ClosestPairs solveBaseCases(ArrayList<House>arr, int n){
        double min = Double.MAX_VALUE;
        double min2 = Double.MAX_VALUE;
        HousePair winner = null, competitor = null;
        for (int i=0; i<n; i++){
            for (int j=i+1; j<n; j++){
                if (distance(arr.get(i), arr.get(j)) < min){
                    min2 = min;
                    competitor = winner;
                    min = distance(arr.get(i), arr.get(j));
                    winner = new HousePair(arr.get(i), arr.get(j), min);
                } else if (distance(arr.get(i), arr.get(j)) < min2){
                    min2 = distance(arr.get(i), arr.get(j));
                    competitor = new HousePair(arr.get(i), arr.get(j), min2);
                }
            }
        }
        if(n==2){
            competitor = new HousePair(new House(), new House(), Double.MAX_VALUE);
        }
        return new ClosestPairs(winner, competitor);
    }

    // method to solve the cases with minimal strip area inside the recursive call
    public static ClosestPairs stripCases(ArrayList<House>strip, int size, ClosestPairs pairs){
        double min = pairs.getClosest().getDistance();
        double min2 = pairs.getSecondClosest().getDistance();

        HousePair winner = new HousePair(pairs.getClosest().getFirst(), pairs.getClosest().getSecond(), min);
        HousePair competitor = new HousePair(pairs.getSecondClosest().getFirst(), pairs.getSecondClosest().getSecond(), min2);

        for(int i=0; i<size; i++){
            for(int j=i+1; j<size && (strip.get(j).getY() - strip.get(i).getY())< min; j++){
                if(distance(strip.get(i), strip.get(j)) == min) {

                } else if (distance(strip.get(i), strip.get(j)) < min){
                    min2 = min;
                    competitor = winner;
                    min = distance(strip.get(i), strip.get(j));
                    winner = new HousePair(strip.get(i), strip.get(j), min);
                } else if(distance(strip.get(i), strip.get(j))<min2){
                    min2 = distance(strip.get(i), strip.get(j));
                    competitor = new HousePair(strip.get(i), strip.get(j), min2);
                }
            }
        }
        // System.out.println(competitor.getDistance());
        return new ClosestPairs(winner, competitor);
    }

    // main recursive function in a divide and conquer approach
    public static ClosestPairs divideAndConquer(ArrayList<House>Vx, ArrayList<House>Vy, int n){
        // base cases:
        if (n<=3){
            return solveBaseCases(Vx, n);
        }
        // other cases:
        // DIVIDE STEP
        int mid = n/2;
        House middlePoint = Vx.get(mid);

        ArrayList<House>leftVy = new ArrayList<>(mid);
        ArrayList<House>rightVy = new ArrayList<>(n-mid);
        int leftIndex = 0, rightIndex = 0;
        for (int i=0; i<n; i++){
            if(Vy.get(i).getX() < middlePoint.getX() && leftIndex<mid){
                leftVy.add(Vy.get(i));
                leftIndex++;
            } else{
                rightVy.add(Vy.get(i));
                rightIndex++;
            }
        }

        // CONQUER STEP
        ClosestPairs smallestLeft = divideAndConquer(Vx, leftVy, mid);
        ArrayList<House> Vx2 = new ArrayList<>(n-mid);
        for (int j=mid; j<n; j++){
            Vx2.add(Vx.get(j));
        }
        ClosestPairs smallestRight = divideAndConquer(Vx2, rightVy,n-mid );

        HousePair smallest = min(smallestLeft.getClosest(), smallestRight.getClosest());


        HousePair competitor = max(smallestLeft.getClosest(), smallestRight.getClosest());

        HousePair secondSmallest = min(smallestLeft.getSecondClosest(), smallestRight.getSecondClosest());

        secondSmallest = min(secondSmallest, competitor);

        ClosestPairs newPairs = new ClosestPairs(smallest, secondSmallest);

        // MERGE STEP
        ArrayList<House> strip = new ArrayList<>(n);
        int j=0;
        // System.out.println("Strip");
        for (int i=0; i<n; i++){
            if(Math.abs(Vy.get(i).getX() - middlePoint.getX())<smallest.getDistance()){
                strip.add(Vy.get(i));
                // System.out.println(strip.get(j).getId());
                j++;
            }
        }
        return stripCases(strip, j, newPairs);
    }

    // driver function to determine the second closest output
    public static HousePair secondClosestPair(ArrayList<House> houses, int n){
        ArrayList<House>Px = new ArrayList<>(n);
        ArrayList<House>Py = new ArrayList<>(n);
        for (int i=0; i<n; i++){
            Px.add(houses.get(i));
            Py.add(houses.get(i));
        }
        Px.sort(new SortByX());
        Py.sort(new SortByY());

        HousePair secondClosest;
        secondClosest = divideAndConquer(Px, Py, n).getSecondClosest();
        return secondClosest;
    }
    public static void main(String[] args) throws IOException {
        int houses;
        FileReader fileReader = new FileReader("input1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        houses = Integer.parseInt(bufferedReader.readLine());
        ArrayList<House>points = new ArrayList<>(houses);
        for (int i=0; i<houses; i++){
            String[] input = bufferedReader.readLine().split(" ");
            House h = new House(Integer.parseInt(input[0]), Integer.parseInt(input[1]), i);
            points.add(h);
        }
        fileReader.close();
        bufferedReader.close();
        HousePair h = secondClosestPair(points, houses);
        System.out.println(h.getFirst().getId() + " " + h.getSecond().getId());
        System.out.printf("%.4f", h.getDistance());
    }
}
