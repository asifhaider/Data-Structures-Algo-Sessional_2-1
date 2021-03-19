public class PlayerNode {

    // each player node class consists the player's reflex time
    // their entry serial number
    // two pointers pointing to next and previous player [Doubly Linked List Structure with Circular Arrangement]

    private int reflexTime;
    private int entryNumber;
    private PlayerNode nextPlayer; // next means right handed side [anticlockwise mode]
    private PlayerNode previousPlayer; // previous means left handed side [anticlockwise mode]

    public int getReflexTime() {
        return reflexTime;
    }

    public void setReflexTime(int reflexTime) {
        this.reflexTime = reflexTime;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public PlayerNode getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(PlayerNode nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public PlayerNode getPreviousPlayer() {
        return previousPlayer;
    }

    public void setPreviousPlayer(PlayerNode previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    // constructor when a player is about to enter the game
    // once the player enters, forward and backward links are changed later inside the List Class
    public PlayerNode(int n, int e){
        this.reflexTime = n;
        this.entryNumber = e;
        this.nextPlayer = null;
        this.previousPlayer = null;
    }
}
