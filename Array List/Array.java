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
        if (start < 1 || start > end || end > this.currentLength ){
            System.out.println("Can't produce subArray! Please try with proper positions!");
            return new Array();
        }
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
