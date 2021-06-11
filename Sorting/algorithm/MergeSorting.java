package algorithm;

public class MergeSorting {
    private int[] sortedArray;
    private double startTime;
    private double endTime;

    public MergeSorting() {
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

    // merge procedure of merge sort
    public void mergeProcedure(int[] arr, int lower, int middle, int upper){
        int size1 = middle-lower+1;
        int size2 = upper-middle;

        int temp1[] = new int[size1];
        int temp2[] = new int[size2];

        for(int i=0; i<size1; i++){
            temp1[i] = arr[lower+i];
        }
        for(int j=0; j<size2; j++){
            temp2[j] = arr[middle+1+j];
        }

        int i=0, j=0;
        int k = lower;

        while(i<size1 && j<size2){
            if(temp1[i]<=temp2[j]){
                arr[k++] = temp1[i++];
            }else{
                arr[k++] = temp2[j++];
            }
        }

        while(i<size1){
            arr[k++] = temp1[i++];
        }

        while(j<size2){
            arr[k++] = temp2[j++];
        }
    }

    // ascending order merge sort
    public void mergeSort(int[] array, int start, int end){
        if(start < end){
            int middleElement = (start+end)/2;
            mergeSort(array, start, middleElement);
            mergeSort(array, middleElement+1, end);
            mergeProcedure(array, start, middleElement, end);
        }
        this.sortedArray = array;
    }

    // merge sort driver
    public void sort(int[] array){
        this.startTime = System.nanoTime();
        mergeSort(array, 0, array.length-1);
        this.endTime = System.nanoTime();
    }
}