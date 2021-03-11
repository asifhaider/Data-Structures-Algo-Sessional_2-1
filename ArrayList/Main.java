/* Solution of Offline 1 on Data Structure: Arraylist
   Author: Md. Asif Haider, 1805112
   Date: 11 March 2021
*/

class Array {
    private String[] myArrayList;
    private int currentLength;
    private static final int PREDEFINED_SIZE = 10;

    public Array(){
        myArrayList = new String[PREDEFINED_SIZE];
        currentLength = 0;
    }

    public Array(int n){
        myArrayList = new String[n];
        currentLength = 0;
    }

    public Array(String[] A){
        myArrayList = new String[A.length];
        for (int i=0; i<A.length; i++){
            myArrayList[i] = A[i];
        }
        currentLength = A.length;
    }

    public Array getArray(){
        return this;
    }

    public String getAnElement(int i){
        if (i>currentLength || i<=0){
            System.out.println("This position is out of capacity!");
            return null;
        }
        return this.myArrayList[i-1]; // returns the i-th element or the element from (i-1)th index
    }

    public void resizeArray(){
        String[] tempArrayList = new String[currentLength];
        for (int i=0; i<currentLength; i++){
            tempArrayList[i] = myArrayList[i]; // putting the current elements inside an temporary array of same size
        }
        myArrayList = new String[currentLength*2];
        for (int i=0; i<currentLength; i++){
            myArrayList[i] = tempArrayList[i]; // getting back the original data into the resized array
        }
        System.out.println("The array has been resized!");
    }

    public void add(String element){
        if (this.currentLength == this.myArrayList.length){
            resizeArray();
        }
        myArrayList[currentLength++] = element;
    }

    public void add(int i, String element){
        if (i>currentLength || i<=0){
            System.out.println("This position is out of capacity!");
            return;
        } else if (this.currentLength==this.myArrayList.length){
            resizeArray();
        }
        for (int j=currentLength; j>=i; j--){
            myArrayList[j]=myArrayList[j-1];
        }
        myArrayList[i-1] = element;
        currentLength++;
    }

    public void remove (String element){
        for (int i=0; myArrayList[i]!=null; i++){
            if (myArrayList[i].equals(element)){
                for (int j=i+1; j<currentLength; j++){
                    myArrayList[j-1]=myArrayList[j];
                }
                myArrayList[currentLength-1]=null; // dealing with the current last blank index
                currentLength--; // fixing the current length
            }
        }
    }

    public void findIndex (String element){
        for (int i=0; i<currentLength; i++){
            if (myArrayList[i].equals(element)){
                System.out.println("Element found at the index number: " + i);
            }
        }
    }

    public Array subArray (int start, int end){
        String[] newArrayList = new String[end-start+1];
        for (int i=start-1, j=0; i<end; i++,j++){
            newArrayList[j]=this.myArrayList[i];
        }
        return new Array(newArrayList);
    }

    public int length(){
        return this.currentLength;
    }

    public boolean isEmpty(){
        return currentLength == 0;
    }

    public void merge(String[] A1, String[] A2){
        this.myArrayList = new String[A1.length + A2.length];
        int i=0, j=0, k=0;
        while (i<A1.length && j<A2.length){
            if (A1[i].compareTo(A2[j])<0){
                myArrayList[k++]=A1[i++];
            } else {
                myArrayList[k++]=A2[j++];
            }
        }
        while (i< A1.length){
            myArrayList[k++]=A1[i++];
        }
        while (j<A2.length){
            myArrayList[k++]=A2[j++];
        }
        this.currentLength = k;
    }

    public void showArray (){
        if (this.isEmpty()){
            System.out.println("The ArrayList is empty now");
            return;
        }
        for (int i=0; i<currentLength; i++){
            System.out.print(myArrayList[i] + " ");
        }
        System.out.print("\n");
    }
}

public class Main {
    public static void main(String[] args) {
        // 1. Creating an array of a predefined size
        Array array1 = new Array();
        array1.showArray();
        System.out.println("====================");

        // 2. Creating an array of length n
        Array array2 = new Array(3);
        array2.showArray();
        System.out.println("====================");

        // 3. Initializing the array with the given list of elements
        String[] string1 = {"My", "name", "is", "Asif", "Haider"};
        String[] string2 = {"I", "am", "currently", "studying", "in", "2-1"};

        Array array3 = new Array(string1);
        Array array4 = new Array(string2);
        array3.showArray();
        array4.showArray();
        System.out.println("====================");

        // 4. returning the array itself
        array1 = array3.getArray(); // array1 is not empty anymore, it just points to the array defined in array3
        array1.showArray();
        System.out.println("====================");

        // 5. returning the i-th element of the array
        String element1 = array1.getAnElement(2);// 2nd element means the element in index 1
        String element2 = array1.getAnElement(7);
        String element3 = array4.getAnElement(5);
        String element4 = array4.getAnElement(-2);

        System.out.println(element1);
        System.out.println(element2);
        System.out.println(element3);
        System.out.println(element4);
        System.out.println("====================");

        // 6. adding an element at the end of the array and if the array is full,
        //    increasing the size of the array and "inserting" it
        array2.add("I");
        array2.add("am");
        array2.add("from");

        array2.showArray();

        array2.add("CSE");
        array2.add("BUET");

        array2.showArray();

        // 7. adding the element on the i-th position of the array
        // 8. removing all element that match with the given element from the array
        array2.add(5, "Department");
        array2.showArray();

        array2.remove("from");
        array2.showArray();

        array2.add(-3, "this won't be added");
        array2.add(10, "this won't be added");
        array2.add(0, "this won't be added");

        array2.add(3, "studying");
        array2.add(4, "at");
        array2.showArray();

        String[] string3 = {"None", "is", "to", "None", "under", "the", "sun"};
        array1 = new Array(string3);
        array1.remove("None");
        array1.showArray();
        System.out.println("====================");

        // 9. returning the indexes of all the occurrences where the given element is found
        array1.add(1,"None");
        array1.add(4, "None");
        array1.showArray();

        array1.findIndex("None");
        System.out.println("====================");

        // 10. returning all elements within the given range
        Array array5 = new Array();
        Array array6 = new Array();

        array5 = array2.subArray(2,5);
        array2.showArray();
        array5.showArray();
        System.out.println("====================");

        // 11. merging and updating with given sorted arrays
        String[] string4 = {"Hello", "Is", "Jewel", "Know", "Mango", "Night", "This"};
        String[] string5 = {"But", "Cat", "Duke", "Fact", "Justice", "League", "Money", "Noble", "Tiny"};
        // we take these string arrays to be sorted by default

        array1.merge(string4, string5);
        array1.showArray();
        System.out.println("====================");

        // 12. returning the length
        array1.showArray();
        array2.showArray();
        array3.showArray();
        array4.showArray();
        array5.showArray();
        array6.showArray();

        System.out.println(array1.length());
        System.out.println(array2.length());
        System.out.println(array3.length());
        System.out.println(array4.length());
        System.out.println(array5.length());
        System.out.println(array6.length());
        System.out.println("====================");
    }
}