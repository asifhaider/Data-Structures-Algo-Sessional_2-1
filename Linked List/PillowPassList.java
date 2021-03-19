public class PillowPassList {

    // A linked list data structure for storing player and pillow information
    // has an attribute of player node count
    // player serial number while the game is on
    // a player node cursor indicating current position of pillow
    // a string indicating direction
    // spare time keeps track of how many seconds left to move the cursor onto next node
    // last check time indicates last when the list was checked for any update by commands

    private int playerCount;
    private int playerSerial;
    private int spareTime;
    private int lastCheck;
    private String direction;
    private PlayerNode pillowCursor;

    public int getPlayerSerial() {
        return playerSerial;
    }

    public void setPlayerSerial(int playerSerial) {
        this.playerSerial = playerSerial;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public PlayerNode getPillowCursor() {
        return pillowCursor;
    }

    public void setPillowCursor(PlayerNode pillowCursor) {
        this.pillowCursor = pillowCursor;
    }

    public PillowPassList (){
        this.playerCount = 0;
        this.playerSerial = 0;
        this.direction = "anticlockwise"; // default direction
        this.pillowCursor = null; // no player available
        this.spareTime = 0;
        this.lastCheck = 0;
    }

    public void moveCursor (String direction){
        // following right handed notations
        if (direction.equals("anticlockwise")){
            pillowCursor = pillowCursor.getNextPlayer();
        } else if (direction.equals("clockwise")){
            pillowCursor = pillowCursor.getPreviousPlayer();
        }

    }

    public void updatePillowPassList (int time){
        if (this.spareTime == 0){ // checks if its the last second for a player to hold the pillow
            moveCursor(this.direction); // and passes it to the next one in turn
            this.spareTime = pillowCursor.getReflexTime();
        }
        this.lastCheck += time; // updating the timespan for the current check
        while (time > this.spareTime){
            time -= this.spareTime;
            moveCursor(this.direction);
            this.spareTime = pillowCursor.getReflexTime();
        }
        spareTime -= time; // update done
    }

    public void addPlayer (PlayerNode p){
        if (pillowCursor == null){
            p.setPreviousPlayer(p); // as there is only one element in this doubly circular linked list
            p.setNextPlayer(p); // first player it points to itself from both directions
            pillowCursor = p;
            this.spareTime = pillowCursor.getReflexTime();
        } else{
            if (direction.equals("anticlockwise")){
                PlayerNode q = pillowCursor.getPreviousPlayer();
                p.setPreviousPlayer(q);
                p.setNextPlayer(pillowCursor);
                pillowCursor.setPreviousPlayer(p);
                q.setNextPlayer(p);
            } else if (direction.equals("clockwise")){
                PlayerNode q = pillowCursor.getNextPlayer();
                p.setPreviousPlayer(pillowCursor);
                p.setNextPlayer(q);
                q.setPreviousPlayer(p);
                pillowCursor.setNextPlayer(p);
            }
        }
        playerCount++;
        playerSerial++; // important for the one who enters the game next
    }

    public int removePlayer(){
        PlayerNode p = pillowCursor.getPreviousPlayer();
        PlayerNode q = pillowCursor.getNextPlayer();
        int e = pillowCursor.getEntryNumber(); // the entry number is needed for console output

        p.setNextPlayer(q);
        q.setPreviousPlayer(p);
        if (direction.equals("anticlockwise")){
            this.pillowCursor = q;
        } else if (direction.equals("clockwise")){
            this.pillowCursor = p;
        }
        playerCount--;
        return e;
    }

    public void checkPillowHolder(int currentTime){
        updatePillowPassList(currentTime-this.lastCheck);
        System.out.println("Player " + pillowCursor.getEntryNumber() +
                " is holding the pillow at t = " + currentTime);
    }

    public boolean eliminatePillowHolder(int currentTime){
        if (this.spareTime != 0) {
            // indicates no time waste
            updatePillowPassList(currentTime - this.lastCheck);
        }

        int e = removePlayer();
        this.spareTime = pillowCursor.getReflexTime();

        if (direction.equals("anticlockwise")) {
            System.out.println("Player " + e +
                    " has been eliminated at t = " + currentTime);
        } else if (direction.equals("clockwise")) {
            System.out.println("Player " + e +
                    " has been eliminated at t = " + currentTime);
        }

        if (playerCount == 1){
            gameExit(currentTime);
            return false; // informs main function that the game is about to over
        }
        return true; // game continues
    }

    public void reverseDirection (int currentTime){
        if (this.spareTime != 0){
            // indicates no time waste
            updatePillowPassList(currentTime - this.lastCheck);
        }
        if (direction.equals("anticlockwise"))
            this.direction = "clockwise";
        else if (direction.equals("clockwise"))
            this.direction = "anticlockwise";
    }

    public void listTraverse (){
        // traverses the list to output the current serial
        System.out.print("Pillow Passing Sequence = Player ");
            while (this.playerCount > 0){
                System.out.print(pillowCursor.getEntryNumber());
                if (this.playerCount != 1)
                    System.out.print(", ");
                moveCursor(this.direction);
                playerCount--;
            }
    }

    public void newPlayerEntry (PlayerNode p, int currentTime){
        if (this.playerCount == 1){
            return; // no player can enter now
        }
        updatePillowPassList(currentTime - this.lastCheck);
        addPlayer(p);
    }

    public void gameExit (int currentTime){
        System.out.print("Game Over: ");
        if (playerCount == 1){
            // everyone is eliminated except the winner
            System.out.println("Player " + pillowCursor.getEntryNumber() + " wins!!");
        }else{
            checkPillowHolder(currentTime);
            listTraverse();
        }
    }
}
