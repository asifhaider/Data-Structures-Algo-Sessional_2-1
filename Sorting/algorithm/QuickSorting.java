package algorithm;

public class QuickSorting {
    private int[] sortedArray;
    private double startTime;
    private double endTime;

    public QuickSorting() {
        this.startTime = 0;
        this.endTime = 0;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    // returns total time needed for sorting
    public double getTotalTime(){
        return this.endTime - this.startTime;
    }

    // swap function
    public void swap(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // partition procedure
    public int partitionProcedure(int[] array, int start, int end){
        int pivotElement = array[end];  // taking the rightmost/last element as the pivot
        int i = start - 1;

        for(int j=start; j<end; j++){
            if(array[j]<=pivotElement){
                i++;
                swap(array, i, j);
            }
        }
        swap(array,i+1, end);
        return (i+1);
    }

    // recursive quicksort function for ascending order
    public void quickSort(int[] arrayToBeSorted, int start, int end){
        if(start<end){
            int partitionerIndex = partitionProcedure(arrayToBeSorted, start, end);
            quickSort(arrayToBeSorted, start, partitionerIndex-1);
            quickSort(arrayToBeSorted, partitionerIndex+1, end);
        }
    }

    // quicksort driver
    public void sort(int[] array){
        this.startTime = System.nanoTime();
        quickSort(array, 0, array.length-1);
        this.endTime = System.nanoTime();
        this.sortedArray = array;
    }
}