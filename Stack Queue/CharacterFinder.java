class CharacterFinder {
    private int letterCount[];

    // an array for storing the counts of letter occurrence
    public CharacterFinder(){
        this.letterCount = new int [26];
    }

    public void firstNonRepeatingCharacterFinder(String input){
        // storing the values in a queue
        LinkedQueue<Character> letterQueue = new LinkedQueue<>();

        for (int i=0; i<input.length(); i++){
            char currentLetter = input.charAt(i);
            letterCount[currentLetter - 'a']++;
            letterQueue.enqueue(currentLetter);

            while (!letterQueue.isEmpty()){
                char frontLetter = letterQueue.first();
                if(letterCount[frontLetter-'a']==1){
                    System.out.println(frontLetter);
                    break;
                } else{
                    letterQueue.dequeue();
                }
            }
            if(letterQueue.isEmpty()){
                System.out.print("#"); // as instructed
            }
        }
        System.out.println();
    }
}
